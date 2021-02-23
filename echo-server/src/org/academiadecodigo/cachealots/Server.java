package org.academiadecodigo.cachealots;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) {

        int serverPort = 7000;
        byte[] receiveBuffer = new byte[1024];
        byte[] sendBuffer;

        DatagramPacket receivePacket;
        DatagramPacket sentPacked;

        try {
            DatagramSocket socket = new DatagramSocket(serverPort);
            receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            System.out.println("Server running...");
            socket.receive(receivePacket);

            //------------------------------------------------------------------------------------------------------------------
            System.out.println("Received packet: " + new String(receivePacket.getData()));
            String responseStr = new String(receivePacket.getData()).toUpperCase();
            sendBuffer = responseStr.getBytes();
            sentPacked = new DatagramPacket(sendBuffer, sendBuffer.length, receivePacket.getAddress(), receivePacket.getPort());
            socket.send(sentPacked);
            System.out.println("Sent packet: " + responseStr);
        }
        catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
