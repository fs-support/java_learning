package namebattler.tactical;

import namebattler.manager.Party;
import namebattler.player.Player;

//相手メンバーの特定のプレイヤーを集中攻撃する作戦
public class IntensAt extends Tactical {
	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public IntensAt() {
		super();
	}

	// =======================
	// public メソッド
	// =======================
	//相手メンバーの特定のプレイヤーを集中攻撃
	public void tacticalAction(Party attackP, Party targetP, Player attacker) {

		//一斉攻撃のターゲットが決まっていない場合
		if(attackP.GetIntensCh() == false) {
			//一斉攻撃の判定をtrueに変更
			attackP.SetIntensCh(true);

			//一斉攻撃のターゲットをランダムで決める
			attackP.SetIntensTarget( targetP.GetMembers().get( this.ran.nextInt( targetP.GetMembers().size() ) ) );
			this.targetPlayer = attackP.GetIntensTarget();
		}

		//一斉攻撃のターゲットが決まっている場合、GetIntensTargetをtargetPlayerに代入
		this.targetPlayer = attackP.GetIntensTarget();

		//指定したターゲットに攻撃
		attacker.Attack(this.targetPlayer);
	}

}
