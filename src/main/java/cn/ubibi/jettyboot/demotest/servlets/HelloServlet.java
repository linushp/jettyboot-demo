package cn.ubibi.jettyboot.demotest.servlets;

import cn.ubibi.jettyboot.framework.commons.ResponseUtils;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet{



    @Override
    protected void doGet(final HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {





        //

        AsyncContext asyncContext = req.startAsync();

        Runnable t = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    asyncContext.getResponse().getWriter().print("hello 33333<br>");
                    asyncContext.complete();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread x = new Thread(t);
        x.start();


        resp.setContentType("text/html;charset=utf8");
        resp.getWriter().print("hello servlet1111<br>");
        resp.getWriter().flush();
        resp.flushBuffer();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        resp.getWriter().print("hello 都能看见蛋壳<br>");
        resp.getWriter().flush();



//        ResponseUtils.tryClose(resp);

    }
}
