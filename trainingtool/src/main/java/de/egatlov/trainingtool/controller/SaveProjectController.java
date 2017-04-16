package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.egatlov.sentiment_api.json.Json;
import de.egatlov.trainingtool.browser.DirectoryBrowser;
import de.egatlov.trainingtool.data.ApplicationData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public final class SaveProjectController {

	@FXML
	private JFXTextField saveToTF;

	@FXML
	private JFXButton browseBtn;

	@FXML
	private JFXButton saveBtn;

	@FXML
	private JFXButton cancelBtn;

	@FXML
	void browse(ActionEvent event) {
		DirectoryBrowser directoryBrowser = new DirectoryBrowser();
		try {
			saveToTF.setText(directoryBrowser.chosenPath());
		} catch (Exception e) {
			// Do nothing because user just didn't pick path
		}
	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save(ActionEvent event) throws Exception {
		// TODO fix bug in writing map to json and read map from json
		// TODO catch exception and tell user that it couldnt be saved
		new Json(saveToTF.getText() + "/project.json").write(ApplicationData.get().getControlUnit());
	}

}