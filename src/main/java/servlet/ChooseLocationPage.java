package servlet;

import getValues.GetLocation;
import javafx.scene.input.DataFormat;
import model.Location;
import model.Weather;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(urlPatterns = "/chooseLocation")
public class ChooseLocationPage extends HttpServlet {
    private Location location = new Location();

    /**
     * @param request an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Location> locations = GetLocation.getLocations();
        for (Location location1:locations) {
            if(request.getParameter(location1.getName())!=null){
                location = location1;
            }
        }

        System.out.println("The button chosen: "+location.getName());
        doGet(request,response);
    }

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
                "    }\n" +
                "\n" +
                "    span{\n" +
                "        color: \t#dbc4b4;\n" +
                "        margin-right: 50px;\n" +
                "    }\n" +
                "\n" +
                "</style>");
        out.print("<body>");
        out.println("<form name=\"weatherForecast\" method=\"post\" action=\"weatherForecast\">");
        out.print("<h1 align=\"center\">Weather Forecast in "+location.getName()+" Locality </h1>\n" +
                "<div class=\"myDiv\">");

        out.print(
                "<table class=\"table\">\n" +
                        "  <thead style=\"font-size:20px\">\n" +
                        "    <tr>\n" +
                        "      <th scope=\"col\">Time</th>\n" +
                        "      <th scope=\"col\">Temperature(&#8451)</th>\n" +
                        "      <th scope=\"col\">Precipitations(%)</th>\n" +
                        "      <th scope=\"col\">Humidity(%)</th>\n" +
                        "       <th scope=\"col\">Wind(km/h)</th>\n" +
                        "    </tr>\n" +
                        "  </thead>\n" );
        out.print("<tbody>") ;

        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time  = LocalTime.parse(df.format(data));
        LocalTime value = time;

        DataGeneration dataGeneration = new DataGeneration();
        Map<Integer, ArrayList<Weather>> weatherForecastList= dataGeneration.getWeatherForecastList();
        ArrayList<Weather> weathers = weatherForecastList.get(location.getId());

            for(int i=0;i<25;i++){
                value = time.plusHours(i);
                out.print("<tr style=\"color:white\"><th scope=\"row\">"+value+"</th>");
                out.print("<td>"+weathers.get(i).getTemperature()+" &#8451</td>");
                out.print("<td>"+weathers.get(i).getPrecipitations()+"%</td>");
                out.print("<td>"+weathers.get(i).getHumidity()+"%</td>");
                out.print("<td>"+weathers.get(i).getWind()+" km/h</td>");
                out.print("</tr>");
            }

            out.print("</tbody>");


        out.print("</form>");
        out.println("</body></html>");
    }
}
