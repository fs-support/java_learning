package equipment;

public class Armor extends Equipment{
// DEFが上昇

	// =======================
	// フィールド変数
	// =======================
	// =======================
	// コンストラクタ
	// =======================
	public Armor(String name,int probability) {
		super(name,probability);
	}

	// =======================
	// Getter / Setter
	// =======================
	// =======================
	// protected メソッド
	// =======================

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	public int EquippedDEF(int def) {
		return def * this.probability / 100;
	}
	@Override
	public String toString() {
		return name;
	}

}
