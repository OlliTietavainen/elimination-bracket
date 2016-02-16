package com.vaadin;

import java.util.List;

import com.vaadin.TournamentDesign;
import com.vaadin.model.Player;
import com.vaadin.ui.HorizontalLayout;

public class TournamentDesignImpl extends TournamentDesign {

	private List<Player> players;
	private HorizontalLayout roundsLayout = new HorizontalLayout();
	
	public TournamentDesignImpl(String name, List<Player> players) {
		tournamentNameLabel.setValue(name);
		this.players = players;
		roundHolderVLayout.removeAllComponents();
		roundHolderVLayout.addComponent(roundsLayout);
		roundsLayout.setSizeFull();
		nextRound();
	}

	private void nextRound() {
		TournamentRoundDesignImpl nextRound = new TournamentRoundDesignImpl(players);
		roundsLayout.addComponent(nextRound);
	}
	
}
