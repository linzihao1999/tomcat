package org.example.tomcat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketHandlerPool {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public void registerHandler(SocketHandler socketHandler) {
        executor.execute(socketHandler);
    }
}
