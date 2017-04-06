package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;

import de.egatlov.trainingtool.viewloader.View;
import de.egatlov.trainingtool.viewloader.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class MainController {

	private ViewLoader viewLoader;

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
	private Menu aboutBtn;

	@FXML
	private TextArea textToAnalyzeTA;

	@FXML
	private JFXButton protestBtn;

	@FXML
	private JFXButton analyzeBtn;

	@FXML
	private JFXMasonryPane masonry;

	public MainController() {
		this.viewLoader = new ViewLoader();
	}

	@FXML
	void aboutWindow(ActionEvent event) {
		// show window with ABOUT-CONTENT
	}

	@FXML
	void loadProjectWindow(ActionEvent event) throws Exception {
		viewLoader.load("Load Project...", View.LOAD_PROJECT, false, true).show();
	}

	@FXML
	void saveProjectWindow(ActionEvent event) throws Exception {
		viewLoader.load("Save Project...", View.SAVE_PROJECT, false, true).show();
	}

	@FXML
	void createSentimentWindow(ActionEvent event) throws Exception {
		viewLoader.load("Create Sentiment...", View.CREATE_SENTIMENT, false, true).show();
	}

	@FXML
	void loadSentimentWindow(ActionEvent event) throws Exception {
		viewLoader.load("Load Sentiment...", View.LOAD_SENTIMENT, false, true).show();
	}

	@FXML
	void saveSentimentWindow(ActionEvent event) throws Exception {
		viewLoader.load("Save Sentiment...", View.SAVE_SENTIMENT, false, true).show();
	}

	@FXML
	void protestWindow(ActionEvent event) throws Exception {
		viewLoader.load("Improve Analysis...", View.IMPROVE_ANALYSIS, false, true).show();
	}

	@FXML
	void analyze(ActionEvent event) {
		// analyze the text in all sentiments and change them
	}

}
