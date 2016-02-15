package com.vaadin;

import java.util.List;

import com.vaadin.TournamentDesign;
import com.vaadin.model.Player;

public class TournamentDesignImpl extends TournamentDesign {

	private List<Player> players;
	
	public TournamentDesignImpl(String name, List<Player> players) {
		tournamentNameLabel.setValue(name);
		this.players = players;
		nextRound();
	}

	private void nextRound() {
		TournamentRoundDesignImpl nextRound = new TournamentRoundDesignImpl(players);
	}
	
}
