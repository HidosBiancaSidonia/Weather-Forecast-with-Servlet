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
        //System.out.println(locations);

        String locationName = request.getParameter("locationName");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");


        System.out.println("Location Name: " + locationName);
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);

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
                        System.out.println("Location Name");
                    }
                    break;
                case "coordinates":
                    if((latitude.isEmpty() || longitude.isEmpty())){
                        RequestDispatcher req = request.getRequestDispatcher("index.html");
                        req.include(request, response);
                    }
                    else
                    {
                        System.out.println("Coordinates");
                    }
                    break;
                default:
                    break;
            }

        }

    }
}
