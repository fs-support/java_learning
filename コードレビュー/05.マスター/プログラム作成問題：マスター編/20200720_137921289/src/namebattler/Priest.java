package namebattler;

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
		
		this.finalatack = 1;
	}

	public void Attack(Player atackplayer, Operation choiseoperation,
			int trunNumber) {

		// 魔法種類の選択
		Magic defusemazic = choiseoperation
				.Choosemagic(atackplayer, trunNumber);

		// 攻撃を受けるプレイヤーの選択
		Player choosedefender = Choosedefender(atackplayer, choiseoperation);
		
		//特技を使うかの選択（1：使用、0：使用しない）
		int ornotusefinalatack = OrnotuseFinalatack(atackplayer);

		// 状態異常の確認
		if (condition == 1) {
			Condition(this);
		} else {
			if (condition == 2) {
				PoizunCondition(this);
			}

			if(ornotusefinalatack == 1 && atackplayer.finalatack == 1){
				System.out.println(atackplayer.name + "は特技を使った！（味方パーティ全体のHP+100）");
				AllPartyHeel(atackplayer);
			}else{
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
				choosedefender.Damage(damage, choosedefender, atackplayer);

			}
			}

		}

		// 倒れた判定
		if (choosedefender.GetHP() <= 0) {
			
			RemoveParty(choosedefender);
		}

		

		
	
	}
	
	public int OrnotuseFinalatack(Player atackplayer){
		//パーティ全体のHP最大値
		int AllpartyMAX_HP = 0;
		//現状のパーティ全体のHP
		int AllpartyHP = 0;
		//パーティ全体のHP最大値の4分の1
		int QuarterpartyHP = 0;
		
		//パーティ全体のHP最大値を求める
		if(atackplayer.partynum == 1){
			for(int i = 0;i < GameManager.makeparty.GetMembers().size();i++){
				AllpartyMAX_HP += GameManager.makeparty.GetMembers().get(i).Max_hp;
			}
		}else{
			for(int i = 0;i < GameManager.makeparty2.GetMembers().size();i++){
				AllpartyMAX_HP += GameManager.makeparty2.GetMembers().get(i).Max_hp;
			}
		}
		
		//現状のパーティ全体のHPを求める
		if(atackplayer.partynum == 1){
			for(int i = 0;i < GameManager.makeparty.GetMembers().size();i++){
				AllpartyHP += GameManager.makeparty.GetMembers().get(i).hp;
			}
		}else{
			for(int i = 0;i < GameManager.makeparty2.GetMembers().size();i++){
				AllpartyHP += GameManager.makeparty2.GetMembers().get(i).hp;
			}
		}
		
		QuarterpartyHP = AllpartyMAX_HP / 4;
		
		if(AllpartyHP < QuarterpartyHP){
			return 1;
			
		}else{
			return 0;
		}
		
	}

}