package namebattler.player;

public class Priest extends Player{
	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name){
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
		this.jobName = "僧侶";
		this.hp = Math.max(Math.min((GetNumber(0, 100) * 10), 200), 80);
		this.mp = Math.max(Math.min((GetNumber(0, 100)), 50), 20);
		this.str = Math.max(Math.min((GetNumber(0, 100)), 70), 10);
		this.def = Math.max(Math.min((GetNumber(0, 100)), 70), 10);
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

		//MPが20以上あり回復魔法使用可の場合
		if(this.mp >= 20 && this.pMedic == true) {
			//回復魔法使用時の処理
			this.Recovery(defender);
		//MPが10以上あって、ターゲットが状態異常でない場合
		} else if(this.mp >= 10 && (defender.stMana.GetStPoison() == false
										|| defender.stMana.GetStParalyze() == false)) {
			//ST魔法使用時の処理
			this.StMagiAttack(defender);
		//MPがない場合は通常攻撃
		}else {
			//通常攻撃の処理
			this.NormalAttack(defender);
		}

		//回復不使用状態にする
		this.pMedic = false;

        //倒れた判定
        if (defender.GetHP() <= 0) {
        	this.PlayerDead(defender);
        }
	}

}
