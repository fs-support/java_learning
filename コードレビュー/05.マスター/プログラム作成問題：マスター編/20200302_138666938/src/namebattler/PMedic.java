package namebattler;

import java.util.ArrayList;

public class PMedic extends Tactical {
	// =======================
	// フィールド変数
	// =======================
	protected ArrayList<Integer> hpPar = new ArrayList<Integer>();

	// =======================
	// コンストラクタ
	// =======================
	public PMedic() {
		super();
	}

	// =======================
	// public メソッド
	// =======================
	//回復するターゲットを決める処理
	public Player target(Player attack,Party A, Party B) {

		System.out.println("作戦：仲間を回復");

		//ターゲットのpartyを選択
		if(attack.party == 1) {
			attackP = A;
			targetP = B;

		}else {
			attackP = B;
			targetP = A;
		}

		//回復使用可にする
		attack.pMedic = true;

		this.hpPar.clear();

		//HP比率の低いメンバーを抽出
		//現在のHP比率を格納
		for(int i = 0; i < attackP.GetMembers().size(); i++) {

			int hp = (int)((attackP.GetMembers().get(i).GetHP() / attackP.GetMembers().get(i).GetMaxHp()) * 100);

			hpPar.add(hp);
		}

		//最低HP比率
		int minHp = hpPar.get(0);
		//最低HP比率のメンバーの要素
		int targetNo = 0;
		for(int i = 1; i <  attackP.GetMembers().size(); i++) {
			//HP比率の比較
			if(minHp > (hpPar.get(i))) {
				minHp = hpPar.get(i);
				targetNo = i;
			//HP比率が同じ場合は運で比較
			}else if(minHp == (attackP.GetMembers().get(i).GetHP())) {
				if((attackP.GetMembers().get(i - 1).GetLUCK()) > (attackP.GetMembers().get(i).GetLUCK())) {
					minHp = hpPar.get(i);
					targetNo = i;
				}
			}
		}

		targetM = attackP.GetMembers().get(targetNo);

		if(minHp > 80) {
			System.out.println("回復の必要はなさそうだ！");
			attack.pMedic = false;
			targetM = targetP.GetMembers().get(this.ran.nextInt(targetP.GetMembers().size()));
		}

		return targetM;
	}

}
