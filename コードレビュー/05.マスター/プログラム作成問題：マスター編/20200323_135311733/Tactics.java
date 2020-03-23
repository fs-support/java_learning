package namebattler_2;

import java.util.ArrayList;
import java.util.Random;

public class Tactics {
	// =======================
	// フィールド変数
	// =======================
	ArrayList<Player> defparty;
	ArrayList<Player> attparty;
	Player defplayer;
	Player attplayer;
	Random rnd = new Random();

	// =======================
	// コンストラクタ
	// =======================

	// =======================
	// Getter / Setter
	// =======================

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
	 * 対象プレイヤーに攻撃を行う
	 * @param player : 攻撃するプレイヤー
	 * @param attmembers : 味方のパーティーメンバー
	 * @param defmembers : 相手のパーティーメンバー
	 */
	public void Action(Player player,ArrayList<Player> attmembers,ArrayList<Player> defmembers)
	{
		// 作戦ごとにオーバーライドして処理を記述してください
	}
}