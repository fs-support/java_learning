package namebatoler;

public class Priest extends Player {

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
		this.job = 3;

		// 僧侶のパラメータを名前から生成する
		this.hp = GetNumber(2, 200) * 5;
		if (this.hp < 80) {
			this.hp = 80;
		}

		this.Max_hp = hp;
		
		this.mp = GetNumber(2, 50);
		if (this.mp < 20) {
			this.mp = 20;
		}

		this.str = GetNumber(2, 70);
		if (this.str < 10) {
			this.str = 10;
		}

		this.def = GetNumber(2, 70);
		if (this.def < 10) {
			this.def = 10;
		}

		this.luk = GetNumber(2, 100);
		if (this.luk < 1) {
			this.luk = 1;
		}

		this.agi = GetNumber(2, 60);
		if (this.agi < 20) {
			this.agi = 20;
		}

		this.condition = 0;
	}



	public void Attack(Player atackplayer, Operation choiseoperation,int trunNumber) {
		

		// 魔法種類の選択
		Mazic defusemazic = choiseoperation.Choosemagic(atackplayer,trunNumber);

		// 攻撃を受けるプレイヤーの選択
		Player choosedefender = Choosedefender(atackplayer, choiseoperation);

		// 状態異常の確認
		if (condition == 1) {
			Condition(this);
		} else {
			if (condition == 2) {
				PoizunCondition(this);
			}

			if (mp > defusemazic.GetMagicMP()
					&& choiseoperation.OrNotmagic == 1) {
				System.out.println(GetName() + "は" + defusemazic.GetMagicName()
						+ "の魔法を使った！\n");
				// 回復とデバフで分ける
				if (defusemazic.muzicNo == 3) {
					defusemazic.MagicAttack(choosedefender, atackplayer);
				} else if (defusemazic.muzicNo != 3
						&& choosedefender.condition == 0) {
					defusemazic.MagicAttack(choosedefender, atackplayer);
				}

			} else {
				// 与えるダメージを求める
				System.out.println(GetName() + "の攻撃！");
				int damage = CalcDamage(choosedefender);

				// 求めたダメージを対象プレイヤーに与える
				choosedefender.Damage(damage,choosedefender,atackplayer);

			}

		}

		// 倒れた判定
		if (choosedefender.GetHP() <= 0) {
			System.out.println(choosedefender.GetName() + "は力尽きた...\n");
		}

		RemoveParty(choosedefender);
	}

}