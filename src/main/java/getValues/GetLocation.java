package getValues;

import model.Location;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GetLocation {

    /**
     * Constructor
     */
    public GetLocation(){
    }

    /**
     * Getting locations to the "Locations.txt" file
     * @return an ArrayList with the locations in the "Locations.txt" file
     */
    public static ArrayList<Location> getLocations()  {
        ArrayList<Location> locations = new ArrayList<>();
        try {
            File f = new File("../webapps/Weather_Forecast_war/resources/Locations.txt");
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] details = line.split(";");
                Location p = new Location(Integer.parseInt(details[0]), details[1],Double.parseDouble(details[2]),Double.parseDouble(details[3]));
                locations.add(p);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File \"Locations.txt\" not found");
        }
        return locations;
    }
}
