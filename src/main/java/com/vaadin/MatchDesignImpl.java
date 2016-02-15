package com.vaadin;

import com.vaadin.MatchDesign;
import com.vaadin.model.Match;

public class MatchDesignImpl extends MatchDesign {
	private Match match;
	
	public MatchDesignImpl(Match match) {
		this.match = match;
		firstNameLabel.setValue(match.getP1().getName());
		secondNameLabel.setValue(match.getP2().getName());
	}
}
