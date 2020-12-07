package servlet;

import getValues.GetLocation;
import model.Location;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

@WebServlet("/chooseServlet")
public class MainPage extends HttpServlet {

    private ArrayList<Location> locationsInList = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("#.0000");

    /**
     * Constructor
     */
    public MainPage() {
    }

    /**
     * @return localities that match what the web client has entered
     */
    public ArrayList<Location> getLocationsInList() {
        return locationsInList;
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Location> locations = GetLocation.getLocations();

        //System.out.println(locations);

        String locationName = request.getParameter("locationName");
        String latitude = df.format(Double.parseDouble(request.getParameter("latitude")));
        String longitude = df.format(Double.parseDouble(request.getParameter("longitude")));

        String option = request.getParameter("option");

        if(locationName.isEmpty() && (latitude.isEmpty() && longitude.isEmpty())){
            RequestDispatcher req = request.getRequestDispatcher("index.html");
            req.include(request, response);
        }
        else {
            switch (option){
                case "name":
                    if(locationName.isEmpty()){
                        RequestDispatcher req = request.getRequestDispatcher("index.html");
                        req.include(request, response);
                    }
                    else
                    {
                        int verify = 0;
                        System.out.println("The location entered is: "+locationName.toUpperCase());
                        for (Location location : locations) {
                            if(location.getName().toLowerCase().contains(locationName.toLowerCase())){
                                verify = 1;
                                locationsInList.add(location);
                            }
                        }

                        switch (verify){
                            case 0:
                                System.out.println("This locality doesn't match those in the list!");
                                RequestDispatcher req = request.getRequestDispatcher("noInfo.html");
                                req.include(request, response);
                                break;
                            case 1:
                                System.out.println("Matches :");
                                System.out.println(locationsInList);


                                break;
                            default:
                                break;
                        }

                    }
                    break;
                case "coordinates":
                    if((latitude.isEmpty() || longitude.isEmpty())){
                        RequestDispatcher req = request.getRequestDispatcher("index.html");
                        req.include(request, response);
                    }
                    else
                    {
                        int verify = 0;

                        System.out.println("The latitude entered is: "+latitude+" and the longitude entered is: "+longitude);
                        for (Location location : locations) {
                            if(location.getLatitude().equals(Double.parseDouble(latitude)) && location.getLongitude().equals(Double.parseDouble(longitude))){
                                verify = 1;
                                locationsInList.add(location);
                            }
                        }

                        switch (verify){
                            case 0:
                                System.out.println("This locality with the respective coordinates doesn't exist in the list!");
                                System.out.println("We Are looking for the closest locality to the entered coordinates!");


                                double minDistance = 10.0000;
                                double distance = 0.0;
                                for (Location location: locations) {
                                    distance = Double.parseDouble(df.format(dsquare(location.getLatitude(),location.getLongitude(),Double.parseDouble(df.format(Double.parseDouble(latitude))),Double.parseDouble(df.format(Double.parseDouble(longitude))))));

                                    if(distance < minDistance){
                                        minDistance = distance;
                                    }
                                }

                                for (Location location: locations) {
                                    distance = Double.parseDouble(df.format(dsquare(location.getLatitude(),location.getLongitude(),Double.parseDouble(df.format(Double.parseDouble(latitude))),Double.parseDouble(df.format(Double.parseDouble(longitude))))));

                                    if(distance == minDistance){
                                        locationsInList.add(location);
                                    }
                                }
                                System.out.println("Matches :");
                                System.out.println(locationsInList);
                                break;
                            case 1:
                                System.out.println("Matches :");
                                System.out.println(locationsInList);
                                break;
                            default:
                                break;
                        }

                    }
                    break;
                default:
                    break;
            }

        }

    }

    private double dsquare(Double x1, Double y1, Double x2, Double y2){
        return Math.sqrt(Math.pow( x2 - x1, 2) + Math.pow( y2 - y1 , 2));
    }

}
