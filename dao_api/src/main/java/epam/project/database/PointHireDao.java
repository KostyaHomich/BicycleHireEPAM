package epam.project.database;

import epam.project.database.exception.DaoException;
import epam.project.entity.PointHire;

public interface PointHireDao extends EntityDao<PointHire, Integer> {
    boolean containsPointHire(int id) throws DaoException;
}
