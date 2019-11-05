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
		this.hp = (GetNumber(0, 30) + 10) * 10;
		this.maxhp = (GetNumber(0, 30) + 10) * 10;
		this.mp = GetNumber(1, 30) + 20;
		this.str = GetNumber(2, 70) + 30;
		this.def = GetNumber(3, 70) + 30;
		this.luck = GetNumber(4, 100) + 1;
		this.agi = GetNumber(5, 50);

	}

	// ↑を修正して魔法使い用のパラメーターに直す
	// ↓を修正して通常攻撃か魔法攻撃を行うようにする
	/**
	 * {@inheritDoc}<br>
	 * 自身のHPが減っている場合は 回復魔法 を使用し、
	 * 自身のHPが減っていない場合は 対象プレイヤー に攻撃を行う
	 * @param attacker {@inheritDoc}
	 * @param defender {@inheritDoc}
	 */
	@Override
	public void Attack(Player attacker, Player defender) {
		MagicManager magicmanager = new MagicManager();
		Magic myMagic = magicmanager.UseRecoverMagic();

		// MPが足りているかの判定
		if ((attacker.GetMP() < myMagic.usemp) ||
				attacker.GetMP() < myMagic.usemp) {
			// MPが足りない場合　通常攻撃
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

		} else {
			//MPが足りている場合

			if (attacker.GetHP() <= attacker.GetMaxHP()/2) {
				// HPが少ないとき
				myMagic.effect(attacker, attacker);
			} else {
				// HPが多いとき
				// MPが足りない場合　通常攻撃
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
		}
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================

}