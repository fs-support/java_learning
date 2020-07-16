package namebattler.magic;

import namebattler.player.Player;

public class Heal extends Magic{
	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	//コンストラクタ
	public Heal() {
		super();
		//名前をセット
		this.name = "ヒール";
		//使用MPをセット
		this.cost = 20;
	}

	// =======================
	// protected メソッド
	// =======================
	//個別効果
	@Override
	protected void Magieffect(Player target) {
		//ターゲットを回復
		System.out.println(target.GetName() + "を" + this.recovery + "回復！");

	}
}
