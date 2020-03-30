package Battle;

import java.util.Scanner;
// JRE 1.8を使用しています

public class NameBattler {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// ==================================================
		// バトル開始準備
		// ==================================================
		System.out.println("プレイヤー1の名前を入力してください");
		Player player1 = Playernamejob();
		System.out.println("プレイヤー2の名前を入力してください");
		Player player2 = Playernamejob();

		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		System.out.println("プレイヤー1");
		player1.PrintStatus();
		System.out.println("プレイヤー2");
		player2.PrintStatus();
		System.out.println("");
		System.out.println("=== バトル開始 ===");
		int turnNumber = 1;
		// 最大でも20ターンまで
		while (turnNumber <= 20) {
			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);
			//毒処理
			if (player1.GetAbnomal() == 1) {
				player1.Poison();
			}
			if (player2.GetAbnomal() == 1) {
				player2.Poison();
			}
			//素早さ判定:プレイヤー１が早い
			if (player1.GetAGI() > player2.GetAGI()) {
				if (player1.GetAbnomal() == 2) {
					player2.Attack(player1);
					// プレイヤー１の敗北判定
					if (player1.GetHP() <= 0) {
						break;
					}
					player1.paralysis();
				} else if (player2.GetAbnomal() == 2) {
					// ■プレイヤー１の攻撃ターン
					player1.Attack(player2);
					// プレイヤー２の敗北判定
					if (player2.GetHP() <= 0) {
						break;
					}
					player2.paralysis();
				} else if (player2.GetAbnomal() == 2 && player1.GetAbnomal() == 2) {
					player1.paralysis();
					player2.paralysis();
				} else {
					// ■プレイヤー１の攻撃ターン
					player1.Attack(player2);
					// プレイヤー２の敗北判定
					if (player2.GetHP() <= 0) {
						break;
					}
					// ■プレイヤー２の攻撃ターン
					player2.Attack(player1);
					// プレイヤー１の敗北判定
					if (player1.GetHP() <= 0) {
						break;
					}
				}
				//素早さ判定:プレイヤー2が早い
			} else {
				if (player1.GetAbnomal() == 2) {
					player2.Attack(player1);
					// プレイヤー１の敗北判定
					if (player1.GetHP() <= 0) {
						break;
					}
					player1.paralysis();
				} else if (player2.GetAbnomal() == 2) {
					// ■プレイヤー１の攻撃ターン
					player1.Attack(player2);
					// プレイヤー２の敗北判定
					if (player2.GetHP() <= 0) {
						break;
					}
					player2.paralysis();
				} else if (player2.GetAbnomal() == 2 && player1.GetAbnomal() == 2) {
					player1.paralysis();
					player2.paralysis();
				} else {
					// ■プレイヤー２の攻撃ターン
					player2.Attack(player1);
					// プレイヤー１の敗北判定
					if (player1.GetHP() <= 0) {
						break;
					}
					// ■プレイヤー１の攻撃ターン
					player1.Attack(player2);
					// プレイヤー２の敗北判定
					if (player2.GetHP() <= 0) {
						break;
					}
				}
			}
			// ※④と⑤の繰り返し
			// ターン終了時のステータスを表示
			System.out.println("");
			player1.PrintStatus();
			player2.PrintStatus();

			// 次のターン
			turnNumber = turnNumber + 1;
		}

		// ⑥勝ち負けの表示(ＨＰが多い方が勝ち)
		System.out.println("");
		if (player1.GetHP() > player2.GetHP()) {
			System.out.println(player1.GetName() + "の勝利！！");
		} else {
			System.out.println(player2.GetName() + "の勝利！！");
		}

		// ==================================================
		// 終了処理
		// ==================================================
	}

	//名前と職業決定
	public static Player Playernamejob() {
		String playerName = scan.next();
		Player player;
		while (true) {
			System.out.println("ジョブを選択してください 1:戦士 2:魔法使い 3:僧侶 4:召霊士");
			String playerJob = scan.next();
			if (playerJob.equals("1")) {
				player = new Fighter(playerName);
				break;
			} else if (playerJob.equals("2")) {
				player = new Wizard(playerName);
				break;
			} else if (playerJob.equals("3")) {
				player = new Priest(playerName);
				break;
			} else if (playerJob.equals("4")) {
				player = new Conjurer(playerName);
				break;
			} else {
				System.out.println("１〜４の値を入力してください");
				continue;
			}
		}
		return player;
	}
}
