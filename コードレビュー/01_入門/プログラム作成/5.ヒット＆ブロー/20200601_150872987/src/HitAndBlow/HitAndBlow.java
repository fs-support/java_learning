package HitAndBlow;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HitAndBlow {

	public static void main(String[] args) {
		System.out.println("**********HIT＆BLOW**********");
		System.out.println("");
		// ランダムに数字を４つ決める

		// 正解の４つの数字を入れる配列
		ArrayList<Integer> correctNumber = new ArrayList<Integer>();

		/*
		 * 数字がかぶらないようにfor文を利用する for文で0から9までの数字をかぶりなく４つ決める かぶりなく4つの数字が決まっているか確認する
		 */

		// 重複をなくすための配列
		// 0から9までの数字を入れる
		ArrayList<Integer> notDuplicateCorrectNumber = new ArrayList<Integer>();

		for (int i = 0; i < 10; i++) {
			notDuplicateCorrectNumber.add(i);
		}

		// for文で0から9までの数字をかぶりなく４つ決める
		Random random = new Random();

		// 1つの数字を決める　4回繰り返す
		for (int i = 0; i < 4; i++) {

			// ランダムに選んだ数字をRandomの中に入れる
			int Random = random.nextInt(notDuplicateCorrectNumber.size());

			// 決めた数字を重複対策の変数に記憶する
			int decideCorrectNumber = notDuplicateCorrectNumber.get(Random);

			// 正解の配列に重複対策に記憶した変数から入れる
			correctNumber.add(decideCorrectNumber);

			// 正解の配列に入れた数字を重複対策の配列の数字から除外する
			notDuplicateCorrectNumber.remove(Random);

		}

		// 確認用：正解の数字をひょうじする
		// System.out.println(correctNumber);

		// 回答者に4つ数字を入力させ、一桁ずつにする

		// 入力した回数をカウントする
		int count = 1;

		// 正解するまで繰り返す
		while (true) {

			// 回答者が決めた４つの数字を入れる配列
			ArrayList<Integer> answerNumber = new ArrayList<Integer>();

			// ４つ数字を入力させる
			System.out.println("4桁の数字を入力してください");
			Scanner scan = new Scanner(System.in);

			int scanAnswerNumber = scan.nextInt();

			// 入力した数字を表示
			System.out.println("4桁の数字は、" + scanAnswerNumber + "です");
			/*
			 * 一桁ずつに分ける 1000の位は1000で割ってあまりを 100の位は100で割ったあまり 10は10で割ったあまり
			 * 1はそのまま余りを求める
			 */

			ArrayList<Integer> oneDigitAnswerNumber = new ArrayList<Integer>();

			oneDigitAnswerNumber.add((scanAnswerNumber / 1000) % 10);
			oneDigitAnswerNumber.add((scanAnswerNumber / 100) % 10);
			oneDigitAnswerNumber.add((scanAnswerNumber / 10) % 10);
			oneDigitAnswerNumber.add(scanAnswerNumber % 10);

			// 確認用answerNumberに1桁筒入っているか確認
			// System.out.println(oneDigitAnswerNumber);

			// 回答の中に正解と同じ数字があり順番も同じならひっと、順番だけ違う場合はぶろー
			// hitの回数をカウントする
			int hit = 0;

			// hitの場合
			for (int i = 0; i < 4; i++) {
				if (correctNumber.get(i) == oneDigitAnswerNumber.get(i)) {
					hit++;
				}
			}

			// blowの場合
			// blowの回数をカウントする
			int blow = 0;
			for (int i = 0; i < 4; i++) {
				int isoneDigitAnswerNumber = oneDigitAnswerNumber.get(i);

				// 記憶した数字の確認用
				//System.out.println(isoneDigitAnswerNumber);

				for (int j = 0; j < 4; j++) {
					if (isoneDigitAnswerNumber != correctNumber.get(j))
						continue;

					if (i != j) {
						blow++;
					}
				}
			}

			// 数字をすべてあてたとき
			if (hit == 4) {
				break;
			}

			// 不正解の時hitとblow数を表示して入力した回数に+1する
			System.out.println("hit:" + hit + "   blow:" + blow);
			count++;
		}
		// breakで抜けた後の表示
		System.out.println(count + "回目で正解しました");
	}
}
