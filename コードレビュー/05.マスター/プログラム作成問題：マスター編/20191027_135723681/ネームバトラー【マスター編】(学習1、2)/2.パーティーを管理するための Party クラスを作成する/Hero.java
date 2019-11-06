package name_battlerM2;

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
		// 勇者のパラメータを名前から生成する
		this.hp = GetNumber(0, 150) + 100;
		this.mp = GetNumber(0, 30) + 20;
		this.str = GetNumber(1, 65) + 20;
		this.def = GetNumber(2, 65) + 20;
		this.luck = GetNumber(3, 99) + 1;
		this.agi = GetNumber(4, 30) + 10;
		this.maxhp = this.hp;
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {

		int damage = 0;
		//HPが最大HPの半分以下で、MPが残っている場合は回復魔法を使用する
		if (GetMP() >= 20 && (this.maxhp / 2) >= GetHP()) {

			Heal();
		}
		else {

			int select = 0;
			//HPが最大HPの半分以上で、MPが残っている場合は攻撃魔法か通常攻撃をランダムで使用する
			if (GetMP() >= 20 && (this.maxhp / 2) <= GetHP()) {

				select = rnd.nextInt(2);
			}
			if (select == 1) {

				damage = Thunder();
			}
			else {
			//MPが0の時に通常攻撃をする
			// 与えるダメージを求める
			System.out.println(GetName() + "の攻撃！");
			damage = CalcDamage(defender);
			}

			// 求めたダメージを対象プレイヤーに与える
			System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			defender.Damage(damage);
		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}
}
