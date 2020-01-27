
public class Fire extends Magic {

	// =======================
	// フィールド変数
	// =======================
	// 魔法の名前
	protected String name = "ファイヤー";
	// 消費MP
	protected int usemp = 10;

	// =======================
	// コンストラクタ
	// =======================
	public Fire() {
super();
	}

	// =======================
	// Getter / Setter
	// =======================
	public String GetName() {
		return this.name;
	}

	public int GetUseMP() {
		return this.usemp;
	}

	// =======================
	// protected メソッド
	// =======================

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * 対称プレーヤーに炎撃を加える
	 * @param activePlayer {@inheritDoc}
	 * @param passivePlayer {@inheritDoc}
	 */
	@Override
	public void effect(Player activePlayer, Player passivePlayer) {

		// MPが足りている場合の処理

		int damage = RandomGenerator.RandomRange(10, 30);

		activePlayer.UseMP(this.usemp);

		System.out.println(activePlayer.GetName() + " の " + this.name + "！");
		System.out.println(passivePlayer.GetName() + " に " + damage + " のダメージ！");

		passivePlayer.Damage(damage);
	}

}
