package IP.service;

import IP.entity.Helper;
import IP.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import IP.repository.HelperRepo;
import IP.repository.UserRepo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class HelperService {

    @Autowired
    private HelperRepo repository = new HelperRepo();

    public List<Helper> allHelpers() {
        List<Helper> allHelpers = repository.findAll();

        if (allHelpers.size() == 0) {
            return new ArrayList<>();
        } else {
            return allHelpers;
        }
    }

    public Helper createHelper(String username, Integer distanceAccepted) {

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

        //daca cream un helper care nu-i user
        if (idUser == -1) {
            return null;
        }

        Helper createHelper = new Helper(idUser, distanceAccepted);
        createHelper.setIsAvailable(1);
        //System.out.println(idUser);
        repository.create(createHelper);

        return createHelper;
    }

    public boolean deleteHelper(String usernameHelper) {

        String usernameToFind;
        Integer idUser = -1;
        User usersetType = new User();
        UserRepo userRepo = new UserRepo();

        List<Helper> helpers = repository.findAll();

        List<Object[]> users = repository.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) (user[0])).getUsername();

            if (usernameToFind.equals(usernameHelper)) {
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

    public Boolean modifyAvailable(String username, int available) {
        String usernameToFind;
        Integer idUser = -1;

        List<Helper> helpers = repository.findAll();

        List<Object[]> users = repository.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) (user[0])).getUsername();

            if (usernameToFind.equals(username)) {
                idUser = ((User) (user[0])).getId();
                break;
            }
        }
        if (idUser != -1) {

            if (repository.findById(idUser).size() == 1) {
                repository.updateAvailable(idUser, available);
                return true;
            }
            return false;
        }
        return false;
    }

    public int findDistance(String username) {
        String usernameToFind;
        Integer idUser = -1;
        Helper helper = new Helper();

        List<Object[]> users = repository.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) (user[0])).getUsername();

            if (usernameToFind.equals(username)) {
                idUser = ((User) (user[0])).getId();
                break;
            }
        }

        if (idUser != -1) {

            helper = repository.findById(idUser).get(0);

            return helper.getDistanceAccepted();

        }

        return 0;

    }

    public String findHelperUsername(int idUser) {

        String usernameToFind = null;
        int id;
        List<Object[]> users = repository.findAllInfo();

        for (Object[] user : users) {
            id = ((User) (user[0])).getId();

            if (id == idUser) {
                usernameToFind = ((User) (user[0])).getUsername();
                break;
            }
        }

        if (usernameToFind != null) {

            return usernameToFind;

        }

        return null;

    }

    public String findAdressH(String usernameHelper) {

        String adress;

        String usernameToFind;
        Integer idUser = -1;
        UserRepo userRepo = new UserRepo();
        User u;

        List<Object[]> users = repository.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) (user[0])).getUsername();

            if (usernameToFind.equals(usernameHelper)) {
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

    public Time[] findIntervalH(String usernameHelper) {

        Time[] interval = new Time[2];

        String usernameToFind;
        Integer idUser = -1;
        UserRepo userRepo = new UserRepo();
        User u;

        List<Object[]> users = repository.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) (user[0])).getUsername();

            if (usernameToFind.equals(usernameHelper)) {
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

    public int countAvailable() {
        return repository.countAvailable();
    }


}
