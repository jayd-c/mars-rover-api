package com.marsroverapi.showphotos.dto;

public class HomeDto {
    private String marsApiRoverData;
    private Integer marsSol;
    private Boolean flexCheckIndeterminate;

    public String getMarsApiRoverData() {
        return marsApiRoverData;
    }

    public void setMarsApiRoverData(String marsApiRoverData) {
        this.marsApiRoverData = marsApiRoverData;
    }

    public Integer getMarsSol() {
        return marsSol;
    }

    public void setMarsSol(Integer marsSol) {
        this.marsSol = marsSol;
    }

    public Boolean getFlexCheckIndeterminate() {
        return flexCheckIndeterminate;
    }

    public void setFlexCheckIndeterminate(Boolean flexCheckIndeterminate) {
        this.flexCheckIndeterminate = flexCheckIndeterminate;
    }
}
