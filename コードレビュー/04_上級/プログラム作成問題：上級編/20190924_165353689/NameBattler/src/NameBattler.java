
// JRE 1.8を使用しています

import java.util.Scanner;

public class NameBattler {

	public static void main(String[] args) {
		// 事前準備
		// 名前入力の準備
		Scanner stdin = new Scanner(System.in);
		Player player1;
		Player player2;

		// ==================================================
		// バトル開始準備
		// ==================================================
		// プレイヤー１の名前と職業を選択させるさせる
		System.out.print("プレイヤー１の名前を入力してください：");
		String name1 = stdin.next();
		System.out.println("プレイヤー１の職業を入力してください");
		System.out.print("0: 戦士\t1: 魔法使い\t2: 僧侶\t3: 勇者");
		Integer job1 = stdin.nextInt();
		System.out.println();

		// プレイヤー２の名前と職業を選択させる
		System.out.print("プレイヤー２の名前を入力してください：");
		String name2 = stdin.next();
		System.out.println("プレイヤー２の職業を入力してください");
		System.out.print("0: 戦士\t1: 魔法使い\t2: 僧侶\\t3: 勇者");
		Integer job2 = stdin.nextInt();

		// ■処理の流れ
		// プレイヤー１のキャラクターを作成
		if (job1 == 0) {
			player1 = new Fighter(name1);
		} else if (job1 == 1) {
			player1 = new Wizard(name1);
		} else if (job1 == 2) {
			player1 = new Priest(name1);
		} else {
			player1 = new Hero(name1);
		}

		// プレイヤー２のキャラクターを作成
		if (job2 == 0) {
			player2 = new Fighter(name2);
		} else if (job2 == 1) {
			player2 = new Wizard(name2);
		} else if (job2 == 2) {
			player2 = new Priest(name2);
		} else {
			player2 = new Hero(name2);
		}
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

			// 先攻後攻の決定
			if (player1.GetAGI() > player2.GetAGI()) {
				// ■先攻の攻撃ターン
				if (player1.GetParalyze() == true) {
					// 麻痺状態になっている場合
				} else {
					// 麻痺状態でない場合{

					player1.Attack(player1, player2);
				}

				// 毒状態の場合の処理
				if (player1.GetPoison() == true) {
					player1.ProcessPoison();
				}

				// 後攻の敗北判定
				if (player2.GetHP() <= 0) {
					break;
				}

				// ■後攻の攻撃ターン
				if (player2.GetParalyze() == true) {
					// 麻痺状態になっている場合
				} else {
					// 麻痺状態でない場合{

					player2.Attack(player2, player1);
				}

				// 毒状態の場合の処理
				if (player2.GetPoison() == true) {
					player2.ProcessPoison();
				}

				// 先攻の敗北判定
				if (player1.GetHP() <= 0) {
					break;
				}

			} else {
				// ■先攻の攻撃ターン
				if (player1.GetParalyze() == true) {
					// 麻痺状態になっている場合
				} else {
					// 麻痺状態でない場合{

					player2.Attack(player2, player1);
				}

				// 毒状態の場合の処理
				if (player1.GetPoison() == true) {
					player1.ProcessPoison();
				}

				// 後攻の敗北判定
				if (player1.GetHP() <= 0) {
					break;
				}

				// ■後攻の攻撃ターン
				if (player1.GetParalyze() == true) {
					// 麻痺状態になっている場合
				} else {
					// 麻痺状態でない場合{

					player1.Attack(player1, player2);
				}

				// 毒状態の場合の処理
				if (player2.GetPoison() == true) {
					player2.ProcessPoison();
				}

				// 先攻の敗北判定
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
			System.out.println(player1.GetName() + " の勝利！！");
		} else {
			System.out.println(player2.GetName() + " の勝利！！");
		}

		// ==================================================
		// 終了処理
		// ==================================================
		stdin.close();
	}

}