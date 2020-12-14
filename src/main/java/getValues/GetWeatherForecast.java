package getValues;

import model.Location;
import model.Weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GetWeatherForecast{

    private final Map<Integer, ArrayList<Weather>> weatherForecastList = new HashMap<>();

    /**
     * Constructor
     */
    public GetWeatherForecast(){

    }

    /**
     * @return generated temperature
     */
    public Integer generateTemperature(){
        Random random = new Random();
        return random.nextInt(36);
    }

    /**
     * @return generated precipitations
     */
    public Integer generatePrecipitations(){
        Random random = new Random();
        return random.nextInt(101);
    }

    /**
     * @return generated humidity
     */
    public Integer generateHumidity(){
        Random random = new Random();
        return random.nextInt(101);
    }

    /**
     * @return generated wind
     */
    public Integer generateWind(){
        Random random = new Random();
        return random.nextInt(11);
    }

    /**
     * Sets weather forecast list
     */
    public void setMap(){
        ArrayList<Location> locations = GetLocation.getLocations();
        ArrayList<Weather> weathers = new ArrayList<>();
        for (Location location:locations) {
            weathers.clear();
            for(int hour=0;hour<=24;hour++){
                Weather weather = new Weather(generateTemperature(),generatePrecipitations(),generateHumidity(),generateWind());
                weathers.add(weather);
            }
            weatherForecastList.put(location.getId(),weathers);
        }
    }

    /**
     * @return weather forecast list
     */
    public Map<Integer, ArrayList<Weather>> getWeatherForecastList() {
        return weatherForecastList;
    }
}
