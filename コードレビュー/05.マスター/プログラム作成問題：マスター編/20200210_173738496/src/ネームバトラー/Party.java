package ネームバトラー;

import java.util.ArrayList;

public class Party {
// =======================
// フィールド変数
// =======================
	private ArrayList<Player> members;
	private Strategy strategy;
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
	 * 
	 * @param player : 追加するプレイヤー
	 */
	public void AppendPlayer(Player player) {
		this.members.add(player);
	}

	/**
	 * パーティーからプレイヤーを離脱させる
	 * 
	 * @param player : 離脱させるプレイヤー
	 */
	public void RemovePlayer(Player player) {
		this.members.remove(player);
	}

	public int size() {
	return this.members.size();
	}
	public Player get(int a) {
		return this.members.get(a);
	}
	public int totalHP() {
		int hp=0;
		for(int i=0;i<this.members.size();i++) {
			hp+=this.members.get(i).GetHP();
		}
		return hp;
	}
	
	public int totalMAXHP() {
		int hp=0;
		for(int i=0;i<this.members.size();i++) {
			hp+=this.members.get(i).GetMaxHP();
		}
		return hp;
	}
	
	public Strategy GetStrategy() {
		return this.strategy;
	}
	public void SetStrategy(Strategy s) {
		this.strategy=s;
	}
}