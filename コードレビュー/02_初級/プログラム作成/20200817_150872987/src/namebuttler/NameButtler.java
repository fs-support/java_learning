package namebuttler;

import java.math.BigInteger;
import java.security.MessageDigest;
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

		// 名前からキャラ1のHPと最大攻撃力を決める
		String name = chara1;
		int chara1HP = 0; // GetNumber(name, (int) (Math.random() * 10));

		chara1HP /= 5;

		// HPが0だった場合0以外になるまで繰り返す
		while (true) {
			if (chara1HP == 0) {
				chara1HP = GetNumber(name, (int) (Math.random() * 10));

				chara1HP /= 5;

			} else {
				break;
			}
		}

		// 最大攻撃力を決める
		int Max_attack_chara1 = GetNumber(name, (int) (Math.random() * 10));

		// 確認用
		// System.out.println(Max_attack_chara1);

		Max_attack_chara1 /= 10;

		System.out.println("名前からHPと攻撃力を設定しました。");
		System.out.println(chara1 + "の最大HP：" + chara1HP);
		System.out.println(chara1 + "	の最大攻撃力：" + Max_attack_chara1);

		System.out.println();

		// 名前からキャラ2のHPと最大攻撃力を決める
		name = chara2;
		int chara2HP = GetNumber(name, (int) (Math.random() * 10));

		chara2HP /= 5;

		// HPが0の場合0以外になるまで繰り返し
		while (true) {
			if (chara2HP == 0) {
				chara2HP = GetNumber(name, (int) (Math.random() * 10));

				chara2HP /= 5;

			} else {
				break;
			}
		}

		// 最大攻撃力を決める
		int Max_attack_chara2 = GetNumber(name, (int) (Math.random() * 10));

		// 確認用
		// System.out.println(Max_attack_chara2);

		Max_attack_chara2 /= 10;

		System.out.println("名前からHPを設定しました。");
		System.out.println(chara2 + "の最大HP：" + chara2HP);
		System.out.println(chara2 + "	の最大攻撃力：" + Max_attack_chara2);

		System.out.println();

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
			int chara1Attack = (int) (Math.random() * Max_attack_chara1);
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
			int chara2Attack = (int) (Math.random() * Max_attack_chara2);
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

	// 死亡判定
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
			byte[] result = MessageDigest.getInstance("SHA-1").digest(
					name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));
			String hex = digest.substring(index * 2, index * 2 + 2);
			return Integer.parseInt(hex, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}