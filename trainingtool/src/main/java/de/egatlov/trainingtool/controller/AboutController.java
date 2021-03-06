package de.egatlov.trainingtool.controller;

import com.jfoenix.controls.JFXButton;

import de.egatlov.trainingtool.browser.WebBrowser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public final class AboutController {

	@FXML
	private Hyperlink hyperlink;

	@FXML
	private JFXButton backBtn;

	@FXML
	void back(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void call(ActionEvent event) {
		WebBrowser.INSTANCE().hostServices().showDocument("https://github.com/EgatlovS");
	}

}