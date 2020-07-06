package nameBattler;

import java.util.ArrayList;

public class Wizard extends Player {
	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name) {
		super(name); //super()：1世代上のコンストラクタを呼び出す。その際括弧内の引数を渡す。
	}

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

		this.hp = GetNumber(0, 100) + 50; //50~150
		this.mp = GetNumber(1, 50) + 30; //30~80
		this.str = GetNumber(2, 49) + 1; //1~50
		this.def = GetNumber(3, 49) + 1; //1~50
		this.luck = GetNumber(4, 99) + 1; //1~100
		this.agi = GetNumber(5, 40) + 20; //20~60
	}

	// =======================
	// public メソッド
	// =======================

	/**
	 * 覚えている魔法のリストを生成する
	 */
	public ArrayList<Magic> MakeMagicList() {
		ArrayList<Magic> magicList = new ArrayList<>();
		magicList.add(new Magic("ファイア", 10, 10, 30, 0, 0, 0, 0));	//ファイア：消費MP10。相手に10~30ダメージ。
		magicList.add(new Magic("サンダー", 20, 20, 40, 0, 0, 0, 0));	//サンダー：消費MP20。相手に20~40ダメージ。
		magicList.add(new Magic("デス", 40, 50, 70, 0, 0, 0, 0));		//デス：消費MP40。相手に50~70ダメージ。
		return magicList;
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
		// 与えるダメージを求める
		System.out.println(GetName() + "のターン！");
		int damage = 0;

		ArrayList<Magic>magicList = MakeMagicList();
		ArrayList<Magic>usableMagic =UsableMagic(magicList);
		//魔法攻撃(使用可能魔法リストが空でない)
		if (!usableMagic.isEmpty()) {
			//使う魔法の決定
			Magic useMagic = DecideUseMagic(usableMagic);
			System.out.println("魔法攻撃「" + useMagic.GetName() + "」！");
			UseMP(useMagic.GetUseMP());
			damage = useMagic.GetDamage();
		}
		//通常攻撃
		else {
			System.out.println("MPがない…通常攻撃！");
			damage = CalcDamage(defender);
		}

		// 求めたダメージを対象プレイヤーに与える
		System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
		defender.Damage(damage);

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}


}
