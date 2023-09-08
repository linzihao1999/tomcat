package org.example.tomcat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServletContainer {
    static volatile private ServletContainer servletContainer;
    private final Map<String, Servlet> container = new ConcurrentHashMap<>();

    private ServletContainer() {
    }

    public static ServletContainer getInstance() {
        if (servletContainer == null) {
            synchronized (ServletContainer.class) {
                if (servletContainer == null) {
                    servletContainer = new ServletContainer();
                }
            }
        }
        return servletContainer;
    }

    public void registerServlet(String URL, Servlet servlet) {
        container.put(URL, servlet);
    }

    public Servlet getServlet(String URL) {
        return container.get(URL);
    }
}
