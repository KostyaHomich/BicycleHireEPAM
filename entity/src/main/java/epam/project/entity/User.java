package epam.project.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Data
@Component
@Scope("prototype")
@Entity
@Table(name = "users", schema = "bicycle_hire")
public class User implements Identified<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "registration_date")
    private String registrationDate;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole;
    private Boolean enabled;

    public User() {
        this.userRole = UserRole.ROLE_USER;
        enabled = true;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


    public enum UserRole {
        ROLE_USER, ROLE_ADMIN
    }

}
