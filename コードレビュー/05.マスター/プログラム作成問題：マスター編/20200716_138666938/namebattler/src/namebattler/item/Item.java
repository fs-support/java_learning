package namebattler.item;

import java.util.Random;

import namebattler.manager.Party;

public class Item {
	// =======================
	// フィールド変数
	// =======================
	//ランダムクラス
	protected Random ran = new Random();
	//アイテム名
	protected String name;

	// =======================
	// コンストラクタ
	// =======================
	public Item() {

	}

	// =======================
	// public メソッド
	// =======================
	//アイテムの使用 *各クラスでオーバーライド
	public void effect(Party party) {

	}

	//使用アイテムのログ
	public void itemUseLog(Party party) {
		if(party.GetPartyNo() == 0) {
			System.out.println("Aチームは" + this.name + "を使用した！");
		}else {
			System.out.println("Bチームは" + this.name + "を使用した！");
		}
	}

}
