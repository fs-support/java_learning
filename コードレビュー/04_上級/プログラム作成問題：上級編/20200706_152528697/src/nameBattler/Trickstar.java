package nameBattler;

import java.util.ArrayList;

public class Trickstar extends Player {
	//トリックスター：魔法が少し使える。運命力と素早さが高い。

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Trickstar(String name) {
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

		// 魔法使いのパラメータを名前から生成する
		// (職種ごとにハッシュ生成位置、上限値を設定可能)

		this.hp = GetNumber(0, 70) + 80; //80~150
		this.mp = GetNumber(1, 20) + 10; //10~30
		this.str = GetNumber(2, 40) + 30; //30~70
		this.def = GetNumber(3, 40) + 10; //10~50
		this.luck = GetNumber(4, 80) + 20; //20~100
		this.agi = GetNumber(5, 30) + 40; //40~70

		//初期HP
		firstHP = this.hp;
	}

	/**
	 * 覚えている魔法のリストを生成する
	 */
	public ArrayList<Magic> MakeMagicList() {
		ArrayList<Magic> magicList = new ArrayList<>();
		magicList.add(new Magic("ケア", 10, 0, 0, 20, 0, 0, 0));
		return magicList;
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
		System.out.println(GetName() + "のターン！");
		int damage = 0;
		ArrayList<Magic> magicList = MakeMagicList();
		ArrayList<Magic> usableMagic = UsableMagic(magicList);

		//回復魔法(現在のHPが初期HPの50%以下、かつ使用可能魔法がある)
		if (this.hp <= firstHP/2 && !usableMagic.isEmpty()) {
			Heal(DecideUseMagic(usableMagic));
		}
		//魔法攻撃(使用可能魔法がある)
//		else if(!usableMagic.isEmpty()) {
//
//		}

		//通常攻撃
		else {
			System.out.println("通常攻撃！");
			damage = CalcDamage(defender);
			// 求めたダメージを対象プレイヤーに与える
			System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			defender.Damage(damage);

			// 倒れた判定
			if (defender.GetHP() <= 0) {
				System.out.println(defender.GetName() + "は力尽きた...");
			}
		}
	}
}
