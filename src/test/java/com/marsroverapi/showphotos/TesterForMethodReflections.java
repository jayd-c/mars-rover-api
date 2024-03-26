package com.marsroverapi.showphotos;

import com.marsroverapi.showphotos.dto.HomeDto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesterForMethodReflections {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Map<String, List<String>> validCameras = new HashMap<>();

        validCameras.put("Curiosity".toLowerCase(),List.of("FHAZ","RHAZ","MAST","CHEMCAM","MAHLI","MARDI","NAVCAM"));
        validCameras.put("Opportunity".toLowerCase(),List.of("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
        validCameras.put("Spirit".toLowerCase(),List.of("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
        print();
    }

    public static void print() throws InvocationTargetException, IllegalAccessException {
        HomeDto homeDto = new HomeDto();
        Method[] methods = homeDto.getClass().getMethods();
        System.out.println("printing m;s ");
        for(Method m: methods) {
            if(m.getName().contains("getCamera") && Boolean.TRUE.equals(m.invoke(homeDto))) {
                String cameraName = m.getName().substring(9);
                System.out.println(cameraName);
            }
        }
    }
}
