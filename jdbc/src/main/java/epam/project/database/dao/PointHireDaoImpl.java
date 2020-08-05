package epam.project.database.dao;

import epam.project.database.AbstractJdbcDao;
import epam.project.database.EntityDao;
import epam.project.database.PointHireDao;
import epam.project.entity.PointHire;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Scope("prototype")
public class PointHireDaoImpl extends AbstractJdbcDao<PointHire, Integer> implements PointHireDao, EntityDao<PointHire,Integer> {

    private static final String CREATE_QUERY =
            "insert into bicycle_hire.point_hire(location,telephone,description) values (:location,:telephone,:description);";
    private static final String UPDATE_QUERY =
            "update bicycle_hire.point_hire set location=:location,telephone=:telephone,description=:description where id=:id;";
    private static final String DELETE_QUERY =
            "DELETE FROM bicycle_hire.point_hire WHERE id=:id;";
    private static final String SELECT_QUERY =
            "SELECT * FROM bicycle_hire.point_hire";
    private static final String CHECK_IF_CONTAINS =
            "SELECT * FROM bicycle_hire.point_hire WHERE id=:id;";

    @Override
    protected MapSqlParameterSource prepareStatementForInsert(PointHire object) {
        return setStatement(object);
    }

    @Override
    protected MapSqlParameterSource prepareStatementForUpdate(PointHire object) {
        return setStatement(object).addValue("id", object.getId());
    }

    @Override
    protected RowMapper getRowMapper() {
        return new PointHireRowMapper();
    }

    private MapSqlParameterSource setStatement(PointHire object) {
        return new MapSqlParameterSource()
                .addValue("location", object.getLocation())
                .addValue("telephone", object.getTelephone())
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
    public boolean containsPointHire(int id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(CHECK_IF_CONTAINS, sqlParameterSource, java.lang.Boolean.class);
    }

    private static final class PointHireRowMapper implements RowMapper<PointHire> {

        @Override
        public PointHire mapRow(ResultSet resultSet, int i) throws SQLException {
            PointHire pointHire = new PointHire();
            pointHire.setId(resultSet.getInt("id"));
            pointHire.setLocation(resultSet.getString("location"));
            pointHire.setTelephone(resultSet.getString("telephone"));
            pointHire.setDescription(resultSet.getString("description"));
            return pointHire;
        }
    }

}
