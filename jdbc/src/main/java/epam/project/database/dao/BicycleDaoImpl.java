package epam.project.database.dao;


import epam.project.database.AbstractJdbcDao;
import epam.project.database.BicycleDao;
import epam.project.database.EntityDao;
import epam.project.database.exception.DaoException;
import epam.project.entity.Bicycle;
import epam.project.entity.PointHire;
import epam.project.entity.PointHireBicycle;
import epam.project.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Scope("prototype")
public class BicycleDaoImpl extends AbstractJdbcDao<Bicycle, Integer> implements BicycleDao, EntityDao<Bicycle, Integer> {

    private static final String CREATE_QUERY =
            "insert into bicycle_hire.bicycle(link,name,description) values (:link,:name,:description)";

    private static final String CREATE_QUERY_POINT_HIRE_BICYCLE =
            "insert into bicycle_hire.point_hire_bicycle(id_bicycle,id_point_hire) values (:id_bicycle,:id_point_hire)";

    private static final String CREATE_QUERY_BEST_BICYCLE =
            "insert into bicycle_hire.best_bicycle(id_user,id_point_hire_bicycle) values (:id_user,:id_point_hire_bicycle)";

    private static final String DELETE_BEST_BICYCLE =
            "DELETE FROM bicycle_hire.best_bicycle WHERE id=:id;";

    private static final String UPDATE_QUERY =
            "update bicycle_hire.bicycle set link=:link, name=:name,description=:description where id=:id;";

    private static final String DELETE_QUERY =
            "DELETE FROM bicycle_hire.bicycle WHERE id=:id;";

    private static final String SELECT_QUERY =
            "SELECT * FROM bicycle_hire.bicycle";

    private static final String SELECT_QUERY_WITHOUT_BEST_BICYCLE_LIMITED_BY_USER_ID =
            " select distinct(bicycle.id),link,name,description" +
                    " from bicycle_hire.bicycle  where bicycle.id not in( " +
                    " SELECT bicycle.id" +
                    " FROM bicycle_hire.bicycle,bicycle_hire.best_bicycle,bicycle_hire.point_hire_bicycle" +
                    " where best_bicycle.id_user=:id_user" +
                    " and best_bicycle.id_point_hire_bicycle=point_hire_bicycle.id" +
                    " and bicycle.id=point_hire_bicycle.id_bicycle) limit :count OFFSET :start;";


    private static final String CHECK_IF_CONTAINS =
            "SELECT * FROM bicycle_hire.bicycle WHERE id=:id";

    private static final String GET_AMOUNT_BICYCLES =
            "SELECT count(*)  FROM bicycle_hire.bicycle;";

    private static final String TAKE_BY_BICYCLE_ID_POINT_HIRE_BICYCLE =
            "SELECT * FROM bicycle_hire.point_hire_bicycle WHERE id_bicycle=:id_bicycle";

    private static final String GET_ALL_BICYCLE_BY_POINT_HIRE =
            "SELECT bicycle.id,name,description,link " +
                    "FROM bicycle_hire.bicycle,bicycle_hire.point_hire_bicycle where point_hire_bicycle.id_point_hire=:id_point_hire" +
                    " and bicycle.id=point_hire_bicycle.id_bicycle;";

    private static final String GET_ALL_BEST_BICYCLE_BY_USER =
            "SELECT bicycle.id,name,description,link " +
                    "FROM bicycle_hire.bicycle,bicycle_hire.best_bicycle,bicycle_hire.point_hire_bicycle where best_bicycle.id_user=:id_user " +
                    "and best_bicycle.id_point_hire_bicycle=point_hire_bicycle.id " +
                    "and bicycle.id=point_hire_bicycle.id_bicycle;";

    private static final String GET_BEST_BICYCLE_ID_BY_BICYCLE_ID =
            "SELECT best_bicycle.id FROM bicycle_hire.best_bicycle,bicycle_hire.bicycle,bicycle_hire.point_hire_bicycle where bicycle.id=:id and " +
                    "point_hire_bicycle.id_bicycle=bicycle.id and best_bicycle.id_point_hire_bicycle=point_hire_bicycle.id;";

    private static final String USER_ID = "id_user";
    private static final String BICYCLE_ID = "id_bicycle";
    private static final String POINT_HIRE_ID = "id_point_hire";

    @Override
    protected MapSqlParameterSource prepareStatementForInsert(Bicycle object) {
        return setStatement(object);
    }

