package namebattler;

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
	//攻撃のターゲットを決める処理
	public Player target(Player attack,Party A, Party B) {

		//ターゲットのpartyを選択
		if(attack.party == 1) {
			attackP = A;
			targetP = B;

		}else {
			attackP = B;
			targetP = A;
		}

		//一斉攻撃可にする
		attackP.intensCh = true;

		//一斉攻撃のターゲットを決める
		attackP.IntensTarget = targetP.GetMembers().get(this.ran.nextInt(targetP.GetMembers().size()));
		targetM = attackP.IntensTarget;

		System.out.println("作戦：一斉攻撃、(対象：" + this.attackP.IntensTarget.GetName() + ")");

		return targetM;
	}

}
