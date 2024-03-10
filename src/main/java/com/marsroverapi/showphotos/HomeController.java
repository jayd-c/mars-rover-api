package com.marsroverapi.showphotos;

import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home (ModelMap model) {
        model.put("name","Jayan Desilva");
        model.put("address","12124 Shadow Street");
        model.put("email","jesusKappiya@gmail.com");
        model.put("id","100");
        return "index";
    }

    @GetMapping("/testing")
    public String testing (ModelMap model) {
        model.put("id","100");
        return "test";
    }
}
