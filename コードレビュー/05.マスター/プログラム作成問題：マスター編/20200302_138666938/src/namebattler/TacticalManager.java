package namebattler;

import java.util.Random;

public class TacticalManager {
	// =======================
	// フィールド変数
	// =======================
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
	//ターゲットメンバー
	protected Player targetM;

	//ランダムクラス
	protected Random ran = new Random();
	//作戦の仕訳
	protected int tCk = 0;

	// =======================
	// コンストラクタ
	// =======================
	public TacticalManager() {

	}

	// =======================
	// public メソッド
	// =======================
	//攻撃のターゲットを決める処理
	public Player targetT(Player attack,Party A, Party B) {

		System.out.print(attack.GetName() + "　のターン");
		System.out.printf("((%s)：HP=%3d : MP=%3d)\n",  attack.GetJobName(),attack.GetHP(), attack.GetMP());

		if(attack.party == 1) {
			attackP = A;
			targetP = B;

		}else {
			attackP = B;
			targetP = A;
		}

		if(attack.jobName == "戦士") {
			this.targetM = this.fighterTM(attack, A, B);
		}else if(attack.jobName == "魔法使い") {
			this.targetM = this.wizardTM(attack, A, B);
		}else if(attack.jobName == "僧侶") {
			this.targetM = this.priestTM(attack, A, B);
		}else if(attack.jobName == "勇者") {
			this.targetM = this.BraveTM(attack, A, B);
		}

		return this.targetM;
	}

	public Player fighterTM(Player attack,Party A, Party B) {

		if(this.attackP.intensCh == true && attackP.IntensTarget.GetHP() > 0) {
			System.out.println("作戦：一斉攻撃を実施中、(対象：" + attackP.IntensTarget.GetName() + ")");
			targetM  = attackP.IntensTarget;
		}else {

			tCk = ran.nextInt(100);
			//一斉攻撃：10％
			if(tCk < 10) {
				this.targetM = this.intensAt.target(attack, A,  B);
			//HPの低いメンバーを狙う：45％
			}else if(tCk < 55) {
				this.targetM = this.lHpAt.target(attack, A,  B);
			//回復職を狙う：45％
			}else {
				this.targetM = this.medicTarget.target(attack, A,  B);
			}

		}

		return this.targetM;
	}

	public Player wizardTM(Player attack,Party A, Party B) {

		if(this.attackP.intensCh == true && attackP.IntensTarget.GetHP() > 0) {
			System.out.println("作戦：一斉攻撃を実施中、(対象：" + attackP.IntensTarget.GetName() + ")");

			//魔法を優先するか選択
			if( (ran.nextInt(100)) < 60 && attack.GetMP() > 10) {
				this.magiAt.action(attack);
			}

			targetM  = attackP.IntensTarget;

		}else {

			//魔法攻撃か通常攻撃か選択
			if( (ran.nextInt(100)) < 60 && attack.GetMP() > 10) {
				this.magiAt.action(attack);
			}

			tCk = ran.nextInt(100);
			//一斉攻撃：10％
			if(tCk < 10) {
				this.targetM = this.intensAt.target(attack, A,  B);
			//HPの低いメンバーを狙う：45％
			}else if(tCk < 55) {
				this.targetM = this.lHpAt.target(attack, A,  B);
			//回復職を狙う：45％
			}else {
				this.targetM = this.medicTarget.target(attack, A,  B);
			}

		}

		return this.targetM;
	}


	public Player priestTM(Player attack,Party A, Party B) {

		if(this.attackP.intensCh == true && attackP.IntensTarget.GetHP() > 0) {

			System.out.println("作戦：一斉攻撃を実施中、(対象：" + attackP.IntensTarget.GetName() + ")");

			//回復か通常攻撃か選択
			tCk = ran.nextInt(100);
			if(tCk < 30 && attack.GetMP() > 20) {
				this.targetM = this.pMedic.target(attack, A,  B);
			}else {
				this.targetM = attackP.IntensTarget;
			}

		}else {

			tCk = ran.nextInt(100);
			//仲間を回復する：30％
			if(tCk < 30 && attack.GetMP() > 20) {
				this.targetM = this.pMedic.target(attack, A,  B);
			//一斉攻撃：10％
			}else if(tCk < 40) {
				this.targetM = this.intensAt.target(attack, A,  B);
			//HPの低いメンバーを狙う：30％
			}else if(tCk < 70) {
				this.targetM = this.lHpAt.target(attack, A,  B);
			//回復職を狙う：30％
			}else {
				this.targetM = this.medicTarget.target(attack, A,  B);
			}

		}


		return this.targetM;
	}

	public Player BraveTM(Player attack,Party A, Party B) {

		if(this.attackP.intensCh == true && attackP.IntensTarget.GetHP() > 0) {

			System.out.println("作戦：一斉攻撃を実施中、(対象：" + attackP.IntensTarget.GetName() + ")");

			//魔法攻撃か回復か通常攻撃か選択
			tCk = ran.nextInt(100);
			if(tCk < 40 && attack.GetMP() > 10) {
				this.magiAt.action(attack);
				this.targetM = attackP.IntensTarget;
			}else if(tCk< 70 && attack.GetMP() > 20){
				this.targetM = this.pMedic.target(attack, A,  B);
			}else {
				this.targetM = attackP.IntensTarget;
			}

		}else {

			tCk = ran.nextInt(100);
			//仲間を回復する：30％
			if(tCk < 30 && attack.GetMP() > 20) {
				this.targetM = this.pMedic.target(attack, A,  B);
			//一斉攻撃：10％
			}else if(tCk < 40) {
				this.targetM = this.intensAt.target(attack, A,  B);
			//HPの低いメンバーを狙う：30％
			}else if(tCk < 70) {
				this.targetM = this.lHpAt.target(attack, A,  B);
			//回復職を狙う：30％
			}else {
				this.targetM = this.medicTarget.target(attack, A,  B);
			}

			//魔法攻撃か通常攻撃か選択
			if( (ran.nextInt(100)) < 60 && attack.GetMP() > 10 && attack.pMedic == false) {
				this.magiAt.action(attack);
			}

		}


		return this.targetM;
	}

}
