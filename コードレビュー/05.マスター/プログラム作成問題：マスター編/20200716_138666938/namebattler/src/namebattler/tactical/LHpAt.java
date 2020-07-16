package namebattler.tactical;

import namebattler.manager.Party;
import namebattler.player.Player;

//相手パーティーのHPが低いプレイヤーを狙う作戦
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
	//相手パーティーのHPが低いプレイヤーを狙って攻撃
	public void tacticalAction(Party attackP, Party targetP, Player attacker) {
		//HPの低いメンバーを抽出
		//targetPの最初のプレイヤーのHPを格納
		int minHp = targetP.GetMembers().get(0).GetHP();
		//最低HPの要素
		int targetNo = 0;
		//HPの比較
		for(int i = 1; i <  targetP.GetMembers().size(); i++) {
			if(minHp > (targetP.GetMembers().get(i).GetHP())) {
				minHp = targetP.GetMembers().get(i).GetHP();
				targetNo = i;
			//HPが同じ場合は運で比較
			}else if(minHp == (targetP.GetMembers().get(i).GetHP())) {
				if((targetP.GetMembers().get(i - 1).GetLUCK()) >= (targetP.GetMembers().get(i).GetLUCK())) {
					minHp = targetP.GetMembers().get(i - 1).GetHP();
					targetNo = (i - 1);
				}
			}
		}

		//HPが低いプレイヤーを攻撃ターゲットに指定
		this.targetPlayer = targetP.GetMembers().get(targetNo);
		//指定したターゲットに攻撃
		attacker.Attack(this.targetPlayer);
	}

}
