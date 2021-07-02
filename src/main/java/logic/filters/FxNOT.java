package logic.filters;
import logic.*;

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
