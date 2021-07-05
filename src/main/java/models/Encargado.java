package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="encargados")
public class Encargado {
    @Id
    @Column(name="idEncargado")
    private int idEncargado;
    @Column(name="area")
    private Area area;
    @Column(name="sueldo")
    private double sueldo;

}
