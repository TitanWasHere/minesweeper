package com.example.pratofiorito;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    Controller c;

    @Override
    public void start(Stage stage) throws IOException {
        c = new Controller(stage);
    }

    public static void main(String[] args) {
        launch();
    }

 }