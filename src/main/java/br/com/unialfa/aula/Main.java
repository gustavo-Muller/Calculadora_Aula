package br.com.unialfa.aula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("Inicio Start");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("view/FXMLCalculadora.fxml"));
			Scene scene = new Scene(root,336,388);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() {
		System.out.println("Aplica��o Iniciada");
	}
	
	public void stop() {
		System.out.println("Aplica��o Destru�da");
	}
}
