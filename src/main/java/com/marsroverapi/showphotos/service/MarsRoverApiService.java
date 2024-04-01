package com.marsroverapi.showphotos.service;

import com.marsroverapi.showphotos.dto.HomeDto;
import com.marsroverapi.showphotos.repository.PreferencesRepository;
import com.marsroverapi.showphotos.response.MarsPhoto;
import com.marsroverapi.showphotos.response.MarsRoverApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class MarsRoverApiService {

    private static final String  API_KEY = "gsnjNeLcmPeGm0bKGfQHjiEaqUnKQwA5ZigzJKJL";
    private final Map<String, List<String>> validCameras = new HashMap<>();
    @Autowired
    private PreferencesRepository preferencesRepo;


    public MarsRoverApiService () {
        validCameras.put("Opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
        validCameras.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"));
        validCameras.put("Spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
    }

    public MarsRoverApiResponse getRoverData(HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        RestTemplate rt = new RestTemplate();

        List<String> apiUrlEnpoints = getApiUrlEnpoints(homeDto);
        List<MarsPhoto> photos = new ArrayList<>();
        MarsRoverApiResponse response = new MarsRoverApiResponse();

        apiUrlEnpoints
                .forEach(url -> {
                    MarsRoverApiResponse apiResponse = rt.getForObject(url, MarsRoverApiResponse.class);
                    assert apiResponse != null;
                    photos.addAll(apiResponse.getPhotos());
                });

        response.setPhotos(photos);

        return response;
    }

    public List<String> getApiUrlEnpoints (HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String> urls = new ArrayList<>();

        Method[] methods = homeDto.getClass().getMethods();

        // This code will grab all getCamera* methods and (if value returns true) then we will build a API URL to
        //  call in order to fetch pictures for a given rover / camera / sol.
        for (Method method : methods) {
            if (method.getName().indexOf("getCamera") > -1 && Boolean.TRUE.equals(method.invoke(homeDto))) {
                String cameraName = method.getName().split("getCamera")[1].toUpperCase();
                System.out.println("Camera name = " + cameraName);
                if (validCameras.get(homeDto.getMarsApiRoverData()).contains(cameraName)) {
                    urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=" + cameraName);
                }
            }
        }

        return urls;
    }

    public Map<String, List<String>> getValidCameras() {
        return validCameras;
    }

    public void save(HomeDto homeDto) {
        preferencesRepo.save(homeDto);
    }
}


