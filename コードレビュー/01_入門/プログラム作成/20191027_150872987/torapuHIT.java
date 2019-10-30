package toranpuHit;

import java.util.Random;
import java.util.Scanner;

public class Toranpu_Hit {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		String[] mark = { "ハート", "ダイヤ", "スペード", "クローバー" };
		String[] number = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

		Scanner scan = new Scanner(System.in);
		//int count = 0;
		Random toranpu = new Random();
		int toranpumark = toranpu.nextInt(4);
		int toranpunumber = toranpu.nextInt(12);
		int count = 0;

		System.out.println("トランプを選びました。");
		System.out.println("トランプのマークをあててください（0～3の数字を入力）");
		System.out.println("");
		System.out.println("");
		System.out.println("**************");
		System.out.println("0:ハート");
		System.out.println("1:ダイヤ");
		System.out.println("2:スペード");
		System.out.println("3:クローバー");
		System.out.println("**************");
		System.out.println("");
		System.out.println("");

		//		while (true) {
		for (int i = 0; i < 2; i++) {
			int choismark = 0;
			System.out.println("0-3を入力してください ＊2回まで＊");
			choismark = scan.nextInt();

			if (choismark > 3 || choismark < 0) {
				System.out.println("0-3を入力してください");

				count++;

			} else if (toranpumark != choismark) {
				count++;
				System.out.println("はずれ・・・");
				System.out.println(mark[choismark] + "ではないです");

			} else {

				System.out.println("正解！");
				System.out.println(mark[toranpumark] + "です");

				break;
			}
		}

		if (count == 2) {
			System.out.println("2回とも外れ・・・");
			System.out.println("正解は" + mark[toranpumark] + "でした");

		}

		System.out.println("");
		System.out.println("次はトランプの数字を当ててください");

		//		while (true) {
		for (int i = 0; i < 4; i++) {
			System.out.println("トランプの数字を入力して下さい（0～12）");
			int choisnumber = 0;
			choisnumber = scan.nextInt();

			if (choisnumber > 12 || choisnumber < 0) {
				System.out.println("1から12の数字を入力してください");
				count++;

			} else if (toranpunumber != choisnumber) {
				System.out.println("外れ・・・");
				System.out.println(number[choisnumber] + "ではないです");

				count++;
			} else {
				System.out.println("正解！");
				System.out.println(number[toranpunumber] + "です");
				break;
			}
		}

		if (count == 4)

		{
			System.out.println("");
			System.out.println("");
			System.out.println("4回とも外れです");
			System.out.println("正解は" + number[toranpunumber] + "でした");
			System.out.println("");
			System.out.println("");
			System.out.println("*********************************************");
			System.out.println("*******************終わり********************");
			System.out.println("*********************************************");
		}
	}
}