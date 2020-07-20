package namebattler_2;

// 魔法：ヒール
public class MagicHeal extends Magic {

	// =======================
	// フィールド変数
	// =======================
	protected int HealDamage = 50;

	// =======================
	// コンストラクタ
	// =======================
	public MagicHeal() {
		super();
	}

	// =======================
	// Getter / Setter
	// =======================

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 魔法のパラメータを設定する
	 */
	@Override
	protected void MakeMagic() {
		// 魔法のパラメータを生成する
		this.name = "ヒール";
		this.mp = 20;
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * 対象プレイヤーに魔法を使用する
	 */
	@Override
	public void UseMagic(Player player) {
		player.hp += HealDamage;
		System.out.println(player.GetName() + "のHPは" + HealDamage + "回復した!");
	}
}
