package epam.project.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Component
@Scope("prototype")
@Entity
@Table(name = "best_bicycle", schema = "bicycle_hire")
public class BestBicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_point_hire_bicycle")
    private PointHireBicycle pointHireBicycle;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;
}
