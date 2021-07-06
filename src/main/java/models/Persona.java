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


}