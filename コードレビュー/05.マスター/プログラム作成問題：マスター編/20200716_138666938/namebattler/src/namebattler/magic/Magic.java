package namebattler.magic;

import java.util.Random;

import namebattler.player.Player;

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
	//ダメージ量
	protected int damage;
	//回復量
	protected int recovery = 50;

	// =======================
	// コンストラクタ
	// =======================
	//コンストラクタ
	public Magic() {

	}

	// =======================
	// Getter / Setter
	// =======================
	//ダメージ量を取得する
	public int GetDamage(){
		return this.damage;
	}

	//回復量を取得する
	public int GetRecovery(){
		return this.recovery;
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
	public void use(Player attacker, Player target) {
		//ログに表示
		System.out.println(attacker.GetName() + "はMPを" + this.cost + "消費して、" + this.name + "を使用した。");
		//使用者のMPを減らす
		attacker.SetMP(attacker.GetMP() - this.cost);
		//個別の効果を実行
		this.Magieffect(target);
	}

}
