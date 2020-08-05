package epam.project.database.dao;

import epam.project.database.AbstractJdbcDao;
import epam.project.database.EntityDao;
import epam.project.database.UserDao;
import epam.project.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Scope("prototype")
public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements UserDao, EntityDao<User,Integer> {


    private static final String CREATE_QUERY =
            "insert into bicycle_hire.users(login,password,first_name,last_name,registration_date,email,enabled,role) " +
                    "values (:login,:password, :first_name,:last_name, :registration_date,:email,:enabled,:role)";

    private static final String UPDATE_QUERY =
            "update bicycle_hire.users set login=:login,password=:password,first_name=:first_name,last_name=:last_name," +
                    "registration_date=:registration_date,email=:email,role=:role where id=:id;";

    private static final String DELETE_QUERY = "DELETE FROM bicycle_hire.users WHERE id=:id;";
    private static final String SELECT_QUERY = "SELECT * FROM bicycle_hire.users";

    private static final String CHECK_IF_CONTAINS_BY_LOGIN = "select exists(SELECT login FROM bicycle_hire.users WHERE login=?)";
    private static final String CHECK_IF_CONTAINS_BY_EMAIL = "select exists(SELECT email FROM bicycle_hire.users WHERE email=?)";

    private static final String GET_BY_LOGIN = "SELECT * FROM bicycle_hire.users WHERE login=:login;";

    private static final String LOGIN = "login";
    private static final String EMAIL = "email";

    @Override
    protected MapSqlParameterSource prepareStatementForInsert(User object) {
        return setDefaultUserData(object);
    }

    @Override
    protected MapSqlParameterSource prepareStatementForUpdate(User object) {
        return setDefaultUserData(object).addValue("id", object.getId());
    }

    @Override
    protected RowMapper getRowMapper() {
        return new UserMapper();
    }

    private MapSqlParameterSource setDefaultUserData(User object) {
        return new MapSqlParameterSource()
                .addValue(LOGIN, object.getLogin())
                .addValue("password", object.getPassword())
                .addValue("first_name", object.getFirstName())
                .addValue("last_name", object.getLastName())
                .addValue("registration_date", object.getRegistrationDate())
                .addValue(EMAIL, object.getEmail())
                .addValue("enabled", object.getEnabled())
                .addValue("role",object.getUserRole().name());
    }

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    public User getByLogin(String login) {
        return (User) namedParameterJdbcTemplate.queryForObject(GET_BY_LOGIN, new MapSqlParameterSource(LOGIN, login), getRowMapper());
    }


    @Override
    public boolean checkLoginExistance(String login) {
        return namedParameterJdbcTemplate.getJdbcTemplate().queryForObject(CHECK_IF_CONTAINS_BY_LOGIN, new Object[]{login}, Boolean.class);
    }

    @Override
    public boolean checkEmailExistance(String email) {
        return namedParameterJdbcTemplate.getJdbcTemplate().queryForObject(CHECK_IF_CONTAINS_BY_EMAIL, new Object[]{email}, Boolean.class);
    }



    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setRegistrationDate(resultSet.getString("registration_date"));
            user.setEmail(resultSet.getString(EMAIL));
            user.setEnabled(resultSet.getBoolean("enabled"));
            user.setUserRole(User.UserRole.valueOf(resultSet.getString("role")));
            return user;
        }
    }

}
