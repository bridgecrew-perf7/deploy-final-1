package IP.repository;

import IP.entity.Request;
import org.springframework.stereotype.Repository;
import IP.root.ManagerFactorySingleton;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RequestRepo {

    public void create(Request object) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();
        mag.persist(object);
        mag.getTransaction().commit();
        mag.close();
    }

    public List<Request> findByIdNeeder(int idNeeder) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Request> requests = mag.createNamedQuery("Request.findByIdNeeder").setParameter("idNeeder", idNeeder).getResultList();
        mag.close();

        return requests;

    }

    public List<Request> findByIdNeeds(int idNeeds) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Request> requests = mag.createNamedQuery("Request.findByIdNeeds").setParameter("idNeeds", idNeeds).getResultList();
        mag.close();

        return requests;
    }

    public List<Request> findByIdNeederNeeds(int idNeeder, int idNeeds) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Request> requests = mag.createNamedQuery("Request.findNeedsNeeder").setParameter("idNeeder", idNeeder).setParameter("idNeeds", idNeeds).getResultList();
        mag.close();

        return requests;

    }

    public List<Request> findAll() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Request> requests = mag.createNamedQuery("Request.findAll").getResultList();
        mag.close();

        return requests;
    }

    public List<Object[]> neederInfo() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Object[]> needers = mag.createNamedQuery("Request.joinNeeder").getResultList();

        mag.close();
        return needers;
    }

    public List<Object[]> needsInfo() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Object[]> needs = mag.createNamedQuery("Request.joinNeeds").getResultList();

        mag.close();
        return needs;
    }

    public List<Object[]> userInfo() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Object[]> users = mag.createNamedQuery("Request.joinNeederUser").getResultList();

        mag.close();
        return users;
    }

    public List<Request> productInfo(int idNeeder) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Request> users = mag.createNamedQuery("Request.findProductInfo").setParameter("idNeeder", idNeeder).getResultList();

        mag.close();
        return users;
    }

    public List<Request> serviceInfo(int idNeeder) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Request> users = mag.createNamedQuery("Request.findServiceInfo").setParameter("idNeeder", idNeeder).getResultList();

        mag.close();
        return users;
    }

    public List<Integer> countUnresolved() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Integer> list = mag.createNamedQuery("Request.countUnresolved").getResultList();

        mag.close();
        return list;
    }


    public void removeNeeder(int idNeeder) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();

        mag.createNamedQuery("Request.removeNeeder").setParameter("idNeeder", idNeeder).executeUpdate();

        mag.getTransaction().commit();
        mag.close();
    }


}
