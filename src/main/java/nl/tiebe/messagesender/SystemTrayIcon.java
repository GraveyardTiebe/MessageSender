package nl.tiebe.messagesender;

import javafx.application.Platform;
import javafx.scene.control.Tooltip;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SystemTrayIcon {
    static Image image;

    static TrayIcon trayIcon;

    public static boolean addToSystemTray() throws IOException, AWTException {
        if (!SystemTray.isSupported()) return false;
        image = ImageIO.read(Objects.requireNonNull(HelloApplication.class.getResource("images/img.png")));
        trayIcon = new TrayIcon(image);
        trayIcon.setToolTip("MessageSender");
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HelloApplication.instance.start(HelloApplication.stage);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        SystemTray.getSystemTray().add(trayIcon);

        return true;
    }

    public static void removeFromSystemTray() {
        if (!SystemTray.isSupported()) return;
        SystemTray.getSystemTray().remove(trayIcon);
    }

    public static void showMessage(String title, String message) {
        if (!SystemTray.isSupported()) return;
        trayIcon.displayMessage(title, message, TrayIcon.MessageType.NONE);
    }

}
