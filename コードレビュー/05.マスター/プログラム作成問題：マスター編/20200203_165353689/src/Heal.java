
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
	public String GetName() {
		return this.name;
	}

	public int GetUseMP() {
		return this.usemp;
	}

	// 魔法のMPの取得
	public int GetRecoverHP() {
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

			activePlayer.UseMP(this.usemp);

			System.out.println(activePlayer.GetName() + " の " + this.name);
			System.out.println(passivePlayer.GetName() + " の HP が " + this.recoverhp +" 回復した！");

			passivePlayer.RecoverHP(this.recoverhp);


	}

}
