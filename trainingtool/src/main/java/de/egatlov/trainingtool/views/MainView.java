package de.egatlov.trainingtool.views;

import de.egatlov.trainingtool.controller.MainController;
import de.egatlov.trainingtool.data.ApplicationData;

public final class MainView extends View {

	private MainController mainController;

	public MainView() {
		super("MainView", "Sentiment Trainingtool (STT)", false, true);
	}

	public MainController getMainController() {
		return mainController;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void addSentimentEvent() throws Exception {
		mainController.masonry().getChildren().add(ApplicationData.get().sentimentsView().pane());
	}

}
