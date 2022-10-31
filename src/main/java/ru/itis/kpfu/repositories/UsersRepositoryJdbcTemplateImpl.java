package ru.itis.kpfu.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.itis.kpfu.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL_USERS = "select * from users;";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_USERNAME = "select * from users where username = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_USERNAME_PASSWORD = "select * from users where username = ? and password = ?";

    //language=SQL
    private static final String SQL_UPDATE_USER = "update users set first_name = ?, last_name = ?, username = ?, " +
            "password = ? where id = ?";

//    //language=SQL
//    private static final String SQL_DELETE_USER = "delete from student_course where student_id = ?;" +
//            "delete from student where id = ?";


    private static final RowMapper<User> userMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .username(row.getString("username"))
            .password(row.getString("password"))
            .role(User.ROLE.valueOf(row.getString("role")))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, userMapper);
    }

    @Override
    public void save(User user) {
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("first_name", user.getFirstName());
        paramsAsMap.put("last_name", user.getLastName());
        paramsAsMap.put("username", user.getUsername());
        paramsAsMap.put("password", user.getPassword());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        Long id = Long.parseLong(insert.withTableName("users")
                .usingGeneratedKeyColumns("id", "role")
                .executeAndReturnKeyHolder(new MapSqlParameterSource(paramsAsMap))
                .getKeys()
                .get("id")
                .toString());


        user.setId(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER, user.getFirstName(), user.getLastName(),
                user.getUsername(), user.getPassword(), user.getId());
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME, userMapper, username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByUsernamePassword(String username, String password) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME_PASSWORD, userMapper, username, password));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


//    @Override
//    public void delete(Long id) {
//        jdbcTemplate.update(SQL_DELETE_USER, id, id);
//    }
}
