package epam.project.service.impl;

import epam.project.database.BicycleDao;
import epam.project.database.exception.DaoException;
import epam.project.entity.Bicycle;
import epam.project.entity.PointHireBicycle;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope("prototype")
public class BicycleService {

    @Autowired
    private BicycleDao bicycleDao;

    public BicycleService() {
    }

    public List<Bicycle> takeAll() throws ServiceException {

        try {
            return bicycleDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all bicycles", e);
        }
    }

    public List<Bicycle> takeAllBicycleByPointHirePk(int id) throws ServiceException {
        try {
            return bicycleDao.getAllBicycleByPointHirePk(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all bicycles by pk", e);
        }
    }

    @Transactional
    public boolean add(Bicycle bicycle) throws ServiceException {
        try {
            Bicycle bicycleInserted = bicycleDao.persist(bicycle);
            bicycleInserted.setPointHire(bicycle.getPointHire());
            bicycleDao.addPointHireBicycle(bicycleInserted);
            return true;
        } catch (DaoException e) {
            throw new ServiceException("Failed to add bicycle", e);
        }
    }

    public void addBestBicycle(Bicycle bicycle, User user) throws ServiceException {

        try {
            PointHireBicycle pointHireBicycle = bicycleDao.getByBicyclePkPointHireBicycle(bicycle.getId());
            bicycleDao.addBestBicycle(pointHireBicycle, user);

        } catch (DaoException e) {
            throw new ServiceException("Failed to add best bicycle", e);
        }

    }

    public List<Bicycle> showBestBicycles(User user) throws ServiceException {

        try {
            return bicycleDao.getAllBestBicyclesByUserId(user.getId());

        } catch (DaoException e) {
            throw new ServiceException("Failed to show best bicycles", e);
        }

    }

    public void deleteBestBicycle(int id) throws ServiceException {

        try {
            int idBestBicycle = bicycleDao.getBestBicycleIdByBicycleId(id);
            bicycleDao.deleteBestBicycle(idBestBicycle);

        } catch (DaoException e) {
            throw new ServiceException("Failed to delete best bicycle", e);
        }

    }

    public boolean delete(Bicycle bicycle) throws ServiceException {
        try {
            bicycleDao.delete(bicycle);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete bicycle", e);
        }
        return true;
    }

    public boolean update(Bicycle bicycle) throws ServiceException {
        try {
            bicycleDao.update(bicycle);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update bicycle", e);
        }
        return true;
    }

    public boolean contains(int id) throws ServiceException {

        try {
            return bicycleDao.containsBicycle(id);

        } catch (DaoException e) {
            throw new ServiceException("Failed to check contains bicycle", e);
        }

    }

    public Bicycle getById(int id) throws ServiceException {

        try {
            PointHireBicycle pointHireBicycle = bicycleDao.getByBicyclePkPointHireBicycle(id);
            Bicycle bicycle = bicycleDao.getByPK(id);
            bicycle.setPointHire(pointHireBicycle.getPointHire());
            return bicycle;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get bicycle", e);
        }

    }

    public List<Bicycle> getBicycles(int start, int count, User user) throws ServiceException {
        try {
            return bicycleDao.getBicycles(start, count, user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get bicycles", e);
        }
    }

    public int getAmountBicycles() throws ServiceException {
        try {
            return bicycleDao.getAmountBicycles();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get amount bicycles", e);
        }
    }
}
