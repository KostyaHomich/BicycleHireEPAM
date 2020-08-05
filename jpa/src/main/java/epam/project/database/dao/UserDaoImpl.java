package epam.project.database.dao;

import epam.project.database.EntityDao;
import epam.project.database.UserDao;
import epam.project.database.exception.DaoException;
import epam.project.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Scope("prototype")
@Transactional
public class UserDaoImpl implements UserDao, EntityDao<User, Integer> {

    private static final String LOGIN = "login";
    private static final String EMAIL = "email";


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getByLogin(String login) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<User> criteria = builder.createQuery(User.class);

            Root<User> root = criteria.from(User.class);

            criteria.where(
                    builder.equal(root.get(LOGIN), login)
            );
            User user = session.createQuery(criteria).getSingleResult();

            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            throw new DaoException("Failed to get by login user", e);
        }
    }

    @Override
    public boolean checkLoginExistance(String login) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);

            query.where(
                    criteriaBuilder.equal(root.get(LOGIN), login)
            );

            User user = session
                    .createQuery(query)
                    .getSingleResult();

            session.getTransaction().commit();
            session.close();
            return user != null;
        } catch (HibernateException e) {
            throw new DaoException("Failed to check login existance", e);
        }
    }

    @Override
    public boolean checkEmailExistance(String email) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);

            query.where(
                    criteriaBuilder.equal(root.get(EMAIL), email)
            );

            User user = session
                    .createQuery(query)
                    .getSingleResult();

            session.getTransaction().commit();
            session.close();
            return user != null;
        } catch (HibernateException e) {
            throw new DaoException("Failed check email existance", e);
        }
    }


    @Override
    public User persist(User object) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
            session.close();
            return object;
        } catch (HibernateException e) {
            throw new DaoException("Failed to persist user", e);
        }

    }

    @Override
    public User getByPK(Integer key) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = session.get(User.class, key);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            throw new DaoException("Failed to get user", e);
        }
    }

    @Override
    public void update(User object) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(object);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new DaoException("Failed to update user", e);
        }
    }

    @Override
    public void delete(User object) throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new DaoException("Failed to delete user", e);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
            criteriaQuery.from(User.class);
            List<User> list = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (HibernateException e) {
            throw new DaoException("Failed to get users", e);
        }
    }
}

