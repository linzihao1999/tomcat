package org.example.demo;

import org.example.tomcat.Servlet;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExampleServlet implements Servlet {
    @Override
    public void init() {

    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        System.out.println(request.uri());
    }

    @Override
    public void destory() {

    }
}
