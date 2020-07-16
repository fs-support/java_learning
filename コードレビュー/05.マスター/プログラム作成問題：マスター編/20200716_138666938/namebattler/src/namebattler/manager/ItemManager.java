package namebattler.manager;

import java.util.Random;

import namebattler.item.Elixir;
import namebattler.item.Ether;
import namebattler.item.Item;
import namebattler.item.Portion;
import namebattler.item.ResurrectPotion;

public class ItemManager {
	// =======================
	// フィールド変数
	// =======================
	//ランダムクラス
	protected Random ran = new Random();
	//アイテムクラス
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

		//各アイテムの出現率
		//リザレクトポーション：5％
		if(itemChoice < 5) {
			this.rPotion.effect(party);
		//エリクサー：10％
		}else if(itemChoice < 15) {
			this.elixir.effect(party);
		//エーテル：35％
		}else if(itemChoice < 50) {
			this.ether.effect(party);
		//ポーション：50％
		}else {
			this.portion.effect(party);
		}
	}

}
