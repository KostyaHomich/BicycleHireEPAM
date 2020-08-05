package epam.project.database;

import epam.project.entity.Identified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements EntityDao<T, PK> {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    protected abstract MapSqlParameterSource prepareStatementForInsert(T object);

    protected abstract MapSqlParameterSource prepareStatementForUpdate(T object);

    protected abstract RowMapper getRowMapper();

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();


    @Override
    public T getByPK(PK key) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", key);
        return (T) namedParameterJdbcTemplate.queryForObject(getSelectQuery() + " WHERE id = :id", sqlParameterSource, getRowMapper());
    }

    @Override
    public List<T> getAll() {
        return namedParameterJdbcTemplate.query(getSelectQuery(), getRowMapper());
    }


    @Override
    public T persist(T object) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = prepareStatementForInsert(object);
        namedParameterJdbcTemplate.update(getCreateQuery(), sqlParameterSource, holder, new String[]{"id"});
        object.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return object;

    }

    @Override
    public void update(T object) {
        SqlParameterSource sqlParameterSource = prepareStatementForUpdate(object);
        namedParameterJdbcTemplate.update(getUpdateQuery(), sqlParameterSource);

    }

    @Override
    public void delete(T object) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", object.getId());
        namedParameterJdbcTemplate.update(getDeleteQuery(), sqlParameterSource);

    }
}