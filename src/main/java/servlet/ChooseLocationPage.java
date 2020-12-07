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

    /**
     * @param request an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        ArrayList<Location> locations = GetLocation.getLocations();
        for (Location location1:locations) {
            if(request.getParameter(location1.getName())!=null){
                location = location1;
            }
        }

        System.out.println("The button chosen: "+location.getName());

    }

    /**
     * @param request an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
}
