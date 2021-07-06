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

    //private List<Turno> turnos = new ArrayList<>();
}
