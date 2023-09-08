package org.example.tomcat;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface Servlet {
    void init();

    void service(HttpRequest request, HttpResponse response);

    void destory();
}
