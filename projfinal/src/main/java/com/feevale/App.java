package com.feevale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static AdminEstabelecimento admin = new AdminEstabelecimento();
    private static TotemAtendimento totem = new TotemAtendimento();

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("totem"), 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static AdminEstabelecimento getAdmin() {
        return admin;
    }

    public static TotemAtendimento getTotem() {
        return totem;
    }

    public static void novoAtendimentoNoTotem() {
        totem = TotemAtendimento.criarNovoAtendimento();
    }

    public static void main(String[] args) {
        launch();
    }
}