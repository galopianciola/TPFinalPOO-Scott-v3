package models;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Column(name="fecha")
    private LocalDate fecha;
    @Column(name="hora")
    private LocalTime hora;
    @ManyToMany(mappedBy = "turnos")
    private List<Persona> jugadores;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="dniEncargado")
    private Encargado encargado;
    @Column(name="pagado")
    private boolean pagado;
    @Column (name="precio")
    private int precio;

    public Turno(int idTurno, Persona titular, LocalDate fecha, LocalTime hora, Encargado encargado, boolean pagado,int precio) {
        this.idTurno = idTurno;
        this.titular = titular;
        this.fecha = fecha;
        this.hora = hora;
        this.encargado = encargado;
        this.pagado = pagado;
        this.jugadores = new ArrayList<>();
        this.precio=precio;
    }

    public Turno(){
        this.titular = null;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public Persona getTitular() {
        return titular;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getPrecio(){
        return this.precio;
    }

    public LocalTime getHora() {
        return hora;
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

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    public void setEncargado(Encargado encargado) {
        if(encargado!=null){
            this.encargado = encargado;
            encargado.setTurno(this);
        }
        else
            this.encargado=null;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public void setJugadores(Persona jugador) {

        this.jugadores.add(jugador);
        jugador.setTurno(this);
    }

    public int getDniTitular() {
        // para PropertyValueFactory
        return this.titular.getDni();
    }

    public String getPagado(){
        // para PropertyValueFactory
        if (this.pagado)
            return "Sí";
        return "No";
    }
    public void setPrecio(int precio){
        this.precio=precio;
    }

    public boolean participaJugador(int dni){
        /*
        Chequea si dado un DNI, esa persona participó o no en el turno
         */

        for (Persona p: this.jugadores)
            if (p.getDni() == dni)
                return true;
        return false;
    }
}
