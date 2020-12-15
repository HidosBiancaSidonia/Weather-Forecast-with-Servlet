package servlet;

import getValues.GetWeatherForecast;
import model.Weather;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UpdateAsyncService implements Runnable  {
    private final AsyncContext aContext;

    /**
     * Constructor
     * @param aContext - AsyncContext object
     */
    public UpdateAsyncService(AsyncContext aContext) {
        this.aContext = aContext;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        aContext.setTimeout(TimeUnit.MINUTES.toMillis(1));

        try {
            //Retrieve the type parameter from the request. This is passed in the URL.
            String typeParam = aContext.getRequest().getParameter("type");

            String id = aContext.getRequest().getParameter("id");
            System.out.println("The id of the selected locality: "+id);

            out = aContext.getResponse().getWriter();

            LocalDateTime data = LocalDateTime.now();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time  = LocalTime.parse(df.format(data));
            System.out.println(time);

            //dupa o ora
            LocalTime value = time.plusHours(1);
            LocalTime fixed = LocalTime.of(value.getHour(),0,0);
            LocalTime fixedTime = LocalTime.of(0,fixed.minusMinutes(time.getMinute()).getMinute(),fixed.minusSeconds(time.getSecond()).getSecond());
            //System.out.println("Minutes left until fixed time: " + fixedTime);
            //long seconds = fixedTime.toSecondOfDay();

            //dupa un minut
            long seconds = 60;

            //Sleeping the thread so as to mimic long running task.
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));

            if (typeParam.equals("1")) {
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

                    LocalDateTime data1 = LocalDateTime.now();
                    DateTimeFormatter df1 = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime time1  = LocalTime.parse(df1.format(data1));
                    LocalTime value1;

                    GetWeatherForecast dataGeneration = new GetWeatherForecast();
                    dataGeneration.setMap();
                    Map<Integer, ArrayList<Weather>> weatherForecastList= dataGeneration.getWeatherForecastList();
                    ArrayList<Weather> weathers = weatherForecastList.get(Integer.parseInt(id));

                    for(int i=0; i<25; i++){
                        value1 = time1.plusHours(i);
                        out.print("<tr style=\"color:white\"><th scope=\"row\">"+value1+"</th>");
                        out.print("<td>"+weathers.get(i).getTemperature()+" &#8451</td>");
                        out.print("<td>"+weathers.get(i).getPrecipitations()+"%</td>");
                        out.print("<td>"+weathers.get(i).getHumidity()+"%</td>");
                        out.print("<td>"+weathers.get(i).getWind()+" km/h</td>");
                        out.print("</tr>");
                    }

                    out.print("</tbody>");
            }


            aContext.complete();

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }
}
