package epam.project.builder;

import epam.project.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class UserBuilder implements Builder<User> {

    private static final String ID = "id";
    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";
    private static final String EMAIL = "email";
    private static final String FIST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    @Override
    public User build(Map<String, String> params) {
        User user = new User();
        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case LOGIN:
                    user.setLogin(value);
                    break;
                case PASSWORD:
                    user.setPassword(value);
                    break;
                case FIST_NAME:
                    user.setFirstName(value);
                    break;
                case LAST_NAME:
                    user.setLastName(value);
                    break;
                case EMAIL:
                    user.setEmail(value);
                    break;
                case ID:
                    user.setId(Integer.parseInt(value));
                    break;
                default:
                    break;
            }
        }
        return user;
    }

}
