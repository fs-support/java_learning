package namebattler.item;

import namebattler.manager.Party;

//メンバー全員のMPを全回復:35%
public class Ether extends Item{
	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Ether() {
		super();
		this.name = "エーテル";
	}

	// =======================
	// public メソッド
	// =======================
	//アイテムの使用
	public void effect(Party party) {
		//使用アイテムのログ
		this.itemUseLog(party);

		//メンバー全員のMPを全回復
		for(int i = 0; i < party.GetMembers().size(); i++) {
			//メンバーのMPを全回復
			party.GetMembers().get(i).SetMP(party.GetMembers().get(i).GetMaxMp());

			//アイテム使用結果のログ
			System.out.print(party.GetMembers().get(i).GetName() + "はMPを全回復した！");
			System.out.printf("(HP=%3d : MP=%3d)\n",
								party.GetMembers().get(i).GetHP(), party.GetMembers().get(i).GetMP());
		}

		System.out.println("");
	}

}
