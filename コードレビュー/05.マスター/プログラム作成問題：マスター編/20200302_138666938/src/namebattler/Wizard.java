package namebattler;

public class Wizard extends Player{
	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name){
		super(name);
	}

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter(){
		// 戦士のパラメータを名前から生成する
		this.jobName = "魔法使い";
		this.hp = Math.max(Math.min((GetNumber(0, 100) * 10), 150), 50);
		this.mp = Math.max(Math.min((GetNumber(0, 100)), 80), 30);
		this.str = Math.max(Math.min((GetNumber(0, 100)), 50), 1);
		this.def = Math.max(Math.min((GetNumber(0, 100)), 50), 1);
		this.luck = Math.max(Math.min((GetNumber(0, 100)), 100), 1);
		this.agi = Math.max(Math.min((GetNumber(0, 100)), 60), 20);
		this.maxHp = this.hp;
		this.maxMp = this.mp;
	}

	// =======================
	// public メソッド
	// =======================
	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender){
		//ステータスチェック
		if(this.stMana.stCheck(this) == true) return;

		//MPがある場合
		if(this.mp >= 10 && this.magiAt == true) {
			//MPが20以上ある場合
			if(this.mp >= 20) {
				//60％の確率でファイアー、40％の確率でサンダーを使用
				switch(this.ran.nextInt(5)) {
					case 0:
					case 1:
					case 2:
						this.magiMana.fire.use(this, defender);
						break;
					default:
						this.magiMana.thunder.use(this, defender);
				}
			//MPが20未満の場合はファイアーを使用
			}else {
				this.magiMana.fire.use(this, defender);
			}
		//MPがない場合は通常攻撃
		}else {
			// 与えるダメージを求める
	        System.out.println(GetName() + "の攻撃！");
	        int damage = CalcDamage(defender);
	        // 求めたダメージを対象プレイヤーに与える
	        System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
	        defender.Damage(damage);
		}

		//魔法不使用状態にする
		this.magiAt = false;

        // 倒れた判定
        if (defender.GetHP() <= 0) {
            System.out.println(defender.GetName() + "は力尽きた...");
        }
	}

}
