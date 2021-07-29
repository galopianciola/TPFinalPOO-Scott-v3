package models;

import models.filters.Filtro;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity()
@DiscriminatorValue("C")
@Table(name = "canchas")
public class Cancha extends Elemento implements Serializable {

    @Column(name="capacidad")
    private int capacidad;
    @Column(name="ocupada")
    private boolean ocupada;
    @Column(name="estado")
    private boolean mantenimiento;
    @Column(name="gastoMensual")
    private double gastoMensual;
    @Column(name="gananciaMensual")
    private int gananciaMensual;
    @ElementCollection
    @Column(name="turno")
    private List<Turno> turnos;
    private String disponible;
    //Este atributo solamente se usa para mostrar de una mejor forma por la interface

    public Cancha(int id, double dimension, String deporte, int capacidad, boolean ocupada, boolean mantenimiento, double gastoMensual) {
        super(id, dimension, deporte);
        this.capacidad = capacidad;
        this.ocupada = ocupada;
        this.mantenimiento = mantenimiento;
        this.gastoMensual = gastoMensual;
        this.turnos = new ArrayList<>();
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
    public int getGananciaMensual(){
        return this.gananciaMensual;
    }

    /*
Devuelve una lista de canchas, con la misma si es que cumple la condicion del filtro.
Si el filtro es null, devuelve la lista de canchas con la misma.
 */
    @Override
    public List<Cancha> getCanchasXFiltro(Filtro f1) {
        List<Cancha> retorno = new ArrayList<>();
        if(f1!=null) {
            if (f1.cumple(this) == true)
                retorno.add(this);
        }
        else {
            retorno.add(this);
        }
        return retorno;
    }

    /*
    Chequea si en este momento hay algun turno en curso o si esta disponible la cancha.
     */
    public String getOcupada(){
        for(Turno turno:this.turnos)
            if ((turno.getFecha().equals(LocalDate.now()) && ((LocalTime.now().getHour()-turno.getHora().getHour())>=0 &&(LocalTime.now().getHour()-turno.getHora().getHour())<=1)))
                return "Si";
        return "No";
    }

    /*
Chequea si esta ocupada en una fecha en particular
 */
    @Override
    public boolean isOcupadaXFecha(LocalDate date,LocalTime time){
        for(Turno turno:this.turnos)
            if ((turno.getFecha().equals(date)) && (turno.getHora().equals(time)))
                return true;
        return false;

    }
    /*
Obtiene lo que recaudo la cancha en un cierto dia
     */
    public double getRecaudado(LocalDate date){
        double recaudado = 0;
        for(Turno turno:this.turnos)
            if(turno.getFecha().equals(date))
                recaudado+= turno.getPrecio()*turno.getJugadores().size();
        return recaudado;
    }

    /*
Retorna si esta disponible la cancha o si esta en mantenimiento
 */
    public String getDisponible(){
        if(this.mantenimiento==true)
            return "No";
        return "Si";
    }

    public List<Turno> getTurnos() {
        return new ArrayList<>(this.turnos);
    }

    /*
Se utiliza para ver la gente que hay en el complejo actualmente.
Matchea con los turnos del dia, y con los que arrancaron y todavia no terminaron.
 */
    public List<Turno> getTurnosXFechaYHora(LocalDate date, LocalTime time){
        List<Turno> turnos = new ArrayList<>();
        for (Turno turno : this.turnos) {
            if ((turno.getFecha().equals(date)) && ((time.getHour()-turno.getHora().getHour())>=0 &&(time.getHour()-turno.getHora().getHour())<1)) //Chequeo que los jugadores todavia sigan en el complejo
                turnos.add(turno);
        }
        return turnos;
    }
    /*
    Seteo el turno y ademas aumento la ganancia mensual.
     */
    public void setTurno(Turno t) {
        this.turnos.add(t);
        this.gananciaMensual+=(t.getPrecio()*(t.getJugadores().size()));
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

}

