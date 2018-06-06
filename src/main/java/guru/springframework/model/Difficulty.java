package guru.springframework.model;


public enum Difficulty {
	EASY("Easy"),
	MODERATE("Moderate"),
	HARD("Hard");
	String label;
	private Difficulty(String label) {
		this.label = label;
	}
	public String getLabel(){
		return this.label;
	}
}
