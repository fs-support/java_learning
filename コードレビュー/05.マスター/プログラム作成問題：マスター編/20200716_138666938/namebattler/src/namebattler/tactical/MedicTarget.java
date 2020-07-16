package namebattler.tactical;

import java.util.ArrayList;
import java.util.Collections;

import namebattler.manager.Party;
import namebattler.player.Player;

//相手メンバーの回復職を狙う作戦
public class MedicTarget extends Tactical {
	// =======================
	// フィールド変数
	// =======================
	//相手パーティーの回復職を格納
	protected ArrayList<Integer> targetMedi = new ArrayList<Integer>();

	// =======================
	// コンストラクタ
	// =======================
	public MedicTarget() {
		super();
	}

	// =======================
	// public メソッド
	// =======================
	//相手メンバーの回復職を狙って攻撃
	public void tacticalAction(Party attackP, Party targetP, Player attacker) {
		//targetMediをクリア
		this.targetMedi.clear();

		//ターゲットパーティーに回復職がいればtargetMediに格納
		for(int i = 0; i <  targetP.GetMembers().size(); i++) {
			if(targetP.GetMembers().get(i).GetJobName() == "僧侶"
					|| targetP.GetMembers().get(i).GetJobName() == "勇者") {
				this.targetMedi.add(i);
			}
		}

		//相手パーティーに回復職が一人の時
		if(this.targetMedi.size() == 1) {
			this.targetPlayer = targetP.GetMembers().get(this.targetMedi.get(0));
		//相手パーティーに回復職が複数の時
		}else if(this.targetMedi.size() > 1) {
			//targetMediをシャッフル　※とりあえずランダムで
			Collections.shuffle(this.targetMedi);
			this.targetPlayer = targetP.GetMembers().get(this.targetMedi.get(0));
		//相手パーティーに回復職がいないときはランダム
		}else {
			this.targetPlayer = targetP.GetMembers().get( ran.nextInt( targetP.GetMembers().size() ) );
		}

		//指定したターゲットに攻撃
		attacker.Attack(this.targetPlayer);
	}

}
