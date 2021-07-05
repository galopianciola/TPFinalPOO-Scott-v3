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
    private int area; //guardo el id del area
    @Column(name="sueldo")
    private double sueldo;
    @Column(name="contrasenia")
    private String contrasenia;

    public Encargado(int idEncargado, int area, double sueldo, String contrasenia) {
        this.idEncargado = idEncargado;
        this.area = area;
        this.sueldo = sueldo;
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Encargado{" +
                "idEncargado=" + idEncargado +
                ", area=" + area +
                ", sueldo=" + sueldo +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
