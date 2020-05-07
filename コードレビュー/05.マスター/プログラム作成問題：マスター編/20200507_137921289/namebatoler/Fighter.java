package namebatoler;

//プレイヤー：戦士
public class Fighter extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Fighter(String name) {
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
		this.job = 1;
		
		// 戦士のパラメータを名前から生成する
		this.hp = GetNumber(0, 300) * 5;
		if (this.hp < 100) {
			this.hp = 100;
		}
		
		this.Max_hp = hp;
		
		this.mp = 0;

		this.str = GetNumber(0, 100);
		if (this.str < 30) {
			this.str = 30;
		}

		this.def = GetNumber(0, 100);
		if (this.def < 30) {
			this.def = 30;
		}

		this.luk = GetNumber(0, 100);
		if (this.luk < 1) {
			this.luk = 1;
		}

		this.agi = GetNumber(0, 50);
		if (this.agi < 1) {
			this.agi = 1;
		}

		this.condition = 0;
		
		
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player atackplayer,Operation choiseoperation,int trunNumber) {

		 Player choosedefender = Choosedefender(atackplayer,choiseoperation);
		
		//状態異常の確認
		if (condition == 1) {
			Condition(this);
		} else if(condition == 2){
			PoizunCondition(this);
			
		} else {
			// 与えるダメージを求める
			System.out.println(GetName() + "の攻撃！");
			int damage = CalcDamage(choosedefender);

			// 求めたダメージを対象プレイヤーに与える
			choosedefender.Damage(damage,choosedefender,atackplayer);
		}
		// 倒れた判定
		if (choosedefender.GetHP() <= 0) {
			System.out.println(choosedefender.GetName() + "は力尽きた...");
		}
		
		RemoveParty(choosedefender);
	}


}