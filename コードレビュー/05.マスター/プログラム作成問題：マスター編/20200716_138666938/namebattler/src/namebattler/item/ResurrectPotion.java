package namebattler.item;

import namebattler.manager.Party;

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
		//使用アイテムのログ
			this.itemUseLog(party);

		//アイテム効果と結果表示の処理を入れる
		//死亡しているメンバーがいる場合
		if( !( party.GetDelMembers().isEmpty() ) ) {
			//蘇生するメンバーを死亡者リストからランダムで選択
			int resurrect = ran.nextInt(party.GetDelMembers().size());

			//delmenberよりmenberに格納
			party.AppendPlayer(party.GetDelMembers().get(resurrect));

			//蘇生したメンバーのmenber内、要素番号
			int last = (party.GetMembers().size()) - 1;

			//蘇生したメンバーのステータスを初期状態に
			//HP・MP全回復
			party.GetMembers().get(last).SetHP(party.GetMembers().get(last).GetMaxHp());
			party.GetMembers().get(last).SetMP(party.GetMembers().get(last).GetMaxMp());
			//攻撃・回復魔法使用の判定をfalseに
			party.GetMembers().get(last).SetMagiAt(false);
			party.GetMembers().get(last).SetPMedic(false);
			//毒・麻痺のステータス判定をfalseに
			party.GetMembers().get(last).GetStMana().SetStPoison(false);
			party.GetMembers().get(last).GetStMana().SetStParalyze(false);

			//蘇生したメンバーをdelmenberより削除
			party.RemoveDelPlayer(party.GetDelMembers().get(resurrect));

			//アイテム使用結果のログ
			System.out.print(party.GetMembers().get(last).GetName() + "は生き返った！");
			System.out.printf("(HP=%3d : MP=%3d)\n",
								party.GetMembers().get(last).GetHP(), party.GetMembers().get(last).GetMP());

		//誰も死んでいない場合
		}else {
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
		}

		System.out.println("");
	}

}
