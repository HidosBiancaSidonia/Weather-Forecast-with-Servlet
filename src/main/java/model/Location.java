package model;

import java.io.Serializable;

public class Location implements Serializable {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;

    /**
     * Constructor
     */
    public Location() {
    }

    /**
     * Constructor
     * @param id - location id
     * @param name - location name
     * @param latitude of location
     * @param longitude of location
     */
    public Location(Integer id, String name, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return location id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the location id
     * @param id - location id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name of location
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of location
     * @param name of location
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return latitude of location
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude of location
     * @param latitude of location
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return longitude of location
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude of location
     * @param longitude of location
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return Location class
     */
    @Override
    public String toString() {
        return "Location: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                "\r\n";
    }
}
