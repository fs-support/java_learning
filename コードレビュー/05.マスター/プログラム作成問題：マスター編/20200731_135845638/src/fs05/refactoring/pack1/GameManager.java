package fs05.refactoring.pack1;

import fs05.refactoring.pack2.Player;

public class GameManager {

	void start() {

		// Partyクラスのインスタンス生成
		Party party1 = new Party();
		Party party2 = new Party();

		// 補助クラスのインスタンス生成
		SubManager sub = new SubManager();
		SortOrder so = new SortOrder();

		// キャラクターを作成してパーティーメイキング
		// (複数のフィールドを持つクラス変数の初期化には「null」を入れる)
		Player avatar = new Player("unKnown");
		Player player1 = null;
		Player player2 = null;
		sub.partyMaking("🐘", "『🐘ゾウさん』", party1, party2, avatar, player1);
		sub.partyMaking("🐻", "『🐻クマさん』", party2, party1, avatar, player2);

		// バトル開始の表示
		System.out.println("=== バトル開始 ===");
		int turnNumber = 1;

		// 最大でも20ターンまで(攻撃ターンと敗北判定の繰り返し)
		while (turnNumber <= 20) {
			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);
			System.out.println();

			// AGIの高い順に行動させるための準備
			// (２つのパーティーのメンバーを１つのリストへまとめる)
			Party allMembers = new Party();
			so.gathering(allMembers, party1);
			so.gathering(allMembers, party2);

			// AGIの高い順に並べ替える
			String order = "降順";
			so.orderBySort(allMembers, order);

			// AGIの高い順に攻撃
			while (allMembers.getMembers().size() != 0) {
				Player attacker = allMembers.getMembers().get(0);
				// どちらかのパーティーが全滅していたらループ(ターン)を抜ける
				if ((party1.getMembers().size() == 0)
						|| (party2.getMembers().size() == 0)) {
					break;
				}

				// 作戦を実行して攻撃
				attacker.getMyPartyInformation().operation(attacker,
						attacker.getEnemyPartyInformation());

				// ダメージ処理済みのターゲットを用意して変数[defender]に代入する
				attacker.getMyPartyInformation().processed();
				Player defender = attacker.getMyPartyInformation()
						.processedPlayer();

				// 戦闘不能プレイヤーをゲームから除外
				if (defender.getHp() <= 0) {
					System.out.println("（" + defender.getName()
							+ "が戦闘不能によりパーティーから離脱しました）");
					// 戦闘不能プレイヤーをallMembersリストから離脱
					for (int i = 0; i < allMembers.getMembers().size(); i++) {
						if (allMembers.getMembers().get(i) == defender) {
							allMembers.removePlayer(allMembers.getMembers()
									.get(i));
						}
					}
					// 戦闘不能プレイヤーを所属パーティーから離脱
					defender.getMyPartyInformation().removePlayer(defender);
				}

				// Attackerの攻撃が済んだらallMembersリストから０番目を除外
				allMembers.removePlayer(allMembers.getMembers().get(0));

			}
			System.out.println();

			// ターン終了時のステータスを表示
			sub.statusOpen("🐘", "『🐘ゾウさん』", party1);
			sub.statusOpen("🐻", "『🐻クマさん』", party2);

			// どちらかのパーティーが全滅していたらループ(全体)を抜ける
			if ((party1.getMembers().size() == 0)
					|| (party2.getMembers().size() == 0)) {
				break;
			}

			// 次のターン
			turnNumber = turnNumber + 1;

			// 残存プレイヤー全員の行動が終了したので作戦④専用変数をリセット
			sub.chameleonReset(party1);
			sub.chameleonReset(party2);

		}

		// 勝ち負けの表示(パーティーメンバーの残存数で判定)
		System.out.println();
		sub.judgment(party1, party2);

	}

}