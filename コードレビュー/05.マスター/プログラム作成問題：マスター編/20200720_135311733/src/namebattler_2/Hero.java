package namebattler_2;

import java.util.ArrayList;
import java.util.Random;

// プレイヤー：勇者
public class Hero extends Player {

	// =======================
	// フィールド変数
	// =======================
	private Random rnd = new Random();
	Player defender;
	int damage;

	// =======================
	// コンストラクタ
	// =======================
	public Hero(String name) {
		super(name);
	}

	// =======================
	// Getter / Setter
	// =======================

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter() {
		// 勇者のパラメータを名前から生成する
		this.hp = GetNumber(0, 250);
		this.str = GetNumber(1, 90);
		this.def = GetNumber(2, 80);
		this.agi = GetNumber(3, 60);
		this.mp = GetNumber(4, 40);
		this.luc = GetNumber(5, 99);

		// パラメータが勇者の最低値未満の場合は最低値を設定
		if (this.hp < 90)
			this.hp = 90;
		if (this.str < 30)
			this.str = 30;
		if (this.def < 20)
			this.def = 20;
		if (this.agi < 30)
			this.agi = 30;
		if (this.mp < 20)
			this.mp = 20;
		if (this.luc < 1)
			this.luc = 1;
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(ArrayList<Player> attackparty,
			ArrayList<Player> defenderparty) {
		// 攻撃対象の設定
		while (true) {
			defender = defenderparty.get(rnd.nextInt(defenderparty.size()));
			if (defender.GetHP() > 0) {
				break;
			}
		}

		// 麻痺判定
		if (this.GetParalys() == true && this.ParaJudge() == true) {
			System.out.println(GetName() + " は麻痺していて動けない！");
		} else {
			// 攻撃と回復をバランスよく使用する
			TacAttHealBalance tacatthealbalance = new TacAttHealBalance();
			tacatthealbalance.Action(this, attackparty, defenderparty);
		}
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
}
