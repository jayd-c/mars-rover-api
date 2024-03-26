package com.marsroverapi.showphotos.service;

import com.marsroverapi.showphotos.dto.HomeDto;
import com.marsroverapi.showphotos.response.MarsPhoto;
import com.marsroverapi.showphotos.response.MarsRoverApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarsRoverApiService {

    private static final String  API_KEY = "gsnjNeLcmPeGm0bKGfQHjiEaqUnKQwA5ZigzJKJL";

    private Map<String,List<String>> validCameras = new HashMap<>();

    public MarsRoverApiService() {
        validCameras.put("Curiosity".toLowerCase(),List.of("FHAZ","RHAZ","MAST","CHEMCAM","MAHLI","MARDI","NAVCAM"));
        validCameras.put("Opportunity".toLowerCase(),List.of("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
        validCameras.put("Spirit".toLowerCase(),List.of("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
    }

    public MarsRoverApiResponse getRoverData (HomeDto homeDto) throws InvocationTargetException, IllegalAccessException {
        RestTemplate rt = new RestTemplate();

        List<String> apiUrlEndpoints = getApiUrlEndpoints(homeDto);
        List<MarsPhoto> photos = new ArrayList<>();

        MarsRoverApiResponse response = new MarsRoverApiResponse();

        apiUrlEndpoints.forEach(url -> {
            MarsRoverApiResponse apiResponse = rt.getForObject(url,MarsRoverApiResponse.class);
            assert apiResponse != null;
            photos.addAll(apiResponse.getPhotos());
        });
        response.setPhotos(photos);
        return response;
    }

    public List<String> getApiUrlEndpoints (HomeDto homeDto) throws InvocationTargetException, IllegalAccessException {
        List<String> urls = new ArrayList<>();

        // This code will grab all getCamera* methods and (if value returns true) then we will build a API URL to
        //  call in order to fetch pictures for a given rover / camera / sol.

        Method[] methods = homeDto.getClass().getMethods();
        for(Method method: methods) {
            if(method.getName().contains("getCamera") && Boolean.TRUE.equals(method.invoke(homeDto))) {
                String cameraName = method.getName().substring(9).toUpperCase();
//                String cameraName = method.getName().split("getCamera")[1].toUpperCase(); *** code from Trevor Page

                if(validCameras.get(homeDto.getMarsApiRoverData()).contains(cameraName)) {
                    urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol="+homeDto.getMarsSol()+"&camera="+cameraName+"&api_key=" + API_KEY);
                }
            }
        }
        return urls;
    }
}
