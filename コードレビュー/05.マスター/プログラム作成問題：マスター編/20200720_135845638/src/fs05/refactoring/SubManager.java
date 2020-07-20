package fs05.refactoring;

//【GameManagerクラス内の重複処理を実行する補助クラス】

public class SubManager {

	// コンストラクタ
	public SubManager() {
		// 空のコンストラクタ
	}

	// パーティーメイキングのメソッド
	public void partyMaking(String pictogram, String partyName, Party MyParty,
			Party enemyParty, Player avatar, Player player) {
		// チュートリアル表示
		MyParty.tutorial(partyName, avatar);
		System.out.println();

		// パーティー参加人数分の名前とジョブを入力
		for (int i = 0; i < MyParty.getSlots(); i++) {
			avatar.playerNaming(pictogram, i); // プレイヤーに名前を付ける
			avatar.playerEmployment(pictogram, i); // プレイヤーのジョブを決定する

			// プレイヤーのインスタンス生成
			player = birth(avatar, player);
			// 所属パーティー、敵パーティーを決定
			player.setPartyInformation(MyParty, enemyParty);
			// 所属パーティーにメンバーを追加
			MyParty.appendPlayer(player);

			System.out.println();
		}

		// 作戦を決定する
		MyParty.tactics(partyName, MyParty);
		// ステータス表示
		statusOpen(pictogram, partyName, MyParty);

		System.out.println();
	}

	// プレイヤーのインスタンス生成メソッド
	public Player birth(Player contents, Player vessel) {
		switch (contents.getJob()) {
		case "1":
			vessel = new Fighter(contents.getName());
			break;
		case "2":
			vessel = new Wizard(contents.getName());
			break;
		case "3":
			vessel = new Priest(contents.getName());
			break;
		case "4":
			vessel = new Mother(contents.getName());
			break;
		}
		return vessel;
	}

	// ステータスを表示するメソッド
	public void statusOpen(String pictogram, String partyName, Party party) {
		if (party.getMembers().size() == 0) {
			System.out.println
					(partyName + "チーム（作戦："
							+ party.getStrategy().getStrategyName() + "）");
			System.out.println("（パーティーが全滅しました）");
		} else {
			party.statusDisplay(partyName, party, pictogram);
		}
		System.out.println();
	}

	// 作戦④専用変数をリセットするメソッド
	public void chameleonReset(Party party) {
		if (party.getStrategy() instanceof Strategy4) {
			party.getStrategy().countReset();
		}
	}

	// 勝敗判定のメソッド
	public void judgment(Party party1, Party party2) {
		if (party2.getMembers().size() < party1.getMembers().size()) {
			System.out.println("『🐘ゾウさん』チームの勝利！！");
		} else if (party1.getMembers().size() < party2.getMembers().size()) {
			System.out.println("『🐻クマさん』チームの勝利！！");
		} else {
			System.out.println("引き分け（両チーム残存メンバー同数のため）");
		}
	}

}