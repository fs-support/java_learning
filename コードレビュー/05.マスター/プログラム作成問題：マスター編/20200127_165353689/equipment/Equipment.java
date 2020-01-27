package equipment;

public class Equipment {
	// =======================
	// フィールド変数
	// =======================

	// 剣の名前
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

	public int GetValue() {
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




}
