package ru.itis.kpfu.repositories.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.repositories.PostsRepository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PostsRepositoryJdbcTemplateImpl implements PostsRepository {
    //language=SQL
    private static final String SQL_SELECT_ALL_POSTS = "select * from posts;";

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_OFFSET_AND_LIMIT = "select * from posts offset ? limit ?;";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from posts where id = ?;";

    //language=SQL
    private static final String SQL_SELECT_ALL_LIKE_TITLE = "select * from posts where title ilike '%' || ? || '%';";

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from posts where id = ?;";

    private static final RowMapper<Post> postMapper = (row, rowNumber) -> Post.builder()
            .id(row.getLong("id"))
            .title(row.getString("title"))
            .text(row.getString("post_text"))
            .imgId(row.getLong("img_id"))
            .userId(row.getLong("user_id"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public PostsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, postMapper);
    }

    @Override
    public List<Post> findAllByOffsetAndLimit(int offset, int limit) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_OFFSET_AND_LIMIT, postMapper, offset, limit);
    }

    @Override
    public void saveWithImg(Post post) {
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("title", post.getTitle());
        paramsAsMap.put("post_text", post.getText());
        paramsAsMap.put("img_id", post.getImgId());
        paramsAsMap.put("user_id", post.getUserId());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        Long id = insert.withTableName("posts")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap)).longValue();

        post.setId(id);
    }

    @Override
    public void saveWithoutImg(Post post) {
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("title", post.getTitle());
        paramsAsMap.put("post_text", post.getText());
        paramsAsMap.put("img_id", null);
        paramsAsMap.put("user_id", post.getUserId());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        Long id = insert.withTableName("posts")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap)).longValue();

        post.setId(id);
    }

    @Override
    public Optional<Post> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, postMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAllLikeTitle(String title) {
        return jdbcTemplate.query(SQL_SELECT_ALL_LIKE_TITLE, postMapper, title);
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
