package IP.service;

import IP.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import IP.repository.NeederRepo;
import IP.repository.NeedsRepo;
import IP.repository.RequestRepo;
import IP.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {

    @Autowired
    private RequestRepo repository = new RequestRepo();

    public String typeOfForm(String username) {

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
            if (repository.findByIdNeeder(idUser).size() > 0) {
                Request request = repository.findByIdNeeder(idUser).get(0);
                return needsRepo.findById(request.getIdNeeds()).get(0).getType();
            }
            return null;

        }

        return null;


    }

    public String getDetailsofForm(String username) {

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
            Request request = repository.findByIdNeeder(idUser).get(0);
            return request.getDetails();

        }

        return null;


    }

    public Map<String, Integer> productsNamesByUsername(String username) {

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

        if (idUser != -1) { //exista user-ul (si din verificarile anterior prin crearea unei needer, exista si in needer)

            List<Request> products = repository.productInfo(idUser);
            Map<String, Integer> namesProd = new HashMap();


            List<Object[]> needs = repository.needsInfo();

            for (Request product : products) {

                for (Object[] need : needs) {
                    if (((Needs) need[0]).getId() == product.getIdNeeds() && product.getIdNeeder() == idUser) {
                        namesProd.put(((Needs) need[0]).getName(), product.getQuantity());
                        break;
                    }

                }
            }
            return namesProd;


        }

        return null;
    }

    public List<String> servicesNamesByUsername(String username) {

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

        if (idUser != -1) { //exista user-ul (si din verificarile anterior prin crearea unei needer, exista si in needer)

            List<Request> services = repository.serviceInfo(idUser);
            List<String> namesServ = new ArrayList<>();


            List<Object[]> needs = repository.needsInfo();

            for (Request service : services) {

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

    public int countUnresolved() {
        List<Integer> ids = repository.countUnresolved();
        return ids.size();
    }

    public Request createRequest(String usernameNeeder, String nameNeed, int quantity, String details) {

        String usernameToFind;
        String needsToFind;
        Integer idUser = -1;
        Integer idNeed = -1;

        NeederRepo neederRepo = new NeederRepo();
        NeedsRepo needsRepo = new NeedsRepo();


        List<Object[]> users = neederRepo.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) user[0]).getUsername();

            if (usernameToFind.equals(usernameNeeder)) {
                idUser = ((User) user[0]).getId();
                break;
            }
        }


        if (idUser != -1) { //exista user-ul (si din verificarile anterior prin crearea unei needer, exista si in needer)

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

            Request req = new Request();
            req.setIdNeeder(idUser);
            req.setIdNeeds(idNeed);
            req.setQuantity(quantity);
            req.setDetails(details);
            repository.create(req);
            return req;
        }
        return null;
    }


    public Boolean deleteReqNeeder(String username) {
        String usernameToFind;
        Integer idUser = -1;

        NeederRepo neederRepo = new NeederRepo();

        List<Object[]> users = neederRepo.findAllInfo();

        for (Object[] user : users) {
            usernameToFind = ((User) user[0]).getUsername();

            if (usernameToFind.equals(username)) {
                idUser = ((User) user[0]).getId();
                break;
            }
        }

        if (idUser != -1) { //exista user-ul (si din verificarile anterior prin crearea unei needer, exista si in needer)
            repository.removeNeeder(idUser);
            return true;
        }
        return false;
    }


}
