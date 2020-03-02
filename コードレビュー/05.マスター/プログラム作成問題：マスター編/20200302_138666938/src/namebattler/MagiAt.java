package namebattler;

import java.util.ArrayList;

public class MagiAt extends Tactical {
	// =======================
	// フィールド変数
	// =======================
	//相手パーティーの魔法使いを格納
	protected ArrayList<Integer> wiz = new ArrayList<Integer>();

	// =======================
	// コンストラクタ
	// =======================
	public MagiAt() {
		super();
	}

	// =======================
	// public メソッド
	// =======================
	public void action(Player attack) {

		System.out.println("作戦：魔法攻撃優先");

//		//ターゲットのpartyを選択
//		if(attack.party == 1) {
//			attackP = A;
//			targetP = B;
//
//		}else {
//			attackP = B;
//			targetP = A;
//		}

		//魔法使用可にする
		attack.magiAt = true;

//		//攻撃ターゲット
//		if(this.attackP.intensCh == true) {
//			targetM = attackP.IntensTarget;
//		}else {
//			targetM = targetP.GetMembers().get(this.ran.nextInt(targetP.GetMembers().size()));
//		}

		return;
	}
}
