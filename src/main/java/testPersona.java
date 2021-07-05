import models.*;


import javax.persistence.*;
import java.util.List;

public class testPersona {
    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args){
        emf = Persistence.createEntityManagerFactory("Persistencia");
        manager = emf.createEntityManager();

        List <Persona> personas = (List<Persona>)manager.createQuery("FROM models.Persona").getResultList();
        System.out.println("Hay "+personas.size()+ " empleados en el sistema");

            Encargado Juli = new Encargado(1,1,40000,"123j");
            Encargado Galo = new Encargado(2,2,38000,"123g");

            manager.getTransaction().begin();
            manager.persist(Juli);
            manager.persist(Galo);
            manager.getTransaction().commit();

            imprimirTodo();

    }
    private static void imprimirTodo(){
        List <Encargado> encargados = (List<Encargado>)manager.createQuery("FROM models.Encargado").getResultList();
        System.out.println("Hay "+encargados.size()+ " encargados en el sistema");
        for(Encargado e:encargados){
            System.out.println(e.toString());
        }


    }
}