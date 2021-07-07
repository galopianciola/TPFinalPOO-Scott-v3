package models;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Column(name="fecha")
    private LocalDate fecha;
    @Column(name="hora")
    private LocalTime hora;
    @ElementCollection
    @Column(name="jugadores")
    private List<Persona> jugadores;
    @OneToOne()
    @JoinColumn(name="dniEncargado")
    private Encargado encargado;
    @Column(name="pagado")
    private boolean pagado;

    public Turno(int idTurno, Persona titular, LocalDate fecha, LocalTime hora, Encargado encargado, boolean pagado) {
        this.idTurno = idTurno;
        this.titular = titular;
        this.fecha = fecha;
        this.hora = hora;
        this.encargado = encargado;
        this.pagado = pagado;
    }



}
