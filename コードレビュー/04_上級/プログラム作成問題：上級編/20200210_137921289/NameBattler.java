package namebatoler;


import java.util.Scanner;

public class NameBattler {

	public static void main(String[] args) {
		// ==================================================
		// バトル開始準備
		// ==================================================

		Player player1 = null;
		Player player2 = null;
		// 職業を保存
		String player1choisejob = "";
		String player2choisejob = "";

		// 名前、職業の入力
		Scanner stdin1 = new Scanner(System.in);
		Scanner stdin2 = new Scanner(System.in);

		System.out.println("名前を入力した後に職業を選択してください");

		// プレイヤー１の名前を入力させてキャラクターを生成
		System.out.println("プレイヤー1の名前を入力してください");
		String player1Name = stdin1.nextLine();
		System.out.println("職業を選んで下さい");
		System.out.println("1：戦士\n2：魔法使い\n3:僧侶\n4:勇者");
		int player1Job = stdin1.nextInt();

		// プレイヤー２の名前を入力させるキャラクターを生成
		System.out.println("プレイヤー2の名前を入力してください");
		String player2Name = stdin2.nextLine();
		System.out.println("職業を選んで下さい");
		System.out.println("1：戦士\n2：魔法使い\n3:僧侶\n4:勇者");
		int player2Job = stdin2.nextInt();

		// ■処理の流れ
		// プレイヤー１のキャラクターを作成
		while (true) {
			if (player1Job == 1) {
				player1choisejob = "戦士";
				player1 = new Fighter(player1Name);
				break;
			} else if (player1Job == 2) {
				player1choisejob = "魔法使い";
				player1 = new Wizard(player1Name);
				break;
			} else if (player1Job == 3) {
				player1choisejob = "僧侶";
				player1 = new Priest(player1Name);
				break;
			} else if (player1Job == 4) {
				player1choisejob = "勇者";
				player1 = new Brave(player1Name);
				break;
			} else {
				System.out.println("該当する選択肢を選んでください");
				player1Job = stdin1.nextInt();
			}
		}
		// プレイヤー２のキャラクターを作成
		while (true) {
			if (player2Job == 1) {
				player2choisejob = "戦士";
				player2 = new Fighter(player2Name);
				break;
			} else if (player2Job == 2) {
				player2choisejob = "魔法使い";
				player2 = new Wizard(player2Name);
				break;
			} else if (player2Job == 3) {
				player2choisejob = "僧侶";
				player2 = new Priest(player2Name);
				break;
			} else if (player2Job == 4) {
				player2choisejob = "勇者";
				player2 = new Brave(player2Name);
				break;
			} else {
				System.out.println("該当する選択肢を選んでください");
				player2Job = stdin2.nextInt();

			}
		}

		stdin1.close();
		stdin2.close();

		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		player1.PrintStatus();
		System.out.println("職業：" + player1choisejob);
		player2.PrintStatus();
		System.out.println("職業：" + player2choisejob);
		System.out.println("");
		System.out.println("=== バトル開始 ===");

		int turnNumber = 1;
		// 最大でも20ターンまで
		while (turnNumber <= 20) {
			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);

			Player farstplayer = null;
			Player secandplayer = null;

			// 速さで先行後攻を決める
			if (player1.GetAGI() > player2.GetAGI()) {
				farstplayer = player1;
				secandplayer = player2;
			} else {
				farstplayer = player2;
				secandplayer = player1;
			}

			// ■プレイヤー１の攻撃ターン
			farstplayer.Attack(secandplayer);

			// プレイヤー２の敗北判定
			if (secandplayer.GetHP() <= 0) {
				break;
			}

			// ■プレイヤー２の攻撃ターン
			secandplayer.Attack(farstplayer);

			// プレイヤー１の敗北判定
			if (farstplayer.GetHP() <= 0) {
				break;
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

}