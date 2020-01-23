package name_battlerM6;

import java.util.Objects;

import name_battlerM6.Tactics.TacticsType;

public class Priest extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name) {

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
		// 僧侶のパラメータを名前から生成する
		this.hp = GetNumber(0, 120) + 80;
		this.mp = GetNumber(0, 30) + 20;
		this.str = GetNumber(1, 60) + 10;
		this.def = GetNumber(2, 60) + 10;
		this.luck = GetNumber(3, 99) + 1;
		this.agi = GetNumber(4, 40) + 20;
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

		//回復対象が空でない場合回復する
		if (GetMP() >= 20 && (!Objects.equals(target[1],tac.player0))) {

			Heal(target[1]);
		} else {

			int command = 2;//基本通常攻撃を行うようにしておく
			//相手の状態がノーマルで、MPが残っている場合はランダムで通常攻撃か状態異常魔法を使用
			//作戦によって攻撃と補助の優先順位(確率)を変える
			//※Priestの場合、AttackMainでは通常攻撃のみになるため、MPsaving同様分岐は記載しない。
			if (GetTactics().equals(TacticsType.HPminAim)) {
				//割合は攻撃：補助 ＝ 2 : 1
				command = rnd.nextInt(6);
			}
			if (GetTactics().equals(TacticsType.Support)) {
				//補助魔法優先
				command = rnd.nextInt(2);
			}
			if(GetTactics().equals(TacticsType.WellBalanced)) {

				//割合はランダム
				command = rnd.nextInt(3);
			}

			if (GetMP() >= 10 && (!Objects.equals(target[1],tac.player0)) && command == 0) {

				Paralysis(target[2]);
			} else if (GetMP() >= 10 && (!Objects.equals(target[1],tac.player0)) && command == 1) {

				Poison(target[2]);
			} else {

				System.out.println(GetName() + "の攻撃！");
				damage = CalcDamage(target[0]);

				if(target[0].GetGuard().equals(Guard.Guard)) {

		        	System.out.println(target[0].GetName() + "はガードしてダメージを防いだ！");
		        	damage = Math.max(damage - 15, 0);
		        }

				System.out.println(target[0].GetName() + "に" + damage + "のダメージ！");
				target[0].Damage(damage);

			}
		}
	}
}
