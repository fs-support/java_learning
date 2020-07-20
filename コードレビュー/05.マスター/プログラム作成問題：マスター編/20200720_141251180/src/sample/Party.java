package sample;

import java.util.ArrayList;

public class Party {
	// ======================= // フィールド変数 // =======================
	private ArrayList<Player> members;
	protected String name; // 名前

	// ======================= // コンストラクタ // =======================
	Party() {
		members = new ArrayList<Player>();
	}

	/**
	 * コンストラクタ
	 *
	 * @param name
	 *            : パーティ名
	 */
	public Party(String name) {
		members = new ArrayList<Player>();
		this.name = name;
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

	 /**
	  * パーティ名を取得する
	  *
	  * @return パーティ名
	  */
	  String GetName() {
		return this.name;
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
	public void appendPlayer(Player player) {
		members.add(player);
	}

	/**
	 * パーティーからプレイヤーを離脱させる
	 * @param player : 離脱させるプレイヤー
	 */
	 public void removePlayer(Player player) {
		 members.remove(player);
	}
	 public int size() {
			// TODO 自動生成されたメソッド・スタブ
		int ans = members.size();
		return ans;
	}
	public Player get(int i) {
		// TODO 自動生成されたメソッド・スタブ
		return this.members.get(i);
	}

}
