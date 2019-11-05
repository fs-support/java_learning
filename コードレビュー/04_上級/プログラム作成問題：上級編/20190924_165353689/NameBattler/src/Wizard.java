// プレイヤー：魔法使い
public class Wizard extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name) {
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
		this.hp = (GetNumber(0, 10) + 5) * 10;
		this.maxhp = GetNumber(1, 50) + 30;
		this.mp = GetNumber(1, 50) + 30;
		this.str = GetNumber(2, 50) + 1;
		this.def = GetNumber(3, 50) + 1;
		this.luck = GetNumber(4, 100);
		this.agi = GetNumber(5, 40) + 20;

	}

	// ↑を修正して魔法使い用のパラメーターに直す
	// ↓を修正して通常攻撃か魔法攻撃を行うようにする
	/**
	 * {@inheritDoc}<br>
	 * 攻撃側プレイヤー(atacker)のMPが魔法の消費MPに足りている場合は
	 * @param attacker {@inheritDoc}
	 * @param defender {@inheritDoc}
	 */
	@Override
	public void Attack(Player attacker, Player defender) {
		MagicManager magicmanager = new MagicManager();
		Magic myMagic = magicmanager.UseAttackMagic();
		if (attacker.GetMP() < myMagic.getMP()) {
			// MPが足りない場合の処理
			// 与えるダメージを求める
			System.out.println(GetName() + " の攻撃！");
			int damage = CalcDamage(defender);

			// 求めたダメージを対象プレイヤーに与える
			System.out.println(defender.GetName() + " に " + damage + " のダメージ！");
			defender.Damage(damage);

			// 倒れた判定
			if (defender.GetHP() <= 0) {
				System.out.println(defender.GetName() + " は力尽きた...");
			}

		} else {
			myMagic.effect(attacker, defender);
		}
		// MPが足りている場合の処理
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================

}