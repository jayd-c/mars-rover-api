package com.marsroverapi.showphotos.service;

import com.marsroverapi.showphotos.response.MarsRoverApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarsRoverApiService {

    private static final String  API_KEY = "gsnjNeLcmPeGm0bKGfQHjiEaqUnKQwA5ZigzJKJL";
    public MarsRoverApiResponse getRoverData (String roverType) {
        RestTemplate rt = new RestTemplate();
        String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/" + roverType + "/photos?sol=2&api_key=" + API_KEY;
        ResponseEntity<MarsRoverApiResponse> response = rt.getForEntity(url, MarsRoverApiResponse.class);
        return response.getBody();
    }
}
