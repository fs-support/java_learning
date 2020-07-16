package namebattler.tactical;

import namebattler.manager.Party;
import namebattler.player.Player;

//魔法を優先して使用する作戦
public class MagiAt extends Tactical {
	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public MagiAt() {
		super();
	}

	// =======================
	// public メソッド
	// =======================
	////魔法を優先して攻撃
	public void tacticalAction(Party attackP, Party targetP, Player attacker) {
		//attackerが魔法使いか勇者の場合、魔法使用可にする
		if(attacker.GetJobName().equals("魔法使い") || attacker.GetJobName().equals("勇者")) {
			attacker.SetMagiAt(true);
		}

		//ターゲットをランダムで指定
		targetPlayer = targetP.GetMembers().get( ran.nextInt( targetP.GetMembers().size() ) );
		//指定したターゲットに攻撃
		attacker.Attack(targetPlayer);
	}

}
