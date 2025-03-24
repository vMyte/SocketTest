package SocketUDP;

import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(8085)) {
            System.out.println("UDP Server started, waiting for messages...");

            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            for (int i = 0; i < 3; i++) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from the Client: " + receivedMessage);

                String response = "\"Hi Client!\" " + (i + 1);
                sendBuffer = response.getBytes();

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer, sendBuffer.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Sent to the Client: " + response);
            }

        } catch (IOException e) {
            System.out.println("Возникла ошибка...");
        }
    }
}