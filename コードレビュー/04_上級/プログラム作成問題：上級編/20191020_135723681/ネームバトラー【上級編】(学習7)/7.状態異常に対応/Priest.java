package name_battler7;

public class Priest extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name) {

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
		// 僧侶のパラメータを名前から生成する
		this.hp = GetNumber(0, 120) + 80;
		this.mp = GetNumber(0, 30) + 20;
		this.str = GetNumber(1, 60) + 10;
		this.def = GetNumber(2, 60) + 10;
		this.luck = GetNumber(3, 99) + 1;
		this.agi = GetNumber(4, 40) + 20;
		this.maxhp = this.hp;
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {

		int damage = 0;
		//HPが、最大HPの半分以下で、MPが残っている場合は回復魔法を使用する
		if (GetMP() >= 20 && (this.maxhp / 2) >= GetHP()) {

			Heal();
		}
		//相手の状態がノーマルで、MPが残っている場合はランダムで通常攻撃か状態異常魔法を使用
		else if(GetMP() >= 10 && (defender.GetStatus() == Status.Nomal)) {

			int command = rnd.nextInt(3);

			if(command == 0) {

				System.out.println(GetName() + "の攻撃！");
				damage = CalcDamage(defender);

				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
			}
			else if(command == 1) {

				Paralysis(defender);
			}
			else {

				Poison(defender);
			}
		}
		else {
			//MPが0の時に通常攻撃をする
			// 与えるダメージを求める
			System.out.println(GetName() + "の攻撃！");
			damage = CalcDamage(defender);

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
