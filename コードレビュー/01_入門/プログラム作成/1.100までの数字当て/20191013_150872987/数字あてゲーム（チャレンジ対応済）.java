package numberHit;

import java.util.Random;
import java.util.Scanner;

public class Number_Hit {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Scanner scan = new Scanner(System.in);
		int count = 0;
		Random number1 = new Random();
		int num = number1.nextInt(999);

		System.out.println("0-999の中から一つの数字を10回であてろ！");

		for (int i = 0; i < 10; i++) {
			int number = scan.nextInt();
			System.out.println("入力した数字は" + number + "です");

			if (number == num) {
				System.out.println("あたり！！");
				System.out.println("*******終わり*******");

				break;

			} else if (number > num) {
				System.out.println("はずれ・・・");
				System.out.println("あたりはより小さい数字です");
				System.out.println("");

				count++;

			} else if (number < num) {
				System.out.println("はずれ・・・");
				System.out.println("あたりはより大きい数字です");
				System.out.println("");

				count++;
			}
		}

		if (count == 5) {
			System.out.println("");
			System.out.println("5回ともはずれ・・・");
			System.out.println("");
			System.out.println("正解は"+num+"でした");
			System.out.println("");
			System.out.println("");
			System.out.println("*******終わり*******");
		}

	}

}
