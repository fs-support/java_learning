//毒を与える魔法
public class Recovery extends Magic {

	// =======================
	// フィールド変数
	// =======================
	// 魔法の名前
	protected String name = "リカバリー";
	// 消費MP
	protected int usemp = 20;

	// =======================
	// コンストラクタ
	// =======================
	public Recovery() {

	}

	// =======================
	// Getter / Setter
	// =======================
	// 魔法のMPの取得
	public String GetUseName() {
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
	 * 対称プレーヤーに毒状態を付与する<br>
	 * MPが足りない場合は、MPが足りないことを System.out で表示する<br>
	 * MPが足りている場合 かつ 毒状態になっている場合、毒を解除するにする<br>
	 * MPが足りている場合 かつ 麻痺状態になっている場合、麻痺状態を解除する<br>
	 * @param activePlayer {@inheritDoc}
	 * @param passivePlayer {@inheritDoc}
	 */
	@Override
	public void effect(Player activePlayer, Player passivePlayer) {

		// MPが足りている場合
		System.out.println(activePlayer.GetName() + " の " + this.name);
		activePlayer.UseMP(this.usemp);

		// 毒にかかっているかの判定
		if (passivePlayer.isPoison() == true) {
			// 対称プレイヤーが毒状態の場合
			activePlayer.SetPoison(false);
			System.out.println(passivePlayer.GetName() + " の毒が解除された！");
			return;
		}

		// 麻痺にかかっているかの判定
		if (passivePlayer.isParalyze() == true) {
			activePlayer.SetParalyze(false);
			System.out.println(passivePlayer.GetName() + " の麻痺が解除された！");
			return;

		}
	}
}
