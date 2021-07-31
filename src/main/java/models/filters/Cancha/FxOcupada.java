
package models.filters.Cancha;
import models.Cancha;

public class FxOcupada implements Filtro {

    public FxOcupada(){

    }
    @Override
    public boolean cumple(Cancha c){
        //return c.isOcupada();
        return true;
    }
}
