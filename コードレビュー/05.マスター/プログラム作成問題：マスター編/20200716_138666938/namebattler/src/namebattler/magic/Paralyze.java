package namebattler.magic;

import namebattler.player.Player;

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
		//ターゲットを麻痺に、stParalyzeをtrueにし、最大ターンを代入
		System.out.println(target.GetName() + "を麻痺状態にした！");
		target.GetStMana().SetStParalyze(true);
		target.GetStMana().SetStParalyzeTurn(target.GetStMana().GetMaxParalyzeTurn());
	}
}
