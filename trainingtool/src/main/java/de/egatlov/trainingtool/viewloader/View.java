package de.egatlov.trainingtool.viewloader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public enum View {

	CREATE_SENTIMENT("CreateSentimentView", "Create Sentiment...", false, true), //
	IMPROVE_ANALYSIS("ImproveAnalysisView", "Improve Analysis...", false, true), //
	LOAD_PROJECT("LoadProjectView", "Load Project...", false, true), //
	LOAD_SENTIMENT("LoadSentimentView", "Load Sentiment...", false, true), //
	MAIN("MainView", "Sentiment Trainingtool (STT)", false, true), //
	SAVE_PROJECT("SaveProjectView", "Save Project...", false, true), //
	SAVE_SENTIMENT("SaveSentimentView", "Save Sentiment...", false, true), //
	SENTIMENT("SentimentView", "Sentiment", false, true);

	private String fileName;
	private String title;
	private boolean resizeable;
	private boolean applicationModal;

	private View(String fileName, String title, boolean resizeable, boolean applicationModal) {
		this.fileName = fileName;
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
