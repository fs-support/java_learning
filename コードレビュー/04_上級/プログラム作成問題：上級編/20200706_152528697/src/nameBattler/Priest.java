package nameBattler;

import java.util.ArrayList;

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

		// 魔法使いのパラメータを名前から生成する
		// (職種ごとにハッシュ生成位置、上限値を設定可能)

		this.hp = GetNumber(0, 120) + 80; //80~200
		this.mp = GetNumber(1, 30) + 20; //20~50
		this.str = GetNumber(2, 60) + 10; //10~70
		this.def = GetNumber(3, 60) + 10; //10~70
		this.luck = GetNumber(4, 99) + 1; //1~100
		this.agi = GetNumber(5, 40) + 20; //20~60

		//初期HP
		firstHP = this.hp;
	}

	/**
	 * 覚えている魔法のリストを生成する
	 */
	public ArrayList<Magic> MakeMagicList() {
		ArrayList<Magic> magicList = new ArrayList<>();
		magicList.add(new Magic("ケア", 10, 0, 0, 20, 0, 0, 0));		//ケア：消費MP10。HP20回復。
		magicList.add(new Magic("ヒール", 20, 0, 0, 50, 0, 0, 0));		//ヒール：消費MP20。HP50回復。
		magicList.add(new Magic("パライズ", 10, 0, 0, 0, 50, 0, 0));	//パライズ：消費MP10。50%で相手を行動不能。
		magicList.add(new Magic("ポイズン", 10, 0, 0, 0, 0, 20, 3));	//ポイズン：消費MP10。3ターンの間20ダメージ。
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
		ArrayList<Magic>magicList = MakeMagicList();
		ArrayList<Magic>usableMagic =UsableMagic(magicList);

		//魔法使用判定(現在のHPが初期HPより小さい、かつ使用可能魔法がある)
		if (this.hp < firstHP && !usableMagic.isEmpty()) {
			//使う魔法の決定
			Magic useMagic = DecideUseMagic(usableMagic);

			//使う魔法によって処理を分岐
			if(useMagic.GetHealAmount() > 0) {
				Heal(useMagic);
			}else if(useMagic.GetBindRate() > 0){
				Bind(useMagic, defender);
			}else {
				SlipDamage(useMagic, defender);
			}
		}
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
