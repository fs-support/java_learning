// JRE 1.8を使用しています

public class GameManager {
	Party party1 = new Party();
	Party party2 = new Party();



	// ゲームの流れ全部ここに
	public void Start() {
		// プレイヤーの新規作成
		Player player1 = new Priest("あかさたな");
		Player player2 = new Hero("いきしちに");
		Player player3 = new Fighter("うくすつぬ");
		Player player4 = new Priest("えけせてね");
		Player player5 = new Wizard("おこそとの");
		Player player6 = new Fighter("わわわわわ");

		// パーティーメンバーの追加

		party1.AppendPlayer(player1);
		player1.SetMyParty(party1);
		party1.AppendPlayer(player2);
		player2.SetMyParty(party1);
		party1.AppendPlayer(player3);
		player3.SetMyParty(party1);
		party2.AppendPlayer(player4);
		player4.SetMyParty(party2);
		party2.AppendPlayer(player5);
		player5.SetMyParty(party2);
		party2.AppendPlayer(player6);
		player6.SetMyParty(party2);

		// 作戦の決定
		party1.ChangeStrategy(new DefaultStrategy());
		party2.ChangeStrategy(new DefaultStrategy());

		// ==================================================
		// バトル処理
		// ==================================================
		// ステータス表示
		StatusPrint();

		// バトル開始の表示
		System.out.println("");
		System.out.println("=== バトル開始 ===");

		int turnNumber = 1;
		// 最大でも20ターンまで
		lose:while (true) {
			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);
			// 行動できる人間がiいる間繰り返す
			while(party1.isActive() && party2.isActive()) {
				// 未行動のプレイヤーで一番AGIが高いプレイヤーが攻撃する
				Player attacker =ComparateAGI();
				// 攻撃するプレイヤーの攻撃できる対象を決める
				Party TargetParty = ContainsParty(attacker);

				System.out.println("▼ " + attacker.GetName() + " の行動");

				// 麻痺状態でない場合の処理
				if (!attacker.GetParalyze()) {
					if (attacker.GetMyParty().equals(party1)) {
						attacker.Action(attacker, party2);
					} else {
						attacker.Action(attacker, party1);
					}
				} else {
					System.out.println(attacker.GetName() + " は身体が麻痺して動けない！");

				}

				if (TargetParty.isLose()) {
					break lose;
				}

				if (attacker.GetPoison()) {
					attacker.ProcessPoison();
				}
				// 全滅してるかどうか
				//　してたらゲーム終了
				if (TargetParty.isLose()) {

					break lose;
				}

				attacker.ChangeActive(false);
			}

			System.out.println("--------------------------------");
			StatusPrint();
			System.out.println("--------------------------------");




			// ターン終了時の処理
			for (Player p : party1.AttackTarget()) {
				p.ChangeActive(true);
			}

			for (Player p : party2.AttackTarget()) {
				p.ChangeActive(true);
			}
			turnNumber++;

		}

		/*
		System.out.println("================================");
		System.out.println("結果");
		System.out.println("================================");
		 */
		System.out.println();

		if (party1.isLose()) {
			System.out.println("■■■■■■■■■■■■■■");
			System.out.println("■　パーティー２の勝利！　■");
			System.out.println("■■■■■■■■■■■■■■");
		}else {
			System.out.println("■■■■■■■■■■■■■■");
			System.out.println("■　パーティー１の勝利！　■");
			System.out.println("■■■■■■■■■■■■■■");

		}
/*
		System.out.println("================================");
		StatusPrint();
*/
	}

	/**
	 * パーティー内で一番早いプレイヤー同士を比較してより早いほうを返す
	 */
	public Player ComparateAGI() {
		if (this.party1.FastestPlayer().GetAGI() >this.party2.FastestPlayer().GetAGI()) {
			return this.party1.FastestPlayer();
		}
		return party2.FastestPlayer();

	}

	public Party ContainsParty(Player attacker) {

		if (party1.isExists(attacker)){
			return party2;
		}

		return party1;
	}

	/**
	 * プレイヤー全員のステータスをパーティーごとに表示する
	 */
	public void StatusPrint() {
		// パーティー1のステータスの表示
		for (int i = 0; i < party1.GetMembers().size(); i++) {
			party1.GetMembers().get(i).PrintStatus();
		}
		// パーティー2のステータスの表示
		for (int i = 0; i < party2.GetMembers().size(); i++) {
			party2.GetMembers().get(i).PrintStatus();
		}
	}


}