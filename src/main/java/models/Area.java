package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("A")
@Table(name = "areas")
public class Area extends Elemento implements Serializable {

    /*@Column(name="idArea")
    private int idArea;*/
    @Column(name="banios")
    private int baños;
    @Column(name="idEncargado")
    private int idEncargado;
    @ElementCollection
    @Column(name="idElemento")
    private List<Elemento> elementos;

    public Area(int id, double dimension, String deporte, int baños, int idEncargado) {
        super(id, dimension, deporte);
        this.baños = baños;
        this.idEncargado = idEncargado;
        this.elementos = new ArrayList<>();
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }
    public void setElementos(Elemento e){this.elementos.add(e);}

    @Override
    public String toString() {
        return "Area{" +
                "baños=" + baños +
                ", idEncargado=" + idEncargado +
                ", elementos=" + elementos +
                '}';
    }
}
