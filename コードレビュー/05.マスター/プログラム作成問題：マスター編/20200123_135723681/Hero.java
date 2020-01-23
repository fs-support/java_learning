package name_battlerM6;

import java.util.Objects;

import name_battlerM6.Tactics.TacticsType;

public class Hero extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Hero(String name) {

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
	protected void MakeCharacter() {
		// 勇者のパラメータを名前から生成する
		this.hp = GetNumber(0, 150) + 100;
		this.mp = GetNumber(0, 30) + 20;
		this.str = GetNumber(1, 65) + 20;
		this.def = GetNumber(2, 65) + 20;
		this.luck = GetNumber(3, 99) + 1;
		this.agi = GetNumber(4, 30) + 10;
		this.maxhp = this.hp;
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack() {

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
		int command = 0;//基本通常攻撃を行うようにしておく

		//回復対象が空でない場合回復する
		if (GetMP() >= 20 && (!Objects.equals(target[1],tac.player0))) {

			Heal(target[1]);
		} else {

			//MPが残っている場合は攻撃魔法か通常攻撃を使用する
			if ((GetTactics().equals(TacticsType.HPminAim)) || (GetTactics().equals(TacticsType.Support))) {
				//割合はランダム
				command = rnd.nextInt(2);
			}
			if (GetTactics().equals(TacticsType.AttackMain)) {
				//攻撃魔法を優先
				command = 1;
			}
			if(GetTactics().equals(TacticsType.WellBalanced)) {

				//割合は攻撃魔法：通常攻撃 ＝ 2 : 1
				command = rnd.nextInt(3);
			}

			if (GetMP() >= 20 && command != 0) {

				damage = Thunder();
			} else {
				//MPが0の時に通常攻撃をする
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
	}
}
