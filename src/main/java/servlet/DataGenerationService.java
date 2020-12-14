package servlet;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;

public class DataGenerationService implements Runnable {

    private final AsyncContext aContext;

    public DataGenerationService(AsyncContext aContext) {
        this.aContext = aContext;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        aContext.setTimeout(60000);

        try {
            String typeParam = aContext.getRequest().getParameter("type");
            System.out.println(typeParam);

            System.out.println("Starting the long running task: Type=" + typeParam);

            out = aContext.getResponse().getWriter();

            //Sleeping the thread so as to mimic long running task.
            Thread.sleep(60);

            switch (typeParam) {
                case "1":
                    out.println("This process invoked.");
                    break;
                default:
                    out.println("Ok... nothing asked of.");
                    break;
            }

            System.out.println("Done processing the long running task: Type=" + typeParam );

            /**
             * Intimating the Web server that the asynchronous task is complete and the
             * response to be sent to the client.
             */
            aContext.complete();

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }
}
