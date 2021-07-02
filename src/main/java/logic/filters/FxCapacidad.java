package logic.filters;
import logic.*;

public class FxCapacidad implements Filtro{

    private int capacidadRequerida;
    private int limiteInf;
    private int limiteSup;

    public FxCapacidad(int capacidadRequerida){
        this.capacidadRequerida=capacidadRequerida;
    }

    public FxCapacidad(int limiteSup,int limiteInf){
        this.capacidadRequerida = -1;
        this.limiteInf=limiteInf;
        this.limiteSup=limiteSup;
    }

    @Override
    public boolean cumple(Cancha c){
        if (this.capacidadRequerida == -1)
            return c.getCapacidad()>=limiteInf && c.getCapacidad()<=limiteSup;
        else
            return c.getCapacidad()==capacidadRequerida;

    }
}
