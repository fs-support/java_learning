import java.util.Random;
import java.util.Scanner;

// JRE 1.8を使用しています

public class NameBattler {

	public static void main(String[] args) {
		// ==================================================
		// バトル開始準備
		// ==================================================
		// プレイヤー１の名前を入力させてキャラクターを生成
		Scanner stdin = new Scanner(System.in);
		boolean order;
		Random random = new Random();
		int prob = random.nextInt(10);

		// ■ゲームの流れ
		//１．プレイヤー名の入力プレイヤー名の入力
		System.out.println("プレイヤー１の名前を入力：");
		String Name1 = stdin.nextLine();
		System.out.println("プレイヤー１のジョブを入力（戦士:f\t魔法使い:w\t僧侶:p\t勇者:h)");
		// プレイヤー１のキャラクターを作成
		String Job1;
		Player player1 = null;
		while (true) {
			try {
				Job1 = stdin.nextLine();
				if (Job1.equals("f") || Job1.equals("w") || Job1.equals("p") || Job1.equals("h")) {
					break;
				} else {
					new Exception();
				}
			} catch (Exception e) {
				System.out.println("適切な値を入力してください");
			}
		}
		if (Job1.equals("f")) {
			player1 = new Fighter(Name1);
		} else if (Job1.equals("w")) {
			player1 = new Wizard(Name1);
		} else if (Job1.equals("p")) {
			player1 = new Priest(Name1);
		} else if (Job1.equals("h")) {
			player1 = new Hero(Name1);
		}

		System.out.println("プレイヤー２の名前を入力：");
		String Name2 = stdin.nextLine();
		System.out.println("プレイヤー２のジョブを入力（戦士:f\t魔法使い:w\t僧侶:p\t勇者:h)");
		// プレイヤー１のキャラクターを作成
		String Job2;
		Player player2 = null;
		while (true) {
			try {
				Job2 = stdin.nextLine();
				if (Job2.equals("f") || Job2.equals("w") || Job2.equals("p") || Job2.equals("h")) {
					break;
				} else {
					new Exception();
				}
			} catch (Exception e) {
				System.out.println("適切な値を入力してください");
			}
		}
		if (Job2.equals("f")) {
			player2 = new Fighter(Name2);
		} else if (Job2.equals("w")) {
			player2 = new Wizard(Name2);
		} else if (Job2.equals("p")) {
			player2 = new Priest(Name2);
		}
		// プレイヤー２の名前を入力させるキャラクターを生成
		// プレイヤー２のキャラクターを作成

		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		player1.PrintStatus();
		player2.PrintStatus();
		System.out.println("");
		System.out.println("=== バトル開始 ===");
		if (player1.GetAGI() > player2.GetAGI()) {
			System.out.println(player1.GetName() + "が先攻！");
			order = true;
		} else {
			System.out.println(player2.GetName() + "が先攻！");
			order = false;
		}

		int turnNumber = 1;
		// 最大でも20ターンまで
		while (turnNumber <= 20) {
			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);
			if (order == true) {
				// ■プレイヤー１の攻撃ターン
				if (player1.cond == "麻痺" && prob <= 2) {
					System.out.println(player1.GetName() + "は痺れて動けない！");
				} else {
					player1.Attack(player2);
				}
				// プレイヤー２の敗北判定
				if (player2.GetHP() <= 0) {
					break;
				}

				//毒判定
				if (player2.cond == "毒") {
					System.out.println(player2 + "は毒で20のダメージ！");
					player2.hp = Math.max(player2.GetHP() - 20, 0);
				}

				// プレイヤー２の敗北判定
				if (player2.GetHP() <= 0) {
					break;
				}

				// ■プレイヤー２の攻撃ターン
				if (player2.cond == "麻痺" && prob <= 2) {
					System.out.println(player2.GetName() + "は痺れて動けない！");
				} else {
					player2.Attack(player1);
				}

				// プレイヤー１の敗北判定
				if (player1.GetHP() <= 0) {
					break;
				}

				//毒判定
				if (player1.cond == "毒") {
					System.out.println(player1 + "は毒で20のダメージ！");
					player1.hp = Math.max(player1.GetHP() - 20, 0);
				}

				// プレイヤー１の敗北判定
				if (player1.GetHP() <= 0) {
					break;
				}
			} else {
				// ■プレイヤー２の攻撃ターン
				if (player2.cond == "麻痺" && prob <= 2) {
					System.out.println(player2.GetName() + "は痺れて動けない！");
				} else {
					player2.Attack(player1);
				}

				// プレイヤー１の敗北判定
				if (player1.GetHP() <= 0) {
					break;
				}

				//毒判定
				if (player1.cond == "毒") {
					System.out.println(player1 + "は毒で20のダメージ！");
					player1.hp = Math.max(player1.GetHP() - 20, 0);
				}

				// プレイヤー１の敗北判定
				if (player1.GetHP() <= 0) {
					break;
				}
				// ■プレイヤー１の攻撃ターン
				if (player1.cond == "麻痺" && prob <= 2) {
					System.out.println(player1.GetName() + "は痺れて動けない！");
				} else {
					player1.Attack(player2);
				}

				// プレイヤー２の敗北判定
				if (player2.GetHP() <= 0) {
					break;
				}

				//毒判定
				if (player2.cond == "毒") {
					System.out.println(player2.GetName() + "は毒で20のダメージ！");
					player2.hp = Math.max(player2.GetHP() - 20, 0);
				}

				// プレイヤー２の敗北判定
				if (player2.GetHP() <= 0) {
					break;
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

		stdin.close();
	}

}
