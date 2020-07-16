package namebattler.item;

import namebattler.manager.Party;

//メンバーの誰かをHP,MP全回復:10%
public class Elixir extends Item{
	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Elixir() {
		super();
		this.name = "エリクサー";
	}

	// =======================
	// public メソッド
	// =======================
	//アイテムの使用
	public void effect(Party party) {
		//使用アイテムのログ
		this.itemUseLog(party);

		//回復するメンバーをランダムで選択
		int recovery = ran.nextInt(party.GetMembers().size());

		//選択したメンバーのHP・MPを全回復、毒・麻痺の回復
		party.GetMembers().get(recovery).SetHP(party.GetMembers().get(recovery).GetMaxHp());
		party.GetMembers().get(recovery).SetMP(party.GetMembers().get(recovery).GetMaxMp());
		party.GetMembers().get(recovery).GetStMana().SetStPoison(false);
		party.GetMembers().get(recovery).GetStMana().SetStParalyze(false);


		//アイテム使用結果のログ
		System.out.print(party.GetMembers().get(recovery).GetName() + "はHP・MPが全回復した！");
		System.out.printf("(HP=%3d : MP=%3d)\n",
							party.GetMembers().get(recovery).GetHP(), party.GetMembers().get(recovery).GetMP());
		System.out.println("");
	}

}
