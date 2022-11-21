module com.example.fwjfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fwjfx to javafx.fxml;
    exports com.example.fwjfx;
}