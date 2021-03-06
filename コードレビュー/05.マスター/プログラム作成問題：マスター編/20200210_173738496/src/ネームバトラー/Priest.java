package ネームバトラー;

import java.util.Random;

public class Priest extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name) {
		super(name);
	}

	// =======================
	// Getter / Setter
	// =======================

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter() {
		// 戦士のパラメータを名前から生成する
		this.hp = GetNumber(0, 120) + 80;
		this.mp = GetNumber(0, 30) + 20;
		this.str = GetNumber(0, 60) + 10;
		this.def = GetNumber(0, 60) + 10;
		this.luck = GetNumber(0, 100);
		this.agi = GetNumber(0, 40) + 20;
		this.magicList.add(new Cure("キュア", 20));
		this.magicList.add(new Poisn("ポイズン", 10));
		this.magicList.add(new Paralysis("パラライズ", 10));
	}

	public void Action1(Player attacker, Party enemy) {
		if (aaction(attacker)) {
			attacker.party.GetStrategy().Action(attacker, enemy);
		}
	}

	public boolean goodAisyou(Player player) {
		if (player instanceof Brave) {
			return true;
		} else {
			return false;
		}
	}

	public boolean badAisyou(Player player) {
		if (player instanceof Wizard) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * 
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
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

	public void Magic(Player attacker, Player affected) {
		System.out.println(GetName() + "の魔法");
		Random ra = new Random();
		while (true) {
			int random = ra.nextInt(magicList.size());
			if (GetMP() >= magicList.get(random).GetUseMP()) {
				System.out.println(magicList.get(random).GetName());
				magicList.get(random).UseMagic(attacker, affected);
				if (affected.GetHP() <= 0) {
					System.out.println(affected.GetName() + "は力尽きた...");
				}
				break;
			}
		}
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
}
