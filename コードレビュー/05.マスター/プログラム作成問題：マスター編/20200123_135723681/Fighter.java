package name_battlerM6;

// プレイヤー：戦士
public class Fighter extends Player {

	// =======================
	// フィールド変数
	// =======================


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
		this.hp = GetNumber(0, 200) + 100;
		this.mp = 0;
		this.str = GetNumber(1, 30) + 70;
		this.def = GetNumber(2, 30) + 70;
		this.luck = GetNumber(3, 99) + 1;
		this.agi = GetNumber(4, 49) + 1;
		this.maxhp = this.hp;
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack()
	{
		Player targetlist[] = new Player[3];


			if (GetTactics().equals(Tactics.TacticsType.HPminAim)) {

				targetlist = aim.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
			}
			else if (GetTactics().equals(Tactics.TacticsType.Support)) {

				targetlist = spt.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
			}
			else if (GetTactics().equals(Tactics.TacticsType.AttackMain)) {

				targetlist = atm.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
			}
			else if (GetTactics().equals(Tactics.TacticsType.MPsaving)) {

				targetlist = mps.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
			}
			else{
				targetlist = wlb.TargetSelect((party.GetEnemys()).GetMembers(), party.GetMembers());
			}

		//※Fighterの場合、作戦にかかわらず行動は通常攻撃のみになるため、特に分岐は記載しない。
        // 与えるダメージを求める
        System.out.println(GetName() + "の攻撃！");
        int damage = CalcDamage(targetlist[0]);

        if(targetlist[0].GetGuard().equals(Guard.Guard)) {

        	System.out.println(targetlist[0].GetName() + "はガードしてダメージを防いだ！");
        	damage = Math.max(damage - 15, 0);
        }

        // 求めたダメージを対象プレイヤーに与える
        System.out.println(targetlist[0].GetName() + "に" + damage + "のダメージ！");
        targetlist[0].Damage(damage);
	}


	// =======================
	// private メソッド
	// =======================


	// =======================
	// public メソッド
	// =======================
}
