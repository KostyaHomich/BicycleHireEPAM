package epam.project.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Component
@Scope("prototype")
@Entity
@Table(name = "point_hire", schema = "bicycle_hire")
public class PointHire implements Identified<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location")
    private String location;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bicycle_hire.point_hire_bicycle",
            joinColumns = { @JoinColumn(name = "id_point_hire") },
            inverseJoinColumns = { @JoinColumn(name = "id_bicycle") })
    private List<Bicycle> bicycleList;

    public PointHire() {
    }

    @Override
    public Integer getId() {
        return id;
    }


    @Override
    public void setId(int id) {
        this.id = id;
    }
}
