package com.marsroverapi.showphotos.web;

import com.marsroverapi.showphotos.response.MarsRover;
import com.marsroverapi.showphotos.response.MarsRoverApiResponse;
import com.marsroverapi.showphotos.service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.thymeleaf.util.StringUtils;

import java.rmi.ServerException;
import java.util.Objects;


@Controller
public class HomeController {
    @Autowired
    private MarsRoverApiService roverService;
    @GetMapping("/")
    public String getHomeView (ModelMap model,  @RequestParam(required = false) String marsApiRoverData,
                               @RequestParam(required = false) Integer marsSol) {
        if(StringUtils.isEmptyOrWhitespace(marsApiRoverData)) {
            marsApiRoverData = "opportunity";
        }
        if(marsApiRoverData.isEmpty() || marsApiRoverData.isBlank()) {
            marsApiRoverData = "opportunity";
        }
        if(marsSol == null) {
            marsSol = 1;
        }
        MarsRoverApiResponse roverData = roverService.getRoverData(marsApiRoverData,marsSol);
        model.put("roverData",roverData);
        return "index";
    }



}
