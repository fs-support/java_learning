package namebattler.tactical;

import java.util.ArrayList;

import namebattler.manager.Party;
import namebattler.player.Player;

//味方プレイヤーの回復を優先する作戦
public class PMedic extends Tactical {
	// =======================
	// フィールド変数
	// =======================
	//味方プレイヤーをHPが低い順に格納
	protected ArrayList<Integer> recoveryP = new ArrayList<Integer>();

	// =======================
	// コンストラクタ
	// =======================
	public PMedic() {
		super();
	}

	// =======================
	// public メソッド
	// =======================
	//味方プレイヤーの回復を優先
	public void tacticalAction(Party attackP, Party targetP, Player recovery) {
		//recoveryが僧侶か勇者の場合
		if(recovery.GetJobName().equals("僧侶") || recovery.GetJobName().equals("勇者")) {
			//回復使用可にする
			recovery.SetPMedic(true);

			//recoveryPをクリア
			this.recoveryP.clear();

			//HP比率の低いメンバーを抽出
			//現在のHP比率を格納
			for(int i = 0; i < attackP.GetMembers().size(); i++) {

				//現在HP比率を格納
				int hpPar = (int)((attackP.GetMembers().get(i).GetHP()
									/ attackP.GetMembers().get(i).GetMaxHp()) * 100);
				this.recoveryP.add(hpPar);
			}

			//最低HP比率
			int minHpPar = this.recoveryP.get(0);
			//最低HP比率のメンバーの要素
			int targetNo = 0;
			//HP比率の比較
			for(int i = 1; i <  attackP.GetMembers().size(); i++) {
				if(minHpPar > (this.recoveryP.get(i))) {
					minHpPar = this.recoveryP.get(i);
					targetNo = i;
				//HP比率が同じ場合は運で比較
				}else if(minHpPar == (attackP.GetMembers().get(i).GetHP())) {
					if((attackP.GetMembers().get(i - 1).GetLUCK()) >= (attackP.GetMembers().get(i).GetLUCK())) {
						minHpPar = this.recoveryP.get(i - 1);
						targetNo = (i - 1);
					}
				}
			}

			//最低HP比率が8割以下の場合
			if(minHpPar < 80) {
				if(recovery.GetMP() >= 20) {
				//HP比率が一番低いプレイヤーを回復ターゲットに指定
				this.targetPlayer = attackP.GetMembers().get(targetNo);
				//指定したターゲットを回復
				recovery.Attack(this.targetPlayer);
				}else {
					System.out.println("回復しようとしたがMPが足りない！");
					//ターゲットをランダムで指定
					this.targetPlayer = targetP.GetMembers().get( ran.nextInt( targetP.GetMembers().size() ) );
					//指定したターゲットに攻撃
					recovery.Attack(this.targetPlayer);
				}

			//最低HP比率が8割以上の場合
			}else {
				System.out.println(attackP.GetPartyName() + "に回復の必要はなさそうだ！");

				//回復使不可にする
				recovery.SetPMedic(false);
				//ターゲットをランダムで指定
				this.targetPlayer = targetP.GetMembers().get( ran.nextInt( targetP.GetMembers().size() ) );
				//指定したターゲットに攻撃
				recovery.Attack(this.targetPlayer);
			}

		//recoveryが戦士か魔法使いの場合
		}else {
			//ターゲットをランダムで指定
			this.targetPlayer = targetP.GetMembers().get( ran.nextInt( targetP.GetMembers().size() ) );
			//指定したターゲットに攻撃
			recovery.Attack(this.targetPlayer);
		}
	}

}
