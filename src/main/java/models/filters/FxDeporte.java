package models.filters;
import models.Cancha;

public class FxDeporte implements Filtro{

    private String deporte;

    public FxDeporte(String deporte){
        this.deporte=deporte;
    }

    @Override
    public boolean cumple(Cancha c){
        return c.getDeporte()==deporte;
    }

}
