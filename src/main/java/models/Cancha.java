package models;

import models.filters.Filtro;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Column(name="estado")
    private boolean mantenimiento;
    @Column(name="gastoMensual")
    private double gastoMensual;
    //@Column(name="gananciaDiaria")
    @ElementCollection
    @Column(name="turno")
    private List<Turno> turnos;

    public Cancha(int id, double dimension, String deporte, int capacidad, boolean ocupada, boolean mantenimiento, double gastoMensual) {
        super(id, dimension, deporte);
        this.capacidad = capacidad;
        this.ocupada = ocupada;
        this.mantenimiento = mantenimiento;
        this.gastoMensual = gastoMensual;
        this.turnos = new ArrayList<>();;
    }

    public Cancha(){
        super();
    }

    @Override
    public int getCapacidad() {
        return this.capacidad;
    }

    @Override
    public boolean getMantenimiento() {
        return this.mantenimiento;
    }

    @Override
    public double getGastoMensual() {
        return gastoMensual;
    }

    @Override
    public List<Cancha> getCanchasXFiltro(Filtro f1) {
        List<Cancha> retorno = new ArrayList<>();
        if (f1.cumple(this) == true)
            retorno.add(this);
        return retorno;
    }

    public boolean getOcupada(){
        for(Turno turno:this.turnos)
            if ((turno.getFecha().equals(LocalDate.now()) && ((LocalTime.now().getHour()-turno.getHora().getHour())>=0 &&(LocalTime.now().getHour()-turno.getHora().getHour())<=1)))
                return true;
        return false;
    }

    @Override
    public boolean isOcupadaXFecha(LocalDate date,LocalTime time){
        for(Turno turno:this.turnos)
            if ((turno.getFecha().equals(date)) && (turno.getHora().equals(time)))
                return true;
        return false;

    }

    public double getRecaudado(LocalDate date){
        double recaudado = 0;
        for(Turno turno:this.turnos)
            if(turno.getFecha().equals(date))
                recaudado+= turno.getPrecio();
        return recaudado;
    }



    public List<Turno> getTurnos() {
        return new ArrayList<>(this.turnos);
    }

    public List<Turno> getTurnosXFechaYHora(LocalDate date, LocalTime time){
        List<Turno> turnos = new ArrayList<>();
        for (Turno turno : this.turnos) {
            if ((turno.getFecha().equals(date)) && ((time.getHour()-turno.getHora().getHour())>0 &&(time.getHour()-turno.getHora().getHour())<=1)) //Chequeo que los jugadores todavia sigan en el complejo
                turnos.add(turno);
        }
        return turnos;
    }

    public void setTurno(Turno t) {
        this.turnos.add(t);
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento=mantenimiento;
    }

    public void setGastoMensual(double gastoMensual) {
        this.gastoMensual = gastoMensual;
    }






        /*
    @Override
    public Cancha getCanchaDisponible(Date fecha, Time hora) {
        return null;
    }
*/

    }

