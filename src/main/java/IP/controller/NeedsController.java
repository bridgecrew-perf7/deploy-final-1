package IP.controller;

import IP.entity.Needs;
import IP.repository.NeedsRepo;
import javassist.compiler.ast.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class NeedsController {

    @GetMapping(value = "/needs", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Map<String,String>> NeedsNames() {

        Map<String,String> map =  new HashMap();

        NeedsRepo needsRepo = new NeedsRepo();

        List<Needs> needs = needsRepo.findAll();

        for (Needs need : needs) {
            map.put(need.getName(), need.getType());
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
