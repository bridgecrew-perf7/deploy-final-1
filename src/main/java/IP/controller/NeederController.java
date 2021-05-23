package IP.controller;

import IP.entity.Helper;
import IP.entity.Needer;
import IP.entity.Offering;
import IP.entity.Request;
import IP.service.NeederService;
import IP.service.OfferingService;
import IP.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class NeederController {

    @Autowired
    private NeederService neederService;
    @Autowired
    private RequestService requestService;

    @PostMapping("/requestNeeder")//@RequestBody
    public ResponseEntity<String> newNeederReq(@RequestBody Response rep) {

        //System.out.println(rep.getUsername() + " " + rep.getDistanceAccepted());

        Needer needer = neederService.createNeeder(rep.getUsername());
        if (needer == null) {
            return new ResponseEntity<>("User " + rep.getUsername() + " doesn't exists", HttpStatus.NOT_FOUND);
        }

        for (Map.Entry<String, Integer> entry : rep.getTags().entrySet()) {
            String name = entry.getKey();
            Integer quantity = (Integer) entry.getValue();

            Request req = requestService.createRequest(rep.getUsername(), name, quantity, rep.getDetails());
            if (req == null) {
                return new ResponseEntity<>("User " + rep.getUsername() + " or need " + name + " doesn't exists", HttpStatus.NOT_FOUND);
            }
        }


        return new ResponseEntity<>("Request created", HttpStatus.OK);
    }
}
