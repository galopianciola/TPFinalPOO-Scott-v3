package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="encargados")
public class Encargado extends Persona implements Serializable {

    @OneToOne
    @JoinColumn(name="idArea")
    private Area area; //guardo el id del area
    @Column(name="sueldo")
    private double sueldo;
    @Column(name="password")
    private String password;

    public Encargado(int dni, String nombre, String apellido, String direccion, int telefono, Area area, double sueldo, String password) {
        super(dni, nombre, apellido, direccion, telefono, true);
        this.area = area;
        this.sueldo = sueldo;
        this.password = password;

        if (this.area != null)
            this.area.setIdEncargado(super.dni);
    }

    public String getPassword() {
        return password;
    }

    public void setArea(Area area) {
        this.area = area;
        this.area.setIdEncargado(super.dni);
    }

    @Override
    public String toString() {
        return "Encargado{" +"dni="+super.dni+
                ", area=" + area.toString() +
                ", sueldo=" + sueldo +
                ", password='" + password  +
                '}';
    }
}
