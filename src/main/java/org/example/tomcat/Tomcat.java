package org.example.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
    static final int PORT = 4321;
    private final SocketHandlerPool handlerPool;

    public Tomcat() {
        handlerPool = new SocketHandlerPool();
    }

    public void registerServlet(String URL, Servlet servlet) {
        ServletContainer.getInstance().registerServlet(URL, servlet);
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                SocketHandler handler = SocketHandler.builder().socket(clientSocket).build();
                handlerPool.registerHandler(handler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
