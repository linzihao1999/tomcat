package org.example.demo;

import org.example.tomcat.Tomcat;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tomcat tomcat = new Tomcat();
        tomcat.registerServlet("/hi", new ExampleServlet());
        tomcat.run();
    }
}
