package com.vaadin;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.model.Match;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class MatchDesignImpl extends MatchDesign {
	private Match match;
	private MatchResolutionDialogDesignImpl dialog;
	private Window window;
	private TournamentRoundDesignImpl parent;
	private LayoutClickListener windowOpener = new LayoutClickListener() {

		@Override
		public void layoutClick(LayoutClickEvent event) {
			dialog = new MatchResolutionDialogDesignImpl(window, match);
			window.setWidth(600, Unit.PIXELS);
			window.setHeight(300, Unit.PIXELS);
			window.setContent(dialog);
			window.center();
			window.setModal(true);
			UI.getCurrent().addWindow(window);
		}
		
	};
	
	public MatchDesignImpl(Match match, int matchCounter, TournamentRoundDesignImpl tournamentRoundDesignImpl) {
		this.match = match;
		parent = tournamentRoundDesignImpl;
		firstNameLabel.setValue(match.getP1().getName());
		secondNameLabel.setValue(match.getP2().getName());
		matchNumberLabel.setValue("Match number #"+matchCounter);
		window = new Window();
		matchHL.addLayoutClickListener(windowOpener);
		window.addCloseListener(listener -> {
			if (match.getStatus() == Match.MatchStatus.RESOLVED) {
				firstScoreLabel.setValue("" + match.getP1score());
				secondScoreLabel.setValue("" + match.getP2score());
				if (match.getWinner() == match.getP1()) {
					setStar(firstNameLabel);
				} else {
					setStar(secondNameLabel);
				}
				matchHL.removeLayoutClickListener(windowOpener);
				matchHL.removeStyleName("hoveremph");
				parent.matchResolved();
			}
		});
	}

	private void setStar(Label label) {
		label.setContentMode(ContentMode.HTML);
		label.setValue(FontAwesome.STAR.getHtml() + " " + label.getValue());
		
	}
}
