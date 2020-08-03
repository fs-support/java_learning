package nameButtler2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

public class NameButtler {

	public static void main(String[] args) {

		/*
		 * キャラを決める⇒攻撃力を決める⇒1から2・2・1に攻撃をHPが0になるまで繰り返す
		 * 攻撃力の最大値は3（最大値の変更はfinalから設定する）
		 * 
		 * 課題 ダメージの計算や死亡判定をメソッド化し繰り返し利用できるようにする 他課題2～5を行う
		 */

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
		
		// 1段階目用　HP（10で固定）
		// int chara1HP = 10;
		// int chara2HP = 10;

		//名前からHPを決める
		String name = chara1;
		int num_chara1= GetNumber(name,  (int) (Math.random() * 3));
		
		name = chara2;
		int num_chara2 = GetNumber(name,  (int) (Math.random() * 3));
		
		int chara1HP = num_chara1;
		int chara2HP = num_chara2;
		
		chara1HP = chara1HP/10;
		chara2HP = chara2HP/10;

		System.out.println("名前からHPを設定しました。");
		System.out.println(chara1 + "の最大HP：" + chara1HP);
		System.out.println(chara2 + "の最大HP：" + chara2HP);

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++++++   Name  Buttler  +++++++++++++++");
		System.out.println("++++++++++++++++  Buttle　Start!  +++++++++++++++");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("");

		// ターン数カウント
		int turnCount = 1;

		while (true) {
			System.out.println();
			System.out.println("*******" + turnCount + "ターン目*******");
			System.out.println(chara1 + "の攻撃  残HP:" + chara1HP);

			// chara1が攻撃する
			int chara1Attack = (int) (Math.random() * Max_Attack_Value);
			if (chara1Attack == 0) {
				System.out.println("回避された！");
			} else {
				System.out.println(chara2 + "に" + chara1Attack + "Hit!!");
			}
			// chara2の死亡判定
			chara2HP -= chara1Attack;
			if (isDead(chara2HP)) {
				resultWinOrLose(chara2, chara1);
				break;
			}

			System.out.println();

			// chara2が攻撃する
			System.out.println(chara2 + "の攻撃  残HP:" + chara2HP);

			// chara2の攻撃力を決める
			int chara2Attack = (int) (Math.random() * Max_Attack_Value);
			if (chara2Attack == 0) {
				System.out.println("回避された！");
			} else {
				System.out.println(chara1 + "に" + chara2Attack + "Hit!!");
			}

			// chara1の死亡判定
			chara1HP -= chara2Attack;
			if (isDead(chara1HP)) {
				resultWinOrLose(chara1, chara2);
				break;
			}

			// ターン数のカウント
			turnCount++;
		}
	}

	// 攻撃力の最大値を固定、最大値はここで変更する
	static final int Max_Attack_Value =5;

	//死亡判定
	private static boolean isDead(int hp) {
		return hp <= 0;
	}
	
	// 勝敗判定表示
	private static void resultWinOrLose(String lose, String win) {
		System.out.println();
		System.out.println(lose + "は力尽きた...");
		System.out.println(win + "の勝利！");
	}

	// ハッシュダイジェストから数値を取り出す
	// name : 名前
	// index : 何番目の数値を取り出すか
	// return 数値(0~255)
	public static Integer GetNumber(String name, Integer index) {
		try {
			byte[] result = MessageDigest.getInstance("SHA-1").digest(name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));
			String hex = digest.substring(index * 2, index * 2 + 2);
			return Integer.parseInt(hex, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
