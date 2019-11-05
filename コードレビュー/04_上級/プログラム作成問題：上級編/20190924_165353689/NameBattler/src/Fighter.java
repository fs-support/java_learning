// プレイヤー：戦士
public class Fighter extends Player {

	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	public Fighter(String name)
	{
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
	protected void MakeCharacter()
	{
		// 戦士のパラメータを名前から生成する
		this.hp = (GetNumber(0, 30) + 10) * 10;
		this.maxhp = (GetNumber(0, 30) + 10) * 10;
		this.mp = GetNumber(1,100) * 0;
		this.str = GetNumber(2, 100) + 30;
		this.def = GetNumber(3, 100) + 30;
		this.luck = GetNumber(4,100) + 1;
		this.agi = GetNumber(5,50) + 1;

	}

	/**
	 * {@inheritDoc}<br>
	 * @param attacker {@inheritDoc}
	 * @param defender {@inheritDoc}
	 */
	@Override
	public void Attack(Player attacker,Player defender)
	{
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