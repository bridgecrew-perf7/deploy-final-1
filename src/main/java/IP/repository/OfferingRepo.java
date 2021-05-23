package IP.repository;

import IP.entity.Offering;
import IP.entity.Request;
import org.springframework.stereotype.Repository;
import IP.root.ManagerFactorySingleton;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OfferingRepo {

    public void create(Offering object) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();
        mag.persist(object);
        mag.getTransaction().commit();
        mag.close();
    }

    public List<Request> findAll() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Request> offerings = mag.createNamedQuery("Offering.findAll").getResultList();
        mag.close();

        return offerings;

    }

    public List<Offering> findByIdHelper(int idHelper) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Offering> offerings = mag.createNamedQuery("Offering.findByIdHelper").setParameter("idHelper", idHelper).getResultList();
        mag.close();

        return offerings;

    }

    public List<Offering> findByIdNeeds(int idNeeds) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Offering> offerings = mag.createNamedQuery("Offering.findByIdNeeds").setParameter("idNeeds", idNeeds).getResultList();
        mag.close();

        return offerings;

    }


    public List<Object[]> helperInfo() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Object[]> helpers = mag.createNamedQuery("Offering.joinHelper").getResultList();

        mag.close();
        return helpers;
    }

    public List<Object[]> needsInfo() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Object[]> needs = mag.createNamedQuery("Offering.joinNeeds").getResultList();

        mag.close();
        return needs;
    }

    public List<Object[]> userInfo() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Object[]> users = mag.createNamedQuery("Offering.joinHelperUser").getResultList();

        mag.close();
        return users;
    }


    public void removeHelper(int idHelper) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();

        mag.createNamedQuery("Offering.removeHelper").setParameter("idHelper", idHelper).executeUpdate();

        mag.getTransaction().commit();
        mag.close();

    }

    public int sumQuantity(int idHelper) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        int sum = ((Number)mag.createNamedQuery("Offering.sumQuantity").setParameter("idHelper", idHelper).getSingleResult()).intValue();

        mag.close();
        return sum;
    }

    public List<Offering> productInfoH(int idHelper) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Offering> users = mag.createNamedQuery("Offering.findProductInfo").setParameter("idHelper", idHelper).getResultList();

        mag.close();
        return users;
    }

    public List<Offering> serviceInfoH(int idHelper) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Offering> users = mag.createNamedQuery("Offering.findServiceInfo").setParameter("idHelper", idHelper).getResultList();

        mag.close();
        return users;
    }



}
