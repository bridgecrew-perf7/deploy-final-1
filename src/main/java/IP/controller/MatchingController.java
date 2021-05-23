package IP.controller;

import IP.entity.Helper;
import IP.entity.Matching;
import IP.entity.Offering;
import IP.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MatchingController {

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


    @PostMapping("/chosenHelper")//@RequestBody
    public ResponseEntity<String> createMatching(@RequestBody Response rep) {

        Matching m = matchingService.createMatching(rep.getUsernameNeeder(), rep.getUsernameHelper());
        if (m == null) {
            return new ResponseEntity<>("Something is wrong!", HttpStatus.NOT_FOUND);
        }

        Boolean verif = helperService.modifyAvailable(rep.getUsernameHelper(), 0);

        if (!verif) {
            return new ResponseEntity<>("Something is wrong!", HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>("Matching created", HttpStatus.OK);
    }

    @GetMapping(value = "/unmatchedNeedersAvailableHelpers", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Map<String, Integer>> countAvailableAndUnmatched() {


        Map<String, Integer> map = new HashMap<>();

        map.put("countUnmatched", requestService.countUnresolved());
        map.put("countAvailable", helperService.countAvailable());


        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping(value = "/statisticForUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Map<String, Integer>> statistic(@RequestBody Response rep) {


        Map<String, Integer> map = new HashMap<>();

        map.put("countHelper", matchingService.statisticsUser(rep.getUsername())[1]);
        map.put("countNeeder", matchingService.statisticsUser(rep.getUsername())[0]);


        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping(value = "/neederToHelp")
    public @ResponseBody
    ResponseEntity<?> helpThisNeeder(@RequestBody Response rep) {

        Map<String, Integer> map = new HashMap<>();
        ResponseForHelper response = new ResponseForHelper();
        String neederToHelp = matchingService.neederToHelp(rep.getUsername());
        if (neederToHelp == null) {
            return new ResponseEntity<>("Something is wrong!", HttpStatus.NOT_FOUND);
        }
        System.out.println(neederToHelp);
        String details;

        List<String> list = requestService.servicesNamesByUsername(neederToHelp);

        if (requestService.typeOfForm(neederToHelp).equals("service")) {
            for (int i = 0; i < list.size(); i++)
                map.put(list.get(i), -1);
        }

        if (requestService.typeOfForm(neederToHelp).equals("product"))
            map = requestService.productsNamesByUsername(neederToHelp);

        details = requestService.getDetailsofForm(neederToHelp);


        response.setUsername(neederToHelp);
        response.setTags(map);
        response.setDetails(details);

        offeringService.deleteOffHelper(rep.getUsername());

        requestService.deleteReqNeeder(neederToHelp);

        neederService.deleteNeeder(neederToHelp);

        helperService.deleteHelper(rep.getUsername());

        return new ResponseEntity<ResponseForHelper>(response, HttpStatus.OK);
    }

}
