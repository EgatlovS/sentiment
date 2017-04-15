package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextArea;

import de.egatlov.trainingtool.data.ApplicationData;
import de.egatlov.trainingtool.viewloader.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainController {

	@FXML
	private MenuItem saveProjectBtn;

	@FXML
	private MenuItem loadProjectBtn;

	@FXML
	private MenuItem createSentimentBtn;

	@FXML
	private MenuItem saveSentimentBtn;

	@FXML
	private MenuItem loadSentimentBtn;

	@FXML
	private MenuItem aboutBtn;

	@FXML
	private JFXTextArea textToAnalyzeTA;

	@FXML
	private JFXButton protestBtn;

	@FXML
	private JFXButton analyzeBtn;

	@FXML
	JFXMasonryPane masonry;

	public void initialize() {
		ApplicationData.get().setMainController(this);
	}

	@FXML
	void aboutWindow(ActionEvent event) throws Exception {
		View.ABOUT.show();
	}

	@FXML
	void loadProjectWindow(ActionEvent event) throws Exception {
		View.LOAD_PROJECT.show();
	}

	@FXML
	void saveProjectWindow(ActionEvent event) throws Exception {
		View.SAVE_PROJECT.show();
	}

	@FXML
	void createSentimentWindow(ActionEvent event) throws Exception {
		View.CREATE_SENTIMENT.show();
	}

	@FXML
	void loadSentimentWindow(ActionEvent event) throws Exception {
		View.LOAD_SENTIMENT.show();
	}

	@FXML
	void saveSentimentWindow(ActionEvent event) throws Exception {
		View.SAVE_SENTIMENT.show();
	}

	@FXML
	void protestWindow(ActionEvent event) throws Exception {
		View.IMPROVE_ANALYSIS.show();
	}

	@FXML
	void analyze(ActionEvent event) {
		ApplicationData.get().getControlUnit().analyzed(textToAnalyzeTA.getText());
		ApplicationData.get().updateSentiments();
	}

	public void update() throws Exception {
		masonry.getChildren().add(View.SENTIMENT.pane());
	}

}
