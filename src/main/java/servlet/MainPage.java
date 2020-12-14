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

@WebServlet(value = "/chooseServlet",loadOnStartup = 0)
public class MainPage extends HttpServlet {

    private final ArrayList<Location> locationsInList = new ArrayList<>();
    private final DecimalFormat df = new DecimalFormat("#.0000");

    /**
     * Constructor
     */
    public MainPage() {
    }

    /**
     * @param request an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ArrayList<Location> locations = GetLocation.getLocations();
        locationsInList.clear();

        String locationName = request.getParameter("locationName");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        String option = request.getParameter("option");

        if (locationName.isEmpty() && (latitude.isEmpty() && longitude.isEmpty())) {
            RequestDispatcher req = request.getRequestDispatcher("index.html");
            req.include(request, response);
        } else {
            switch (option) {
                case "name":
                    if (locationName.isEmpty()) {
                        RequestDispatcher req = request.getRequestDispatcher("index.html");
                        req.include(request, response);
                    } else {
                        int verify = 0;
                        System.out.println("The location entered is: " + locationName.toUpperCase());
                        for (Location location : locations) {
                            if (location.getName().toLowerCase().contains(locationName.toLowerCase())) {
                                verify = 1;
                                locationsInList.add(location);
                            }
                        }

                        switch (verify) {
                            case 0:
                                System.out.println("This locality doesn't match those in the list!");
                                RequestDispatcher req = request.getRequestDispatcher("noInfo.html");
                                req.include(request, response);
                                break;
                            case 1:
                                System.out.println("Matches :");
                                System.out.println(locationsInList);

                                doGet(request, response);

                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case "coordinates":
                    if ((latitude.isEmpty() || longitude.isEmpty())) {
                        RequestDispatcher req = request.getRequestDispatcher("index.html");
                        req.include(request, response);
                    } else {
                        latitude = df.format(Double.parseDouble(request.getParameter("latitude")));
                        longitude = df.format(Double.parseDouble(request.getParameter("longitude")));

                        int verify = 0;

                        System.out.println("The latitude entered is: " + latitude + " and the longitude entered is: " + longitude);
                        for (Location location : locations) {
                            if (location.getLatitude().equals(Double.parseDouble(latitude)) && location.getLongitude().equals(Double.parseDouble(longitude))) {
                                verify = 1;
                                locationsInList.add(location);
                            }
                        }

                        switch (verify) {
                            case 0:
                                System.out.println("This locality with the respective coordinates doesn't exist in the list!");
                                System.out.println("We Are looking for the closest locality to the entered coordinates!");


                                double minDistance = 10.0000;
                                double distance;
                                for (Location location : locations) {
                                    distance = Double.parseDouble(df.format(distanceBetweenTwoPoints(location.getLatitude(), location.getLongitude(), Double.parseDouble(df.format(Double.parseDouble(latitude))), Double.parseDouble(df.format(Double.parseDouble(longitude))))));

                                    if (distance < minDistance) {
                                        minDistance = distance;
                                    }
                                }

                                for (Location location : locations) {
                                    distance = Double.parseDouble(df.format(distanceBetweenTwoPoints(location.getLatitude(), location.getLongitude(), Double.parseDouble(df.format(Double.parseDouble(latitude))), Double.parseDouble(df.format(Double.parseDouble(longitude))))));

                                    if (distance == minDistance) {
                                        locationsInList.add(location);
                                    }
                                }
                                System.out.println("Matches :");
                                System.out.println(locationsInList);
                                doGet(request, response);
                                break;
                            case 1:
                                System.out.println("Matches :");
                                System.out.println(locationsInList);
                                doGet(request, response);
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

    /**
     * @param x1 latitude from point 1
     * @param y1 longitude from point 1
     * @param x2 latitude from point 2
     * @param y2 longitude from point 2
     * @return distance between two points
     */
    private double distanceBetweenTwoPoints(Double x1, Double y1, Double x2, Double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /**
     * @param request an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("index.html");
        PrintWriter out = response.getWriter();
        out.print("<html><head>");
        out.print("<link rel=\"icon\" href=\"images/icon.png\" type=\"image/icon type\">");
        out.print("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
        out.print("<title>Weather Forecast</title></head>");
        out.print("<style>\n" +
                "    body {\n" +
                "        background-image: url(\"images/background.jpg\");\n" +
                "        background-repeat: no-repeat;\n" +
                "        background-attachment: fixed;\n" +
                "        background-size: cover;\n" +
                "    }\n" +
                "\n" +
                "    .myDiv {\n" +
                "        border: 5px groove #3c5984;\n" +
                "        background: rgba(0,9,44, 0.6);\n" +
                "        text-align: center;\n" +
                "        top:50%;\n" +
                "        left:50%;\n" +
                "        width: 800px;\n" +
                "        margin: 50px 0 0 250px;\n" +
                "        padding: 30px 90px 30px 90px;\n" +
                "    }\n" +
                "\n" +
                "    span{\n" +
                "        color: \t#dbc4b4;\n" +
                "        margin-right: 50px;\n" +
                "    }\n" +
                "\n" +
                "</style>");
        out.print("<body>");
        out.print("<input type=\"button\" class=\"btn btn-primary\"  onclick=\"location.href='http://localhost:8080/Weather_Forecast_war/'\"  value=\"Back to main page\" />");
        out.println("<form name=\"location\" method=\"post\" action=\"location\">");
        out.print("<h1 align=\"center\">Weather Forecast in Brasov County</h1>\n" +
                "<div class=\"myDiv\">");
        out.print("<h2 style=\"color: aliceblue; padding-bottom: 10px\" >Matching locations:</h2>");
        for (Location location : locationsInList) {
            out.print("<input type=\"submit\"  class=\"btn btn-primary\" style=\" width:150px;margin-top: 30px\" name="+location.getName()+"   value="+location.getName()+"><br/>");
        }
        out.print("</form>");
        out.println("</body></html>");
    }

}
