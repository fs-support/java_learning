package name_battlerM6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import name_battlerM6.Player.Guard;
import name_battlerM6.Tactics.TacticsType;

public class GameManager {

	Scanner stdin = new Scanner(System.in);
	HPminAim aim = new HPminAim();
	Support spt = new Support();
	AttackMain atm = new AttackMain();
	MPsaving mps = new MPsaving();
	WellBalanced wlb = new WellBalanced();


	public void Start() {

		Party party1 = new Party();
		Party party2 = new Party();

		// ==================================================
		// バトル開始準備
		// ==================================================

		System.out.println("あなたのパーティーキャラクターを3体作成してください");
		Player player1 = null;
		player1 = Jobselect(player1, 1);
		party1.AppendPlayer(player1);
		//キャラ毎に作戦を決める
		TacticsSelect(player1);

		Player player2 = null;
		player2 = Jobselect(player2, 2);
		party1.AppendPlayer(player2);
		TacticsSelect(player2);

		Player player3 = null;
		player3 = Jobselect(player3, 3);
		party1.AppendPlayer(player3);
		TacticsSelect(player3);

		System.out.println("\n相手のパーティーキャラクターを3体作成してください");
		Player player4 = null;
		player4 = Jobselect(player4, 4);
		party2.AppendPlayer(player4);
		TacticsSelect(player4);

		Player player5 = null;
		player5 = Jobselect(player5, 5);
		party2.AppendPlayer(player5);
		TacticsSelect(player5);

		Player player6 = null;
		player6 = Jobselect(player6, 6);
		party2.AppendPlayer(player6);
		TacticsSelect(player6);

		party1.enemys = party2;
		party2.enemys = party1;

		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		System.out.println("");
		System.out.println("◆あなたのパーティー");
		for (int i = 0; i < party1.GetMembers().size(); i++) {
			party1.GetMembers().get(i).PrintStatus();
		}

		System.out.println("◇相手のパーティー");
		for (int i = 0; i < party2.GetMembers().size(); i++) {
			party2.GetMembers().get(i).PrintStatus();
		}
		System.out.println("");
		System.out.println("=== バトル開始 ===");

		//TreeMap：Mapに格納した値を昇順、降順で取得できる(デフォルトだと昇順。降順に変えるには処理の追加要)
		//素早さによる行動順判定のためのMap
		Map<Player, Integer> map = new HashMap<Player, Integer>();
		map.put(player1, player1.GetAGI());
		map.put(player2, player2.GetAGI());
		map.put(player3, player3.GetAGI());
		map.put(player4, player4.GetAGI());
		map.put(player5, player5.GetAGI());
		map.put(player6, player6.GetAGI());

		//※※※キーでソートする場合重複したキーは値が上書きされるため、値でソートする
		map.entrySet().stream()
				.sorted(java.util.Collections.reverseOrder(java.util.Map.Entry.comparingByValue()));

		int turnNumber = 1;

		// 最大でも20ターンまで※※※今回どちらかが全滅するまで戦い続ける処理に変更しました。
		while (party1.GetMembers().size() > 0 && party2.GetMembers().size() > 0) {
			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);


			//行動開始前にガードをしているキャラクターはガードを宣言する
			for (Player key1 : map.keySet()) {

				if (key1.GetHP() > 0) {

					//現時点でガードを実装している作戦のみ条件にする
					if (key1.GetTactics().equals(TacticsType.Support)) {

						spt.GuardSelect(key1);
					}
					else if(key1.GetTactics().equals(TacticsType.WellBalanced)) {

						wlb.GuardSelect(key1);
					}
				}

				if (key1.GetGuard().equals(Guard.Guard)){
					System.out.println("");
					System.out.println(key1.GetName() + "は身を守っている！");
				}
			}

			//行動開始
			for (Player key2 : map.keySet()) {

				if (key2.GetHP() > 0 && key2.GetGuard().equals(Guard.NoGuard)) {

					System.out.println("");
					//状態異常：麻痺の判定
					if (key2.Status_check() == 1) {
						System.out.println(key2.GetName() + "は、身体が痺れて動けない！");
					} else {

						key2.Attack();
						//生存判定
						isAlive(key2.GetParty().GetEnemys());

						//状態異常：毒の判定
						if (key2.Status_check() == 2) {
							key2.PoisonDamage();
						}
					}
					//相手パーティーの全滅判定
					if (party2.GetMembers().size() == 0) {
						break;
					}
				}
				//状態異常の自己治癒判定
				if (!key2.GetStatus().equals(Player.Status.Nomal)) {
					key2.Recovery();
				}
			}
			//全員の行動終了後、全員ガードを解く
			for (Player key3 : map.keySet()) {

				if(key3.GetGuard().equals(Guard.Guard)) {

					key3.guard = Guard.NoGuard;
				}
			}

			// ※④と⑤の繰り返し
			// ターン終了時のステータスを表示
			System.out.println("");
			System.out.println("◆あなたのパーティー");
			if (party1.GetMembers().size() == 0) {
				System.out.println("生存者なし");
			}
			for (int i = 0; i < party1.GetMembers().size(); i++) {
				party1.GetMembers().get(i).PrintStatus();
			}
			System.out.println("◇相手のパーティー");
			if (party2.GetMembers().size() == 0) {
				System.out.println("生存者なし");
			}
			for (int i = 0; i < party2.GetMembers().size(); i++) {
				party2.GetMembers().get(i).PrintStatus();
			}
			// 次のターン
			turnNumber = turnNumber + 1;
		}

		// ⑥勝ち負けの表示(パーティーの人数が1以上のほうが勝ち)
		System.out.println("");
		if (party1.GetMembers().size() == 0) {

			System.out.println("相手のパーティーの勝利！！");
		} else {

			System.out.println("あなたのパーティーの勝利！！");
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
			PlayerJOB = stdin.nextInt();
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

	protected void TacticsSelect(Player player) {

		System.out.println("作戦を選択してください");
		System.out.print("(1)狙い撃ち (2)後方支援 (3)ガンガンいこうぜ (4)魔法つかうな (5)バランスよく:");
		int TacticsNumber = stdin.nextInt();

		while (TacticsNumber != 1 && TacticsNumber != 2 && TacticsNumber != 3 && TacticsNumber != 4
				&& TacticsNumber != 5) {

			System.out.println("正しい数値を入力してください");
			TacticsNumber = stdin.nextInt();
		}
		if (TacticsNumber == 1) {

			player.tactics = TacticsType.HPminAim;

		} else if (TacticsNumber == 2) {

			player.tactics = TacticsType.Support;

		} else if (TacticsNumber == 3) {

			player.tactics = TacticsType.AttackMain;

		} else if (TacticsNumber == 4) {

			player.tactics = TacticsType.MPsaving;

		} else {

			player.tactics = TacticsType.WellBalanced;

		}
	}
	protected void isAlive(Party party) {

		//相手パーティー内で体力が0以下を削除する
		for(Player member : party.GetMembers()) {

			if (member.GetHP() <= 0) {
				System.out.println(member.GetName() + "は力尽きた...");
				party.RemovePlayer(member);
				break;
			}
		}
	}
}
