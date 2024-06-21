package model;

public enum Category {
	VOID, OBSTACLE, ADVERSARY, TEAM_MEMBER, TEAM1, TEAM2, TEAM3;
	
	boolean isAbsoluteTeam() {
		return this == TEAM1 || this == TEAM2 || this == TEAM3;
	}
}
