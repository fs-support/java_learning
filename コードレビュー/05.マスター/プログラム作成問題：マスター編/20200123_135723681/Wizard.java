package name_battlerM6;

import java.util.Random;

import name_battlerM6.Tactics.TacticsType;

public class Wizard extends Player{

	Random rnd = new Random();

	// =======================
	// フィールド変数
	// =======================

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
		this.hp = GetNumber(0, 100) + 50;
		this.mp = GetNumber(0, 50) + 30;;
		this.str = GetNumber(1, 49) + 1;
		this.def = GetNumber(2, 49) + 1;
		this.luck = GetNumber(3, 99) + 1;
		this.agi = GetNumber(4, 40) + 20;
		this.maxhp = this.hp;
	}
	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(){

		Player target[] = new Player[3];

		if (GetTactics().equals(Tactics.TacticsType.HPminAim)) {

			target = aim.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
		}
		else if (GetTactics().equals(Tactics.TacticsType.Support)) {

			target = spt.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
		}
		else if (GetTactics().equals(Tactics.TacticsType.AttackMain)) {

			target = atm.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
		}
		else if (GetTactics().equals(Tactics.TacticsType.MPsaving)) {

			target = mps.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
		}
		else{
			target = wlb.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
		}

		int damage = 0;
		int command = 2;//基本通常攻撃を行うようにしておく

		if ((GetTactics().equals(TacticsType.HPminAim)) || (GetTactics().equals(TacticsType.Support))) {

			//割合はランダム
			command = rnd.nextInt(3);
		}
		if (GetTactics().equals(TacticsType.AttackMain)) {

			//攻撃魔法を優先(Thunder か Fireかはランダム)
			command = rnd.nextInt(2);
		}
		if(GetTactics().equals(TacticsType.WellBalanced)) {

			//割合は攻撃魔法：通常攻撃 ＝ 2 : 1
			command = rnd.nextInt(5);
		}

		if(GetMP() >= 20 && (command == 0 || command == 3)) {

			damage = Thunder();
		}
		else if(GetMP() >= 10 && (command == 1|| command == 4)){

				damage = Fire();
		}
		else {
		//MPが残っていない時に通常攻撃をする
        // 与えるダメージを求める
        System.out.println(GetName() + "の攻撃！");
        damage = CalcDamage(target[0]);
		}

		if(target[0].GetGuard().equals(Guard.Guard)) {

        	System.out.println(target[0].GetName() + "はガードしてダメージを防いだ！");
        	damage = Math.max(damage - 15, 0);
        }
        // 求めたダメージを対象プレイヤーに与える
        System.out.println(target[0].GetName() + "に" + damage + "のダメージ！");
        target[0].Damage(damage);
	}
	// =======================
	// private メソッド
	// =======================


	// =======================
	// public メソッド
	// =======================
}
