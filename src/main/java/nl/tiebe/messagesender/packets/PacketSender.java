package nl.tiebe.messagesender.packets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class PacketSender {
    public static void sendPacket(InetAddress address, int port, byte[] message) throws IOException {
        DatagramPacket packet = new DatagramPacket(message, message.length, address, port);

        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        socket.close();
    }
}
