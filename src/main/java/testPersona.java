import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class testPersona {
    private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args){
        emf = Persistence.createEntityManagerFactory("Persistencia");
        manager = emf.createEntityManager();

        List <Persona> personas = (List<Persona>)manager.createQuery("FROM Persona").getResultList();
        System.out.println("Hay "+personas.size()+ " empleados en el sistema");

            Persona p = new Persona(41525,"Julian","Wagner","urquiza y Fal",225845);
            Persona galopia = new Persona(415252,"Galo","Pianciola","Alsina y Colon",252525);

            manager.getTransaction().begin();
            manager.persist(p);
            manager.persist(galopia);
            manager.getTransaction().commit();

            imprimirTodo();

    }
    private static void imprimirTodo(){
        List <Persona> personas = (List<Persona>)manager.createQuery("FROM Persona").getResultList();
        System.out.println("Hay "+personas.size()+ " empleados en el sistema");
        for(Persona p:personas){
            System.out.println(p.toString());
        }


    }
}