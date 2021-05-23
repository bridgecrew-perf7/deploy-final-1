package IP.repository;

import IP.entity.Matching;

import org.springframework.stereotype.Repository;
import IP.root.ManagerFactorySingleton;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MatchingRepo {

    public void create(Matching object) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();
        mag.persist(object);
        mag.getTransaction().commit();
        mag.close();
    }

    public List<Matching> findAll() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Matching> matchings = mag.createNamedQuery("Matching.findAll").getResultList();
        mag.close();


        return matchings;

    }

    public List<Matching> findByNeeder(String username) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Matching> matchings = mag.createNamedQuery("Matching.findByUsernameNeeder").setParameter("usernameNeeder", username).getResultList();
        mag.close();

        return matchings;

    }

    public List<Matching> findByHelper(String username) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Matching> matchings = mag.createNamedQuery("Matching.findByUsernameHelper").setParameter("usernameHelper", username).getResultList();
        mag.close();

        return matchings;
    }

    public List<Matching> findByDate(String date) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Matching> matchings = mag.createNamedQuery("Matching.findByDate").setParameter("dateMatching", date).getResultList();
        mag.close();

        return matchings;
    }


    public void removeUsername(String usernameHelper) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();

        Matching m = (Matching) mag.createNamedQuery("Matching.findByUsernameHelper").setParameter("usernameHelper", usernameHelper).getResultList().get(0);

        mag.remove(m);

        mag.getTransaction().commit();
        mag.close();

    }

    public void removeDate(String dateMatching) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();

        Matching m = (Matching) mag.createNamedQuery("Matching.findByDate").setParameter("dateMatching", dateMatching).getResultList().get(0);
        mag.remove(m);

        mag.getTransaction().commit();
        mag.close();

    }


}

