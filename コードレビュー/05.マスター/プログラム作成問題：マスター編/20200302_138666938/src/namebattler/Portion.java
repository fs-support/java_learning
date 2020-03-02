package namebattler;

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

		if(party.partyNo == 1) {
			System.out.println("Aチームは" + this.name + "を使用した！");
		}else {
			System.out.println("Bチームは" + this.name + "を使用した！");
		}

		//メンバー全員のHPを最大HPの30％回復
		for(int i = 0; i < party.GetMembers().size(); i++) {
			party.GetMembers().get(i).hp += (int)(0.3*(party.GetMembers().get(i).maxHp));

			//現在HPが最大HPより多くなった場合の調整
			if(party.GetMembers().get(i).hp > party.GetMembers().get(i).maxHp) {
				party.GetMembers().get(i).hp = party.GetMembers().get(i).maxHp;
			}

			System.out.print(party.GetMembers().get(i).name + "はHPを"
						+ (int)(0.3*(party.GetMembers().get(i).maxHp)) + "回復した！");
			System.out.printf("(HP=%3d : MP=%3d)\n",  party.GetMembers().get(i).GetHP(), party.GetMembers().get(i).GetMP());
		}

		System.out.println("");

	}

}
