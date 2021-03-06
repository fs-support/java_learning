package name_battlerM2;

import java.util.ArrayList;

public class Party {

	// =======================
	// フィールド変数
	// =======================
	private ArrayList<Player> members;
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
	}
	/**
	* パーティーからプレイヤーを離脱させる
	* @param player : 離脱させるプレイヤー
	*/
	public void RemovePlayer(Player player) {

		members.remove(player);
	}

}
