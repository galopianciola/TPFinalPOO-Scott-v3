package models.filters.Turno;

import models.Turno;

public class FxAnd implements Filtro{

    private Filtro f1;
    private Filtro f2;

    public FxAnd(Filtro f1, Filtro f2){
        this.f1=f1;
        this.f2=f2;
    }

    @Override
    public boolean cumple(Turno t) {
            return (f1.cumple(t) && f2.cumple(t));
    }
}
