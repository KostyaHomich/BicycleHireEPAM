package epam.project.database;

import epam.project.database.exception.DaoException;
import epam.project.entity.User;

public interface UserDao extends EntityDao<User, Integer> {
    User getByLogin(String login) throws DaoException;

    boolean checkLoginExistance(String login) throws DaoException;

    boolean checkEmailExistance(String email) throws DaoException;
}
