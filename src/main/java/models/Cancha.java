package models;

import models.filters.Filtro;

import javax.persistence.*;
import java.io.Serializable;
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
    @Column(name="precioTurno")
    private int precioTurno;
    @Column(name="estado")
    private boolean estado;
    @Column(name="gastoMensual")
    private double gastoMensual;

    @ElementCollection
    @Column(name="turno")
    private List<Turno> turnos;

    public Cancha(int id, double dimension, String deporte, int capacidad, boolean ocupada, int precioTurno, boolean estado, double gastoMensual) {
        super(id, dimension, deporte);
        this.capacidad = capacidad;
        this.ocupada = ocupada;
        this.precioTurno = precioTurno;
        this.estado = estado;
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
    public boolean getEstado() {
        return this.estado;
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


    public boolean isOcupada() {
        return ocupada;
    }

    public int getPrecioTurno() {
        return precioTurno;
    }

    public boolean isEstado() {
        return estado;
    }


    public List<Turno> getTurnos() {
        List<Turno> retorno = new ArrayList<>(this.turnos);
        return retorno;
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

    public void setPrecioTurno(int precioTurno) {
        this.precioTurno = precioTurno;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

