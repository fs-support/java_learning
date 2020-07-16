package namebattler.magic;

import namebattler.player.Player;

public class Thunder extends Magic{
	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	//コンストラクタ
	public Thunder() {
		super();
		//名前をセット
		this.name = "サンダー";
		//使用MPをセット
		this.cost = 20;
	}

	// =======================
	// protected メソッド
	// =======================
	//個別効果
	@Override
	protected void Magieffect(Player target) {
		//ダメージ量を設定(10～30)
		this.damage = this.ran.nextInt(21)+10;
	}
}
