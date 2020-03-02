package namebattler;

public class Paralyze extends Magic{
	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	//コンストラクタ
	public Paralyze() {
		super();
		//名前をセット
		this.name = "パライズ";
		//使用MPをセット
		this.cost = 10;
	}

	// =======================
	// protected メソッド
	// =======================
	//個別効果
	@Override
	protected void Magieffect(Player target) {
		//ターゲットを麻痺に
		System.out.print(target.GetName() + "を麻痺状態にした！");
		target.stMana.stParalyze = true;
	}
}
