package namebatoler;


public class Brave extends Player {


	// =======================
	// コンストラクタ
	// =======================
	public Brave(String name) {
		super(name);
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter() {
		this.job = 4;

		this.hp = GetNumber(1, 300) * 5;
		if (this.hp < 100) {
			this.hp = 100;
		}
		
		this.Max_hp = hp;
		
		this.mp = GetNumber(1, 100);
		if (this.mp < 30) {
			this.mp = 30;
		}

		this.str = GetNumber(1, 150);
		if (this.str < 50) {
			this.str = 50;
		}

		this.def = GetNumber(1, 100);
		if (this.def < 40) {
			this.def = 40;
		}

		this.luk = GetNumber(1, 100);
		if (this.luk < 10) {
			this.luk = 10;
		}

		this.agi = GetNumber(1, 100);
		if (this.agi < 30) {
			this.agi = 30;
		}

		this.condition = 0;

	}


	public void Attack(Player atackplayer,Operation choiseoperation,int trunNumber) {

		// 魔法種類の選択
		Mazic defusemazic = choiseoperation.Choosemagic(atackplayer,trunNumber);

		// 攻撃を受けるプレイヤーの選択
		Player choosedefender = Choosedefender(atackplayer,choiseoperation);

		// 状態異常の確認
		if (condition == 1) {
			Condition(this);
		} else if (condition == 2) {
			PoizunCondition(this);

		} else {

			// 攻撃魔法を使う
			if (this.mp > defusemazic.GetMagicMP()
					&& choiseoperation.OrNotmagic == 1) {
				System.out.println(GetName() + "は" + defusemazic.GetMagicName()
						+ "の魔法を使った！\n");
				// 回復と魔法攻撃で分ける
				if (defusemazic.muzicNo == 3) {
					defusemazic.MagicAttack(choosedefender, atackplayer);
				} else if (defusemazic.muzicNo != 3
						&& choosedefender.condition == 0) {
					defusemazic.MagicAttack(choosedefender, atackplayer);
				}

			} else {

				// 与えるダメージを求める
				System.out.println(GetName() + "の通常攻撃！");
				int damage = CalcDamage(choosedefender);

				// 求めたダメージを対象プレイヤーに与える
				choosedefender.Damage(damage,choosedefender,atackplayer);
			}

		}

		// 倒れた判定
		if (choosedefender.GetHP() <= 0) {
			System.out.println(choosedefender.GetName() + "は力尽きた...");
		}
		
		RemoveParty(choosedefender);
	}

	

}