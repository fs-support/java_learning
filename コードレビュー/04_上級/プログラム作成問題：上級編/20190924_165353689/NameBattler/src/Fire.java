
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
	// 魔法の名前の取得
	public String getName() {
		return this.name;
	}
	// 魔法の消費MPの取得
	public int getMP() {
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
		if (activePlayer.GetMP() < 10) {
			// MPが足りない場合の処理
			System.out.println("MPが足りない！");
		} else {
			// MPが足りている場合の処理

			int damage = RandomGenerator.RandomRange(10,30);

			activePlayer.UseMP(this.usemp);

			System.out.println(activePlayer.GetName() + " の " + this.name);
			System.out.println(passivePlayer.GetName() + " に " + damage + " のダメージ");

			passivePlayer.Damage(damage);
		}


	}

}
