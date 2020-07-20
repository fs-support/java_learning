package namebattler_2;

import java.util.ArrayList;
import java.util.Random;

public class TacPriorityWizardMagic implements Tactics {
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
	TacPriorityWizardMagic(){

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

		// 攻撃対象の設定
		while(true) {
			defplayer = defparty.get(rnd.nextInt(defparty.size()));
			if(defplayer.GetHP() > 0) {
				break;
			}
		}

		// 使用する魔法をランダムに選択
		// サンダーを使用
		if(rnd.nextInt(2) == 1){
			Magic magicThunder = new MagicThunder();
			if(player.mp >= magicThunder.GetMP()) {
				System.out.println(player.GetName() + " は " + magicThunder.GetName() + " を使った！");
				player.mp -= magicThunder.GetMP();
				magicThunder.UseMagic(defplayer);
			}
			else {
				// MPが足りない場合は通常攻撃
				// 与えるダメージを求める
				System.out.println(player.GetName() + "の攻撃！");
				int damage = player.CalcDamage(defplayer);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defplayer.GetName() + "に" + damage + "のダメージ！");
				defplayer.Damage(damage);
			}
		}
		else {
			// ファイアを使用
			Magic magicFire = new MagicFire();
			if(player.mp >= magicFire.GetMP()) {
				System.out.println(player.GetName() + " は " + magicFire.GetName() + " を使った！");
				player.mp -= magicFire.GetMP();
				magicFire.UseMagic(defplayer);
			}
			else {
				// MPが足りない場合は通常攻撃
				// 与えるダメージを求める
				System.out.println(player.GetName() + "の攻撃！");
				int damage = player.CalcDamage(defplayer);
				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defplayer.GetName() + "に" + damage + "のダメージ！");
				defplayer.Damage(damage);
			}
		}
		// 倒れた判定
    	if (defplayer.GetHP() <= 0) {
    		System.out.println(defplayer.GetName() + "は力尽きた...");
    	}
	}
}
