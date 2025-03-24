package SocketUDP;

import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];

            for (int i = 0; i < 3; i++) {
                String message = "\"Hi Server!\" " + (i + 1);
                sendBuffer = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer, sendBuffer.length, serverAddress, 8085);
                clientSocket.send(sendPacket);
                System.out.println("Sent to the Server: " + message);

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);

                String receivedMessage = new String(
                        receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from the Server: " + receivedMessage);
            }

        } catch (IOException e) {
            System.out.println("Возникла ошибка...");
        }
    }
}