package com.vaadin.model;

public class Match {
	private Player p1;
	private Player p2;
	private Player winner;
	private MatchStatus matchStatus;

	public enum MatchStatus {
		NOT_READY, READY, ONGOING, RESOLVED
	}

	public void populate(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		matchStatus = MatchStatus.READY;
	}

	public Match() {
		matchStatus = MatchStatus.NOT_READY;
	}

	public MatchStatus getStatus() {
		return matchStatus;
	}

	public void start() {
		matchStatus = MatchStatus.ONGOING;
	}

	public void resolve(Player winner) {
		this.winner = winner;
		matchStatus = MatchStatus.RESOLVED;
	}

	public Player getP1() {
		return p1;
	}

	public Player getP2() {
		return p2;
	}

	public Player getWinner() {
		if (matchStatus != MatchStatus.RESOLVED) {
			return null;
		}
		return winner;
	}

}