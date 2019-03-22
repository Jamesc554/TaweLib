package server;

import library.Library;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException {
        Library.start();
        try (ServerSocket listener = new ServerSocket(4242)) {
            ExecutorService pool = Executors.newFixedThreadPool(1000);

            while (true){
                pool.execute(new Client(listener.accept()));
            }
        }
    }

}
