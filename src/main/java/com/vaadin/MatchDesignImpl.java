package com.vaadin;

import com.vaadin.model.Match;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class MatchDesignImpl extends MatchDesign {
	private Match match;
	private MatchResolutionDialogDesignImpl dialog;
	
	public MatchDesignImpl(Match match, int matchCounter) {
		this.match = match;
		firstNameLabel.setValue(match.getP1().getName());
		secondNameLabel.setValue(match.getP2().getName());
		matchNumberLabel.setValue("Match number #"+matchCounter);
		Window window = new Window();
		matchHL.addLayoutClickListener(event -> {
			dialog = new MatchResolutionDialogDesignImpl(window, match);
			window.setPosition(event.getClientX(), event.getClientY());
			window.setWidth(600, Unit.PIXELS);
			window.setHeight(300, Unit.PIXELS);
			window.setContent(dialog);
			UI.getCurrent().addWindow(window);
		});
		window.addCloseListener(listener -> {
			firstScoreLabel.setValue(":" + match.getP1score());
			secondScoreLabel.setValue(":" + match.getP2score());
		});
	}
}
