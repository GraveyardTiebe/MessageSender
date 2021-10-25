package nl.tiebe.messagesender;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tiebe.messagesender.packets.PacketReceiver;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    public static HelloApplication instance;
    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        HelloApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Platform.setImplicitExit(false);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Send message!");
        stage.setScene(scene);

        stage.show();
    }

    static PacketReceiver packetReceiver;

    public static void main(String[] args) throws IOException, AWTException {
        packetReceiver = new PacketReceiver();
        Thread receiver = new Thread(packetReceiver);
        receiver.start();

        SystemTrayIcon.addToSystemTray();

        launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        packetReceiver.stop();
        SystemTrayIcon.removeFromSystemTray();
    }
}