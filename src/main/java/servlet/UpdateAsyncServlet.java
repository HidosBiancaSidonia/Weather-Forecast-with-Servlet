package servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@WebServlet(name = "UpdateAsyncServlet", urlPatterns = {"/asyncUpdate"}, asyncSupported = true)
public class UpdateAsyncServlet extends HttpServlet {

    /**
     * @param request an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){

        System.out.println("Async Servlet with thread: " + Thread.currentThread().toString());

        AsyncContext ac = request.startAsync();

        ac.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) {
                System.out.println("Async complete");
            }

            @Override
            public void onTimeout(AsyncEvent event) {
                System.out.println("Timed out...");
            }

            @Override
            public void onError(AsyncEvent event) {
                System.out.println("Error...");
            }

            @Override
            public void onStartAsync(AsyncEvent event) {
                System.out.println("Starting async...");
            }
        });

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(100);
        executor.execute(new UpdateAsyncService(ac));
        System.out.println("Servlet completed request handling");
    }

    /**
     * @param request an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }
}
