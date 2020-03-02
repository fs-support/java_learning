package namebattler;

import java.util.Random;

public class ItemManager {
	// =======================
	// フィールド変数
	// =======================
	//ランダムクラス
	protected Random ran = new Random();

	protected Item rPotion = new ResurrectPotion();
	protected Item elixir = new Elixir();
	protected Item ether = new Ether();
	protected Item portion = new Portion();

	// =======================
	// コンストラクタ
	// =======================
	public ItemManager() {

	}

	// =======================
	// public メソッド
	// =======================
	public void itemUse(Party party) {
		int itemChoice = ran.nextInt(100);

		if(itemChoice < 5) {
			this.rPotion.effect(party);
		}else if(itemChoice < 15) {
			this.elixir.effect(party);
		}else if(itemChoice < 50) {
			this.ether.effect(party);
		}else {
			this.portion.effect(party);
		}

	}
}
