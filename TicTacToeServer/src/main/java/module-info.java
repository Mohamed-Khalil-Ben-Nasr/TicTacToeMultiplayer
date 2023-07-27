module edu.lawrence.chatserver {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.lawrence.chatserver to javafx.fxml;
    exports edu.lawrence.chatserver;
}
