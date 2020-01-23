// プレイヤー：魔法使い
public class Wizard extends Player {

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
		//職業:魔法使い HP:50～150  MP:30～80 STR:1～50   DEF:1～50   LUCK:1～100 AGI:20～60
		// 魔法使いのパラメータを名前から生成する
		this.hp = GetNumber(2, 150);  //HP:50～150
		this.str = GetNumber(1, 50); //STR:1～50
		this.def = GetNumber(4, 50); //DEF:1～50
		this.mp = GetNumber(5, 80);  //MP:30～80
		this.luck = GetNumber(2, 100);//LUCK:1～100
		this.agi = GetNumber(0, 60);  //AGI:20～60

		if(this.hp < 50){
			this.hp = 100;
		}
		if(this.str < 1){
			this.str = 30;
		}
		if(this.def < 30){
			this.def = 30;
		}
		if(this.mp < 30){
			this.mp = 30;
		}
		if(this.luck < 1){
			this.luck = 1;
		}
		if(this.agi < 20){
			this.agi = 1;
		}
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender)
	{
		//名称:ファイア 職業:魔法使い 消費MP:10 効果:敵に10～30の防御無視ダメージ
		//名称:サンダー 職業:魔法使い 消費MP:20 効果:敵に10～30の防御無視ダメージ
		//   ・MPがあれば魔法をランダムで使用して攻撃し、MPが無い場合は通常攻撃を行う
		int damage = -1;

		String magicName = null;
		System.out.println(GetName() + "の攻撃！");
		// 与えるダメージを求める
		if(this.mp >= 20){
			int random = (int)((Math.random()*1));

			if(random == 0){
				//ファイア
				magicName = "ファイア";
				damage = magicDamage(defender,10,30);
				//mpを10消費
				this.mp = this.mp - 10;

			}else if(random == 1){
				//サンダー
				magicName = "サンダー";
				damage = magicDamage(defender,10,30);
				//mpを20消費
				this.mp = this.mp - 20;
			}
		}else if(this.mp >= 10){
			//ファイア
			magicName = "ファイア";
			damage = magicDamage(defender,10,30);
			//mpを10消費
			this.mp = this.mp - 10;

		}else{
			//通常
			magicName = "通常攻撃";
			damage = CalcDamage(defender);
		}

        // 求めたダメージを対象プレイヤーに与える
        System.out.println(defender.GetName() + "に" + magicName + " で " + damage + "のダメージ！");
        defender.Damage(damage);

        // 倒れた判定
        if (defender.GetHP() <= 0) {
            System.out.println(defender.GetName() + "は力尽きた...");
        }
	}


	// =======================
	// private メソッド
	// =======================


	// =======================
	// public メソッド
	// =======================
}
