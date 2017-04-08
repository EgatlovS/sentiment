package de.egatlov.trainingtool.hyperlink;

import javafx.application.HostServices;

public class DefaultBrowser {

	private static DefaultBrowser INSTANCE;
	private final HostServices hostServices;

	private DefaultBrowser(HostServices hostServices) {
		this.hostServices = hostServices;
	}

	public static DefaultBrowser INSTANCE() {
		return INSTANCE;
	}

	public static void init(HostServices hostServices) {
		if (INSTANCE == null) {
			INSTANCE = new DefaultBrowser(hostServices);
		}
	}

	public HostServices hostServices() {
		return hostServices;
	}

}
