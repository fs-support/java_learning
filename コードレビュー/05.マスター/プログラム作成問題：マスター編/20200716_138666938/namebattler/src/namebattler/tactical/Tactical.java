package namebattler.tactical;

import java.util.Random;

import namebattler.manager.Party;
import namebattler.player.Player;

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
	//ターゲットプレイヤー
	protected Player targetPlayer;

	// =======================
	// コンストラクタ
	// =======================
	public Tactical() {

	}

	// =======================
	// Getter / Setter
	// =======================
	//ターゲットプレイヤーの取得
	public Player GetTargetPlayer() {
		return this.targetPlayer;
	}

	// =======================
	// public メソッド
	// =======================
	//作戦の実行 *各クラスでオーバーライド
	public void tacticalAction(Party attackP, Party targetP, Player attacker) {

	}

}