package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextArea;

import de.egatlov.trainingtool.data.ApplicationData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public final class MainController {

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
	private JFXMasonryPane masonry;

	public void initialize() {
		ApplicationData.get().mainView().setMainController(this);
	}

	@FXML
	void aboutWindow(ActionEvent event) throws Exception {
		ApplicationData.get().aboutView().show();
	}

	@FXML
	void createSentimentWindow(ActionEvent event) throws Exception {
		ApplicationData.get().createSentimentView().show();
	}

	@FXML
	void loadSentimentWindow(ActionEvent event) throws Exception {
		ApplicationData.get().loadSentimentView().show();
	}

	@FXML
	void saveSentimentWindow(ActionEvent event) throws Exception {
		ApplicationData.get().saveSentimentView().show();
	}

	@FXML
	void protestWindow(ActionEvent event) throws Exception {
		ApplicationData.get().improveAnalysisView().show();
	}

	@FXML
	void analyze(ActionEvent event) {
		ApplicationData.get().getControlUnit().analyzed(textToAnalyzeTA.getText());
		ApplicationData.get().sentimentsView().updateSentiments();
	}

	public JFXMasonryPane masonry() {
		return masonry;
	}

}
