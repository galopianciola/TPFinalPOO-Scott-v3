package models;

import models.filters.Filtro;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("A")
@Table(name = "areas")
public class Area extends Elemento implements Serializable {

    @Column(name="banios")
    private int baños;
    @Column(name="idEncargado")
    private int idEncargado;
    @Column(name="nombreArea")
    private String nombreArea;
    @ElementCollection
    @Column(name="idElemento")
    private List<Elemento> elementos;

    public Area(int id, double dimension, String deporte, int baños, int idEncargado,String nombreArea) {
        super(id, dimension, deporte);
        this.baños = baños;
        this.idEncargado = idEncargado;
        this.elementos = new ArrayList<>();
        this.nombreArea=nombreArea;
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
    public boolean getMantenimiento() {
        boolean retorno=false;
        for(Elemento e:this.elementos)
            if(e.getMantenimiento()==true)
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

    @Override
    public List<Cancha> getCanchas(){
        List<Cancha> retorno = new ArrayList<>();
        for(Elemento e:this.elementos)
            retorno.addAll(e.getCanchas());
        return retorno;
    }

    @Override
    public List<Turno> getTurnos() {
        List<Turno> turnos = new ArrayList<>();
        for(Elemento elemento:this.elementos)
            turnos.addAll(elemento.getTurnos());
        return turnos;
    }

    @Override
    public int getGananciaMensual(){
        int gananciaMensualArea =0;
        for(Elemento elemento:this.elementos)
            gananciaMensualArea+=elemento.getGananciaMensual();
        return gananciaMensualArea;
    }

    public String getPorcentajeElementosDisponibles(){ //devuelve el porcentaje de elementos disponibles
        int cantElementosDisponibles=0;
        double retorno=0;
        for(Elemento e:this.elementos)
            if (e.getMantenimiento()==true)
                cantElementosDisponibles++;
        if (cantElementosDisponibles == 0)
            return "Disponible en 0%";
        else
            retorno = ( (double) cantElementosDisponibles/(double) this.elementos.size())*100;
        return "Disponible en " + retorno+"%";
    }

    public boolean getDisponibilidad(){
        for(Elemento e:this.elementos) {
            if (e.getMantenimiento() == false)
                return true;
        }
        return false;
    }

    public int getBaños() {
        return baños;
    }

    public int getIdEncargado() {
        return idEncargado;
    }

    public String getNombreArea(){
        return this.nombreArea;
    }

    public List<String> getNombreSubAreas(){
        List<String> retorno = new ArrayList<>();
        for (Elemento elemento:this.elementos) {
            if (elemento.getClass().equals(Cancha.class))
                    retorno.add(this.getNombreArea());
            else
                    retorno.addAll(((Area) elemento).getNombreSubAreas());
            }
        if(this.elementos.isEmpty())
            if(this.getClass().equals(Area.class))
                retorno.add(this.getNombreArea());
        if(!retorno.isEmpty())
            retorno = retorno.stream().distinct().collect(Collectors.toList());
        return retorno;
    }

    public List<Area> getAreaXNombre(String nombre) {
        List <Area> retorno = new ArrayList<>();
        if (this.getNombreArea().equals(nombre))
            retorno.add(this);
        else
            for (Elemento elemento : this.elementos) {
                if (elemento.getClass().equals(Area.class))
                    retorno.addAll(((Area) elemento).getAreaXNombre(nombre));
            }
            return retorno;
    }



    public List<Elemento> getElementos() {
        List<Elemento> retorno = new ArrayList<>(this.elementos);
        return retorno;
    }

    @Override
    public boolean isOcupadaXFecha(LocalDate date, LocalTime time){
        for(Elemento elemento:this.elementos)
            if (elemento.isOcupadaXFecha(date,time))
                return true;
        return false;
    }

    @Override
    public void setTurno(Turno t){
        int contador=0;
        for(Elemento elemento:this.elementos) {
            if ((!elemento.isOcupadaXFecha(t.getFecha(),t.getHora())) && (contador==0)) {
                elemento.setTurno(t);
                contador++;
            }
        }
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    public void setElementos(Elemento e){
        this.elementos.add(e);
        super.setDimension(super.getDimension()+e.getDimension());
        e.setId_Area_Padre(this.getId());
    }

    public void setBaños(int baños) {
        this.baños = baños;
    }

    public void setNombreArea(String nombreArea){
        this.nombreArea=nombreArea;
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

