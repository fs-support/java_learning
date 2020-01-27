
public class Thunder extends Magic {

	// =======================
	// フィールド変数
	// =======================
	// 魔法の名前
	protected String name = "サンダー";
	// 消費MP
	protected int usemp = 20;

	// =======================
	// コンストラクタ
	// =======================
	public Thunder() {

	}

	// =======================
	// Getter / Setter
	// =======================
	// 魔法のMPの取得
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
	 * 対称プレーヤーに電撃を加える
	 * @param activePlayer {@inheritDoc}
	 * @param passivePlayer {@inheritDoc}
	 */
	@Override
	public void effect(Player activePlayer, Player passivePlayer) {

		// MPが足りている場合の処理
		int damage = RandomGenerator.RandomRange(10, 30);

		activePlayer.UseMP(this.usemp);

		System.out.println(activePlayer.GetName() + " の " + this.name);
		System.out.println(passivePlayer.GetName() + " に " + damage + " のダメージ");

		passivePlayer.Damage(damage);
	}

}
