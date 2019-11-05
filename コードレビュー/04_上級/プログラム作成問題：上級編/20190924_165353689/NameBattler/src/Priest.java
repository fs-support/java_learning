// プレイヤー：魔法使い
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
		// 戦士のパラメータを名前から生成する
		this.hp = (GetNumber(0, 12) + 8) * 10;
		this.maxhp = (GetNumber(0, 12) + 8) * 10;
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
	 * MPが足りてない場合には、攻撃を行う<br>
	 * MPが足りている場合 かつ HPが少ない場合は、回復魔法 を使う<br>
	 * MPが足りている場合 かつ HPが多い場合は、状態異常魔法 を使う<br>
	 * @param attacker {@inheritDoc}
	 * @param defender {@inheritDoc}
	 */
	@Override
	public void Attack(Player attacker, Player defender) {
		MagicManager magicmanager = new MagicManager();
		Magic myRecoverMagic = magicmanager.UseRecoverMagic();
		Magic myDebuffMagic = magicmanager.UseDebuffMagic();

		// MPが足りているかの判定
		if ((attacker.GetMP() < myRecoverMagic.usemp) ||
				attacker.GetMP() < myDebuffMagic.usemp) {
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
				myRecoverMagic.effect(attacker, attacker);
			} else {
				// HPが多いとき
				myDebuffMagic.effect(attacker, defender);
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