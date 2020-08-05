package epam.project.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Component
@Scope("prototype")
@Entity
@Table(name = "bicycle", schema = "bicycle_hire")
public class Bicycle implements Identified<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String link;
    private String description;

    @OneToOne
    @JoinColumn(name = "id")
    private PointHire pointHire;



    public Bicycle() {
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
