package shokyu;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Scanner;

public class shokyu11 {
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		Random random = new Random();

		// ■ゲームの流れ
		//１．プレイヤー名の入力プレイヤー名の入力
		//１－１．プレイヤー１の名前を決める
		//１－２．プレイヤー２の名前を決める
		System.out.println("プレイヤー１の名前を入力：");
		String Name1 = stdin.nextLine();
		System.out.println("プレイヤー２の名前を入力：");
		String Name2 = stdin.nextLine();

		//２．キャラクターの作成キャラクターの作成
		//２－１．プレイヤー１のキャラクターを作成
		int player1HP = GetNumber(Name1, 0) * 5;
		int player1STR = GetNumber(Name1, 1);
		int player1DEF = GetNumber(Name1, 2);
		int player1LUCK = GetNumber(Name1, 3) * 50 / 255;//運は答えを参照しました

		// ２ー２．プレイヤー２のキャラクターを作成
		int player2HP = GetNumber(Name2, 0) * 5;
		int player2STR = GetNumber(Name2, 1);
		int player2DEF = GetNumber(Name2, 2);
		int player2LUCK = GetNumber(Name2, 3) * 50 / 255;//運は答えを参照しました

		// ３．バトル開始の表示
		System.out.println("プレイヤー１：" + Name1 + "(HP " + player1HP + " / STR " + player1STR + " / DEF "
				+ player1DEF + " / LUCK " + player1LUCK + ")");
		System.out.println("プレイヤー２：" + Name2 + "(HP " + player2HP + " / STR " + player2STR + " / DEF "
				+ player2DEF + " / LUCK " + player2LUCK + ")");
		System.out.println("");
		System.out.println("=== バトル開始 ===");

		while (true) {
			int power;
			int damage;
			int luck;

			// ４．プレイヤー１の攻撃ターン
			// ４－１．プレイヤー１の攻撃
			System.out.println(Name1 + "の攻撃！");
			power = random.nextInt(player1STR) + 1;

			// ４－２．プレイヤー２のダメージ
			luck = random.nextInt(100);
			if (luck <= player1LUCK) {
				System.out.println("会心の一撃!");
				damage = power;
			} else {
				damage = power - player2DEF;
			}
			//運の周りは模範解答を参照

			// ダメージ０以下の場合は１を確実に与える
			if (damage <= 0) {
				damage = 1;
			}
			System.out.println(Name2 + "に" + damage + "のダメージ！");
			player2HP = player2HP - damage;

			// ４－３．プレイヤー２の死亡判定→６へ
			if (player2HP <= 0) {
				System.out.println(Name2 + "は力尽きた");
				break;
			}

			// ５．プレイヤー２の攻撃ターン
			// ５－１．プレイヤー２の攻撃
			System.out.println(Name2 + "の攻撃！");
			power = random.nextInt(player2STR) + 1;

			// ５－２．プレイヤー１のダメージ
			luck = random.nextInt(100);
			if (luck <= player2LUCK) {
				System.out.println("会心の一撃!");
				damage = power;
			} else {
				damage = power - player1DEF;
			}
			//運は模範解答を参照

			// ダメージが０以下の場合は１を確実に与える
			if (damage <= 0) {
				damage = 1;
			}
			System.out.println(Name1 + "に" + damage + "のダメージ！");
			player1HP = player1HP - damage;

			// ５－２．プレイヤー１の死亡判定→６へ
			if (player1HP <= 0) {
				System.out.println(Name1 + "は力尽きた");
				break;
			}

			//※どちらかのＨＰが０になるまで、４と５の繰り返し
			System.out.println("");
			System.out.println("- 次のターン -");
			System.out.println("プレイヤー１：" + Name1 + "(HP " + player1HP + ")");
			System.out.println("プレイヤー２：" + Name2 + "(HP " + player2HP + ")");
			System.out.println("--------------------------------");
		}

		// ６．勝ち負けの表示
		System.out.println("");
		if (player1HP <= 0) {
			System.out.println(Name2 + "の勝利！！");
		} else {
			System.out.println(Name1 + "の勝利！！");
		}

		// プログラム終了前の後片付け
		stdin.close();
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