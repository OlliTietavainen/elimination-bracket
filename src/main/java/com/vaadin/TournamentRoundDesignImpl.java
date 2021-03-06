package com.vaadin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.TournamentRoundDesign;
import com.vaadin.model.Match;
import com.vaadin.model.Player;
import com.vaadin.model.Match.MatchStatus;
import com.vaadin.ui.VerticalLayout;

public class TournamentRoundDesignImpl extends TournamentRoundDesign {
	
	private List<Match> matches = new ArrayList<>();
	private TournamentDesignImpl parent;
	
	public static int matchCounter = 1;
	
	
	public TournamentRoundDesignImpl(List<Player> remainingPlayers, TournamentDesignImpl tournamentDesignImpl) {
		parent = tournamentDesignImpl;
		VerticalLayout vl = new VerticalLayout();
		for (int i = 0; i < remainingPlayers.size() / 2; i++) {
			Match m = new Match();
			matches.add(m);
		}
		Iterator<Match> matchIter = matches.iterator();
		Iterator<Player> playerIter = remainingPlayers.iterator();
		while(matchIter.hasNext()) {
			Match match = matchIter.next();
			match.populate(playerIter.next(), playerIter.next());
			MatchDesignImpl mdi = new MatchDesignImpl(match, matchCounter++, this);
			vl.addComponent(mdi);
			
		}
		tournamentRoundPanel.setContent(vl);
	}
	
	public void matchResolved() {
		List<Player> winners = new ArrayList<>();
		for (Match m : matches) {
			if (m.getStatus() != MatchStatus.RESOLVED) {
				return;
			}
			winners.add(m.getWinner());
		}
		parent.allMatchesResolved(winners);
	}
	
}