    @Override
    protected MapSqlParameterSource prepareStatementForUpdate(Bicycle object) {
        return setStatement(object).addValue("id", object.getId());
    }

    @Override
    protected RowMapper getRowMapper() {
        return new BicycleRowMapper();
    }

    private MapSqlParameterSource setStatement(Bicycle object) {
        return new MapSqlParameterSource()
                .addValue("link", object.getLink())
                .addValue("name", object.getName())
                .addValue("description", object.getDescription());

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
    public void addPointHireBicycle(Bicycle bicycle) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(BICYCLE_ID, bicycle.getId())
                .addValue(POINT_HIRE_ID, bicycle.getPointHire().getId());
        namedParameterJdbcTemplate.update(CREATE_QUERY_POINT_HIRE_BICYCLE, mapSqlParameterSource);
    }

    @Override
    public PointHireBicycle getByBicyclePkPointHireBicycle(int id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(BICYCLE_ID, id);
        return namedParameterJdbcTemplate.queryForObject(TAKE_BY_BICYCLE_ID_POINT_HIRE_BICYCLE, sqlParameterSource, new PointHireBicycleRowMapper());
    }

    @Override
    public boolean containsBicycle(int id) throws DaoException {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(CHECK_IF_CONTAINS, sqlParameterSource, java.lang.Boolean.class);
    }

    @Override
    public List<Bicycle> getAllBicycleByPointHirePk(int id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(POINT_HIRE_ID, id);
        return namedParameterJdbcTemplate.query(GET_ALL_BICYCLE_BY_POINT_HIRE, sqlParameterSource, getRowMapper());
    }

    @Override
    public List<Bicycle> getBicycles(int start, int count, User user) throws DaoException {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID, user.getId())
                .addValue("start", start)
                .addValue("count", count);

        return namedParameterJdbcTemplate.query(SELECT_QUERY_WITHOUT_BEST_BICYCLE_LIMITED_BY_USER_ID, sqlParameterSource, getRowMapper());
    }

    @Override
    public void addBestBicycle(PointHireBicycle pointHireBicycle, User user) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID, user.getId())
                .addValue("id_point_hire_bicycle", pointHireBicycle.getId());
        namedParameterJdbcTemplate.update(CREATE_QUERY_BEST_BICYCLE, mapSqlParameterSource);
    }

    @Override
    public void deleteBestBicycle(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(DELETE_BEST_BICYCLE, mapSqlParameterSource);

    }

    @Override
    public List<Bicycle> getAllBestBicyclesByUserId(int id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(USER_ID, id);
        return namedParameterJdbcTemplate.query(GET_ALL_BEST_BICYCLE_BY_USER, sqlParameterSource, getRowMapper());
    }

    @Override
    public int getBestBicycleIdByBicycleId(int id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        Bicycle bicycle = (Bicycle) namedParameterJdbcTemplate.queryForObject(GET_BEST_BICYCLE_ID_BY_BICYCLE_ID, sqlParameterSource, getRowMapper());
        return Objects.requireNonNull(bicycle).getId();
    }

    @Override
    public int getAmountBicycles() {
        return namedParameterJdbcTemplate.queryForObject(GET_AMOUNT_BICYCLES, new MapSqlParameterSource(), java.lang.Integer.class);
    }

    private static final class BicycleRowMapper implements RowMapper<Bicycle> {

        @Override
        public Bicycle mapRow(ResultSet resultSet, int i) throws SQLException {
            Bicycle bicycle = new Bicycle();
            bicycle.setId(resultSet.getInt("id"));
            bicycle.setLink(resultSet.getString("link"));
            bicycle.setName(resultSet.getString("name"));
            bicycle.setDescription(resultSet.getString("description"));
            return bicycle;
        }
    }

    private static final class PointHireBicycleRowMapper implements RowMapper<PointHireBicycle> {

        @Override
        public PointHireBicycle mapRow(ResultSet resultSet, int i) throws SQLException {
            PointHireBicycle pointHireBicycle = new PointHireBicycle();
            pointHireBicycle.setId(resultSet.getInt("id"));
            Bicycle bicycle = new Bicycle();
            bicycle.setId(resultSet.getInt(BICYCLE_ID));
            pointHireBicycle.setBicycle(bicycle);
            PointHire pointHire = new PointHire();
            pointHire.setId(resultSet.getInt(POINT_HIRE_ID));
            pointHireBicycle.setPointHire(pointHire);
            return pointHireBicycle;
        }
    }
}
