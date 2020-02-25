package namebattler_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TacKillPreast extends Tactics {
	// =======================
	// フィールド変数
	// =======================

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
		defparty = defmembers;
		// 対象パーティーのうち、HPの低いPlayerを優先して攻撃
		Collections.sort(defparty, new Comparator<Player>() {
			@Override
			public int compare(Player personFirst, Player personSecond) {
				return Integer.compare(personFirst.GetHP(), personSecond.GetHP());
			}
		});

		// 対象PlayerのHPが0の場合は、次にHPが低いPlayerを対象にする
		for(int i = 0;i < defparty.size();i++)  {
			defplayer = defparty.get(i);
			if(defplayer.GetHP() > 0) {
				break;
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