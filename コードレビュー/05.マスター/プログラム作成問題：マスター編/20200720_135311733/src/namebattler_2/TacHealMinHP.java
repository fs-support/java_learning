package namebattler_2;

import java.util.ArrayList;
import java.util.Random;

public class TacHealMinHP implements Tactics {
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
	TacHealMinHP() {

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
	public void Action(Player player, ArrayList<Player> attmembers,
			ArrayList<Player> defmembers) {
		attparty = (ArrayList<Player>) attmembers.clone();
		defparty = (ArrayList<Player>) defmembers.clone();

		// 味方パーティーのうち、HPの低いPlayerを回復の対象に設定
		Party gwp = new Party();
		this.attplayer = gwp.GetWeakestPlayer(attparty);

		// 攻撃対象の設定
		while (true) {
			this.defplayer = defparty.get(rnd.nextInt(defparty.size()));
			if (this.defplayer.GetHP() > 0) {
				break;
			}
		}

		int damage;
		if (attplayer.hp <= 30) {
			// HP回復時の行動
			Magic magicHeal = new MagicHeal();
			if (player.mp >= magicHeal.GetMP()) {
				System.out.println(player.GetName() + " は "
						+ magicHeal.GetName() + " を使った！");
				magicHeal.UseMagic(attplayer);
				player.mp -= magicHeal.GetMP();
			} else {
				// 与えるダメージを求める
				System.out.println(player.GetName() + "の攻撃！");
				damage = player.CalcDamage(this.defplayer);

				// 求めたダメージを対象プレイヤーに与える
				System.out
						.println(this.defplayer.GetName() + "に" + damage + "のダメージ！");
				this.defplayer.Damage(damage);
			}
		} else {
			if (rnd.nextInt(2) == 1) {
				// 魔法 ポイズン使用
				Magic magicPoison = new MagicPoison();
				if (player.mp >= magicPoison.GetMP()
						&& this.defplayer.GetPoison() != true) {
					System.out.println(player.GetName() + " は "
							+ magicPoison.GetName() + " を使った！");
					magicPoison.UseMagic(this.defplayer);
					player.mp -= magicPoison.GetMP();
				} else {
					// 与えるダメージを求める
					System.out.println(player.GetName() + "の攻撃！");
					damage = player.CalcDamage(this.defplayer);

					// 求めたダメージを対象プレイヤーに与える
					System.out.println(
							this.defplayer.GetName() + "に" + damage + "のダメージ！");
					this.defplayer.Damage(damage);
				}

			} else {
				// 魔法 パライズ使用
				Magic magicParays = new MagicParays();
				if (player.mp >= magicParays.GetMP()
						&& this.defplayer.GetParalys() != true) {
					System.out.println(player.GetName() + " は "
							+ magicParays.GetName() + " を使った！");
					magicParays.UseMagic(this.defplayer);
					player.mp -= magicParays.GetMP();
				} else {
					// 与えるダメージを求める
					System.out.println(player.GetName() + "の攻撃！");
					damage = player.CalcDamage(this.defplayer);

					// 求めたダメージを対象プレイヤーに与える
					System.out.println(
							this.defplayer.GetName() + "に" + damage + "のダメージ！");
					this.defplayer.Damage(damage);
				}
			}
		}
		// 倒れた判定
		if (this.defplayer.GetHP() <= 0) {
			System.out.println(this.defplayer.GetName() + "は力尽きた...");
		}
	}
}
