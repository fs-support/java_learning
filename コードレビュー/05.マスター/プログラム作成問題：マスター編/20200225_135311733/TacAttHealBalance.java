package namebattler_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TacAttHealBalance extends Tactics {
	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	TacAttHealBalance(){

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
		attparty = attmembers;
		defparty = defmembers;

		// 攻撃対象の設定
		while(true) {
			defplayer = defparty.get(rnd.nextInt(defparty.size()));
			if(defplayer.GetHP() > 0) {
				break;
			}
		}

		// 味方パーティーのうち、HPの低いPlayerを優先して回復
		Collections.sort(attparty, new Comparator<Player>() {
			@Override
			public int compare(Player personFirst, Player personSecond) {
				return Integer.compare(personFirst.GetHP(), personSecond.GetHP());
			}
		});
		// 対象PlayerのHPが0の場合は、次にHPが低いPlayerを対象にする
		for(int i = 0;i < attparty.size();i++)  {
			attplayer = attparty.get(i);
			if(attplayer.GetHP() > 0) {
				break;
			}
		}

		// HPが減ったらヒールを使用
		// 勇者はHP回復を優先しない場合があるようにする
		int damage;
		if(attplayer.hp <= 40 && rnd.nextInt(2) == 1){
			Magic magicHeal = new MagicHeal();
			if(player.mp >= magicHeal.GetMP()) {
				System.out.println(player.GetName() + " は " + magicHeal.GetName() + " を使った！");
				magicHeal.UseMagic(attplayer);
				player.mp -= magicHeal.GetMP();
			}
			else {
				// 与えるダメージを求める
				System.out.println(player.GetName() + "の攻撃！");
				damage = player.CalcDamage(defplayer);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defplayer.GetName() + "に" + damage + "のダメージ！");
				defplayer.Damage(damage);
			}
		}
		else {
			// 与えるダメージを求める
			System.out.println(player.GetName() + "の攻撃！");
			damage = player.CalcDamage(defplayer);

			// 求めたダメージを対象プレイヤーに与える
			System.out.println(defplayer.GetName() + "に" + damage + "のダメージ！");
			defplayer.Damage(damage);
		}

		// 倒れた判定
		if (defplayer.GetHP() <= 0) {
			System.out.println(defplayer.GetName() + "は力尽きた...");
		}
	}
}