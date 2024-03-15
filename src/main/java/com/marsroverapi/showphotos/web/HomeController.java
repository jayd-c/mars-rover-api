package com.marsroverapi.showphotos.web;

import com.marsroverapi.showphotos.response.MarsRoverApiResponse;
import com.marsroverapi.showphotos.service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private MarsRoverApiService roverService;
    @GetMapping("/")
    public String getHomeView (ModelMap model) {
        MarsRoverApiResponse roverData = roverService.getRoverData("opportunity");
        model.put("roverData",roverData);
        return "index";
    }

}
