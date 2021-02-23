import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * UDPClient class that sends packets to a specific UDP host server and receives it's response
 */
class UDPClient {

    private static final int BUFFER_SIZE = 1024;
    private static final int RCV_TIMEOUT = 10000;
    private static final String CHARSET = "UTF8";

    private String host;
    private int port;
    private String message;

    private DatagramSocket clientSocket;

    /**
     * UDPClient entry point
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) {

        UDPClient udpClient = new UDPClient();
        udpClient.start();

    }

    private void start() {

        try {

            // gets the desired udp server host ip, port, and the message content from the console
            getUserInput();

            // sent the datagram packet to the server
            sendDatagram();

            // receive a datagram package from the server, dump to console and close socket
            receiveDatagram();

        } catch (UnknownHostException ex) {

            System.err.println("Error: Invalid host: " + this.host);

        } catch (NumberFormatException ex) {

            System.err.println("Error: Invalid port!");

        } catch (SocketException ex) {

            System.err.println("Error: Could not connect to server: " + this.host + ':' + this.port);

        } catch (IOException ex) {

            System.err.println("Error: Network Failure: " + ex.getMessage());
        }
    }

    private void getUserInput() throws IOException, NumberFormatException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Hostname? ");
        host = in.readLine();

        System.out.print("Port? ");
        port = Integer.parseInt(in.readLine());

        System.out.print("Enter Message: ");
        message = in.readLine();

        in.close();

    }

    private void sendDatagram() throws IOException {

        clientSocket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(host);

        byte[] sendData = message.getBytes(CHARSET);

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        clientSocket.send(sendPacket);

    }

    private void receiveDatagram() throws IOException {

        byte[] receiveData = new byte[BUFFER_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        System.out.println("Waiting for return packet");
        clientSocket.setSoTimeout(RCV_TIMEOUT);

        try {

            clientSocket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength(), CHARSET);

            System.out.println("Received Message: " + response);

        } catch (SocketTimeoutException ste) {

            System.err.println("Error: Timeout Occurred, packet assumed lost");

        } finally {

            clientSocket.close();

        }

    }

}

