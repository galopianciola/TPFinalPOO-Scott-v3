package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("C")
@Table(name = "canchas")
public class Cancha extends Elemento implements Serializable {

    /*@Column(name="idCancha")
    private int idCancha;*/
    //@Column(name="area")
    //private Area area;
    @Column(name="capacidad")
    private int capacidad;
    @Column(name="ocupada")
    private boolean ocupada;
    @Column(name="precioTurno")
    private int precioTurno;
    @Column(name="estado")
    private boolean estado;
    @Column(name="gastoMensual")
    private double gastoMensual;

    @ElementCollection
    @Column(name="turno")
    private List<Turno> turnos;

    public Cancha(int id, double dimension, String deporte, int capacidad, boolean ocupada, int precioTurno, boolean estado, double gastoMensual) {
        super(id, dimension, deporte);
        this.capacidad = capacidad;
        this.ocupada = ocupada;
        this.precioTurno = precioTurno;
        this.estado = estado;
        this.gastoMensual = gastoMensual;
        this.turnos = new ArrayList<>();;
    }

    public void setTurno(Turno t) {
        this.turnos.add(t);
    }


}
