package com.vaadin;

import java.util.List;

import com.vaadin.TournamentDesign;
import com.vaadin.model.Player;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class TournamentDesignImpl extends TournamentDesign {

	private List<Player> players;
	private HorizontalLayout roundsLayout = new HorizontalLayout();
	
	public TournamentDesignImpl(String name, List<Player> players) {
		tournamentNameLabel.setValue(name);
		this.players = players;
		roundHolderVLayout.removeAllComponents();
		roundHolderVLayout.addComponent(roundsLayout);
		roundsLayout.setSizeFull();
		nextRound(players);
	}

	private void nextRound(List<Player> roundPlayers) {
		TournamentRoundDesignImpl nextRound = new TournamentRoundDesignImpl(roundPlayers, this);
		roundsLayout.addComponent(nextRound);
		roundsLayout.setComponentAlignment(nextRound, Alignment.MIDDLE_LEFT);
	}

	public void allMatchesResolved(List<Player> winners) {
		if (winners.size() == 1) {
			winnerFound(winners.get(0));
		} else {
			nextRound(winners);
		}
		
	}
	
	private void winnerFound(Player winner) {
		Notification.show("Winner is found: " + winner.getName());
	}
	
}
