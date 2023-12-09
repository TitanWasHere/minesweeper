module com.example.pratofiorito {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pratofiorito to javafx.fxml;
    exports com.example.pratofiorito;
}