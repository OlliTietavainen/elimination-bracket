package com.vaadin;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;

public class InitDialogDesignImpl extends InitDialogDesign {
	private Window window;
	
	public InitDialogDesignImpl(Window window) {
		this.window = window;
		playersTextArea.addValueChangeListener(listener -> {
			String v = playersTextArea.getValue();
			Notification.show("v");
		});
	}
}
