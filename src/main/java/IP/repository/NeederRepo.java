package IP.repository;

import IP.entity.Needer;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import IP.root.ManagerFactorySingleton;

import java.util.List;

@Repository
public class NeederRepo {

    public void create(Needer object) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();
        mag.persist(object);
        mag.getTransaction().commit();
        mag.close();
    }

    public List<Needer> findAll() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Needer> needers = mag.createNamedQuery("Needer.findAll").getResultList();
        mag.close();

        return needers;
    }


    public List<Object[]> findAllInfo() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Object[]> users = mag.createNamedQuery("Needer.findNeeders").getResultList();

        mag.close();

        return users;
    }


    public List<Needer> findById(int idUser) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Needer> needers = mag.createNamedQuery("Needer.findById").setParameter("idUser", idUser).getResultList();
        mag.close();

        return needers;
    }


    public void remove(int idUser) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();

        Needer n = (Needer) mag.createNamedQuery("Needer.findById").setParameter("idUser", idUser).getResultList().get(0);

        mag.createNamedQuery("Request.removeNeeder").setParameter("idNeeder", idUser).executeUpdate();
        mag.remove(n);

        mag.getTransaction().commit();
        mag.close();
    }
}
