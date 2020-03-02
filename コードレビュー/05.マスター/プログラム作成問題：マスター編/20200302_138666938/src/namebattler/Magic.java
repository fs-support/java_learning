package namebattler;

import java.util.Random;

public class Magic {
	// =======================
	// フィールド変数
	// =======================
	//ランダムクラス
	protected Random ran = new Random();
	//名前
	protected String name;
	//使用MP
	protected int cost;

	// =======================
	// コンストラクタ
	// =======================
	//コンストラクタ
	public Magic() {

	}

	// =======================
	// protected メソッド
	// =======================
	//個別効果
	protected void Magieffect(Player target) {
		//個別にオーバーライド
		//ターゲットへの効果
	}

	// =======================
	// public メソッド
	// =======================
	//使用時の処理
	public void use(Player wiz, Player target) {
		//ログに表示
		System.out.println(wiz.GetName() + "は、" + this.name + "を使用した。");
		//個別の効果を実行
		this.Magieffect(target);
		//使用者のMPを減らす
		System.out.println(wiz.GetName() + "は、MPを" + this.cost + "消費した。");
		wiz.mp -= this.cost;
	}

}
