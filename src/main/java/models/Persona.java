package models;

import javax.persistence.*;

/*
Clase que contiene Encargados y Jugadores
 */

//@Entity
//@Table(name="personas")
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
    @Column(name="direccion")
    private String direccion;
    @Column(name="telefono")
    private int telefono;
    @Column(name="isEncargado")
    private boolean isEncargado;

    public Persona(int dni,String nombre, String apellido,String direccion, int telefono, boolean isEncargado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.isEncargado = isEncargado;
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

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public boolean isEncargado() {
        return isEncargado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEncargado(boolean encargado) {
        isEncargado = encargado;
    }
}