package servlet;

import getValues.GetLocation;
import getValues.GetWeatherForecast;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

@WebServlet(urlPatterns = "/location",loadOnStartup = 1)
public class SecondPage extends HttpServlet {

    private Location location = new Location();
    private GetWeatherForecast dataGeneration;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);

    /**
     * When the servlet is started this method will be called first
     */
    @Override
    public void init() {
        System.out.println("Generated weather forecasts:");
        dataGeneration = new GetWeatherForecast();
        dataGeneration.setMap();
        //System.out.println(dataGeneration.getWeatherForecastList());
        dataSettingAfterAWhile();
    }

    /**
     * Resetting data after a certain time or at a certain time
     */
    public void dataSettingAfterAWhile() {
            final Runnable set = () -> {
                System.out.println("dataSettingAfterAWhile");
                dataGeneration.setMap();
            };


            LocalDateTime data = LocalDateTime.now();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time  = LocalTime.parse(df.format(data));
            System.out.println(time);

            //dupa o ora
            LocalTime value = time.plusHours(1);
            LocalTime fixed = LocalTime.of(value.getHour(),0,0);
            LocalTime fixedTime = LocalTime.of(0,fixed.minusMinutes(time.getMinute()).getMinute(),fixed.minusSeconds(time.getSecond()).getSecond());
            System.out.println("Minutes left until fixed time: " + fixedTime);

            //long seconds = fixedTime.toSecondOfDay();


            //dupa un minut
            long seconds = 60;

            final ScheduledFuture<?> handle = executor.scheduleAtFixedRate(set, seconds, 60, SECONDS);
            executor.schedule(() -> { handle.cancel(true); }, 60 * 60, SECONDS);
        }

    /**
     * @param request -  an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response - an HttpServletResponse object that contains the response the servlet sends to the client
     * @throws IOException - if an input or output error is detected when the servlet handles the GET request
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

    /**
     * @param request  - an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response - an HttpServletResponse object that contains the response the servlet sends to the client
     * @throws IOException - if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("index.html");
        PrintWriter out = response.getWriter();
        out.print("<html><head>");
        out.print("<link rel=\"icon\" href=\"images/icon.png\" type=\"image/icon type\">");
        out.print("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");

        out.print("<script src=\"asyncUpdate.js\"></script>");

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
        out.print("<h5 align=\"center\">The weather forecast is updated every minute!</h5>");
        out.println("<input type=\"button\" class=\"btn btn-primary\"  onclick=\"location.href='http://localhost:8080/Weather_Forecast_war/'\"  value=\"Back to main page\" />");
        out.println("<input type=\"button\" class=\"btn btn-primary\"  onclick=\"location.href='http://localhost:8080/Weather_Forecast_war/location'\"  value=\"Update data\" />");
        //out.print("<br/><input type=\"button\" style=\"color: transparent; background-color: transparent; border-color: transparent; cursor: default;\" class=\"btn btn-primary\" id=\"ajaxBt"+location.getId()+"\"  value=\"Update data\" />");

        out.print("<form name=\"weatherForecast\" method=\"post\" action=\"weatherForecast\">");
        out.print("<h1 align=\"center\">Weather Forecast in "+location.getName()+" Locality </h1>");
        out.print("<h3 align=\"center\">Latitude: "+location.getLatitude()+"   Longitude: "+location.getLongitude()+"</h3>");
        out.print("<div class=\"myDiv\"  id=\"reqResponse\">");

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
        LocalTime value;

        Map<Integer, ArrayList<Weather>> weatherForecastList= dataGeneration.getWeatherForecastList();
        ArrayList<Weather> weathers = weatherForecastList.get(location.getId());

            for(int i=0; i<25; i++){
                value = time.plusHours(i);
                out.print("<tr style=\"color:white\"><th scope=\"row\">"+value+"</th>");
                out.print("<td>"+weathers.get(i).getTemperature()+" &#8451</td>");
                out.print("<td>"+weathers.get(i).getPrecipitations()+"%</td>");
                out.print("<td>"+weathers.get(i).getHumidity()+"%</td>");
                out.print("<td>"+weathers.get(i).getWind()+" km/h</td>");
                out.print("</tr>");
            }

            out.print("</tbody>");
        out.print("</div>");
        out.print("</form>");
        out.println("</body></html>");
    }
}
