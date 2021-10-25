package nl.tiebe.messagesender.packets;

import nl.tiebe.messagesender.SystemTrayIcon;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PacketReceiver implements Runnable {
    private boolean running = true;

    private DatagramSocket serverSocket;

    public void waitForMessage(int port) {
        while (running) {
            try {
                serverSocket = new DatagramSocket(port);
                byte[] receiveData = new byte[260];

                System.out.printf("Listening on udp:%s:%d%n",
                        InetAddress.getLocalHost().getHostAddress(), port);
                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);

                while (true) {
                    serverSocket.receive(receivePacket);
                    String sentence = new String(receivePacket.getData(), 0,
                            receivePacket.getLength());
                    System.out.println("RECEIVED: " + sentence);
                    SystemTrayIcon.showMessage("Message received!", sentence);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        if (serverSocket != null) {
            serverSocket.close();
        }
        running = false;
    }

    @Override
    public void run() {
        waitForMessage(5643);
    }
}
