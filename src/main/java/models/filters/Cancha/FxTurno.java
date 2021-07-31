package models.filters.Cancha;

import models.Cancha;
import models.Turno;

public class FxTurno implements Filtro{
    private Turno t;

    public FxTurno(Turno t){
        this.t=t;
    }

    @Override
    public boolean cumple(Cancha c) {
        for(Turno t:c.getTurnos()) {
            if (t.equals(this.t))
                return true;
        }
        return false;
    }
}
