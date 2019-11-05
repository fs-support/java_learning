public class Magic {

	// =======================
	// フィールド変数
	// =======================
	// 魔法の名前
	protected String name;
	// 消費MP
	protected int usemp;


	// =======================
	// コンストラクタ
	// =======================
	public Magic() {

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
     *魔法を行使する
     * @param activePlayer : 行為プレイヤー
	 * @param passivePlayer : 対象プレイヤー
     */
	public void effect(Player activePlayer, Player passivePlayer) {
		// 魔法ごとに処理をオーバーライドして記述する
	}


}
