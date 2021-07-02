package logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="turnos")
public class Turno {
    @Id
    @Column(name="idTurno")
    private int idTurno;
    @Column(name="titular")
    private Persona titular;
    @Column(name="fecha_hora")
    private Timestamp fecha_hora;
    @Column(name="encargado")
    private Encargado encargado;
    @Column(name="pagado")
    private boolean pagado;
    private List<Persona> jugadores;

    public Turno (Persona titular,Timestamp fecha_hora,Encargado encargado,boolean pagado){

        this.titular=titular;
        this.fecha_hora=fecha_hora;
        this.jugadores=new ArrayList<>();
        this.encargado=encargado;
        this.pagado=pagado;
    }

    public void setJugadores(Persona jugador) {
        this.jugadores.add(jugador);
    }

    public Persona getTitular() {
        return titular;
    }

    public List<Persona> getJugadores() {
        return jugadores;
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public boolean isPagado() {
        return pagado;
    }

    public Timestamp getFechaHora() { return this.fecha_hora; }

}

