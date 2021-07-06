package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "elementos")
public class Elemento {
    @Id
    @Column(name = "idCancha")
    private int idCancha;
    @Column(name = "capacidad")
    private int capacidad;
    @Column(name = "ocupada")
    private boolean ocupada;
    @Column(name = "precioTurno")
    private int precioTurno;
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "gastoMensual")
    private double gastoMensual;
    //@Columm(name="elementos")
    //private List<Elemento> elementos;
    @Column(name = "baños")
    private int baños;
    @Column(name = "padre")
    private int padre;

}
