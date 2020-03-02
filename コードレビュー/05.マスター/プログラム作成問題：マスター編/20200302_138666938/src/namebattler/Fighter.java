package namebattler;


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
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter(){
		// 戦士のパラメータを名前から生成する
		this.jobName = "戦士";
		this.hp = Math.max(Math.min((GetNumber(0, 100) * 10), 300), 100);
		this.str = Math.max(Math.min((GetNumber(0, 100)), 100), 30);
		this.def = Math.max(Math.min((GetNumber(0, 100)), 100), 30);
		this.luck = Math.max(Math.min((GetNumber(0, 100)), 100), 1);
		this.agi = Math.max(Math.min((GetNumber(0, 100)), 50), 1);
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

        // 与えるダメージを求める
        System.out.println(GetName() + "の攻撃！");
        int damage = CalcDamage(defender);

        // 求めたダメージを対象プレイヤーに与える
        System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
        defender.Damage(damage);

        // 倒れた判定
        if (defender.GetHP() <= 0) {
            System.out.println(defender.GetName() + "は力尽きた...");
        }
	}

}
