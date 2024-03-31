package com.marsroverapi.showphotos.web;

import com.marsroverapi.showphotos.dto.HomeDto;
import com.marsroverapi.showphotos.response.MarsRoverApiResponse;
import com.marsroverapi.showphotos.service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import java.lang.reflect.InvocationTargetException;



@Controller
public class HomeController {
    @Autowired
    private MarsRoverApiService roverService;
    @GetMapping("/")
    public String getHomeView (ModelMap model, HomeDto homeDto)
            throws InvocationTargetException,IllegalArgumentException, IllegalAccessException {
        if(StringUtils.isEmpty(homeDto.getMarsApiRoverData())) {
            homeDto.setMarsApiRoverData("Opportunity");
        }
        if(homeDto.getMarsSol() == null) {
            homeDto.setMarsSol(1);
        }
        MarsRoverApiResponse roverData = roverService.getRoverData(homeDto);
        model.put("roverData",roverData);
        model.put("homeDto",homeDto);
        model.put("validCameras",roverService.getValidCameras());
        return "index";
    }

    @PostMapping("/")
    public String postHomeView (HomeDto homeDto) {
        System.out.println(homeDto);
        return "redirect:/";
    }

}
