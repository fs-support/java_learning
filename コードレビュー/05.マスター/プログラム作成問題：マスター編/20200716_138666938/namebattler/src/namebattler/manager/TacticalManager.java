package namebattler.manager;

import java.util.ArrayList;
import java.util.Random;

import namebattler.player.Player;
import namebattler.tactical.IntensAt;
import namebattler.tactical.LHpAt;
import namebattler.tactical.MagiAt;
import namebattler.tactical.MedicTarget;
import namebattler.tactical.PMedic;
import namebattler.tactical.Tactical;

public class TacticalManager {
	// =======================
	// フィールド変数
	// =======================
	//ランダムクラス
	protected Random ran = new Random();

	//作戦クラス
	protected Tactical magiAt = new MagiAt();
	protected Tactical pMedic = new PMedic();
	protected Tactical lHpAt = new LHpAt();
	protected Tactical medicTarget = new MedicTarget();
	protected Tactical intensAt = new IntensAt();

	//アタックパーティ
	protected Party attackP;
	//ターゲットパーティー
	protected Party targetP;
	//アタックプレイヤー
	protected Player attackPlayer;
	//実行した作戦
	protected Tactical activeTa;

	// =======================
	// コンストラクタ
	// =======================
	public TacticalManager(ArrayList<Party> battleParty, Player attacker) {
		//attackPにattackerのパーティーを代入し、targetPにもう一方のパーティーを代入
		for(int i = 0; i < 2; i++) {
			if( i == attacker.GetPartyNo() ) {
				this.attackP = battleParty.get(i);
			}else {
				this.targetP = battleParty.get(i);
			}
		}

		//attackPlayerにattackerを代入
		this.attackPlayer = attacker;
	}

	// =======================
	// public メソッド
	// =======================
	//作戦番号に合わせた作戦を実行
	public void tacticalPlay() {
		//特定のプレイヤーを集中攻撃する作戦を実行
		if(this.attackP.GetTacticalNo() == 1) {
			this.intensAt.tacticalAction(this.attackP, this.targetP, this.attackPlayer);
			this.activeTa = this.intensAt;

		//HPが低いプレイヤーを狙う作戦を実行
		}else if(this.attackP.GetTacticalNo() == 2) {
			this.lHpAt.tacticalAction(this.attackP, this.targetP, this.attackPlayer);
			this.activeTa = this.lHpAt;

		//魔法を優先して使用する作戦を実行
		}else if(this.attackP.GetTacticalNo() == 3) {
			this.magiAt.tacticalAction(this.attackP, this.targetP, this.attackPlayer);
			this.activeTa = this.magiAt;

		//相手メンバーの回復職を狙う作戦を実行
		}else if(this.attackP.GetTacticalNo() == 4) {
			this.medicTarget.tacticalAction(this.attackP, this.targetP, this.attackPlayer);
			this.activeTa = this.medicTarget;

		//味方プレイヤーの回復を優先する作戦を実行
		}else {
			this.pMedic.tacticalAction(this.attackP, this.targetP, this.attackPlayer);
			this.activeTa = this.pMedic;
		}
	}

}
