package de.egatlov.trainingtool.browser;

import java.io.File;

import javafx.stage.FileChooser;

public class FileBrowser {

	private final String title;

	public FileBrowser(String title) {
		this.title = title;
	}

	public FileBrowser() {
		this.title = "Choose File...";
	}

	public String chosenPath() throws Exception {
		FileChooser fileChooser = new FileChooser();
		// Extention filter
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Json files (*.json)", "*.json");
		fileChooser.getExtensionFilters().add(extentionFilter);
		// Set to user directory or go to default if cannot access
		String userDirectoryString = System.getProperty("user.home");
		File userDirectory = new File(userDirectoryString);
		if (!userDirectory.canRead()) {
			userDirectory = new File("c:/");
		}
		fileChooser.setInitialDirectory(userDirectory);
		fileChooser.setTitle(title);
		File chosenFile = fileChooser.showOpenDialog(null);
		// Make sure a file was selected, if not return default
		if (chosenFile != null) {
			return chosenFile.getPath();
		} else {
			throw new Exception("Couldn't get Path");
		}
	}

}
