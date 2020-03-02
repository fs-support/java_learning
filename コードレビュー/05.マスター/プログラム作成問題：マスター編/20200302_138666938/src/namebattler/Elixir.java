package namebattler;

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

		if(party.partyNo == 1) {
			System.out.println("Aチームは" + this.name + "を使用した！");
		}else {
			System.out.println("Bチームは" + this.name + "を使用した！");
		}

		//メンバーの誰かをHP,MP全回復
		//回復するメンバーの要素を決める
		int recovery = ran.nextInt(party.GetMembers().size());

		party.GetMembers().get(recovery).hp = party.GetMembers().get(recovery).maxHp;

		party.GetMembers().get(recovery).mp = party.GetMembers().get(recovery).maxMp;

		System.out.print(party.GetMembers().get(recovery).GetName() + "はHP・MPが全回復した！");
		System.out.printf("(HP=%3d : MP=%3d)\n",  party.GetMembers().get(recovery).GetHP(), party.GetMembers().get(recovery).GetMP());
		System.out.println("");

	}

}
