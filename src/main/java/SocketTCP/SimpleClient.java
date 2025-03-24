package SocketTCP;
import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8085)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            for (int i = 0; i < 3; i++) {
                String message = " \"Hi Server!\" " + (i + 1) ;
                out.println(message);
                System.out.println("Is sent to the Server: " + message);
                System.out.println("Received from the Server: " + in.readLine());
            }

        } catch (IOException e) {
            System.out.println("Вознилка ошибка...");
        }
    }
}
