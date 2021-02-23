package org.academiadecodigo.cachealots;

import java.io.IOException;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        byte[] receiveBuffer = new byte[1024];
        byte[] sendBuffer = "test".getBytes();

        InetAddress hostIP = null;

        {
            try {
                hostIP = InetAddress.getByName("127.0.0.1");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        DatagramPacket sendDatagram;
        DatagramPacket receiveDatagram;
        DatagramSocket socket;

        {
            try {
                socket = new DatagramSocket();
                sendDatagram = new DatagramPacket(sendBuffer, sendBuffer.length, hostIP, 7000);
                socket.send(sendDatagram);
                System.out.println("Client sent data: " + new String(sendDatagram.getData()));

                //----------------------------------------------------------------------------------------------
                receiveDatagram = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receiveDatagram);
                System.out.println("Client received: " + new String(receiveDatagram.getData()));

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
