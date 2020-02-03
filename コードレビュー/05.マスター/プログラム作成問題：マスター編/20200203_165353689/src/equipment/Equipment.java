package equipment;

public class Equipment {
	// =======================
	// フィールド変数
	// =======================

	// 装備の名前
	protected String name;

	// 上昇値
	protected int probability;

	// =======================
	// コンストラクタ
	// =======================
	public Equipment(String name,int probability) {
		this.name = name;
		this.probability = probability;
	}

	// =======================
	// Getter / Setter
	// =======================
	public String GetName() {
		return this.name;
	}

	public int GetProbability() {
		return this.probability;
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
	 * デフォルト値から装備が持つ％分上昇した値を返す
	 * @param value	：	能力のデフォルト値
	 * @return		：	デフォルト値からprobabilityで上昇
	 */
	public int EquippedValue(int value) {
		return value * this.probability / 100;
	}

	/**
	 * 装備の名前を返す
	 */
	@Override
	public String toString() {
		return name;
	}

}
