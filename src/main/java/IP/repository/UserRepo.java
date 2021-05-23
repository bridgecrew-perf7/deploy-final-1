package IP.repository;

import IP.entity.User;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import IP.root.ManagerFactorySingleton;

import java.sql.Time;
import java.util.List;

@Repository
public class UserRepo {

    public List<User> findAll() {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<User> users = mag.createNamedQuery("User.findAll").getResultList();
        mag.close();

        return users;
    }

    public List<User> findById(int id) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<User> users = mag.createNamedQuery("User.findById").setParameter("id", id).getResultList();
        mag.close();

        return users;

    }

    public List<User> findByUsername(String username) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<User> users = mag.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
        mag.close();

        return users;

    }

    public List<User> findByAddress(String address) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<User> users = mag.createNamedQuery("User.findByAddress").setParameter("address", address).getResultList();
        mag.close();

        return users;

    }


    public List<User> findByStartHour(Time startHour) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<User> users = mag.createNamedQuery("User.findByStartHour").setParameter("startHour", startHour).getResultList();
        mag.close();

        return users;

    }

    public List<User> findByFinalHour(Time finalHour) {
        EntityManager mag = ManagerFactorySingleton.getFactory().createEntityManager();
        List<User> users = mag.createNamedQuery("User.findByFinalHour").setParameter("finalHour", finalHour).getResultList();
        mag.close();

        return users;

    }

}



