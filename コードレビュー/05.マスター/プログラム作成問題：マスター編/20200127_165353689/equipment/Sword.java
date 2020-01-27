package equipment;

public class Sword extends Equipment{
// ATKが上昇

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Sword(String name,int probability) {
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
	public int EquippedSTR(int str) {
		return str * this.probability / 100;
	}

	@Override
	public String toString() {
		return name;
	}

}
