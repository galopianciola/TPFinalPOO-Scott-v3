package logic.filters;
import logic.*;

public class FxAND implements Filtro {
    private Filtro f1;
    private Filtro f2;

    FxAND(Filtro f1, Filtro f2){
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public boolean cumple(Cancha c) {
        return f1.cumple(c) && f2.cumple(c);
    }
}
