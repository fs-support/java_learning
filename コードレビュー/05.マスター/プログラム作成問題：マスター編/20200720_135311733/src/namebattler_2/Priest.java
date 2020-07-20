package namebattler_2;

import java.util.ArrayList;

// プレイヤー：僧侶
public class Priest extends Player {

	// =======================
	// フィールド変数
	// =======================
	Player defender;
	int damage;

	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name)
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
		// 僧侶のパラメータを名前から生成する
		this.hp = GetNumber(0, 200);
		this.str = GetNumber(1, 70);
		this.def = GetNumber(2, 70);
		this.agi = GetNumber(3,60);
		this.mp = GetNumber(4,50);
		this.luc = GetNumber(5,99);

		// パラメータが僧侶の最低値未満の場合は最低値を設定
		if(this.hp < 80)
			this.hp = 80;
		if(this.str < 10)
			this.str = 10;
		if(this.def < 10)
			this.def = 10;
		if(this.agi < 20)
			this.agi = 20;
		if(this.mp < 20)
			this.mp = 20;
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
			// HPの低い味方の回復を優先して行動する
			TacHealMinHP tacHealminhp = new TacHealMinHP();
			tacHealminhp.Action(this,attackparty,defenderparty);
		}
	}


	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
}
