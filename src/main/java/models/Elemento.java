package models;

import models.filters.Filtro;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="element_type")
@Table(name = "elementos")
public abstract class Elemento {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "dimension")
    private double dimension;
    @Column(name = "deporte")
    private String deporte;
    //private Elemento padre; Hay que ver si poner denbtoe de que area o comlejo estoy o no.

    public Elemento(int id, double dimension, String deporte) {
        this.id = id;
        this.dimension = dimension;
        this.deporte = deporte;
    }


    public abstract int getCapacidad();
    public abstract boolean getEstado();
    public abstract double getGastoMensual();
    public abstract List<Cancha> getCanchasXFiltro(Filtro f1);
    // public abstract Cancha getCanchaDisponible(Date fecha, Time hora);


    public int getId() {
        return id;
    }

    public double getDimension() {
        return dimension;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    //public logic.Elemento getPadre(){return this.padre;}
    //public void setPadre(logic.Elemento p){this.padre=p;}
    //falta el getCopia


}
