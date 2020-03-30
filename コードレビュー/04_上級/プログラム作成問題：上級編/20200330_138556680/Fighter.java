package Battle;

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
		this.hp = GetNumber(1, 200)+100; //HP:100～300
		this.str = GetNumber(2, 70)+30; //STR:30～100
		this.def = GetNumber(3, 70)+30; //DEF:30～100
		this.mp = 0; //MP:0
		this.luck = GetNumber(5, 99)+1;//LUCK:1～100
		this.agi = GetNumber(0, 49)+1; //AGI:1～50
		this.abnomal = 0;//abnomal:0=通常
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

		if (damage == 0) {
			System.out.println("ミス!");
		} else {
			System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			defender.Damage(damage);
		}


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
