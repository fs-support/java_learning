package middle.pokimon;

public class CaptureBall {
	private String name;
	private int correct;
	private int count;

	CaptureBall(BallKind ball, int count) {
		name = ball.getName();
		correct = ball.getCorrect();
		this.count = count;
	}

	String Name() {
		return this.name;
	}

	int Correct() {
		return this.correct;
	}

	int Count() {
		return this.count;
	}

	/**
	 * 捕獲玉を使用する(１個減らす)
	 */
	void Use() {
		if (count > 0) {
			count--;
			System.out.println("ボールを投げた");
		} else {
			System.out.println("ボールがない");
		}
	}
}
