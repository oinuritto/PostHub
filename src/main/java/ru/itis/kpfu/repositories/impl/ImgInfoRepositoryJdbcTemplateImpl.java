package ru.itis.kpfu.repositories.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.kpfu.models.ImgInfo;
import ru.itis.kpfu.repositories.ImgInfoRepository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ImgInfoRepositoryJdbcTemplateImpl implements ImgInfoRepository {
    //language=SQL
    private static final String SQL_SELECT_ALL_FILES = "select * from img_info;";

    //language=SQL
    private static final String SQL_SELECT_BY_STORAGE_FILE_NAME = "select * from img_info where storage_file_name = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from img_info where id = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from img_info where id = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_STORAGE_FILE_NAME = "delete from img_info where storage_file_name = ?";

    private static final RowMapper<ImgInfo> fileInfoMapper = (row, rowNumber) -> ImgInfo.builder()
            .id(row.getLong("id"))
            .size(row.getLong("size"))
            .type(row.getString("file_type"))
            .originalFileName(row.getString("original_file_name"))
            .storageFileName(row.getString("storage_file_name"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public ImgInfoRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ImgInfo save(ImgInfo imgInfo) {
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("size", imgInfo.getSize());
        paramsAsMap.put("file_type", imgInfo.getType());
        paramsAsMap.put("original_file_name", imgInfo.getOriginalFileName());
        paramsAsMap.put("storage_file_name", imgInfo.getStorageFileName());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        Long id = insert.withTableName("img_info")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap)).longValue();

        imgInfo.setId(id);

        return imgInfo;
    }

    @Override
    public List<ImgInfo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_FILES, fileInfoMapper);
    }

    @Override
    public Optional<ImgInfo> findByStorageFileName(String name) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_STORAGE_FILE_NAME, fileInfoMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ImgInfo> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileInfoMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public void delete(String storageFileName) {
        jdbcTemplate.update(SQL_DELETE_BY_STORAGE_FILE_NAME, storageFileName);
    }
}
