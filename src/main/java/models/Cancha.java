package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "canchas")
public class Cancha {
    @Id
    @Column(name="idCancha")
    private int idCancha;
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
    @CollectionTable(name="turnos", joinColumns=@JoinColumn(name="idCancha"))
    @Column(name="turno")
    private List<Turno> turnos = new ArrayList<>();

    public Cancha(int idCancha, int capacidad, boolean ocupada, int precioTurno, boolean estado, double gastoMensual) {
        this.idCancha = idCancha;
        this.capacidad = capacidad;
        this.ocupada = ocupada;
        this.precioTurno = precioTurno;
        this.estado = estado;
        this.gastoMensual = gastoMensual;
    }

    public void setTurno(Turno t) {
        this.turnos.add(t);
    }
}
