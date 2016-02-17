package com.vaadin;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.vaadin.model.Player;
import com.vaadin.ui.Window;

public class InitDialogDesignImpl extends InitDialogDesign {
	private Window window;
	private MyUI parent;
	private List<Player> players = new ArrayList<>();
	
	private boolean isPowerOfTwo(int n) {
	  return ((n & (n - 1)) == 0);
	}
	
	public InitDialogDesignImpl(Window window, MyUI ui) {
		parent = ui;
		this.window = window;
		startButton.addClickListener(listener -> {
			List<Player> players = getPlayers();
			if (players != null && players.size() > 0) {
				ui.startTournament(players, tournamentNameTextField.getValue());
				window.close();
			}
		});
		playersTextArea.addValueChangeListener(listener -> {
			String v = playersTextArea.getValue();
			StringTokenizer st = new StringTokenizer(v, "\n");
			players.clear();
			while (st.hasMoreTokens()) {
				players.add(new Player(st.nextToken()));
			}
			if (players.size() > 0 && isPowerOfTwo(players.size())) {
				startButton.setEnabled(true);
			} else {
				startButton.setEnabled(false);
			}
		});
		
	}
	
	private List<Player> getPlayers() {
		return players;
	}
	
}
