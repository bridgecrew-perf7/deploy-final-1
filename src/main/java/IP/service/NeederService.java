package IP.service;

import IP.entity.Needer;
import IP.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import IP.repository.NeederRepo;
import IP.repository.UserRepo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class NeederService {

    @Autowired
    private NeederRepo repository = new NeederRepo();


    public List<Needer> allNeeders() {
        List<Needer> allNeeders = repository.findAll();

        if (allNeeders.size() == 0) {
            return new ArrayList<>();
        } else {
            return allNeeders;
        }
    }


    public Needer createNeeder(String username) {

        String usernameToFind;
        Integer idUser = -1;
        UserRepo userRepo = new UserRepo();

        List<User> users = userRepo.findAll();

        for (User user : users) {
            usernameToFind = user.getUsername();

            if (usernameToFind.equals(username)) {
                idUser = user.getId();
                break;
            }
        }
        if (idUser != -1) {

            if (repository.findById(idUser).size() == 1) {
                repository.remove(idUser);
            }
        }

        //daca cream un needer care nu-i user
        if (idUser == -1) {
            return null;
        }

        Needer createNeeder = new Needer(idUser);

        repository.create(createNeeder);

        return createNeeder;
    }


    public boolean deleteNeeder(String usernameNeeder) {

        String usernameToFind;
        Integer idUser = -1;

        List<Object[]> users = repository.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) (user[0])).getUsername();

            if (usernameToFind.equals(usernameNeeder)) {
                idUser = ((User) (user[0])).getId();
                break;
            }
        }
        if (idUser != -1) {

            if (repository.findById(idUser).size() == 1) {
                repository.remove(idUser);
                return true;
            }
            return false;
        }
        return false;

    }

    public String findAdress(String usernameNeeder) {

        String adress;

        String usernameToFind;
        Integer idUser = -1;
        UserRepo userRepo = new UserRepo();
        User u;

        List<Object[]> users = repository.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) (user[0])).getUsername();

            if (usernameToFind.equals(usernameNeeder)) {
                idUser = ((User) (user[0])).getId();
                break;
            }
        }

        if (idUser != -1) {

            u = userRepo.findById(idUser).get(0);
            adress = u.getAddress();
            return adress;

        }

        return null;

    }

    public Time[] findInterval(String usernameNeeder) {

        Time[] interval = new Time[2];

        String usernameToFind;
        Integer idUser = -1;
        UserRepo userRepo = new UserRepo();
        User u;

        List<Object[]> users = repository.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) (user[0])).getUsername();

            if (usernameToFind.equals(usernameNeeder)) {
                idUser = ((User) (user[0])).getId();
                break;
            }
        }

        if (idUser != -1) {

            u = userRepo.findById(idUser).get(0);
            interval[0] = u.getStartHour();
            interval[1] = u.getFinalHour();

            return interval;

        }

        return null;

    }

}
