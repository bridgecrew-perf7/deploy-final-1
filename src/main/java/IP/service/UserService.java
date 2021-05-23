package IP.service;

import IP.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import IP.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repository = new UserRepo();


    public List<User> allUsers() {
        List<User> allUsers = repository.findAll();

        if (allUsers.size() == 0) {
            return new ArrayList<>();
        } else {
            return allUsers;
        }
    }




}
