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
    @Column(name="jugadores")
    private List<Persona> jugadores;
    @Column(name="encargado")
    private Persona encargado;
    @Column(name="pagado")
    private boolean pagado;

    public Turno (int idTurno,Persona titular,Timestamp fecha_hora,Persona encargado,boolean pagado){
        this.idTurno = idTurno;
        this.titular=titular;
        this.fecha_hora=fecha_hora;
        this.jugadores=new ArrayList<>();
        this.encargado=encargado;
        this.pagado=pagado;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public Persona getTitular() {
        return titular;
    }

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public List<Persona> getJugadores() {
        return jugadores;
    }

    public Persona getEncargado() {
        return encargado;
    }

    public boolean isPagado() {
        return pagado;
    }
}
