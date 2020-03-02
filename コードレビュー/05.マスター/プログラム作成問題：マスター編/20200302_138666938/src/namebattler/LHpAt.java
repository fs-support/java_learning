package namebattler;

public class LHpAt extends Tactical {
	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public LHpAt() {
		super();
	}

	// =======================
	// public メソッド
	// =======================
	//攻撃のターゲットを決める処理
	public Player target(Player attack,Party A, Party B) {

		System.out.println("作戦：HPの低い敵を攻撃");

		//ターゲットのpartyを選択
		if(attack.party == 1) targetP = B;
		else targetP = A;

		//HPの低いメンバーを抽出
		//最低HPを格納
		int minHp = targetP.GetMembers().get(0).GetHP();
		//最低HPの要素
		int targetNo = 0;
		for(int i = 1; i <  targetP.GetMembers().size(); i++) {
			//HPの比較
			if(minHp > (targetP.GetMembers().get(i).GetHP())) {
				minHp = targetP.GetMembers().get(i).GetHP();
				targetNo = i;
			//HPが同じ場合は運で比較
			}else if(minHp == (targetP.GetMembers().get(i).GetHP())) {
				if((targetP.GetMembers().get(i - 1).GetLUCK()) > (targetP.GetMembers().get(i).GetLUCK())) {
					minHp = targetP.GetMembers().get(i).GetHP();
					targetNo = i;
				}
			}
		}

		targetM = targetP.GetMembers().get(targetNo);

		return targetM;
	}





}
