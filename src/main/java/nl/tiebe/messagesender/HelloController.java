package nl.tiebe.messagesender;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nl.tiebe.messagesender.packets.PacketSender;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class HelloController {
    @FXML
    private TextField message;

    @FXML
    protected void sendMessage() {
        System.out.println(message.getText());
        try {
            PacketSender.sendPacket(InetAddress.getByAddress(new byte[] {(byte) 255, (byte) 255, (byte) 255, (byte) 255}), 5643, message.getText().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}