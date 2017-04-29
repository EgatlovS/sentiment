package de.egatlov.trainingtool.data;

import de.egatlov.sentiment_api.sentiment.control.SentimentCU;
import de.egatlov.trainingtool.views.AboutView;
import de.egatlov.trainingtool.views.CreateSentimentView;
import de.egatlov.trainingtool.views.ImproveAnalysisView;
import de.egatlov.trainingtool.views.LoadSentimentView;
import de.egatlov.trainingtool.views.MainView;
import de.egatlov.trainingtool.views.SaveSentimentView;
import de.egatlov.trainingtool.views.SentimentView;

public final class ApplicationData {

	private static ApplicationData DATA;
	// Views
	private CreateSentimentView createSentimentView = new CreateSentimentView();
	private ImproveAnalysisView improveAnalysisView = new ImproveAnalysisView();
	private LoadSentimentView loadSentimentView = new LoadSentimentView();
	private MainView mainView = new MainView();
	private SaveSentimentView saveSentimentView = new SaveSentimentView();
	private SentimentView sentimentsView = new SentimentView();
	private AboutView aboutView = new AboutView();
	// SentimentControlUnit
	private SentimentCU controlUnit;
	private String lastAnalyzedText;

	private ApplicationData() {
	}

	public static ApplicationData get() {
		if (DATA == null) {
			DATA = new ApplicationData();
		}
		return DATA;
	}

	public SentimentCU getControlUnit() {
		if (controlUnit == null) {
			controlUnit = new SentimentCU();
		}
		return controlUnit;
	}

	public void setControlUnit(SentimentCU controlUnit) {
		this.controlUnit = controlUnit;
	}

	public CreateSentimentView createSentimentView() {
		return createSentimentView;
	}

	public ImproveAnalysisView improveAnalysisView() {
		return improveAnalysisView;
	}

	public LoadSentimentView loadSentimentView() {
		return loadSentimentView;
	}

	public MainView mainView() {
		return mainView;
	}

	public SaveSentimentView saveSentimentView() {
		return saveSentimentView;
	}

	public SentimentView sentimentsView() {
		return sentimentsView;
	}

	public AboutView aboutView() {
		return aboutView;
	}

	public String getLastAnalyzedText() {
		return lastAnalyzedText;
	}

	public void setLastAnalyzedText(String lastAnalyzedText) {
		this.lastAnalyzedText = lastAnalyzedText;
	}

}
