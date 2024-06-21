package model;

public enum Category {
	VOID, OBSTACLE, ADVERSARY, TEAM_MEMBER, TEAM1, TEAM2, TEAM3, TEAM4;

	public boolean isSameTeam(Category cat) {
		return this.equals(cat);
	}
}
