package IP.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter

public class ResponseForHelper {
    private String username = null;
    private Map<String, Integer> tags = null;
    private String details  = null;

}
