package logic.filters;
import logic.*;

public class FxOcupada implements Filtro {

    public FxOcupada(){

    }

    @Override
    public boolean cumple(Cancha c){
        return c.isOcupada();
    }
}
