package nameButtler;

import java.util.Scanner;

public class NameButtler {

	public static void main(String[] args) {

		// キャラ1とキャラ2の名前を決める
		// キャラ1⇒chara1
		// キャラ2⇒chara2

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++++   Name  Buttler  +++++++++++++++");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("");
		System.out.println("キャラ1の名前を決めてください");

		Scanner sc = new Scanner(System.in);
		String chara1 = sc.next();

		System.out.println("");

		System.out.println("キャラ2の名前を決めてください");

		sc = new Scanner(System.in);
		String chara2 = sc.next();

		System.out.println("");

		System.out.println("キャラ1 : " + chara1);
		System.out.println("キャラ2 : " + chara2);

		System.out.println("");

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++++   Name  Buttler  +++++++++++++++");
		System.out.println("++++++++++++++++  Buttle　Start!  +++++++++++++++");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("");

		// フルHP
		int chara1HP = 10;
		int chara2HP = 10;

		//ターン数カウント
		int turnCount = 1;
		
		while (true) {
			System.out.println();
			System.out.println("*******"+turnCount+"ターン目*******");
			System.out.println(chara1 + "の攻撃  残HP:" + chara1HP);

			// chara1が攻撃する
			int chara1Attack = (int) (Math.random() * 3);
			if (chara1Attack == 0) {
				System.out.println("回避された！");
			} else {
				System.out.println(chara2 + "に" + chara1Attack + "Hit!!");
			}
			// chara2の死亡判定
			chara2HP -= chara1Attack;
			if (chara2HP <= 0) {
				System.out.println();
				System.out.println(chara2 + "は力尽きた...");
				System.out.println(chara1 + "の勝利！");
				break;
			}

			System.out.println();

			// chara2が攻撃する
			System.out.println(chara2 + "の攻撃  残HP:" + chara2HP);

			// chara2の攻撃力を決める
			int chara2Attack = (int) (Math.random() * 3);
			if (chara2Attack == 0) {
				System.out.println("回避された！");
			} else {
				System.out.println(chara1 + "に" + chara2Attack + "Hit!!");
			}

			// chara1の死亡判定
			chara1HP -= chara2Attack;
			if (chara1HP <= 0) {
				System.out.println();
				System.out.println(chara1 + "は力尽きた...");
				System.out.println(chara2 + "の勝利！");
				break;
				
				
			}
			
			//ターン数のカウント
			turnCount++;
		}
	}
}
