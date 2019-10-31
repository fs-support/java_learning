package jp.co.FStestNM01;

import java.util.Random;
import java.util.Scanner;

public class FStestNM01 {

	public static void main(String[] args) throws Exception {

		// コンピュータの数字を決める
		Random random = new Random();
//		int number = random.nextInt(100);
		int number = random.nextInt(1000);

		System.out.println("数字を当ててみてね。");
//		System.out.println("答えられるのは５回までだよ。");
		System.out.println("答えられるのは１０回までだよ。");
		System.out.println();

		Scanner stdin = new Scanner(System.in);
//		for (int count = 1; count <= 5; count++) {
		for (int count = 1; count <= 10; count++) {
			System.out.print(count + "回目：");

			int inputNumber = stdin.nextInt();
			System.out.println(inputNumber);

			int s = (number - inputNumber); //追加
			int s2 = (inputNumber - number); //追加

			if (number == inputNumber) {
				System.out.print("すごい！！" + count + "回で当てられちゃった！");

				// 終了処理
				stdin.close();
				return;
			}
//			if (number < inputNumber) {
			if ((51 <= s && s <= 100) || (51 <= s2 && s2 <= 100)){

//				System.out.println("もっと小さい数字だよ");
				System.out.println("近いよ");

			} else if ((21 <= s && s <= 50) || (21 <= s2 && s2 <= 50)){ //追加
				System.out.println("あと少しだよ");

			} else if ((11 <= s && s <= 20) || (11 <= s2 && s2 <= 20)){ //追加
				System.out.println("おしいよ");

			} else if ((1 <= s && s <= 10) || (1 <= s2 && s2 <= 10)){ //追加
				System.out.println("もうすぐ答えだよ");

			} else {
//				System.out.println("もっと大きい数字だよ");
				System.out.println("遠いよ");
			}
		}

		System.out.println("残念！！正解は " + number + " でした！");

		// 終了処理
		stdin.close();
	}
}
