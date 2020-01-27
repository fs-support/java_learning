import java.util.Random;

public class RandomGenerator {

	/**
	 * 最小値と最大値を渡してその範囲内のランダムな数字を返す
	 * @param min
	 * @param max
	 * @return
	 */
	public static int RandomRange(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	/**
	 * 成功確率のパーセンテージを渡して成功したかどうかを判定する
	 * @param probability
	 * @return
	 */
	public static boolean RandomJudge(int probability) {
		int judgementValue = RandomRange(0, 100);

		if (probability >= judgementValue) {
			return true;
		}
		return false;

	}
}
