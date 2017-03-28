package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

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
	private Menu aboutBtn;

	@FXML
	private TextArea textToAnalyzeTA;

	@FXML
	private JFXButton protestBtn;

	@FXML
	private JFXButton analyzeBtn;

	@FXML
	private JFXMasonryPane masonry;

	@FXML
	void aboutWindow(ActionEvent event) {
		// show window with ABOUT-CONTENT
	}

	@FXML
	void loadProjectWindow(ActionEvent event) {
		// open window with textfield for the path to the project
	}

	@FXML
	void saveProjectWindow(ActionEvent event) {
		// open window with textfield for the path to the project save location
	}

	@FXML
	void createSentimentWindow(ActionEvent event) {
		// open window to create a sentiment
	}

	@FXML
	void loadSentimentWindow(ActionEvent event) {
		// open window with textfield for the path to the sentiment
	}

	@FXML
	void saveSentimentWindow(ActionEvent event) {
		// show window with textfield for the path to save to
	}

	@FXML
	void protestWindow(ActionEvent event) {
		// open window where real sentiment winner can be picked from
	}

	@FXML
	void analyze(ActionEvent event) {
		// analyze the text in all sentiments and change them
	}

}
