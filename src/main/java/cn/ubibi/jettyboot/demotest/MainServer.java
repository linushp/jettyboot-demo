package cn.ubibi.jettyboot.demotest;

import cn.ubibi.jettyboot.demotest.controller.MyExceptionHandler;
import cn.ubibi.jettyboot.demotest.controller.UserController;
import cn.ubibi.jettyboot.demotest.controller.parser.CurrentUser;
import cn.ubibi.jettyboot.demotest.dao.UserDAO;
import cn.ubibi.jettyboot.demotest.dao.base.MyConnectionFactory;
import cn.ubibi.jettyboot.demotest.servlets.HelloServlet;
import cn.ubibi.jettyboot.framework.rest.ClassPathResourceHandler;
import cn.ubibi.jettyboot.framework.rest.ifs.RequestAspect;
import cn.ubibi.jettyboot.framework.rest.Request;
import cn.ubibi.jettyboot.framework.rest.RestContextHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

import java.lang.reflect.Method;


public class MainServer {

    private static Logger logger = Log.getLogger(MainServer.class);


    public static void main(String[] args) throws Exception {

        long t1 = System.currentTimeMillis();

        MyConnectionFactory connectionFactory = MyConnectionFactory.getInstance();
        connectionFactory.init();


        RestContextHandler context = new RestContextHandler("/");
        context.addService(new UserDAO());
        context.addController("/user",new UserController());
        context.addServlet("/hello*",new HelloServlet());

        context.addRequestAspect(new RequestAspect() {

            public void invokeBefore(Method method, Request request) throws Exception {
                System.out.println(method.getName());
                String token  = request.getCookieValue("token");
                if (token==null || token.isEmpty()){
//                    throw new NotLoginException();
                }
                request.setAspectVariable("currentUser",new CurrentUser());
            }


            public void invokeAfter(Method method, Request request, Object invokeResult) throws Exception {
                System.out.println(method.getName());
            }

        });


        context.addExceptionHandler(new MyExceptionHandler());
        context.addResourceHandler(new ClassPathResourceHandler("/public/"));






        Server server = new Server(8001);
        server.setHandler(context);
        server.start();

        long t2 = System.currentTimeMillis();


        logger.info("" + MainServer.class.getClassLoader().getClass().getName());

        logger.info("Server Started success , cost time " + (t2 - t1) + " ms");
        server.join();

    }



//
//    public static void main(String [] args) throws Exception {
//
//        MyConnectionFactory.getInstance().init();
//
//
//
//        JettyServer server = new JettyServer();
//
//        server.addService(new UserDAO());
//        server.addController("/user", new UserController());
//
//        server.addMethodArgumentResolver(new MethodArgumentResolver() {
//            @Override
//            public boolean isSupport(MethodArgument methodArgument) {
//                return false;
//            }
//
//            @Override
//            public Object resolveArgument(MethodArgument methodArgument, Request request) {
//                return null;
//            }
//        });
//
//
//        server.listen(8001);
//
//    }

}
