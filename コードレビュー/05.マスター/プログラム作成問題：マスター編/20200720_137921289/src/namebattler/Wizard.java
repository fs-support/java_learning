package namebattler;

public class Wizard extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name) {
		super(name);

	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter() {

		this.job = 2;
		// 魔法使いのパラメータを名前から生成する
		this.hp = GetNumber(1, 150) * 5;
		if (this.hp < 50) {
			this.hp = 50;
		}

		this.Max_hp = hp;

		this.mp = GetNumber(1, 80);
		if (this.mp < 30) {
			this.mp = 30;
		}

		this.str = GetNumber(1, 50);
		if (this.str < 1) {
			this.str = 1;
		}

		this.def = GetNumber(1, 50);
		if (this.def < 1) {
			this.def = 1;
		}

		this.luk = GetNumber(1, 100);
		if (this.luk < 1) {
			this.luk = 1;
		}

		this.agi = GetNumber(1, 60);
		if (this.agi < 20) {
			this.agi = 20;
		}

		this.condition = 0;
		
		this.finalatack = 1;

	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * 
	 * @param defender
	 *            : 対象プレイヤー
	 */
	@Override
	public void Attack(Player atackplayer, Operation choiseoperation,
			int trunNumber) {
		// 魔法種類の選択
		Magic defusemazic = choiseoperation
				.Choosemagic(atackplayer, trunNumber);

		// 攻撃を受けるプレイヤーの選択
		Player choosedefender = Choosedefender(atackplayer, choiseoperation);
		// 状態異常の確認
		if (condition == 1) {
			Condition(this);
		} else{ 
			if (condition == 2) {
			PoizunCondition(this);
		} 

			if (mp > defusemazic.GetMagicMP()
					&& choiseoperation.OrNotmagic == 1) {
				// 魔法を使う

				System.out.println(GetName() + "は" + defusemazic.GetMagicName()
						+ "の魔法を使った！\n");
				defusemazic.MagicAttack(choosedefender, atackplayer);

			}//HPが4分の1になった時に特技を発動
			 //相手パーティ全体に100のダメージ
			else if(atackplayer.hp < atackplayer.Max_hp / 4 && finalatack <= 1){
				System.out.println(atackplayer.name + "は魔法使いの特技を使った！");
				System.out.println("相手パーティ全体に100のダメージ");
				AllfinalDamege(atackplayer);
				
			}else {

				// 与えるダメージを求める
				System.out.println(GetName() + "の通常攻撃！");
				int damage = CalcDamage(choosedefender);

				// 求めたダメージを対象プレイヤーに与える
				choosedefender.Damage(damage, choosedefender, atackplayer);
			}

		}

		// 倒れた判定
		if (choosedefender.GetHP() <= 0) {
			RemoveParty(choosedefender);
			
		}

		

		
	}

}