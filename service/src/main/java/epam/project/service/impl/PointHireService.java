package epam.project.service.impl;

import epam.project.database.PointHireDao;
import epam.project.database.exception.DaoException;
import epam.project.entity.PointHire;
import epam.project.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class PointHireService {

    @Autowired
    private PointHireDao pointHireDao;

    public PointHireService() {
    }

    public List<PointHire> takeAll() throws ServiceException {
        try {

            return pointHireDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all point hires", e);
        }
    }

    public boolean add(PointHire pointHire) throws ServiceException {

        try {
            pointHireDao.persist(pointHire);
        } catch (DaoException e) {
            throw new ServiceException("Failed to add point hire", e);
        }
        return true;

    }

    public boolean delete(PointHire pointHire) throws ServiceException {

        try {
            pointHireDao.delete(pointHire);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete point hire.", e);
        }
        return true;
    }

    public boolean contains(int id) throws ServiceException {

        try {
            return pointHireDao.containsPointHire(id);

        } catch (DaoException e) {
            throw new ServiceException("Failed to check contains point hire", e);
        }

    }

    public boolean update(PointHire pointHire) throws ServiceException {
        try {
            pointHireDao.update(pointHire);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update point hire", e);
        }
        return true;
    }

    public PointHire getById(int id) throws ServiceException {

        try {
            return pointHireDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get by id point hire", e);
        }
    }
}
