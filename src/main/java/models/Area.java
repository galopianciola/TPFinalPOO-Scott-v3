package models;

import models.filters.Filtro;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("A")
@Table(name = "areas")
public class Area extends Elemento implements Serializable {

    /*@Column(name="idArea")
    private int idArea;*/
    @Column(name="banios")
    private int baños;
    @Column(name="idEncargado")
    private int idEncargado;
    @ElementCollection
    @Column(name="idElemento")
    private List<Elemento> elementos;

    public Area(int id, double dimension, String deporte, int baños, int idEncargado) {
        super(id, dimension, deporte);
        this.baños = baños;
        this.idEncargado = idEncargado;
        this.elementos = new ArrayList<>();
    }

    public Area(){
        super();

    }

    @Override
    public int getCapacidad() {
        int retorno=0;
        for(models.Elemento e:this.elementos)
            retorno=retorno+e.getCapacidad();

        return retorno;
    }

    @Override
    public boolean getEstado() {
        boolean retorno=false;
        for(Elemento e:this.elementos)
            if(e.getEstado()==true)
                retorno=true;
        return retorno;
    }

    @Override
    public double getGastoMensual() {
        double retorno = 0;
        for(Elemento e:this.elementos)
            retorno=retorno + e.getGastoMensual();
        return retorno;
    }

    @Override
    public List<Cancha> getCanchasXFiltro(Filtro f1) {
        List<Cancha> retorno = new ArrayList<>();
        for(Elemento e:this.elementos)
            retorno.addAll(e.getCanchasXFiltro(f1));
        return retorno;
    }

    public String getPorcentajeElementosDisponibles(){ //devuelve el porcentaje de elementos disponibles
        int cantElementosDisponibles=0;
        double retorno=0;
        for(Elemento e:this.elementos)
            if (e.getEstado()==true)
                cantElementosDisponibles++;
        if (cantElementosDisponibles == 0)
            return "Disponible en 0%";
        else
            retorno = ( (double) cantElementosDisponibles/(double) this.elementos.size())*100;
        return "Disponible en " + retorno+"%";
    }

    public int getBaños() {
        return baños;
    }

    public int getIdEncargado() {
        return idEncargado;
    }

    public List<Elemento> getElementos() {
        List<Elemento> retorno = new ArrayList<>(this.elementos);
        return retorno;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public void setElementos(Elemento e){
        this.elementos.add(e);}

    public void setBaños(int baños) {
        this.baños = baños;
    }


    @Override
    public String toString() {
        return "Area{" +
                "baños=" + baños +
                ", idEncargado=" + idEncargado +
                ", elementos=" + elementos +
                '}';
    }

    }

