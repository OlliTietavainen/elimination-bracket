package com.vaadin.model;

public class Match {
	private Player p1;
	private Player p2;
	private int p1score = 0;
	public int getP1score() {
		return p1score;
	}

	public void setP1score(int p1score) {
		this.p1score = p1score;
	}

	public int getP2score() {
		return p2score;
	}

	public void setP2score(int p2score) {
		this.p2score = p2score;
	}

	private int p2score = 0;
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

	public void resolve(int p1score, int p2score) {
		matchStatus = MatchStatus.RESOLVED;
		setP1score(p1score);
		setP2score(p2score);
		if (p1score > p2score) {
			this.winner = p1;
		} else {
			this.winner = p2;
		}
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