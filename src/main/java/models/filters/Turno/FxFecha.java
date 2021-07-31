package models.filters.Turno;

import models.Turno;
import net.bytebuddy.asm.Advice;
import org.hibernate.criterion.BetweenExpression;

import java.time.LocalDate;

public class FxFecha implements Filtro {
    private LocalDate fecha1;
    private LocalDate fecha2;

    public FxFecha(LocalDate f1, LocalDate f2){
        this.fecha1=f1;
        this.fecha2=f2;
    }


    @Override
    public boolean cumple(Turno t) {
        if(t.getFecha().isAfter(this.fecha1) && (t.getFecha().isBefore(this.fecha2) || t.getFecha().equals(this.fecha2)))
            return true;
        return false;
    }
}
