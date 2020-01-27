package equipment;

public class Jewelry extends Equipment{
// MP

	// =======================
	// フィールド変数
	// =======================
	// =======================
	// コンストラクタ
	// =======================
	public Jewelry(String name,int probability) {
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
	public int EquippedAGI(int agi) {
		return agi * this.probability / 100;
	}

	@Override
	public String toString() {
		return name;
	}

}
