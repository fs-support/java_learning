package namebattler.player;

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

		//MPが10以上残っている場合
		if(this.mp >= 10) {
			//魔法使用可の場合、もしくは50％確率で攻撃魔法の処理
			if(ran.nextInt(100) < 50 || this.magiAt == true) {
				//魔法攻撃の処理
				this.MagicAttack(defender);
			}else {
				//通常攻撃の処理
				this.NormalAttack(defender);
			}

		//MPがない場合は通常攻撃
		}else {
			// 通常攻撃の処理
			this.NormalAttack(defender);
		}

		//魔法不使用状態にする
		this.magiAt = false;

        //倒れた判定
        if (defender.GetHP() <= 0) {
        	this.PlayerDead(defender);
        }
	}

}
