package namebattler;

import java.util.ArrayList;
import java.util.Collections;

public class MedicTarget extends Tactical {
	// =======================
	// フィールド変数
	// =======================
	//相手パーティーの回復職を格納
	protected ArrayList<Integer> medi = new ArrayList<Integer>();

	// =======================
	// コンストラクタ
	// =======================
	public MedicTarget() {
		super();
	}

	// =======================
	// public メソッド
	// =======================
	public Player target(Player attack,Party A, Party B) {

		System.out.println("作戦：回復職を攻撃");


		//ターゲットのpartyを選択
		if(attack.party == 1) targetP = B;
		else targetP = A;

		//mediをクリア
		this.medi.clear();

		for(int i = 0; i <  targetP.GetMembers().size(); i++) {
			if(targetP.GetMembers().get(i).GetJobName() == "僧侶"
					|| targetP.GetMembers().get(i).GetJobName() == "勇者") {
				medi.add(i);
			}
		}

		//相手パーティーに回復職が一人の時
		if(medi.size() == 1) {
			targetM = targetP.GetMembers().get(medi.get(0));
		//相手パーティーに回復職が複数の時
		}else if(medi.size() > 1) {
			//mediをシャッフル    ※とりあえずランダムで
			Collections.shuffle(this.medi);
			targetM = targetP.GetMembers().get(medi.get(0));
		//相手パーティーに回復職がいないとき　※仮
		}else {
			targetM = targetP.GetMembers().get(0);
		}

		return targetM;
	}
}
