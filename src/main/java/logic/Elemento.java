package logic;

import logic.filters.Filtro;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public abstract class Elemento {
    private double dimension;
    private String deporte;

    public Elemento(){

    }

    public abstract int getCapacidad();
    public abstract Cancha getCanchaDisponible(Date fecha, Time hora);
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

    //falta el buscar por filtro y el getCopia
}
