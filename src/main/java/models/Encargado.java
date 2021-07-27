package models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="encargados")
public class Encargado extends Persona implements Serializable {

    @OneToOne()
    @JoinColumn(name="idArea")
    private Area area; //guardo el id del area
    @Column(name="sueldo")
    private double sueldo;
    @Column(name="password")
    private String password;
    @OneToMany(mappedBy = "encargado",cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public Encargado(int dni, String nombre, String apellido, int telefono, Area area, double sueldo,String password) {
        super(dni, nombre, apellido, telefono, true);
        this.area = area;
        this.sueldo = sueldo;
        this.password = password;
        this.turnos = new ArrayList<>();

        if (this.area != null)
            this.area.setIdEncargado(super.dni);
    }
    public Encargado(){
        super();

    }

    public String getPassword() {
        return password;
    }


    public Area getArea() {
        return area;
    }

    public int getIdArea() {
        if(this.area!=null)
            return this.area.getId();
        else
            return -1;
    }

    public double getSueldo() {
        return sueldo;
    }

    public int getDni(){
        return super.getDni();
    }

    public String getDeporte(){
        if(this.area!=null)
        return this.area.getDeporte();
        else
            return "Ninguno";
    }

    public void setArea(Area area) {
        this.area = area;
        this.area.setIdEncargado(super.dni); //Le seteo al area que yo soy el encargado
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public void setPassword(String password) {
        this.password = password;
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
