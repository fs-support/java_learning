package namebattler.magic;

import namebattler.player.Player;

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
		//ターゲットを毒に、stPoisonをtrueにし、最大ターンを代入
		System.out.println(target.GetName() + "を毒状態にした！");
		target.GetStMana().SetStPoison(true);
		target.GetStMana().SetStPoisonTurn(target.GetStMana().GetMaxPoisonTurn());
	}
}
