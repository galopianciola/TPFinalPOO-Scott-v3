package models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @Column(name="idTurno")
    private int idTurno;
    @OneToOne
    @JoinColumn(name="dniTitular")
    private Persona titular;
    @Column(name="fecha_hora")
    private Timestamp fecha_hora;
    @ElementCollection
    @Column(name="jugadores")
    private List<Persona> jugadores;
    @OneToOne()
    @JoinColumn(name="dniEncargado")
    private Encargado encargado;
    @Column(name="pagado")
    private boolean pagado;

    public Turno(int idTurno, Persona titular, Timestamp fecha_hora, Encargado encargado, boolean pagado) {
        this.idTurno = idTurno;
        this.titular = titular;
        this.fecha_hora = fecha_hora;
        this.encargado = encargado;
        this.pagado = pagado;
    }



}
