package namebattler;

public class Poison extends Magic{
	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	//コンストラクタ
	public Poison() {
		super();
		//名前をセット
		this.name = "ポイズン";
		//使用MPをセット
		this.cost = 10;
	}

	// =======================
	// protected メソッド
	// =======================
	//個別効果
	@Override
	protected void Magieffect(Player target) {
		//ターゲットを毒に
		System.out.print(target.GetName() + "を毒状態にした！");
		target.stMana.stPoison = true;
	}
}
