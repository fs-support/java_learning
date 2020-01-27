package equipment;

public class Shield extends Equipment{
// 装備したとき防御ができる
// 少しだけDEFが上がる

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Shield(String name,int probability) {
		super(name,probability);}

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
