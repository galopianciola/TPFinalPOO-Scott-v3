package logic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logic.filters.*;

public class Cancha extends Elemento{

    private int idCancha;
    private int capacidad;
    private boolean ocupada;
    private int precioTurno;
    private boolean estado;
    private double gastoMensual;

    private List<Turno>turnos = new ArrayList<>();


    public Cancha(int idCancha, int capacidad, boolean ocupada, int precioTurno, boolean estado, double gastoMensual) {
        this.capacidad = capacidad;
        this.ocupada = ocupada;
        this.precioTurno = precioTurno;
        this.estado = estado;
        this.gastoMensual = gastoMensual;
        this.idCancha = idCancha;
    }

    public int getCapacidad() {
        return this.capacidad;
    }
/*
    @Override
    public Cancha getCanchaDisponible(Date fecha, Time hora) {
        return null;
    }
*/
    @Override
    public boolean getEstado() {
      return this.estado;
    }

    @Override
    public double getGastoMensual() {
        return this.gastoMensual;
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

    public List<Turno> getTurnos() {
        return this.turnos;
    }

    public int getNroCancha(){
        return this.idCancha;
     }

    public void setTurno(Turno t) {
        this.turnos.add(t);
    }

    public void setCapacidad(int c){
        this.capacidad = c;
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

    public void setNroCancha(int nroCancha) {
        this.idCancha = idCancha;
    }

}
