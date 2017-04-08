package de.egatlov.trainingtool.browser;

import java.io.File;

import javafx.stage.DirectoryChooser;

public final class DirectoryBrowser {

	private final String title;

	public DirectoryBrowser(String title) {
		this.title = title;
	}

	public DirectoryBrowser() {
		this.title = "Choose Directory...";
	}

	public String chosenPath() throws Exception {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		// Set to user directory or go to default if cannot access
		String userDirectoryString = System.getProperty("user.home");
		File userDirectory = new File(userDirectoryString);
		if (!userDirectory.canRead()) {
			userDirectory = new File("c:/");
		}
		directoryChooser.setInitialDirectory(userDirectory);
		directoryChooser.setTitle(title);
		// Show open file dialog
		File file = directoryChooser.showDialog(null);
		if (file != null) {
			return file.getPath();
		} else {
			throw new Exception("Couldn't get Path");
		}
	}

}
