package IP.controller;


import IP.entity.Helper;
import IP.entity.Needs;
import IP.entity.Offering;
import IP.repository.NeedsRepo;
import IP.service.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import IP.service.HelperService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HelperOfferingController {

    @Autowired
    private HelperService helpService;
    @Autowired
    private OfferingService offService;

    @PostMapping("/offeringHelper")//@RequestBody
    public ResponseEntity<String> newHelperOff(@RequestBody Response rep) {

        //System.out.println(rep.getUsername() + " " + rep.getDistanceAccepted());

        Helper helper = helpService.createHelper(rep.getUsername(), rep.getDistanceAccepted());
        if (helper == null) {
            return new ResponseEntity<>("User " + rep.getUsername() + " doesn't exists", HttpStatus.NOT_FOUND);
        }

        for (Map.Entry<String, Integer> entry : rep.getTags().entrySet()) {
            String name = entry.getKey();
            Integer quantity = (Integer) entry.getValue();

            Offering off = offService.createOffering(rep.getUsername(), name, quantity, rep.getDetails());
            if (off == null) {
                return new ResponseEntity<>("User " + rep.getUsername() + " or need " + name + " doesn't exists", HttpStatus.NOT_FOUND);
            }
        }


        return new ResponseEntity<>("Offering created", HttpStatus.OK);
    }


    @PostMapping(value = "/modifyAvailable")
    public ResponseEntity<String> modifyAva(@RequestBody Response rep) {

        Boolean verif = helpService.modifyAvailable(rep.getUsername(), rep.getAvailable());
        if (verif == false) {
            return new ResponseEntity<>("User " + rep.getUsername() + " doesn't exists", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Available field modified", HttpStatus.OK);
    }


}
