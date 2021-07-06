package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="element_type")
@Table(name = "elementos")
public abstract class Elemento {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "dimension")
    private double dimension;
    @Column(name = "deporte")
    private String deporte;

    public Elemento(int id, double dimension, String deporte) {
        this.id = id;
        this.dimension = dimension;
        this.deporte = deporte;
    }


}
