package de.egatlov.trainingtool.main;

import de.egatlov.trainingtool.browser.WebBrowser;
import de.egatlov.trainingtool.viewloader.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		setupHyperlinkSupport();
		try {
			Parent root = FXMLLoader.load(getClass().getResource(View.MAIN.fileName()));
			Scene scene = new Scene(root);
			primaryStage.setTitle(View.MAIN.title());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setupHyperlinkSupport() {
		WebBrowser.init(getHostServices());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
