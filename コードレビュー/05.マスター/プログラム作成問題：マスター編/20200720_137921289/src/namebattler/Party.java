package namebattler;

import java.util.ArrayList;

public class Party {

	// ==================================
	// フィールド変数
	// ==================================
	private ArrayList<Player> members;
	

	// ==================================
	// コンストラクタ
	// ==================================
	Party() {
		members = new ArrayList<Player>();
	}

	// ==================================
	// Getter　/　Setter
	// ==================================
	/*
	 * パーティメンバーをArrsyListで取得する
	 */
	ArrayList<Player> GetMembers() {
		return members;
	}
	
	
	

	// ==================================
	// protectedメソッド
	// ==================================

	// ==================================
	// privateメソッド
	// ==================================

	// ==================================
	// publicメソッド
	// ==================================

	/*
	 * パーティにプレイヤーを追加する
	 * 
	 * @param player；追加するプレイヤー
	 */
	public void AppendPlayer(Player player) {
		members.add(player);

	}
	

	/*
	 * パーティからプレイヤーを離脱さえる
	 * 
	 * @param player；離脱するプレイヤー
	 */
	public void RemovePlayer(Player player) {
		
		members.remove(player);

	}
	


}
