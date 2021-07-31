package models.filters.Turno;

import models.Persona;
import models.Turno;

public class FxDni implements Filtro{
    private int dni;

    public FxDni(int dni){
        this.dni=dni;
    }

    @Override
    public boolean cumple(Turno t) {
        for(Persona p: t.getJugadores()){
            if (p.getDni() == this.dni)
                return true;
        }
        return false;
    }
}
