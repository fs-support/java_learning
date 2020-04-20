package namebatoler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GameManager {

	// パーティ1
	static Party makeparty = new Party();
	// パーティ2
	static Party makeparty2 = new Party();
	// 作戦を立てる
	Operation operation = new Operation();

	// 作戦を格納
	ArrayList<Operation> OperationList = new ArrayList<Operation>();

	public void Start() {
		// ==================================================
		// バトル開始準備
		// ==================================================

		// パーティメンバーの人数を保存
		int Allpartymenber = 3;

		// 攻撃順に格納
		ArrayList<Player> turnorder = new ArrayList<Player>();

		Player player1 = null;
		Player player2 = null;

		// 名前、職業の入力
		Scanner stdin1name = new Scanner(System.in);
		Scanner stdin1job = new Scanner(System.in);
		Scanner stdin2name = new Scanner(System.in);
		Scanner stdin2job = new Scanner(System.in);
		// 作戦を入力
		Scanner stdinpulan = new Scanner(System.in);

		System.out.println("名前を入力した後に職業を選択してください");

		// パーティ１のプレイヤーの名前を入力させてキャラクターを生成
		for (int i = 0; i < Allpartymenber; i++) {

			System.out.println("パーティ1の" + (i + 1) + "人目の名前を入力してください");
			String player1Name = stdin1name.nextLine();
			System.out.println("職業を選んで下さい");
			System.out.println("1：戦士\n2：魔法使い\n3:僧侶\n4:勇者");
			int player1Job = stdin1job.nextInt();

			// ■処理の流れ
			// プレイヤーのキャラクターを作成
			while (true) {

				if (player1Job == 1) {
					player1 = new Fighter(player1Name);
					player1.partynum = 1;
					break;
				} else if (player1Job == 2) {
					player1 = new Wizard(player1Name);
					player1.partynum = 1;
					break;
				} else if (player1Job == 3) {
					player1 = new Priest(player1Name);
					player1.partynum = 1;
					break;
				} else if (player1Job == 4) {
					player1 = new Brave(player1Name);
					player1.partynum = 1;
					break;
				} else {
					System.out.println("該当する選択肢を選んでください");
					player1Job = stdin1job.nextInt();
				}
			}

			makeparty.AppendPlayer(player1);
			turnorder.add(player1);

			player1 = null;

		}

		for (int i = 0; i < Allpartymenber; i++) {

			// プレイヤー２の名前を入力させるキャラクターを生成
			System.out.println("パーティ2の" + (i + 1) + "人目の名前を入力してください");
			String player2Name = stdin2name.nextLine();
			System.out.println("職業を選んで下さい");
			System.out.println("1：戦士\n2：魔法使い\n3:僧侶\n4:勇者");
			int player2Job = stdin2job.nextInt();

			// プレイヤー２のキャラクターを作成
			while (true) {
				if (player2Job == 1) {
					player2 = new Fighter(player2Name);
					player2.partynum = 2;
					break;
				} else if (player2Job == 2) {
					player2 = new Wizard(player2Name);
					player2.partynum = 2;
					break;
				} else if (player2Job == 3) {
					player2 = new Priest(player2Name);
					player2.partynum = 2;
					break;
				} else if (player2Job == 4) {
					player2 = new Brave(player2Name);
					player2.partynum = 2;
					break;
				} else {
					System.out.println("該当する選択肢を選んでください");
					player2Job = stdin2job.nextInt();

				}
			}

			makeparty2.AppendPlayer(player2);
			turnorder.add(player2);

			player2 = null;

		}
		stdin1name.close();
		stdin1job.close();
		stdin2name.close();
		stdin2job.close();

		// ==================================================
		// 作戦を立てる
		// ==================================================
		System.out.println("作戦を番号で選んでください");

		// 作戦を入力
		int INputchoiseOperation = stdinpulan.nextInt();
		
		// 一致する作戦を検索
		int pulanslistNO = 0;
		if(operation.GetPulans().indexOf(operation.operationNO) == -1){
			System.out.println("番号が違います");
		}else{
			pulanslistNO = operation.GetPulans().indexOf(operation.operationNO);
			
		}
		// 選んだ作戦を格納
		Operation choiseoperation = null; //operation.GetPulans().get(pulanslistNO);
		
		switch(INputchoiseOperation){
		case 1:
			choiseoperation = operation.GetPulans().get(pulanslistNO);
			System.out.println("あなたは" + choiseoperation.operationName
					+ "を選択しました");
			break;
		default:
			System.out.println("番号が違います。もう一度選んでください");
			// 作戦を入力
			INputchoiseOperation = stdinpulan.nextInt();
		}

//		while (true) {
//			// 作戦の表示
//			if (INputchoiseOperation == pulanslistNO) {
//				System.out.println("あなたは" + choiseoperation.operationName
//						+ "を選択しました");
//				break;
//			} else {
//				System.out.println("番号が違います。もう一度選んでください");
//				// 作戦を入力
//				INputchoiseOperation = stdinpulan.nextInt();
//			}
//		}

		stdinpulan.close();

		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		for (int i = 0; i < makeparty.GetMembers().size(); i++) {
			makeparty.GetMembers().get(i).PrintStatus();

			if (makeparty.GetMembers().get(i).job == 1) {
				System.out.println("職業：戦士");
			} else if (makeparty.GetMembers().get(i).job == 2) {
				System.out.println("職業：魔法使い");
			} else if (makeparty.GetMembers().get(i).job == 3) {
				System.out.println("職業：僧侶");
			} else if (makeparty.GetMembers().get(i).job == 4) {
				System.out.println("職業：勇者");
			}

		}

		for (int i = 0; i < makeparty2.GetMembers().size(); i++) {
			makeparty2.GetMembers().get(i).PrintStatus();

			if (makeparty2.GetMembers().get(i).job == 1) {
				System.out.println("職業：戦士");
			} else if (makeparty2.GetMembers().get(i).job == 2) {
				System.out.println("職業：魔法使い");
			} else if (makeparty2.GetMembers().get(i).job == 3) {
				System.out.println("職業：僧侶");
			} else if (makeparty2.GetMembers().get(i).job == 4) {
				System.out.println("職業：勇者");
			}

		}

		System.out.println("");
		System.out.println("=== バトル開始 ===");

		// 素早さで攻撃する順番を変える
		Collections.sort(turnorder, new PlayerComparator());

		int turnNumber = 1;
		// 最大でも20ターンまで
		while (turnNumber <= 20) {

			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);

			// 攻撃を受けるプレイヤー
			// Player defenceplayer = null;

			// プレイヤーが全員攻撃したら1ターン
			for (Player atackplayer : turnorder) {
				// if (atackplayer.hp <= 0) {
				// continue;
				// }

				// パーティが全滅したら終了
				if (makeparty.GetMembers().size() == 0
						|| makeparty2.GetMembers().size() == 0) {
					break;
				}

				// ■プレイヤーの攻撃ターン

				atackplayer.Attack(atackplayer);

			}

			// パーティが全滅したら終了
			if (makeparty.GetMembers().size() == 0
					|| makeparty2.GetMembers().size() == 0) {
				break;
			}

			// ターン終了時のステータスを表示
			System.out.println("");
			System.out.println("各パーティステータス");
			// attackplayer.PrintStatus();
			for (int i = 0; i < makeparty.GetMembers().size(); i++) {
				makeparty.GetMembers().get(i).PrintStatus();
			}
			for (int i = 0; i < makeparty2.GetMembers().size(); i++) {
				makeparty2.GetMembers().get(i).PrintStatus();
			}

			// 次のターン
			turnNumber = turnNumber + 1;

		}

		// ⑥勝ち負けの表示(生き残っているものが多い方が勝ち)
		System.out.println("");
		// 生存者のHPを集約
		// パーティ1用
		int Allpartylife = 0;
		for (int i = 0; i < makeparty.GetMembers().size(); i++) {
			Allpartylife += makeparty.GetMembers().get(i).hp;
		}
		// パーティ2用
		int Allpartylife2 = 0;
		for (int i = 0; i < makeparty2.GetMembers().size(); i++) {
			Allpartylife2 += makeparty2.GetMembers().get(i).hp;
		}

		if (makeparty.GetMembers().size() > makeparty2.GetMembers().size()) {
			System.out.println("パーティ2は全滅した！");
			System.out.println("パーティ1の勝利");
		} else if (makeparty.GetMembers().size() < makeparty2.GetMembers()
				.size()) {
			System.out.println("パーティ1は全滅した！");
			System.out.println("パーティ2の勝利");
		} else {
			if (Allpartylife > Allpartylife2) {
				System.out.println("パーティ1の勝利");
			} else if (Allpartylife < Allpartylife2) {
				System.out.println("パーティ2の勝利");
			} else {
				System.out.println("引き分けだ！");
			}
		}

		// ==================================================
		// 終了処理
		// ==================================================

	}
}