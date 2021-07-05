package logic;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logic.filters.*;

public class Area extends Elemento{

    private int idArea;
    private int baños;
    private List<Elemento> elementos;

    public Area (int baños){
        this.baños = baños;
        this.elementos = new ArrayList<>();
    }

    @Override
    public int getCapacidad() {
        int retorno=0;
        for(Elemento e:this.elementos)
            retorno=retorno+e.getCapacidad();

        return retorno;
    }

  /*  @Override
    public Cancha getCanchaDisponible(Date fecha, Time hora) {
        return null;
    }
*/
    @Override
    public List<Cancha> getCanchasXFiltro(Filtro f1){
        List<Cancha> retorno = new ArrayList<>();
        for(Elemento e:this.elementos)
            retorno.addAll(e.getCanchasXFiltro(f1));
        return retorno;
    }

    @Override
    public boolean getEstado() { //se fija si el area esta disponible(con que haya 1 cancha disponible alcanza)
        boolean retorno=false;
        for(Elemento e:this.elementos)
            if(e.getEstado()==true)
                retorno=true;
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

    @Override
    public double getGastoMensual() {
        double retorno = 0;
        for(Elemento e:this.elementos)
            retorno=retorno + e.getGastoMensual();
        return retorno;

    }

    public int getBaños() {
        return baños;
    }

    public void addElemento(Elemento e) {
        this.elementos.add(e);
        e.setPadre(this);
    }

    public void setBaños(int baños) {
        this.baños = baños;
    }
}
