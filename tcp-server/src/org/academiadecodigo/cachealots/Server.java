package org.academiadecodigo.cachealots;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private String str;

    public Server(String str){
        this.str = str;
    }

    public static void main(String[] args) {

        int serverPort = 7000;

        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server running and waiting for connections...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Server accepted a new connection...");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                String receiveStr = in.readLine();
                //System.out.println("Server received from client: " + receiveStr);
                System.out.println(receiveStr);
                //------------------------------------------------------------------------------------------------------

                //String response = receiveStr.toUpperCase();
            //    out.println(receiveStr);
                //out.newLine();

                //out.flush();
                //System.out.println("Server sent response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String getStr() {
        return str;
    }
}
