package namebattler.magic;

import namebattler.player.Player;

public class Fire extends Magic{
	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	//コンストラクタ
	public Fire() {
		super();
		//名前をセット
		this.name = "ファイアー";
		//使用MPをセット
		this.cost = 10;
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
