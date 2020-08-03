package middle.pokimon;

public enum BallKind {
	BALL1("ノーマル捕獲玉", 0), 
	BALL2("スーパー捕獲玉", 20), 
	BALL3("ミラクル捕獲玉", 50);

	private String name;
	private int correct;

	BallKind(String name, int correct) {
		this.name = name;
		this.correct = correct;
	}

	String getName() {
		return this.name;
	}

	int getCorrect() {
		return this.correct;
	}
}
