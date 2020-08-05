package epam.project.service.impl;

import epam.project.database.UserDao;
import epam.project.database.exception.DaoException;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope("prototype")
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public List<User> takeAll() throws ServiceException {


        try {
           return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all users", e);

        }
    }

    @Transactional
    public boolean register(User user) throws ServiceException {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.persist(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to register user", e);
        }
        return true;

    }

    public User signIn(String login, String password) throws ServiceException {
        try {
            User user = userDao.getByLogin(login);

            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                throw new ServiceException("Wrong password");
            }
        } catch (DaoException e) {
            throw new ServiceException("Failed to sign in user.", e);
        }
    }

    @Transactional
    public boolean delete(User user) throws ServiceException {

        try {
            userDao.delete(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete user", e);
        }
        return true;
    }

    @Transactional
    public User getById(int id) throws ServiceException {

        try {
           return userDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete user", e);
        }
    }

    @Transactional
    public boolean update(User user) throws ServiceException {
        try {

            user.setPassword(userDao.getByLogin(user.getLogin()).getPassword());
            user.setRegistrationDate(userDao.getByLogin(user.getLogin()).getRegistrationDate());
            userDao.update(user);
            return true;
        } catch (DaoException e) {
            throw new ServiceException("Failed to update user", e);
        }
    }

    @Transactional
    public User takeUser(String login) throws ServiceException {
        try {
           return userDao.getByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Failed to take user.", e);
        }

    }

    public boolean checkLoginExistence(String login) throws ServiceException {
        try {
            return userDao.checkLoginExistance(login);

        } catch (DaoException e) {
            throw new ServiceException("Failed to check contains user", e);
        }

    }

    public boolean checkEmailExistence(String email) throws ServiceException {

        try {
            return userDao.checkEmailExistance(email);
        } catch (DaoException e) {
            throw new ServiceException("Failed to check contains user", e);
        }

    }


}
