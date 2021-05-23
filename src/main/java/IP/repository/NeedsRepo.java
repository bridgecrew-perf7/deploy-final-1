package IP.repository;

import IP.entity.Needs;
import org.springframework.stereotype.Repository;
import IP.root.ManagerFactorySingleton;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class NeedsRepo {

    public List<Needs> findAll() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Needs> needs = mag.createNamedQuery("Needs.findAll").getResultList();
        mag.close();

        return needs;
    }

    public List<Needs> findById(int id) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Needs> needs = mag.createNamedQuery("Needs.findById").setParameter("id", id).getResultList();
        mag.close();

        return needs;
    }

    public List<Needs> findByName(String name) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Needs> needs = mag.createNamedQuery("Needs.findByName").setParameter("name", name).getResultList();
        mag.close();

        return needs;
    }

    public List<Needs> findByType(String type) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Needs> Needs = mag.createNamedQuery("Needs.findByType").setParameter("type", type).getResultList();
        mag.close();

        return Needs;
    }

}
