package models;

import models.filters.Filtro;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Column(name = "areaPadre")
    private int id_Area_Padre;

    public Elemento(int id, double dimension, String deporte) {
        this.id = id;
        this.dimension = dimension;
        this.deporte = deporte;
        this.id_Area_Padre = -1;
    }

    public Elemento() {

    }

    public abstract int getCapacidad();
    public abstract boolean getMantenimiento();
    public abstract double getGastoMensual();
    public abstract int getGananciaMensual();
    public abstract List<Cancha> getCanchas();
    public abstract List<Cancha> getCanchasXFiltro(Filtro f1);
    public abstract List<Turno> getTurnos();
    public abstract void setTurno(Turno t);
    public abstract boolean isOcupadaXFecha(LocalDate date, LocalTime time);

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

    public void setId_Area_Padre(int id_Area){
        this.id_Area_Padre=id_Area;
    }

}
