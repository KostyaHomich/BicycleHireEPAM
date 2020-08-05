package epam.project.service.impl;

import epam.project.dto.UserDetailsImpl;
import epam.project.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) {
        try {
            return new UserDetailsImpl(userService.takeUser(login));
        } catch (ServiceException e) {
            throw new UsernameNotFoundException(login, e);
        }

    }
}
