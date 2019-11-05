import java.util.Random;

public class RandomGenerator {

	public static int RandomRange(int min,int max){
		Random random = new Random();
		return random.nextInt(max-min)+min;
	}

	public static boolean RandomJudge(int probability) {
		int judgementValue = RandomRange(0,100);

		if (probability >= judgementValue) {
			return true;
		} else {
			return false;
		}


	}
}
