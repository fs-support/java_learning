package namebattler;

import java.util.Random;

public class StatusManager {
	// =======================
	// フィールド変数
	// =======================
	//毒判定
	protected boolean stPoison;
	//毒状態の経過ターン（最大〇〇ターン）
	protected int stPoisonTurn = 3;
	//毒のダメージ量
	private int poisonDamage = 20;
	//麻痺判定
	protected boolean stParalyze;
	//麻痺状態の経過ターン（最大〇〇ターン）
	protected int stParalyzeTurn = 5;


	// =======================
	// コンストラクタ
	// =======================
	public StatusManager() {

	}

	// =======================
	// public メソッド
	// =======================
	//毒状態の時の効果
	public void Poison(Player player) {
		player.Damage(this.poisonDamage);
	}

	//麻痺状態の時の効果
	public boolean Paralyze(Player player) {
		//麻痺判定のランダム値の設定
		Random ran = new Random();
		int paraCheck = ran.nextInt(100) + 1;

		//20％で行動不能
		if(paraCheck < 21) {
			System.out.println(player.name + "は麻痺で体が動かない！");
    		stParalyzeTurn--;
    		//stParalyzeTurnが0なら毒解除
    		if(stParalyzeTurn <= 0) {
    			player.stMana.stParalyze = false;
    			System.out.println(player.name + "の麻痺が治った！");
    		}
			return true;
		}else {
			return false;
		}
	}

    //ステータスチェック（HPが尽きたり、ターンの強制終了の場合にtrueを返す）
    public boolean stCheck(Player player) {
    	//毒状態の判定
    	if(player.stMana.stPoison == true) {
    		System.out.println(player.name + "は毒で" + this.poisonDamage + "のダメージを受けた！");
    		player.stMana.Poison(player);
    		stPoisonTurn--;
    		//stPoisonTurnが0なら毒解除
    		if(stPoisonTurn <= 0) {
    			player.stMana.stPoison = false;
    			System.out.println(player.name + "の毒が治った！");
    		}
    		//毒ダメージでHPが尽きた場合
    		if(player.hp <= 0) {
    			System.out.println(player.name + "は力尽きた...");
    			player.stMana.stPoison = false;
    			return true;
    		}
    	}

    	//麻痺状態の判定
    	if(player.stMana.stParalyze == true) {
    		return  player.stMana.Paralyze(player);

    	}

    	//状態異常の影響がない場合
    	return false;
    }

}
