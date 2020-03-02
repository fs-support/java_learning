package namebattler;

//死んだメンバーをランダムで一人蘇生、全員生きていれば誰かのHP,MPを全快:5%
public class ResurrectPotion extends Item{
	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public ResurrectPotion() {
		super();
		this.name = "蘇生薬";

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

		//アイテム効果と結果表示の処理を入れる

		if(!(party.GetDelMembers().isEmpty())) {
			//蘇生するメンバーの要素
			int resurrect = ran.nextInt(party.GetDelMembers().size());

			//delmenberよりmenberに格納
			party.AppendPlayer(party.GetDelMembers().get(resurrect));

			//蘇生したメンバーのmenber内、要素番号
			int last = (party.GetMembers().size()) - 1;

			//蘇生したメンバーのHP・MPを全回復
			party.GetMembers().get(last).hp = party.GetMembers().get(last).maxHp;
			party.GetMembers().get(last).mp = party.GetMembers().get(last).maxMp;

			//蘇生したメンバーをdelmenberより削除
			party.RemoveDelPlayer(party.GetDelMembers().get(resurrect));

			System.out.print(party.GetMembers().get(last).GetName() + "は生き返った！");
			System.out.printf("(HP=%3d : MP=%3d)\n",  party.GetMembers().get(last).GetHP(), party.GetMembers().get(last).GetMP());

		}else {
			int recovery = ran.nextInt(party.GetMembers().size());

			party.GetMembers().get(recovery).hp = party.GetMembers().get(recovery).maxHp;

			party.GetMembers().get(recovery).mp = party.GetMembers().get(recovery).maxMp;

			System.out.print(party.GetMembers().get(recovery).GetName() + "はHP・MPが全回復した！");
			System.out.printf("(HP=%3d : MP=%3d)\n",  party.GetMembers().get(recovery).GetHP(), party.GetMembers().get(recovery).GetMP());

		}

		System.out.println("");

	}

}
