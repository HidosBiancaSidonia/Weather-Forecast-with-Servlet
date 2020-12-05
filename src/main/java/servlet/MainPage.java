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
import java.util.ArrayList;

@WebServlet("/chooseServlet")
public class MainPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Location> locations = GetLocation.getLocations();
        ArrayList<Integer> id_locations = new ArrayList<>();
        //System.out.println(locations);

        String locationName = request.getParameter("locationName");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

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
                                id_locations.add(location.getId());
                            }
                        }

                        switch (verify){
                            case 0:
                                System.out.println("Nu exista in lista acesta localitate!");
                                break;
                            case 1:
                                System.out.println("Exista!");
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
                                id_locations.add(location.getId());
                            }
                        }

                        switch (verify){
                            case 0:
                                System.out.println("Nu exista in lista acesta localitate cu coordonatele respective!");
                                break;
                            case 1:
                                System.out.println("Exista!");
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
}
