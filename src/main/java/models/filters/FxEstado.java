package models.filters;

import models.Cancha;

public class FxEstado implements Filtro{

    @Override
    public boolean cumple(Cancha c){
        return c.getMantenimiento();
    }
}
