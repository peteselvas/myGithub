package org.academiadecodigo.cachealots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private String str;

    public Client(String str){
        this.str = str;
    }


    public static void main(String[] args) {

        InetAddress inetAddress;
        //int port = 7000;
        int port = 50666;

        try {
            //inetAddress = InetAddress.getByName("localhost");
            inetAddress = InetAddress.getByName("192.168.1.137");
            Socket socket = new Socket(inetAddress, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                out.println("<Pete> " + getUserMessage());
                //---------------------------------------------------------------------------------------------

                //String serverResponse = in.readLine();
                //System.out.println(serverResponse);
            }
        } catch (UnknownHostException e) {
            System.out.println("exception 1");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("exception 2");
            e.printStackTrace();
        }
    }

    public static String getUserMessage(){
        System.out.print("<Pete> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
