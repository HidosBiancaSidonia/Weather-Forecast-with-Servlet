package model;

import java.io.Serializable;

public class Location implements Serializable {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double difference;

    public Location() {
    }

    public Location(Integer id, String name, Double latitude, Double longitude, Double difference) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.difference = difference;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    @Override
    public String toString() {
        return "Location: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", difference=" + difference +
                "\r\n";
    }
}
