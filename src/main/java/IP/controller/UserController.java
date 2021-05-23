package IP.controller;

import IP.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private HelperService helperService;
    @Autowired
    private NeederService neederService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private OfferingService offeringService;
    @Autowired
    private MatchingService matchingService;

    @DeleteMapping("/deleteAccount")//@RequestBody
    public ResponseEntity<String> deleteAccount(@RequestBody Response rep) {

       offeringService.deleteOffHelper(rep.getUsername());

      requestService.deleteReqNeeder(rep.getUsername());

      neederService.deleteNeeder(rep.getUsername());


       helperService.deleteHelper(rep.getUsername());

       matchingService.removeHelper(rep.getUsername());

        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
    }
}
