package com.DomDevs.app.rest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiControllers {
    @GetMapping("/")
    public String getPage() {
        return "Hello World";
    }
}
