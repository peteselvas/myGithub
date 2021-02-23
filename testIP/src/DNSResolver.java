import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;
import java.net.*;


public class DNSResolver {


    public static void main(String[] args) {
        String hostName = getHost();

        InetAddress hostAdress = null;
        try {
            hostAdress = InetAddress.getByName(hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Testing reachability for " + hostAdress.getHostAddress() + " : " + hostAdress.isReachable(10));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static String getHost() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hostname? ");
        return reader.nextLine();
    }


}
