package namebattler;

import java.util.Random;

public class Tactical {
	// =======================
	// フィールド変数
	// =======================
	//ランダムクラス
	protected Random ran = new Random();
	//アタックパーティ
	protected Party attackP;
	//ターゲットパーティー
	protected Party targetP;
	//ターゲットメンバー
	protected Player targetM;

	// =======================
	// コンストラクタ
	// =======================
	public Tactical() {

	}

	// =======================
	// public メソッド
	// =======================
	public Player target(Player attack,Party A, Party B) {

		return targetM;
	}

	public void action(Player attack) {
		return;
	}

}
