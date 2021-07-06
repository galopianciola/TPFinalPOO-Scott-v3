package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "areas")
public class Area {
    @Id
    @Column(name="idArea")
    private int idArea;
    @Column(name="banios")
    private int baños;
    @Column(name="idEncargado")
    private int idEncargado;

    public Area(int idArea, int baños, int idEncargado) {
        this.idArea = idArea;
        this.baños = baños;
        this.idEncargado = idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    /*@Entity
    @OneToMany
    @Column(name="idElemento")
    //private List<Elemento> elementos;*/
}
