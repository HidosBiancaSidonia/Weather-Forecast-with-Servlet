package model;

import java.io.Serializable;

public class Weather implements Serializable {
    private Integer id;
    private Integer temperature;
    private Integer precipitations;
    private Integer humidity;
    private Integer wind;

    public Weather() {
    }

    public Weather(Integer id, Integer temperature, Integer precipitations, Integer humidity, Integer wind) {
        this.id = id;
        this.temperature = temperature;
        this.precipitations = precipitations;
        this.humidity = humidity;
        this.wind = wind;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getPrecipitations() {
        return precipitations;
    }

    public void setPrecipitations(Integer precipitations) {
        this.precipitations = precipitations;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getWind() {
        return wind;
    }

    public void setWind(Integer wind) {
        this.wind = wind;
    }
}
