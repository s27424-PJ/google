package org.example.google;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {
    @GetMapping("/user")
    public String greetingsUser(){
        return  "Hello User";
    }
    @GetMapping("/admin")
    public String greetingsAdmin(){
        return  "Hello admin";
    }
}
