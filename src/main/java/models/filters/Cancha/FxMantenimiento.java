package models.filters.Cancha;

import models.Cancha;

public class FxMantenimiento implements Filtro {

    @Override
    public boolean cumple(Cancha c){
        return (!c.getMantenimiento());
    }
}
