package epam.project.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "point_hire_bicycle", schema = "bicycle_hire")
public class PointHireBicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_bicycle")
    private Bicycle bicycle;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_point_hire")
    private PointHire pointHire;




    public PointHireBicycle() {
    }
}
