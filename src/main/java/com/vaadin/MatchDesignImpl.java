package com.vaadin;

import com.vaadin.MatchDesign;
import com.vaadin.model.Match;

public class MatchDesignImpl extends MatchDesign {
	private Match match;
	
	public MatchDesignImpl(Match match, int matchCounter) {
		this.match = match;
		firstNameLabel.setValue(match.getP1().getName());
		secondNameLabel.setValue(match.getP2().getName());
		matchNumberLabel.setValue("Match number #"+matchCounter);
		firstMatchHL.addLayoutClickListener(event -> System.out.println("clicked"));
		
	}
}
