package namebattler_2;

import java.util.ArrayList;

public class TacAttackMinHP implements Tactics {
	// =======================
	// フィールド変数
	// =======================
	ArrayList<Player> defparty;
	ArrayList<Player> attparty;
	Player defplayer;
	Player attplayer;

	// =======================
	// コンストラクタ
	// =======================
	TacAttackMinHP() {

	}

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
	@Override
	public void Action(Player player, ArrayList<Player> attmembers,
			ArrayList<Player> defmembers) {
		defparty = (ArrayList<Player>) defmembers.clone();

		// 対象パーティーのうち、HPの低いPlayerを優先して攻撃
		Party gwp = new Party();
		this.defplayer = gwp.GetWeakestPlayer(defparty);

		// 与えるダメージを求める
		System.out.println(player.GetName() + "の攻撃！");
		int damage = player.CalcDamage(this.defplayer);
		// 求めたダメージを対象プレイヤーに与える
		System.out.println(this.defplayer.GetName() + "に" + damage + "のダメージ！");
		this.defplayer.Damage(damage);
		// 倒れた判定
		if (this.defplayer.GetHP() <= 0) {
			System.out.println(this.defplayer.GetName() + "は力尽きた...");
		}
	}
}