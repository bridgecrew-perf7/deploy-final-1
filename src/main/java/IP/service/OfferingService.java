package IP.service;

import IP.entity.Needs;
import IP.entity.Offering;
import IP.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import IP.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OfferingService {

    @Autowired
    private OfferingRepo repository = new OfferingRepo();

    public Offering createOffering(String usernameHelper, String nameNeed, int quantity, String details) {

        String usernameToFind;
        String needsToFind;
        Integer idUser = -1;
        Integer idNeed = -1;

        HelperRepo helperRepo = new HelperRepo();
        NeedsRepo needsRepo = new NeedsRepo();


        List<Object[]> users = helperRepo.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) user[0]).getUsername();

            if (usernameToFind.equals(usernameHelper)) {
                idUser = ((User) user[0]).getId();
                break;
            }
        }


        if (idUser != -1) { //exista user-ul (si din verificarile anterior prin crearea unei helper, exista si in helper)

            List<Needs> needs = needsRepo.findAll();

            for (Needs need : needs) {
                needsToFind = need.getName();

                if (needsToFind.equals(nameNeed)) {
                    idNeed = need.getId();
                    break;
                }
            }

            if (idNeed == -1) {
                return null;
            }

            Needs need = needsRepo.findById(idNeed).get(0);

            Offering offering = new Offering();
            offering.setIdHelper(idUser);
            offering.setIdNeeds(idNeed);
            offering.setQuantity(quantity);
            offering.setDetails(details);
            repository.create(offering);
            return offering;
        }
        return null;
    }

    public Boolean deleteOffHelper(String username) {
        String usernameToFind;
        Integer idUser = -1;

        HelperRepo helperRepo = new HelperRepo();

        List<Object[]> users = helperRepo.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) user[0]).getUsername();

            if (usernameToFind.equals(username)) {
                idUser = ((User) user[0]).getId();
                break;
            }
        }

        if (idUser != -1) { //exista user-ul (si din verificarile anterior prin crearea unei helper, exista si in helper)
            repository.removeHelper(idUser);
            return true;
        }
        return false;
    }


    public String typeOfFormH(String username) {

        String usernameToFind;
        Integer idUser = -1;
        UserRepo userRepo = new UserRepo();
        NeedsRepo needsRepo = new NeedsRepo();

        List<User> users = userRepo.findAll();

        for (User user : users) {
            usernameToFind = user.getUsername();

            if (usernameToFind.equals(username)) {
                idUser = user.getId();
                break;
            }
        }

        if (idUser != -1) {
            if (repository.findByIdHelper(idUser).size() > 0) {
                Offering offering = repository.findByIdHelper(idUser).get(0);
                return needsRepo.findById(offering.getIdNeeds()).get(0).getType();
            }
            return null;

        }

        return null;


    }

    public Map<String, Integer> productsNamesByUsernameH(String username) {

        String usernameToFind;
        Integer idUser = -1;

        List<Object[]> users = repository.userInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) user[0]).getUsername();

            if (usernameToFind.equals(username)) {
                idUser = ((User) user[0]).getId();
                break;
            }
        }

        if (idUser != -1) {

            List<Offering> products = repository.productInfoH(idUser);

            Map<String, Integer> namesProd = new HashMap();


            List<Object[]> needs = repository.needsInfo();

            for (Offering product : products) {

                for (Object[] need : needs) {
                    if (((Needs) need[0]).getId() == product.getIdNeeds() && product.getIdHelper() == idUser) {
                        namesProd.put(((Needs) need[0]).getName(), product.getQuantity());
                        break;
                    }

                }
            }

            return namesProd;
        }

        return null;
    }

    public List<String> servicesNamesByUsernameH(String username) {

        String usernameToFind;
        Integer idUser = -1;

        List<Object[]> users = repository.userInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) user[0]).getUsername();

            if (usernameToFind.equals(username)) {
                idUser = ((User) user[0]).getId();
                break;
            }
        }

        if (idUser != -1) {

            List<Offering> services = repository.serviceInfoH(idUser);
            List<String> namesServ = new ArrayList<>();

            List<Object[]> needs = repository.needsInfo();

            for (Offering service : services) {

                for (Object[] need : needs) {
                    if (((Needs) need[0]).getId() == service.getIdNeeds()) {
                        namesServ.add(((Needs) need[0]).getName());
                        break;
                    }

                }
            }

            return namesServ;
        }

        return null;
    }
}
