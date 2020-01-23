
public class Beastmaster extends Player {
	public static Boolean poisonFlag = false;
	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	public Beastmaster(String name)
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
		//6.職業（好きなもの）を追加
		//   ・好きな職業を考えて作ってみる
		//   例）戦闘力もあり回復魔法も使える「勇者」


		//職業:獣使い     HP:60  MP:0 STR:40～80  DEF:10～70  LUCK:1～100 AGI:20～60
		// 獣使いのパラメータを名前から生成する
		this.hp = GetNumber(2, 60);  //HP:60
		this.str = GetNumber(1, 80); //STR:40～80
		this.def = GetNumber(4, 70); //DEF:10～70
		this.mp = 0;
		this.luck = GetNumber(2, 100);//LUCK:LUCK:1～100
		this.agi = GetNumber(0, 60);  //AGI:20～60

		if(this.hp < 80){
			this.hp = 80;
		}
		if(this.str < 40){
			this.str = 40;
		}
		if(this.def < 10){
			this.def = 10;
		}
		if(this.luck < 1){
			this.luck = 1;
		}
		if(this.agi < 20){
			this.agi = 20;
		}
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender)
	{

		//動物:トラ   効果:ひっかく 50のダメージ
		//動物:ヘビ   効果:かみつく 20のダメージ 確率で毒を付与
		//動物:ネズミ 効果:かみつく 20のダメージ
		//動物:ウサギ 効果：回復    30回復
		//自身の攻撃                10のダメージ

		int damage = -1;

		String magicName = null;
		System.out.println(GetName() + "の攻撃！");

		if(poisonFlag){
			defender.Damage(20);
		}

		// 与えるダメージを求める(0～5)
		int random = (int)((Math.random()*5));
		damage = CalcDamage(defender);
		if(random == 0){
			//トラ
			damage = beastDamage(defender,50,1);

			// 求めたダメージを対象プレイヤーに与える
			System.out.println("トラが召喚された：ひっかく攻撃");
            System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
            defender.Damage(damage);

		}else if(random == 1){
			//蛇
			damage = beastDamage(defender,20,2);
			//randomで毒を付与(1/10)

			// 求めたダメージを対象プレイヤーに与える
			System.out.println("ヘビが召喚された：かみつく攻撃");
            System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
            defender.Damage(damage);

		}else if(random == 2){
			//ネズミ
			damage = beastDamage(defender,20,3);

			// 求めたダメージを対象プレイヤーに与える
			System.out.println("ネズミが召喚された：かみつく攻撃");
            System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
            defender.Damage(damage);

		}else if(random == 3){
			//ウサギ
			damage = beastDamage(defender,30,4);

			// 求めたダメージを対象プレイヤーに与える
			System.out.println("ウサギが召喚された：ユーザーのHPを回復");
		}else {
			//自身の攻撃
			damage = CalcDamage(defender);
			System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			defender.Damage(damage);
		}

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
