package servlet;

import getValues.GetLocation;
import model.Location;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/chooseLocation")
public class ChooseLocationPage extends HttpServlet {
    private Location location = new Location();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Location> locations = GetLocation.getLocations();
        for (Location location1:locations) {
            if(request.getParameter(location1.getName())!=null){
                location = location1;
            }
        }

        System.out.println("The button chosen: "+location.getName());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
