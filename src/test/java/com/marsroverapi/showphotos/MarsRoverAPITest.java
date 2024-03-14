package com.marsroverapi.showphotos;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MarsRoverAPITest {
    @Test
    public void smallTes() {
        RestTemplate rt = new RestTemplate();
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";
        ResponseEntity<MarsRoverApiResponse> response = rt.getForEntity(url, MarsRoverApiResponse.class);
        System.out.println(response.getBody());
    }
}

