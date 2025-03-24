package SocketTCP;
import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8085)) {
            System.out.println("TCP Server started, waiting for messages...");

            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client is connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                for (int i = 0; i < 3; i++) {
                    String message = in.readLine() ;
                    System.out.println("Received from the Client: " + message);
                    message = " \"Hi Client!\" " + (i + 1);
                    System.out.println("Is sent to the Client: " + message);
                    out.println(message);
                }
            }

        } catch (IOException e) {
            System.out.println("Возникла ошибка...");
        }
    }
}
