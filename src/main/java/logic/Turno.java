package logic;

//Fecha y hora
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class Turno {
    private int idTurno;
    private Persona titular;
    private int getDniTitular; //para PropertyValueFactory
    private LocalDate fecha;
    private LocalTime hora;
    private Encargado encargado;
    private boolean pagado;
    private String getPagado; // para PropertyValueFactory
    private List<Persona> jugadores;

    public Turno (int idTurno, Persona titular,LocalDate fecha, LocalTime hora,Encargado encargado,boolean pagado){
        this.idTurno = idTurno;
        this.titular=titular;
        this.getDniTitular=titular.getDni();
        this.fecha = fecha;
        this.hora = hora;
        this.jugadores=new ArrayList<>();
        this.encargado=encargado;
        this.pagado=pagado;
        this.getPagado = this.getGetPagado();
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

    public LocalDate getFecha() { return this.fecha; }

    public LocalTime getHora(){
        return this.hora;
    }

    public int getGetDniTitular() {
        return this.titular.getDni();
    }

    public int getIdTurno() {
        return idTurno;
    }

    public String getGetPagado(){
        // para PropertyValueFactory
        if (this.pagado)
            return "Sí";
        return "No";
    }
}

