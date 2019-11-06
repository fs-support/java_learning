package name_battlerM2;

import java.util.Random;
import java.util.Scanner;

public class GameManager {

	Scanner stdin = new Scanner(System.in);

	public void Start() {

		Random rnd = new Random();

		// ==================================================
		// バトル開始準備
		// ==================================================

		Player player1 = null;
		player1 = Jobselect(player1, 1);

		Player player2 = null;
		player2 = Jobselect(player2, 2);

		stdin.close();

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

			//素早さが同じ場合、ランダムで先に攻撃するようにする
			int select = 3;
			if (player1.GetAGI() == player2.GetAGI()) {

				//selectが0の時はプレイヤー1、1の時はプレイヤー2が先に攻撃するようにする
				select = rnd.nextInt(2);
			}
			//素早さの高いほうから先に攻撃をする
			if ((player1.GetAGI() > player2.GetAGI()) || select == 0) {

				// ■プレイヤー１から攻撃
				//状態異常：麻痺の判定
				if (player1.Status_check() == 1) {
					System.out.println(player1.GetName() + "は、身体が痺れて動けない！");
				} else {
					player1.Attack(player2);
				}
				//状態異常：毒の判定
				if (player1.Status_check() == 2) {
					player1.PoisonDamage();
				}
				// プレイヤー２の敗北判定
				if (player2.GetHP() <= 0) {
					break;
				}
				//状態異常の自己治癒判定
				if (!player1.GetStatus().equals(Player.Status.Nomal)) {
					player1.Recovery();
				}

				// ■プレイヤー２の攻撃ターン
				if (player2.Status_check() == 1) {
					System.out.println(player2.GetName() + "は、身体が痺れて動けない！\n");
				} else {
					player2.Attack(player1);
				}
				if (player2.Status_check() == 2) {
					player2.PoisonDamage();
				}
				// プレイヤー１の敗北判定
				if (player1.GetHP() <= 0) {
					break;
				}
				//状態異常の自己治癒判定
				if (!player2.GetStatus().equals(Player.Status.Nomal)) {
					player2.Recovery();
				}

			} else if (player1.GetAGI() < player2.GetAGI() || select == 1) {

				// ■プレイヤー２の攻撃ターン
				if (player2.Status_check() == 1) {

					System.out.println(player2.GetName() + "は、身体が痺れて動けない！\n");
				} else {
					player2.Attack(player1);
				}
				if (player2.Status_check() == 2) {
					player2.PoisonDamage();
				}
				// プレイヤー１の敗北判定
				if (player1.GetHP() <= 0) {
					break;
				}
				//状態異常の自己治癒判定
				if (!player2.GetStatus().equals(Player.Status.Nomal)) {
					player2.Recovery();
				}
				// ■プレイヤー１の攻撃
				if (player1.Status_check() == 1) {
					System.out.println(player1.GetName() + "は、身体が痺れて動けない！\n");
				} else {
					player1.Attack(player2);
				}
				if (player1.Status_check() == 2) {
					player1.PoisonDamage();
				}
				// プレイヤー２の敗北判定
				if (player2.GetHP() <= 0) {
					break;
				}
				//状態異常の自己治癒判定
				if (!player1.GetStatus().equals(Player.Status.Nomal)) {
					player1.Recovery();
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

	//職業選択
	protected Player Jobselect(Player player, int playerNumber) {

		System.out.print("プレイヤー" + playerNumber + "の名前を入力してください:");
		String PlayerName = stdin.next();
		System.out.println("職業を選択してください");
		System.out.print("(1)戦士 (2)魔法使い (3)僧侶 (4)勇者:");
		int PlayerJOB = stdin.nextInt();

		// プレイヤー１のキャラクターを作成

		while (PlayerJOB != 1 && PlayerJOB != 2 && PlayerJOB != 3 && PlayerJOB != 4) {

			System.out.println("正しい数値を入力してください");

		}
		if (PlayerJOB == 1) {

			player = new Fighter(PlayerName);
		} else if (PlayerJOB == 2) {

			player = new Wizard(PlayerName);
		} else if (PlayerJOB == 3) {

			player = new Priest(PlayerName);
		} else {

			player = new Hero(PlayerName);
		}

		return player;
	}
}
