package ru.itis.kpfu.repositories.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.kpfu.models.Like;
import ru.itis.kpfu.repositories.LikesRepository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LikesRepositoryJdbcTemplateImpl implements LikesRepository {
    //language=SQL
    private static final String SQL_SELECT_BY_POST_ID = "select * from likes where post_id = ?;";

    //language=SQL
    private static final String SQL_DELETE_LIKE_BY_USER_ID_AND_POST_ID = "delete from likes where user_id = ? and " +
            "post_id = ?;";

    //language=SQL
    private static final String SQL_SELECT_BY_USER_ID_AND_POST_ID = "select * from likes where user_id = ? and " +
            "post_id = ?;";

    private static final RowMapper<Like> likeMapper = (row, rowNumber) -> Like.builder()
            .userId(row.getLong("user_id"))
            .postId(row.getLong("post_id"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public LikesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void save(Like like) {
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("user_id", like.getUserId());
        paramsAsMap.put("post_id", like.getPostId());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("likes")
                .execute(new MapSqlParameterSource(paramsAsMap));
    }

    @Override
    public List<Like> findByPostId(Long id) {
        return jdbcTemplate.query(SQL_SELECT_BY_POST_ID, likeMapper, id);
    }

    @Override
    public Optional<Like> find(Like like) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_USER_ID_AND_POST_ID,
                    likeMapper, like.getUserId(), like.getPostId()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Like like) {
        jdbcTemplate.update(SQL_DELETE_LIKE_BY_USER_ID_AND_POST_ID, like.getUserId(), like.getPostId());
    }
}
