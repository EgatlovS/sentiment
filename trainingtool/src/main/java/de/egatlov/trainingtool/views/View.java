package de.egatlov.trainingtool.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class View {

	private String fileName;
	private String title;
	private boolean resizeable;
	private boolean applicationModal;

	public View(String fileName, String title, boolean resizeable, boolean applicationModal) {
		this.fileName = fileName;
		this.title = title;
		this.resizeable = resizeable;
		this.applicationModal = applicationModal;
	}

	public void show() throws Exception {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName()));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			if (applicationModal) {
				stage.initModality(Modality.APPLICATION_MODAL);
			}
			stage.setTitle(title);
			stage.setResizable(resizeable);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			throw new Exception("Couldn't load view", e);
		}
	}

	public Pane pane() throws Exception {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName()));
			Pane pane = (Pane) fxmlLoader.load();
			return pane;
		} catch (Exception e) {
			throw new Exception("Couldn't create pane", e);
		}
	}

	public String fileName() {
		return "/view/" + fileName + ".fxml";
	}

	public String title() {
		return title;
	}

	public boolean resizeable() {
		return resizeable;
	}

	public boolean applicationModal() {
		return applicationModal;
	}

}
