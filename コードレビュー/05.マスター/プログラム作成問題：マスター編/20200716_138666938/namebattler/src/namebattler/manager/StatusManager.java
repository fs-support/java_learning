package namebattler.manager;

import java.util.Random;

import namebattler.player.Player;

public class StatusManager {
	// =======================
	// フィールド変数
	// =======================
	//毒判定
	protected boolean stPoison;
	//毒状態の最大ターン
	protected int maxPoisonTurn = 3;
	//毒状態の経過ターン
	protected int stPoisonTurn;
	//毒のダメージ量
	private int poisonDamage = 20;
	//麻痺判定
	protected boolean stParalyze;
	//麻痺状態の最大ターン
	protected int maxParalyzeTurn = 5;
	//麻痺状態の経過ターン
	protected int stParalyzeTurn;

	// =======================
	// コンストラクタ
	// =======================
	public StatusManager() {

	}

	// =======================
	// Getter / Setter
	// =======================
	//毒判定の取得
	public boolean GetStPoison() {
		return this.stPoison;
	}

	//毒のダメージ量の取得
	public int GetPoisonDamage() {
		return this.poisonDamage;
	}

	//maxPoisonTurnを取得
	public int GetMaxPoisonTurn() {
		return this.maxPoisonTurn;
	}

	//麻痺判定の取得
	public boolean GetStParalyze() {
		return this.stParalyze;
	}

	//maxParalyzeTurnを取得
	public int GetMaxParalyzeTurn() {
		return this.maxParalyzeTurn;
	}


	//毒判定を変更
	public void SetStPoison(boolean stPoison) {
		this.stPoison = stPoison;
	}

	//stPoisonTurnを変更
	public void SetStPoisonTurn(int stPoisonTurn) {
		this.stPoisonTurn = stPoisonTurn;
	}

	//麻痺判定を変更
	public void SetStParalyze(boolean stParalyze) {
		this.stParalyze = stParalyze;
	}

	//stParalyzeTurnを変更
	public void SetStParalyzeTurn(int stParalyzeTurn) {
		this.stParalyzeTurn = stParalyzeTurn;
	}


	// =======================
	// protected メソッド
	// =======================
	//毒状態の時の効果
	protected void Poison(Player player) {
		System.out.println(player.GetName() + "は毒で" + this.poisonDamage + "のダメージを受けた！");
		player.PoisonEffect(player);

		//毒ダメージでHPが尽きた場合
		if(player.GetHP() <= 0) {
			player.PlayerDead(player);
			return;
		}

		//毒状態の継続ターンの減少
		stPoisonTurn--;
		//stPoisonTurnが0なら毒解除
		if(stPoisonTurn <= 0) {
			player.GetStMana().stPoison = false;
			System.out.println(player.GetName() + "の毒が治った！");
		}

	}

	//麻痺状態の時の効果
	protected boolean Paralyze(Player player) {
		//麻痺判定のランダム値の設定
		Random ran = new Random();
		int paraCheck = ran.nextInt(100) + 1;
		//麻痺状態の継続ターンの減少
		stParalyzeTurn--;

		//20％で行動不能
		if(paraCheck < 21) {
			System.out.println(player.GetName() + "は麻痺で体が動かない！");

    		//stParalyzeTurnが0なら毒解除
    		if(stParalyzeTurn <= 0) {
    			player.GetStMana().stParalyze = false;
    			System.out.println(player.GetName() + "の麻痺が治った！");
    		}
			return true;
		}else {
			return false;
		}
	}

	// =======================
	// public メソッド
	// =======================
    //ステータスチェック（HPが尽きたり、ターンの強制終了の場合にtrueを返す）
    public boolean stCheck(Player player) {
    	//毒状態の判定
    	if(player.GetStMana().stPoison == true) {
    		player.GetStMana().Poison(player);

    		//毒ダメージでHPが尽きた場合
    		if(player.GetHP() <= 0) {
    			return true;
    		}
    	}

    	//麻痺状態の判定
    	if(player.GetStMana().stParalyze == true) {
    		return  player.GetStMana().Paralyze(player);
    	}

    	//状態異常の影響がない場合
    	return false;
    }

}
