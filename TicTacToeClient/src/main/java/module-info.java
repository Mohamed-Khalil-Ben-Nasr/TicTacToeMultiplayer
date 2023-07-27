module edu.lawrence.chatclient {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.lawrence.chatclient to javafx.fxml;
    exports edu.lawrence.chatclient;
}
