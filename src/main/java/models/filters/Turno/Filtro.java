package models.filters.Turno;

import models.Cancha;
import models.Turno;

public interface Filtro {
    boolean cumple(Turno t);
}
