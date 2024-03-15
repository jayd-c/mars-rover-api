package com.marsroverapi.showphotos.response;

import java.util.ArrayList;
import java.util.List;

public class MarsRoverApiResponse {
    List<MarsPhoto> photos = new ArrayList<>();

    @Override
    public String toString() {
        System.out.println(photos.get(0).getImgSrc());
        return "MarsRoverApiResponse{" +
                "photos=" + photos +
                '}';
    }

    public List<MarsPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<MarsPhoto> photos) {
        this.photos = photos;
    }
}
