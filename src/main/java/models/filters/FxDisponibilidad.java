package models.filters;

import models.Cancha;
import models.Turno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FxDisponibilidad implements Filtro{

    private LocalDate fecha;

    public FxDisponibilidad(LocalDate fecha){
        this.fecha=fecha;
    }

    @Override
    public boolean cumple(Cancha c){
        boolean disponible = true;
        List<Turno> aux = new ArrayList<>();
        if(c.getMantenimiento()==true)
            disponible=false;
        else
            aux = c.getTurnos();
            for(Turno t : aux) {
                if (t.getFecha()==fecha)
                    //ver bien si esta incluido el rango horario
                    disponible = false;
        }

        return disponible;
    }
}

