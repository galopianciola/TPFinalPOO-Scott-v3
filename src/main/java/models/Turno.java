package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @Column(name="idTurno")
    private int idTurno;
    @Column(name="titular")
    private Persona titular;
    @Column(name="fecha_hora")
    private Timestamp fecha_hora;
    //@Column(name="jugadores")
    //private List<Persona> jugadores;
    @Column(name="encargado")
    private Persona encargado;
    @Column(name="pagado")
    private boolean pagado;

}
