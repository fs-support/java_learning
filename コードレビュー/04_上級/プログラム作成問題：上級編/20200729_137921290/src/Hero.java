
// プレイヤー：勇者
public class Hero extends Player {

	// =======================
	// フィールド変数
	// =======================

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
		// 戦士のパラメータを名前から生成する
		this.job = "勇者";
		this.hp = GetNumber(0, 150) + 100;
		this.MAX = GetNumber(0, 150) + 100;
		this.mp = 20;
		this.str = GetNumber(2, 70) + 30;
		this.def = GetNumber(3, 70) + 30;
		this.luck = GetNumber(4, 99) + 1;
		this.agi = GetNumber(5, 40) + 20;
		this.cond = " ";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
		int damage = 0;

		// 与えるダメージを求める
		if (GetMP() >= 20 && GetHP() <= MAXHP() - 50) {
			System.out.println(GetName() + "のヒール！体力を５０回復！");
			this.mp = Math.max(this.GetMP() - 20, 0);
			this.hp = Math.max(this.GetHP() + 50, 0);
		} else {
			System.out.println(GetName() + "の攻撃！");
			damage = CalcDamage(defender);
			// 求めたダメージを対象プレイヤーに与える
			System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
		}
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
