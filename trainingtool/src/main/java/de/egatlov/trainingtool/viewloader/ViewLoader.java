package de.egatlov.trainingtool.viewloader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class ViewLoader {

	public Stage load(String title, View view, boolean resizeable, boolean applicationModal) throws Exception {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view.getValue()));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			if (applicationModal) {
				stage.initModality(Modality.APPLICATION_MODAL);
			}
			stage.setTitle(title);
			stage.setResizable(resizeable);
			stage.setScene(new Scene(root));
			return stage;
		} catch (Exception e) {
			throw new Exception("Couldn't load view", e);
		}
	}

}
