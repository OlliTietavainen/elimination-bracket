package com.vaadin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.TournamentRoundDesign;
import com.vaadin.model.Match;
import com.vaadin.model.Player;
import com.vaadin.ui.VerticalLayout;

public class TournamentRoundDesignImpl extends TournamentRoundDesign {
	
	private List<Match> matches = new ArrayList<>();
	
	public TournamentRoundDesignImpl(List<Player> remainingPlayers) {
		VerticalLayout vl = new VerticalLayout();
		for (int i = 0; i < remainingPlayers.size() / 2; i = i + 2) {
			Match m = new Match();
			m.populate(remainingPlayers.get(i), remainingPlayers.get(i+1));
			matches.add(m);
		}
		Iterator<Match> iter = matches.iterator();
		while(iter.hasNext()) {
			Match match = iter.next();
			MatchDesignImpl mdi = new MatchDesignImpl(match);
			vl.addComponent(mdi);
			
		}
		tournamentRoundPanel.setContent(vl);
	}
}
