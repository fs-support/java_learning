package namebattler_2;

import java.util.ArrayList;

// プレイヤー：魔法使い
public class Wizard extends Player {

	// =======================
	// フィールド変数
	// =======================
	Player defender;
	int damage;

	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name)
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
		// 魔法使いのパラメータを名前から生成する
		this.hp = GetNumber(0, 50) * 3;
		this.str = GetNumber(1, 50);
		this.def = GetNumber(2, 50);
		this.agi = GetNumber(3,60);
		this.mp = GetNumber(4,80);
		this.luc = GetNumber(5,99);

		// パラメータが魔法使いの最低値未満の場合は最低値を設定
			if(this.hp < 50)
				this.hp = 50;
			if(this.str < 1)
				this.str = 1;
			if(this.def < 1)
				this.def = 1;
			if(this.agi < 20)
				this.agi = 20;
			if(this.mp < 30)
				this.mp = 30;
			if(this.luc < 1)
				this.luc = 1;
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(ArrayList<Player> attackparty,ArrayList<Player> defenderparty)
	{


		// 麻痺判定
		if(this.GetParalys() == true && this.ParaJudge() == true) {
			System.out.println(GetName() + " は麻痺していて動けない！");
		}
		else {
			// 魔法を優先して使用する
			TacPriorityWizardMagic tacprioritywizardmagic = new TacPriorityWizardMagic();
			tacprioritywizardmagic.Action(this,attackparty,defenderparty);
        }
	}


	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
}
