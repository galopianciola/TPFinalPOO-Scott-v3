package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="personas")
public class Persona {
    @Id
    @Column(name="dni")
    protected int dni;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="telefono")
    private int telefono;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public Persona(int dni,String nombre, String apellido, int telefono, boolean isEncargado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.turnos = new ArrayList<>();
    }

    public Persona() {

    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEncargado(boolean encargado) {
    }

    public void setTurno(Turno t){
        this.turnos.add(t);
    }

    public void eliminarTurno(Turno turno){
        if (this.turnos.contains(turno))
            this.turnos.remove(turno);
    }

}