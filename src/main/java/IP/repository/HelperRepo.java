package IP.repository;

import IP.entity.Helper;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import IP.root.ManagerFactorySingleton;

import java.util.List;

@Repository
public class HelperRepo {

    public void create(Helper object) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();
        mag.persist(object);
        mag.getTransaction().commit();
        mag.close();
    }

    public List<Helper> findAll() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Helper> helpers = mag.createNamedQuery("Helper.findAll").getResultList();
        mag.close();

        return helpers;
    }

    public List<Object[]> findAllInfo() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        List<Object[]> users = mag.createNamedQuery("Helper.findHelpers").getResultList();

        mag.close();
        return users;
    }

    public List<Helper> findById(int idUser) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Helper> helpers = mag.createNamedQuery("Helper.findById").setParameter("idUser", idUser).getResultList();
        mag.close();

        return helpers;
    }

    public List<Helper> findByDistance(int distanceAccepted) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Helper> helpers = mag.createNamedQuery("Helper.findByDistance").setParameter("distanceAccepted", distanceAccepted).getResultList();
        mag.close();

        return helpers;
    }

    public List<Helper> findByIsAvailable(boolean isAvailable) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<Helper> helpers = mag.createNamedQuery("Helper.findByAvailable").setParameter("isAvailable", isAvailable).getResultList();
        mag.close();

        return helpers;

    }


    public void updateDistance(int distanceAccepted, int idUser) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();

        mag.createNamedQuery("Helper.updateDistance").setParameter("distanceAccepted", distanceAccepted).setParameter("idUser", idUser).executeUpdate();

        Helper helper = findById(idUser).get(0);
        helper.setDistanceAccepted(distanceAccepted);
        mag.getTransaction().commit();
        mag.close();
    }

    public void updateAvailable(int idUser, int isAvailable) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();

        mag.createNamedQuery("Helper.updateIsAvailable").setParameter("isAvailable", isAvailable).setParameter("idUser", idUser).executeUpdate();

        Helper helper = findById(idUser).get(0);
        helper.setIsAvailable(isAvailable);
        mag.getTransaction().commit();
        mag.close();
    }


    public void remove(int idUser) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        mag.getTransaction().begin();

        Helper h = (Helper) mag.createNamedQuery("Helper.findById").setParameter("idUser", idUser).getResultList().get(0);

        mag.createNamedQuery("Offering.removeHelper").setParameter("idHelper", idUser).executeUpdate();
        mag.remove(h);

        mag.getTransaction().commit();
        mag.close();

    }

    public int countAvailable() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();

        int sum = ((Number)mag.createNamedQuery("Helper.countAvailable").getSingleResult()).intValue();

        mag.close();
        return sum;
    }

}
