package logic;

import logic.filters.Filtro;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public abstract class Elemento {
    private double dimension;
    private String deporte;
    private Elemento padre;

    public Elemento(){

    }

    public abstract int getCapacidad();
   // public abstract Cancha getCanchaDisponible(Date fecha, Time hora);
    public abstract boolean getEstado();
    public abstract double getGastoMensual();
    public abstract List<Cancha> getCanchasXFiltro(Filtro f1);

    public void setDimension(double dimension) {
        this.dimension = dimension;
   }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public double getDimension() {
        return dimension;
    }

    public String getDeporte() {
        return deporte;
    }

    public Elemento getPadre(){return this.padre;}
    public void setPadre(Elemento p){this.padre=p;}

    //falta el buscar por filtro y el getCopia
}
