package jp.co.FSsakusei3;

// プレイヤー：戦士
public class Fighter extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Fighter(String name) {
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
		// 戦士のパラメータを名前から生成する
		this.hp = GetNumber(0, 100, 300);
		this.mp = GetNumber(1, 0, 0);
		this.str = GetNumber(2, 30, 100);
		this.def = GetNumber(3, 30, 100);
		this.luck = GetNumber(4, 1, 100);
		this.agi = GetNumber(5, 1, 50);
		this.state ="健康";
		this.maxhp = hp;

	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
		// 与えるダメージを求める
		System.out.println(GetName() + "の攻撃！");
		int damage = CalcDamage(defender);

		// 求めたダメージを対象プレイヤーに与える
		System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
		defender.Damage(damage);

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
}
