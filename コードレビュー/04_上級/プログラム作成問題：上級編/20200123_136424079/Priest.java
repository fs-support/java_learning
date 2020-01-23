
public class Priest extends Player  {
	private static Boolean poisonFlag = false;
	public static int parizeFlag = 0;

	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name)
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
		//5.職業（僧侶）を追加
		//   ・回復魔法を使える僧侶(Priest)クラスを作成する
		//   ・僧侶のパラメータ生成を「職業別パラメータ表」に合わせて実装する
		//   ・僧侶が使用できる魔法は「魔法一覧」を参照して実装する
		//   ※この段階では「ヒール」のみの実装でよい
		//   ・HPが減っていれば「ヒール」を使用し、減ってなければ通常攻撃を行う


		//職業:僧侶     HP:80～200  MP:20～50 STR:10～70  DEF:10～70  LUCK:1～100 AGI:20～60
		// 僧侶のパラメータを名前から生成する
		this.hp = GetNumber(2, 200);  //HP:80～200
		this.str = GetNumber(1, 70); //STR:10～70
		this.def = GetNumber(4, 70); //DEF:10～70
		this.mp = GetNumber(5, 50);  //MP:20～50
		this.luck = GetNumber(2, 100);//LUCK:LUCK:1～100
		this.agi = GetNumber(0, 60);  //AGI:20～60

		if(this.hp < 80){
			this.hp = 80;
		}
		if(this.str < 70){
			this.str = 70;
		}
		if(this.def < 70){
			this.def = 70;
		}
		if(this.mp < 20){
			this.mp = 20;
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
		//名称:ヒール   職業:僧侶     消費MP:20 効果:HPを50回復
		//名称:パライズ 職業:僧侶     消費MP:10 効果:麻痺の効果を与える 麻痺：20%の確率で麻痺で行動不能
		//名称:ポイズン 職業:僧侶     消費MP:10 効果:毒状態にする 毒：毎ターン20のダメージを受ける
		//   ・MPがあれば魔法をランダムで使用して攻撃し、MPが無い場合は通常攻撃を行う
		//   ※この段階では「ヒール」のみの実装でよい
		//   ・HPが減っていれば「ヒール」を使用し、減ってなければ通常攻撃を行う

		if(poisonFlag){
			//毎ターン20のダメージ
			 defender.Damage(20);
		}
		int damage = -1;

		String magicName = null;
		System.out.println(GetName() + "の攻撃！");

		parizeFlag = 1;

		// 与えるダメージを求める
		if(this.mp >= 20){
			//ダメージ
			int random = (int)((Math.random()*2));
			if(random == 0){
				//ヒール
				magicName = "ヒール";
				damage = specialMagic(defender,50,0);
				//mpを20消費
				this.mp = this.mp - 20;
				System.out.println(magicName + " で " + damage + "の回復！");
			}else if(random == 1){
				//パライズ
				magicName = "パライズ";
				damage = specialMagic(defender,10,1);
				this.mp = this.mp - 10;
				if(damage == 0){
					//相手の行動不能
					System.out.println(defender.GetName() + "に" + magicName + "、"+defender.GetName()+"の行動不能");
					parizeFlag =1;
				}else {
					//相手の行動不能失敗
					System.out.println(defender.GetName() + "に" + magicName + "失敗");
				}
			}else if(random == 2){
				//ポイズン
				magicName = "ポイズン";
				damage = specialMagic(defender,10,2);
				this.mp = this.mp - 10;
				poisonFlag = true;
			}
		}else if(this.mp >= 10){
			//ダメージ
			int random = (int)((Math.random()*1));
			if(random == 0){
				//パライズ
				magicName = "パライズ";
				damage = specialMagic(defender,10,1);
				this.mp = this.mp - 10;
				if(damage == 0){
					//相手の行動不能
					System.out.println(defender.GetName() + "に" + magicName + "、"+defender.GetName()+"の行動不能");
					parizeFlag =1;
				}else {
					//相手の行動不能失敗
					System.out.println(defender.GetName() + "に" + magicName + "失敗");
				}
			}else if(random == 0){
				//ポイズン
				magicName = "ポイズン";
				damage = specialMagic(defender,10,2);
				this.mp = this.mp - 10;
				poisonFlag = true;
			}
		}else{
			//通常
			magicName = "通常攻撃";
			damage = CalcDamage(defender);
			System.out.println(defender.GetName() + "に" + magicName + " で " + damage + "のダメージ！");
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
