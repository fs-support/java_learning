
public class Heal extends Magic {

	// =======================
	// フィールド変数
	// =======================
	// 魔法の名前
	protected String name = "ヒール";
	// 消費MP
	protected int usemp = 20;
	// 回復量
	protected int recoverhp = 50;

	// =======================
	// コンストラクタ
	// =======================
	Heal() {

	}

	// =======================
	// Getter / Setter
	// =======================
	// 魔法のMPの取得
	public String getName() {
		return this.name;
	}

	public int getMP() {
		return this.usemp;
	}

	public int getRecoverHP() {
		return this.recoverhp;
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
	 * 対称プレーヤーの体力を回復する
	 * @param activePlayer {@inheritDoc}
	 * @param passivePlayer {@inheritDoc}
	 */
	@Override
	public void effect(Player activePlayer, Player passivePlayer) {
		  // HPを 50 回復する
     	if (activePlayer.GetMP() < this.usemp) {
			// MPが足りない場合の処理
			System.out.println("MPが足りない！");
		} else {
			// MPが足りている場合の処理
			activePlayer.UseMP(this.usemp);

			System.out.println(activePlayer.GetName() + "の" + this.name);
			System.out.println(passivePlayer.GetName() + "の HP が" + this.recoverhp +" 回復した！");

			passivePlayer.RecoverHP(this.recoverhp);

		}
	}

}
