//package cn.ubibi.jettyboot.demotest.controller;
//
//import java.io.IOException;
//
//import org.eclipse.jetty.util.Callback;
//import org.eclipse.jetty.util.FutureCallback;
//import org.eclipse.jetty.websocket.core.api.WebSocketConnection;
//import org.eclipse.jetty.websocket.core.api.WebSocketException;
//import org.eclipse.jetty.websocket.core.api.WebSocketListener;
//
//public class MyEchoSocket implements WebSocketListener
//{
//    private WebSocketConnection outbound;
//
//    @Override
//    public void onWebSocketBinary(byte[] payload, int offset,
//                                  int len)
//    {
//        /* only interested in text messages */
//    }
//
//    @Override
//    public void onWebSocketClose(int statusCode, String reason)
//    {
//        this.outbound = null;
//    }
//
//    @Override
//    public void onWebSocketConnect(WebSocketConnection connection)
//    {
//        this.outbound = connection;
//    }
//
//    @Override
//    public void onWebSocketException(WebSocketException error)
//    {
//        error.printStackTrace();
//    }
//
//    @Override
//    public void onWebSocketText(String message)
//    {
//        if (outbound == null)
//        {
//            return;
//        }
//
//        try
//        {
//            String context = null;
//            Callback callback = new FutureCallback&lt;&gt;();
//            outbound.write(context,callback,message);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
//}