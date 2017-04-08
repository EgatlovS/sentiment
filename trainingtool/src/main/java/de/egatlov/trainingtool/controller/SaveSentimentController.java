package de.egatlov.trainingtool.controller;

import java.io.File;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SaveSentimentController {

	@FXML
	private JFXTextField saveToTF;

	@FXML
	private JFXButton browseBtn;

	@FXML
	private JFXButton saveBtn;

	@FXML
	private JFXButton cancelBtn;

	@FXML
	private JFXComboBox<?> choseSentimentCB;

	@FXML
	void browse(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		// Set to user directory or go to default if cannot access
		String userDirectoryString = System.getProperty("user.home");
		File userDirectory = new File(userDirectoryString);
		if (!userDirectory.canRead()) {
			userDirectory = new File("c:/");
		}
		directoryChooser.setInitialDirectory(userDirectory);
		directoryChooser.setTitle("Choose save location...");
		// Show open file dialog
		File file = directoryChooser.showDialog(null);
		if (file != null) {
			saveToTF.setText(file.getPath());
		}
	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save(ActionEvent event) {

	}

}
