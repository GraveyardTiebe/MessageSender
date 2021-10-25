module nl.tiebe.messagesender {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens nl.tiebe.messagesender to javafx.fxml;
    exports nl.tiebe.messagesender;
    exports nl.tiebe.messagesender.UI;
}