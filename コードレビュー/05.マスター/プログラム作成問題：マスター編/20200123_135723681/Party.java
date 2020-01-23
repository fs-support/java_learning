package name_battlerM6;

import java.util.ArrayList;

public class Party {

	// =======================
	// フィールド変数
	// =======================
	private ArrayList<Player> members;
	public Party enemys;

	// =======================
	// コンストラクタ
	// =======================
	Party() {
	members = new ArrayList<Player>();
	}

	// =======================
	// Getter / Setter
	// =======================
	/**
	* パーティーメンバーを ArrayList で取得する
	*/
	ArrayList<Player> GetMembers() {
	return members;
	}

	Party GetEnemys() {
	return enemys;
	}


	// =======================
	// protected メソッド
	// =======================
	// =======================
	// private メソッド
	// =======================
	// =======================
	// public メソッド
	// =======================
	/**
	* パーティーにプレイヤーを追加する
	* @param player : 追加するプレイヤー
	*/
	public void AppendPlayer(Player player) {

		members.add(player);
		player.party = this;
	}

	/**
	* パーティーからプレイヤーを離脱させる
	* @param player : 離脱させるプレイヤー
	*/
	public void RemovePlayer(Player player) {

		members.remove(player);
		player.party = null;
	}

	/*public void AppendEnemy(ArrayList<Player> player) {

		enemys = player;
	}

	public void RemoveEnemy(Player player) {

		enemys.remove(player);
	}*/

}
