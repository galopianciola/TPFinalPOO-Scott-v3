package models;

import models.filters.Filtro;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
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

    /*
recorre todos los elementos del area y retorna la suma de las capacidades de los mismos.
 */
    @Override
    public int getCapacidad() {
        int retorno=0;
        for(models.Elemento e:this.elementos)
            retorno=retorno+e.getCapacidad();
        return retorno;
    }

    /*
Si al menos 1 elemento del area se encuentra disponible, entonces el area se encuentra disponible.
     */
    @Override
    public boolean getMantenimiento() {
        for(Elemento e:this.elementos)
            if(e.getMantenimiento()==false)
                return false;
        return true;
    }

    /*
Suma el gasto mensual de todos los elementos del area, y retorna ese valor como gasto mensual del area.
     */
    @Override
    public double getGastoMensual() {
        double retorno = 0;
        for(Elemento e:this.elementos)
            retorno=retorno + e.getGastoMensual();
        return retorno;
    }

    /*
Devuelve unas lista de canchas que cumplen con la condicion del filtro.
     */
    @Override
    public List<Cancha> getCanchasXFiltro(Filtro f1) {
        List<Cancha> retorno = new ArrayList<>();
        for(Elemento e:this.elementos)
            retorno.addAll(e.getCanchasXFiltro(f1));
        return retorno;
    }

    /*
Devuelve una lista de canchas, sin ningun tipo de condicion.
     */
    @Override
    public List<Cancha> getCanchas(){
        List<Cancha> retorno = new ArrayList<>();
        for(Elemento e:this.elementos)
            retorno.addAll(e.getCanchas());
        return retorno;
    }

    /*
Devuelve todos los turnos que tienen las canchas del Area.
     */
    @Override
    public List<Turno> getTurnos() {
        List<Turno> turnos = new ArrayList<>();
        for(Elemento elemento:this.elementos)
            turnos.addAll(elemento.getTurnos());
        return turnos;
    }

    /*
Suma las ganancias mensuales de todos los elementos que compone el area, y retorna ese valor.
     */
    @Override
    public int getGananciaMensual(){
        int gananciaMensualArea =0;
        for(Elemento elemento:this.elementos)
            gananciaMensualArea+=elemento.getGananciaMensual();
        return gananciaMensualArea;
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

    /*
Se utiliza para mostrar por interface los nombres de las subAreas del deporte.
Para esto, recorro los elementos y agrego los nombres de los que son areas.
     */
    public List<String> getNombreSubAreas(){
        List<String> retorno = new ArrayList<>();
        if(this.elementos.isEmpty())
            retorno.add(this.getNombreArea());
        else {
            for (Elemento elemento : this.elementos) {
                if (elemento.getClass().equals(Cancha.class))
                    retorno.add(this.getNombreArea());
                else
                    retorno.addAll(((Area) elemento).getNombreSubAreas());
            }
        }
        if(!retorno.isEmpty())
            retorno = retorno.stream().distinct().collect(Collectors.toList());
        return retorno;
    }

    /*
retorna una lista de las areas que coincidan con el nombre
     */
    public List<Area> getAreaXNombre(String nombre) {
        List <Area> retorno = new ArrayList<>();
        if (this.getNombreArea().equals(nombre))
            retorno.add(this);
        else
            for (Elemento elemento : this.elementos) {
                if (elemento.getClass().equals(Area.class))
                    if(!(((Area) elemento).getAreaXNombre(nombre)).isEmpty()){
                    retorno.addAll(((Area) elemento).getAreaXNombre(nombre));
                }
            }
            if(!retorno.isEmpty()){
            retorno.removeAll(Collections.singleton(null));
            }
        return retorno;
    }
    /*
Retorna lista de elementos
     */
    public List<Elemento> getElementos() {
        List<Elemento> retorno = new ArrayList<>(this.elementos);
        return retorno;
    }

    /*
Chequea si hay alguna cancha disponible para asignar un turno.
     */
    @Override
    public boolean isOcupadaXFecha(LocalDate date, LocalTime time){
        for(Elemento elemento:this.elementos)
            if (!elemento.isOcupadaXFecha(date,time))
                return false;
        return true;
    }

    /*
Setea el turno en una cancha que no se encuentre ocupada y no este en mantenimiento.
     */
    @Override
    public void setTurno(Turno t){
        int contador=0;
        for(Elemento elemento:this.elementos) {
            if ((!elemento.isOcupadaXFecha(t.getFecha(),t.getHora())) && (elemento.getMantenimiento()==false) && (contador==0)) {
                elemento.setTurno(t);
                contador++;
            }
        }
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
    }

    /*
Seteo un elemento al area. Puede ser una sub area o una cancha
     */
    public void setElementos(Elemento e){
        this.elementos.add(e);
        e.setId_Area_Padre(this.getId());
    }

    public void setBaños(int baños) {
        this.baños = baños;
    }

    public void setNombreArea(String nombreArea){
        this.nombreArea=nombreArea;
    }

    public void eliminarElemento(Elemento e){
        if(this.elementos.contains(e))
            this.elementos.remove(e);
    }

}

