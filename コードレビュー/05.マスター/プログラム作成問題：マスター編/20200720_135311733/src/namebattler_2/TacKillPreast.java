package namebattler_2;

import java.util.ArrayList;
import java.util.Random;

public class TacKillPreast implements Tactics {
	// =======================
	// フィールド変数
	// =======================
	ArrayList<Player> defparty;
	ArrayList<Player> attparty;
	Player defplayer;
	Player attplayer;
	Random rnd = new Random();

	// =======================
	// コンストラクタ
	// =======================
	TacKillPreast(){

	}

	// =======================
	// Getter / Setter
	// =======================

	// =======================
	// protected メソッド
	// =======================

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param player : 攻撃するプレイヤー
	 * @param attmembers : 味方のパーティーメンバー
	 * @param defmembers : 相手のパーティーメンバー
	 */
	@Override
	public void Action(Player player,ArrayList<Player> attmembers,ArrayList<Player> defmembers) {
		defparty = (ArrayList<Player>)defmembers.clone();
		// Priestクラスのプレイヤーを優先して攻撃
		// PriestかつHPが残っていれば攻撃優先対象とする
		Party defPriest = new Party();
		//ArrayList<Player> defPriest = null;
		for(int i = 0;i < defparty.size();i++)  {
			if((defparty.get(i) instanceof Priest) && (defparty.get(i).GetHP() > 0)) {
				defPriest.AppendPlayer(defparty.get(i));
			}
		}

		// HPの残っているPriestがいなければ、ほかのプレイヤーをランダムで攻撃
		if(defPriest.GetMembers().size() > 0) {
			defplayer = defPriest.GetMembers().get(rnd.nextInt(defPriest.GetMembers().size()));
		}
		else {
			while(true) {
				defplayer = defparty.get(rnd.nextInt(defparty.size()));
				if(defplayer.GetHP() > 0) {
					break;
				}
			}
		}

		// 与えるダメージを求める
		System.out.println(player.GetName() + "の攻撃！");
		int damage = player.CalcDamage(defplayer);
		// 求めたダメージを対象プレイヤーに与える
		System.out.println(defplayer.GetName() + "に" + damage + "のダメージ！");
		defplayer.Damage(damage);
		// 倒れた判定
    	if (defplayer.GetHP() <= 0) {
    		System.out.println(defplayer.GetName() + "は力尽きた...");
    	}
	}
}
