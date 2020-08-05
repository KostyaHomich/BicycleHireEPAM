package epam.project.entity;

import java.io.Serializable;

public interface Identified<PK extends Serializable> {
    PK getId();

    void setId(int id);
}