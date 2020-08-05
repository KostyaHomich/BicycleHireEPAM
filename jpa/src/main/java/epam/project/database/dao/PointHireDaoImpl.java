package epam.project.database.dao;

import epam.project.database.EntityDao;
import epam.project.database.PointHireDao;
import epam.project.database.exception.DaoException;
import epam.project.entity.Bicycle;
import epam.project.entity.PointHire;
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
public class PointHireDaoImpl implements PointHireDao, EntityDao<PointHire, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean containsPointHire(int id) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Bicycle bicycle = session.get(Bicycle.class, id);
            session.getTransaction().commit();
            session.close();
            return bicycle != null;
        } catch (HibernateException e) {
            throw new DaoException("Failed to check for contains point hire", e);
        }
    }

    @Override
    public PointHire persist(PointHire object) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
            session.close();
            return object;
        } catch (HibernateException e) {
            throw new DaoException("Failed to persist point hire", e);
        }
    }

    @Override
    public PointHire getByPK(Integer key) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            PointHire pointHire = session.get(PointHire.class, key);
            session.getTransaction().commit();
            session.close();
            return pointHire;
        } catch (HibernateException e) {
            throw new DaoException("Failed to get point hire", e);
        }

    }

    @Override
    public void update(PointHire object) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new DaoException("Failed to update point hire", e);
        }
    }

    @Override
    public void delete(PointHire object) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new DaoException("Failed to delete point hire", e);
        }
    }

    @Override
    public List<PointHire> getAll() throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaQuery<PointHire> criteriaQuery = session.getCriteriaBuilder().createQuery(PointHire.class);
            criteriaQuery.from(PointHire.class);
            List<PointHire> list = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (HibernateException e) {
            throw new DaoException("Failed to get point hires", e);
        }
    }
}
