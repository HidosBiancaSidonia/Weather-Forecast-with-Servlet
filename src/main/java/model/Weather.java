package model;

import java.io.Serializable;

public class Weather implements Serializable {
    private Integer temperature;
    private Integer precipitations;
    private Integer humidity;
    private Integer wind;

    /**
     * Constructor
     */
    public Weather() {
    }

    /**
     * Constructor
     * @param temperature from locality
     * @param precipitations from locality
     * @param humidity from locality
     * @param wind from locality
     */
    public Weather(Integer temperature, Integer precipitations, Integer humidity, Integer wind) {
        this.temperature = temperature;
        this.precipitations = precipitations;
        this.humidity = humidity;
        this.wind = wind;
    }

    /**
     * @return temperature
     */
    public Integer getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature from locality
     * @param temperature from locality
     */
    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    /**
     * @return precipitations
     */
    public Integer getPrecipitations() {
        return precipitations;
    }

    /**
     * Sets the precipitations from locality
     * @param precipitations from locality
     */
    public void setPrecipitations(Integer precipitations) {
        this.precipitations = precipitations;
    }

    /**
     * @return humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * Sets the humidity from locality
     * @param humidity from locality
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * @return wind
     */
    public Integer getWind() {
        return wind;
    }

    /**
     * Sets the wind from locality
     * @param wind from locality
     */
    public void setWind(Integer wind) {
        this.wind = wind;
    }

    /**
     * @return Weather class
     */
    @Override
    public String toString() {
        return "Weather{" +
                ", temperature=" + temperature +
                ", precipitations=" + precipitations +
                ", humidity=" + humidity +
                ", wind=" + wind +
                '}';
    }
}
