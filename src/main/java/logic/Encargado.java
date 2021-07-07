package logic;

public class Encargado extends Persona{

    private int idEncargado;
    private String contrasenia;
    private Area area;
    private double sueldo;


    public Encargado(int dni,String contrasenia,String nombre, String apellido, String direccion, int telefono,Area area,double sueldo) {
        super(dni,nombre, apellido,direccion, telefono);
        this.area=area;
        this.sueldo=sueldo;
        this.contrasenia = contrasenia;
    }

    public void setTurno(Turno t){

    }

    public void setArea(Area a){
        this.area=a;
    }
}