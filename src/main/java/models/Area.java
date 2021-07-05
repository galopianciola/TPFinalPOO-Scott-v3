package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="areas")
public class Area {
    @Id
    @Column(name="idArea")
    private int idArea;
    @Column(name="banios")
    private int baños;


    /*@Entity
    @OneToMany
    @Column(name="idElemento")
    //private List<Elemento> elementos;*/
}
