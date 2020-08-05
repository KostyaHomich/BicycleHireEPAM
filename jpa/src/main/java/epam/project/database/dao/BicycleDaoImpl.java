package epam.project.database.dao;

import epam.project.database.BicycleDao;
import epam.project.database.EntityDao;
import epam.project.database.exception.DaoException;
import epam.project.entity.BestBicycle;
import epam.project.entity.Bicycle;
import epam.project.entity.PointHireBicycle;
import epam.project.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Scope("prototype")
@Transactional
public class BicycleDaoImpl implements BicycleDao, EntityDao<Bicycle, Integer> {

    private static final String SELECT_QUERY_WITHOUT_BEST_BICYCLE_LIMITED_BY_USER_ID =
            " select distinct(bicycle.id),link,name,description" +
                    " from bicycle_hire.bicycle  where bicycle.id not in( " +
                    " SELECT bicycle.id" +
                    " FROM bicycle_hire.bicycle,bicycle_hire.best_bicycle,bicycle_hire.point_hire_bicycle" +
                    " where best_bicycle.id_user=:id_user" +
                    " and best_bicycle.id_point_hire_bicycle=point_hire_bicycle.id" +
                    " and bicycle.id=point_hire_bicycle.id_bicycle) limit :count OFFSET :start;";
    private static final String GET_ALL_BEST_BICYCLE_BY_USER =
            "SELECT bicycle.id,name,description,link " +
                    "FROM bicycle_hire.bicycle,bicycle_hire.best_bicycle,bicycle_hire.point_hire_bicycle where best_bicycle.id_user=:id_user " +
                    "and best_bicycle.id_point_hire_bicycle=point_hire_bicycle.id " +
                    "and bicycle.id=point_hire_bicycle.id_bicycle;";
    private static final String GET_BEST_BICYCLE_ID_BY_BICYCLE_ID =
            "SELECT best_bicycle.id FROM bicycle_hire.best_bicycle,bicycle_hire.bicycle,bicycle_hire.point_hire_bicycle where bicycle.id=:id and " +
                    "point_hire_bicycle.id_bicycle=bicycle.id and best_bicycle.id_point_hire_bicycle=point_hire_bicycle.id;";
    private static final String GET_AMOUNT_BICYCLES =
            "SELECT count(*)  FROM bicycle_hire.bicycle;";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPointHireBicycle(Bicycle bicycle) throws DaoException {
        try{
        PointHireBicycle pointHireBicycle = new PointHireBicycle();
        pointHireBicycle.setBicycle(bicycle);
        pointHireBicycle.setPointHire(bicycle.getPointHire());
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(pointHireBicycle);
        session.getTransaction().commit();
        session.close();
        } catch (HibernateException e) {
            throw new DaoException("Failed to add point hire bicycle", e);
        }
    }

    @Override
    public PointHireBicycle getByBicyclePkPointHireBicycle(int id) throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        PointHireBicycle pointHireBicycle = session.get(PointHireBicycle.class, id);
        session.getTransaction().commit();
        session.close();
        return pointHireBicycle;
        } catch (HibernateException e) {
            throw new DaoException("Failed to get dto by bicycle id", e);
        }
    }

    @Override
    public boolean containsBicycle(int id) throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Bicycle bicycle = session.get(Bicycle.class, id);
        session.getTransaction().commit();
        session.close();
        return bicycle != null;
    } catch (HibernateException e) {
        throw new DaoException("Failed to check bicycle", e);
    }
    }

    @Override
    public List<Bicycle> getAllBicycleByPointHirePk(int id) throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        PointHireBicycle pointHireBicycle = session.get(PointHireBicycle.class, id);
        session.getTransaction().commit();
        session.close();
        return pointHireBicycle.getPointHire().getBicycleList();
    } catch (HibernateException e) {
        throw new DaoException("Failed to bicycles by point hire id", e);
    }
    }

    @Override
    public List<Bicycle> getBicycles(int start, int count, User user) throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        javax.persistence.Query query = session.createQuery(SELECT_QUERY_WITHOUT_BEST_BICYCLE_LIMITED_BY_USER_ID, Bicycle.class);
        query.setParameter("user_id", user.getId());
        query.setParameter("start", start);
        query.setParameter("count", count);
        session.getTransaction().commit();
        session.close();
        return query.getResultList();
        } catch (HibernateException e) {
            throw new DaoException("Failed to get bicycles", e);
        }
    }

    @Override
    public void addBestBicycle(PointHireBicycle pointHireBicycle, User user) throws DaoException {
        try {

        BestBicycle bestBicycle = new BestBicycle();
        bestBicycle.setPointHireBicycle(pointHireBicycle);
        bestBicycle.setUser(user);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(bestBicycle);
        session.getTransaction().commit();
        session.close();
        } catch (HibernateException e) {
            throw new DaoException("Failed to add best bicycles", e);
        }
    }

    @Override
    public void deleteBestBicycle(int id) throws DaoException {
        try{
        BestBicycle bestBicycle = new BestBicycle();
        bestBicycle.setId(id);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(bestBicycle);
        session.getTransaction().commit();
        session.close();
    } catch (HibernateException e) {
        throw new DaoException("Failed to delete best bicycles", e);
    }
    }

    @Override
    public List<Bicycle> getAllBestBicyclesByUserId(int id) throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        javax.persistence.Query query = session.createQuery(GET_ALL_BEST_BICYCLE_BY_USER, Bicycle.class);
        query.setParameter("user_id", id);
        session.getTransaction().commit();
        session.close();
        return query.getResultList();
        } catch (HibernateException e) {
            throw new DaoException("Failed to get best bicycles", e);
        }
    }

    @Override
    public int getBestBicycleIdByBicycleId(int id) throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        javax.persistence.Query query = session.createQuery(GET_BEST_BICYCLE_ID_BY_BICYCLE_ID);
        query.setParameter("id", id);
        session.getTransaction().commit();
        session.close();
        return query.getFirstResult();
        } catch (HibernateException e) {
            throw new DaoException("Failed to get best bicycle id by bicycle id", e);
        }

    }

    @Override
    public int getAmountBicycles() throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        javax.persistence.Query query = session.createQuery(GET_AMOUNT_BICYCLES);
        session.getTransaction().commit();
        session.close();
        return query.getFirstResult();
        } catch (HibernateException e) {
            throw new DaoException("Failed to get amount bicycles", e);
        }
    }

    @Override
    public Bicycle persist(Bicycle object) throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
        return object;
        } catch (HibernateException e) {
            throw new DaoException("Failed to persist bicycle", e);
        }
    }

    @Override
    public Bicycle getByPK(Integer key) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Bicycle bicycle = session.get(Bicycle.class, key);
            session.getTransaction().commit();
            session.close();
            return bicycle;
        } catch (HibernateException e) {
            throw new DaoException("Failed to get bicycle", e);
        }
    }

    @Override
    public void update(Bicycle object) throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new DaoException("Failed to update bicycle", e);
        }
    }

    @Override
    public void delete(Bicycle object) throws DaoException {
        try{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    } catch (HibernateException e) {
        throw new DaoException("Failed to delete bicycle", e);
    }
    }

    @Override
    public List<Bicycle> getAll() throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaQuery<Bicycle> criteriaQuery = session.getCriteriaBuilder().createQuery(Bicycle.class);
            criteriaQuery.from(Bicycle.class);
            List<Bicycle> list = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (HibernateException e) {
            throw new DaoException("Failed to get bicycles", e);
        }
    }
}
