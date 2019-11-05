//毒を与える魔法
public class Poison extends Magic {

	// =======================
	// フィールド変数
	// =======================
	// 魔法の名前
	protected String name = "ポイズン";
	// 消費MP
	protected int usemp = 10;

	// =======================
	// コンストラクタ
	// =======================
	public Poison() {

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
	 * MPが足りている場合 かつ 毒状態になっていない場合、毒状態にする<br>
	 * MPが足りている場合 かつ 毒状態になっている場合、毒にかかっていることを System.out で表示する<br>
	 * @param activePlayer {@inheritDoc}
	 * @param passivePlayer {@inheritDoc}
	 */
	@Override
	public void effect(Player activePlayer, Player passivePlayer) {
		if (activePlayer.GetMP() < this.usemp) {
			// MPが足りない場合
			System.out.println("MPが足りない！");
		} else {
			// MPが足りている場合
			System.out.println(activePlayer.GetName() + " の " + this.name);
			activePlayer.UseMP(this.usemp);

			// 毒にかかっているかの判定
			if (passivePlayer.GetParalyze() == false) {
				// 対称プレイヤーが毒にかかってない場合
				activePlayer.SetPoison(true);
				System.out.println(passivePlayer + " は すでに毒状態だ");

			} else {
				// 対称プレイヤーが毒にかかっている場合
				System.out.println(passivePlayer + " は すでに毒にかかっている");

			}
		}
	}
}
