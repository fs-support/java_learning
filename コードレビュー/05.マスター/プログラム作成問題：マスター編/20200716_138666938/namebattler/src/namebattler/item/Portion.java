package namebattler.item;

import namebattler.manager.Party;

//メンバー全員のHPを最大HPの30％回復:50%
public class Portion extends Item{
	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Portion() {
		super();
		this.name = "ポーション";
	}

	// =======================
	// public メソッド
	// =======================
	//アイテムの使用
	public void effect(Party party) {
		//使用アイテムのログ
		this.itemUseLog(party);

		//メンバー全員のHPを最大HPの30％回復
		for(int i = 0; i < party.GetMembers().size(); i++) {
			party.GetMembers().get(i).SetHP( (party.GetMembers().get(i).GetHP())
												+ (int)(0.3*(party.GetMembers().get(i).GetMaxHp())) );

			//現在HPが最大HPより多くなった場合は最大HPの値を入れる
			if(party.GetMembers().get(i).GetHP() > party.GetMembers().get(i).GetMaxHp()) {
				party.GetMembers().get(i).SetHP(party.GetMembers().get(i).GetMaxHp());
			}

			//アイテム使用結果のログ
			System.out.print(party.GetMembers().get(i).GetName() + "はHPを"
								+ (int)(0.3*(party.GetMembers().get(i).GetMaxHp())) + "回復した！");
			System.out.printf("(HP=%3d : MP=%3d)\n",
								party.GetMembers().get(i).GetHP(), party.GetMembers().get(i).GetMP());
		}

		System.out.println("");
	}

}
