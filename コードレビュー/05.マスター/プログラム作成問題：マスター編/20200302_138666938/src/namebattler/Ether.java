package namebattler;

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

		if(party.partyNo == 1) {
			System.out.println("Aチームは" + this.name + "を使用した！");
		}else {
			System.out.println("Bチームは" + this.name + "を使用した！");
		}

		//メンバー全員のMPを全回復
		for(int i = 0; i < party.GetMembers().size(); i++) {
			party.GetMembers().get(i).mp = party.GetMembers().get(i).maxMp;

			System.out.print(party.GetMembers().get(i).name + "はMPを全回復した！");
			System.out.printf("(HP=%3d : MP=%3d)\n",  party.GetMembers().get(i).GetHP(), party.GetMembers().get(i).GetMP());
		}

		System.out.println("");

	}

}
