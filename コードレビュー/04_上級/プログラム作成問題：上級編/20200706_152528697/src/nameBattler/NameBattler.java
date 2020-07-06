// JRE 1.8を使用しています
package nameBattler;

import java.util.Random;
import java.util.Scanner;

public class NameBattler {

	//終了フラグ(trueで戦闘終了)
	public static boolean endFlag = false;

	public static void main(String[] args) {
		// ==================================================
		// バトル開始準備
		// ==================================================
		//ジョブ数
		int jobNum = 4;

		//作成キャラ数
		int playerNum = 2;

		//キャラ人数分の配列を作成
		Player player[] = new Player[playerNum];

		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < player.length; i++) {

			// プレイヤー(i人目)の名前を入力させてキャラクターを生成
			System.out.print((i + 1) + "人目の名前を入力してください：");
			String name = sc.nextLine();
			int job = 0;
			while (job < 1 || jobNum < job) {
				System.out.print("1：戦士\n2：魔法使い\n3：僧侶\n4：トリックスター\n職業を選んでください：");
				job = sc.nextInt();
				System.out.println();
				sc.nextLine(); //改行文字が残るためnextLineで空読み
				if (job < 1 || jobNum < job)
					System.out.println("1～" + jobNum + "を選んでください\n");
			}
			// プレイヤー[i]のキャラクターを作成
			switch (job) {
			case 1:
				player[i] = new Fighter(name);
				break;
			case 2:
				player[i] = new Wizard(name);
				break;
			case 3:
				player[i] = new Priest(name);
				break;
			case 4:
				player[i] = new Trickstar(name);
				break;
			}
		}
		sc.close();

		// ■処理の流れ

		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		player[0].PrintStatus();
		player[1].PrintStatus();
		System.out.println("");
		System.out.println("=== バトル開始 ===");

		int turnNumber = 1;
		// 最大でも20ターンまで
		while (turnNumber <= 20) {
			System.out.println("----------------------------------------------------------------------------\n");
			System.out.printf("- ターン%d -\n\n", turnNumber);

			DecideTheOrder(player[0], player[1]);

			//相手のHPが0ならbreak
			if (endFlag)
				break;

			// ※④と⑤の繰り返し
			// ターン終了時のステータスを表示
			System.out.println("");
			player[0].PrintStatus();
			player[1].PrintStatus();

			// 次のターン
			turnNumber = turnNumber + 1;

		}

		// ==================================================
		// 終了処理
		// ==================================================
		// ⑥勝ち負けの表示(ＨＰが多い方が勝ち)
		System.out.println("");
		if (player[0].GetHP() > player[1].GetHP()) {
			System.out.println(player[0].GetName() + "の勝利！！");
		} else if (player[0].GetHP() < player[1].GetHP()) {
			System.out.println(player[1].GetName() + "の勝利！！");
		} else {
			System.out.println("引き分け！");
		}

	}

	//攻撃順を決める
	public static void DecideTheOrder(Player player1, Player player2) {
		Random rand = new Random();
		int randomNum = rand.nextInt(2);
		int hold = 0;
		//プレイヤー2の素早さが高い場合
		if (player1.GetAGI() < player2.GetAGI()) {
			hold = 1;
		}
		//素早さが同じ
		else {
			hold = randomNum;
		}
		switch (hold) {
		case 0:
			//プレイヤー1の攻撃
			PlayerAction(player1, player2);
			//endFlagがfalseならプレイヤー2の攻撃
			if (!endFlag)
				PlayerAction(player2, player1);
			break;
		case 1:
			//プレイヤー2の攻撃
			PlayerAction(player2, player1);

			//endFlagがfalseならプレイヤー1の攻撃
			if (!endFlag)
				PlayerAction(player1, player2);
			break;
		}
	}

	//プレイヤー関連の処理
	public static void PlayerAction(Player attacker, Player defender) {
		//行動可能フラグがtureで攻撃
		if (attacker.GetMovableFlag()) {
			// アタッカーの攻撃
			attacker.Attack(defender);
		} else {
			System.out.println(attacker.GetName() + "は動けない！");
			attacker.SetMovableFlagTrue();
		}
		//スリップダメージの処理
		if (attacker.GetBadStatusTurn() > 0 && attacker.GetHP() > 0) {
			System.out.println("毒ダメージ！" + attacker.GetName() + "に" + attacker.GetSlipDamage() + "のダメージ！");
			attacker.Damage(attacker.GetSlipDamage());
			attacker.SetBadStatus(attacker.GetBadStatusTurn() - 1);
		}
		System.out.println();
		// 両プレイヤーの敗北判定
		if (attacker.GetHP() <= 0 || defender.GetHP() <= 0) {
			endFlag = true;
		}
	}

}
