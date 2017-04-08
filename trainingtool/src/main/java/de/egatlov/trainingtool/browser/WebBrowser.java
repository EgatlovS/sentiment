package de.egatlov.trainingtool.browser;

import javafx.application.HostServices;

public final class WebBrowser {

	private static WebBrowser INSTANCE;
	private final HostServices hostServices;

	private WebBrowser(HostServices hostServices) {
		this.hostServices = hostServices;
	}

	public static WebBrowser INSTANCE() {
		return INSTANCE;
	}

	public static void init(HostServices hostServices) {
		if (INSTANCE == null) {
			INSTANCE = new WebBrowser(hostServices);
		}
	}

	public HostServices hostServices() {
		return hostServices;
	}

}
