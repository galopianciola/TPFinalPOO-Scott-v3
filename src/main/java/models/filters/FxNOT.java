package models.filters;
import models.Cancha;
import models.filters.Cancha.Filtro;

public class FxNOT implements Filtro {
    private Filtro f;

    FxNOT(Filtro f) {
        this.f = f;
    }

    @Override
    public boolean cumple(Cancha c) {
        return !f.cumple(c);
    }
}
