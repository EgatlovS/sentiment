package de.egatlov.trainingtool.viewloader;

public enum View {

	CREATE_SENTIMENT("CreateSentimentView"), IMPROVE_ANALYSIS("ImproveAnalysisView"), LOAD_PROJECT(
			"LoadProjectView"), LOAD_SENTIMENT("LoadSentimentView"), MAIN("MainView"), SAVE_PROJECT(
					"SaveProjectView"), SAVE_SENTIMENT("SaveSentimentView"), SENTIMENT("SentimentView");

	private String value;

	private View(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return "/view/" + value + ".fxml";
	}

	public void setValue(String value) {
		this.value = value;
	}

}
