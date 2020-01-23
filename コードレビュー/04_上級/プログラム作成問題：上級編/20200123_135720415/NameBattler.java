package jp.co.FSsakusei3;

import java.util.Scanner;

// JRE 1.8を使用しています

public class NameBattler {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// ==================================================
		// バトル開始準備
		// ==================================================
		// プレイヤー１の名前を入力させてキャラクターを生成
		// プレイヤー２の名前を入力させるキャラクターを生成
		// player2Name = scan.next();

		// ■処理の流れ
		// プレイヤー１のキャラクターを作成
		System.out.println("プレイヤー1の名前を入力してください");
		Player player1 = CreatePlayer();

		System.out.println("プレイヤー2の名前を入力してください");
		Player player2 = CreatePlayer();
		// プレイヤー２のキャラクターを作成
		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		player1.PrintStatus();
		player2.PrintStatus();
		System.out.println("");
		System.out.println("=== バトル開始 ===");

		int turnNumber = 1;
		// 最大でも20ターンまで
		while (turnNumber <= 20) {
			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);
			Player x;
			Player y;

			if (player1.GetAGI() >= player2.GetAGI()) {
				x = player1;
				y = player2;
			} else {
				x = player2;
				y = player1;
			}
			// ■プレイヤー１の攻撃ターン
			if (x.GetSTATE().equals("麻痺")) {
				if (x.Paralysis() >= 20) {
					x.Attack(y);
				} else {
					System.out.println(x.GetName() + "は麻痺で動けない");
				}
			} else {
				x.Attack(y);
			}
			// プレイヤー２の敗北判定
			if (y.GetHP() <= 0) {
				break;
			}

			// ■プレイヤー２の攻撃ターン
			if (y.GetSTATE().equals("麻痺")) {
				if (y.Paralysis() >= 20) {
					y.Attack(x);
				} else {
					System.out.println(y.GetName() + "は麻痺で動けない");
				}
			} else {
				y.Attack(x);
			}
			// プレイヤー１の敗北判定
			if (x.GetHP() <= 0) {
				break;
			}

			if (x.GetSTATE().equals("毒")) {
				x.PoisonDamage();
			}

			if (x.GetHP() <= 0) {
				break;
			}

			if (y.GetSTATE().equals("毒")) {
				y.PoisonDamage();
			}

			if (y.GetHP() <= 0) {
				break;
			}
			// }
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

	public static Player CreatePlayer() {
		// プレイヤーの名前を入力
		System.out.println("名前を入力してください");
		String playerName = scan.next();
		Player player;
		while (true) {
			System.out.println("ジョブを選択してください A:戦士 B:魔法使い C:僧侶 D:勇者");
			String playerJob = scan.next();
			if (playerJob.equals("A")) {
				player = new Fighter(playerName);
				break;
			} else if (playerJob.equals("B")) {
				player = new Wizard(playerName);
				break;
			} else if (playerJob.equals("C")) {
				player = new Priest(playerName);
				break;
			} else if (playerJob.equals("D")) {
				player = new Brave(playerName);
				break;
			} else {
				System.out.println("正しい値を入力してください");
			}
		}
		return player;
	}

}
