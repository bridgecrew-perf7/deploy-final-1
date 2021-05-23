package IP.service;

import IP.entity.Matching;
import IP.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import IP.repository.HelperRepo;
import IP.repository.MatchingRepo;
import IP.repository.NeederRepo;
import IP.repository.UserRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private MatchingRepo repository = new MatchingRepo();

    public Matching createMatching(String usernameNeeder, String usernameHelper) {
        String neederToFind, helperToFind = null;
        Integer idNeeder = -1, idHelper = -1;
        int ID;
        UserRepo userRepo = new UserRepo();
        NeederRepo neederRepo = new NeederRepo();
        HelperRepo helperRepo = new HelperRepo();
        Matching matching = new Matching();

        List<User> users = userRepo.findAll();

        for (User user : users) {
            neederToFind = user.getUsername();
            helperToFind = user.getUsername();

            if (neederToFind.equals(usernameNeeder)) {
                idNeeder = user.getId();
            }

            if (helperToFind.equals(usernameHelper)) {
                idHelper = user.getId();
            }

            if (idNeeder != -1 && idHelper != -1)
                break;
        }

        if (neederRepo.findById(idNeeder).size() == 1) {
            if (helperRepo.findById(idHelper).size() == 1) {
                List<Matching> mlist = repository.findAll();
                Date date = new Date();
                int idMax = -1;

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateMatching = formatter.format(date);

                for (int i = 0; i < mlist.size(); i++) {
                    if (idMax < mlist.get(i).getID())
                        idMax = mlist.get(i).getID();
                }

                matching.setID(idMax + 1);
                matching.setUsernameNeeder(usernameNeeder);
                matching.setUsernameHelper(usernameHelper);
                matching.setDateMatching(dateMatching);

                repository.create(matching);

                return matching;

            }
            return null;
        }
        return null;

    }

    public String neederToHelp(String usernameHelper) {
        List<Matching> matchings = repository.findAll();

        for (int i = matchings.size() - 1; i >= 0; i--) {
            if (matchings.get(i).getUsernameHelper().equals(usernameHelper))
                return matchings.get(i).getUsernameNeeder();

        }
        return null;
    }

    public int[] statisticsUser(String username) {
        int[] statistics = new int[2];

        List<Matching> matchings = repository.findAll();

        for (int i = 0; i < matchings.size(); i++) {
            //System.out.println(matchings.get(i).getID());
            if (username.equals(matchings.get(i).getUsernameNeeder()))
                statistics[0]++;

            if (username.equals(matchings.get(i).getUsernameHelper()))
                statistics[1]++;
        }

        return statistics;
    }

    public boolean removeHelper(String usernameHelper) {

        String usernameToFind;
        int idUser = -1;
        HelperRepo helperRepo = new HelperRepo();
        List<Object[]> users = helperRepo.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) (user[0])).getUsername();

            if (usernameToFind.equals(usernameHelper)) {
                idUser = ((User) (user[0])).getId();
                break;
            }
        }

        if (idUser != -1) {
            repository.removeUsername(usernameHelper);
            return true;

        }
        return false;
    }


}
