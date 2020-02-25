package namebattler_2;

import java.util.ArrayList;
import java.util.Random;

// プレイヤー：戦士
public class Fighter extends Player {

	// =======================
	// フィールド変数
	// =======================
	Random rnd = new Random();
	Player defender;

	// =======================
	// コンストラクタ
	// =======================
	public Fighter(String name)
	{
		super(name);
	}


	// =======================
	// Getter / Setter
	// =======================


	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter()
	{
		// 戦士のパラメータを名前から生成する
		this.hp = GetNumber(0, 100) * 3;
		this.str = GetNumber(1, 100);
		this.def = GetNumber(2, 100);
		this.agi = GetNumber(3,50);
		this.mp = 0;

		// パラメータが戦士の最低値未満の場合は最低値を設定
		if(this.hp < 100)
			this.hp = 100;
		if(this.str < 30)
			this.str = 30;
		if(this.def < 30)
			this.def = 30;
		if(this.agi < 1)
			this.agi = 1;
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(ArrayList<Player> attackparty,ArrayList<Player> defenderparty)
	{
		// 攻撃対象の設定
		//while(true) {
		//	defender = defparty.GetMembers().get(rnd.nextInt(defparty.GetMembers().size()));
		//	if(defender.GetHP() > 0) {
		//		break;
		//	}
		//}

		// 麻痺判定
		if(this.GetParalys() == true && this.ParaJudge() == true) {
			System.out.println(GetName() + " は麻痺していて動けない！");
		}
		else {
			// HPの低い相手を優先して攻撃対象にする
			TacAttackMinHP tacattackminhp = new TacAttackMinHP();
			tacattackminhp.Action(this,attackparty,defenderparty);
		}
	}


	// =======================
	// private メソッド
	// =======================


	// =======================
	// public メソッド
	// =======================
}
