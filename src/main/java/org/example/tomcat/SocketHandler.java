package org.example.tomcat;

import lombok.Builder;

import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Builder
public class SocketHandler extends Thread {
    private final Socket socket;

    void handle() throws IOException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder requestBuilder = new StringBuilder();
        String line;
        while (((line = reader.readLine()) != null) && (!line.isEmpty())) {
            requestBuilder.append(line).append("\r\n");
//            System.out.println(line);
        }
        if (requestBuilder.toString().isEmpty()) return;
        String[] requestParts = requestBuilder.toString().split("\r\n", 2);
        String requestLine = requestParts[0];
        String requestHeader = requestParts.length > 1 ? requestParts[1] : "";

        String[] requestLineParts = requestLine.split(" ");
        String method = requestLineParts[0];
        String URL = requestLineParts[1];
        String httpVersion = requestLineParts[2];

        HttpRequest request = HttpRequest.newBuilder()
                                         .method(method, null)
                                         .uri(URI.create(URL))
                                         .version(HttpClient.Version.valueOf(httpVersion))
                                         .build();

        Servlet servlet = ServletContainer.getInstance().getServlet(URL);
        if (servlet != null) {
            servlet.init();
            servlet.service(request, null);
            servlet.destory();
        }
    }

    @Override
    public void run() {
        try {
            handle();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
