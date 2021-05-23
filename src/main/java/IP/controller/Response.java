package IP.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter

public class Response {
    private String username = null;
    private Integer distanceAccepted  = null;
    private Map<String, Integer> tags = null;
    private String details  = null;
    private Integer available  = null;
    private String usernameNeeder  = null;
    private String usernameHelper  = null;

}