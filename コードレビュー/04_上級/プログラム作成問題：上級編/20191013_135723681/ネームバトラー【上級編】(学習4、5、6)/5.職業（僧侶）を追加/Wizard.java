package name_battler5;

import java.util.Random;

public class Wizard extends Player{

	Random rnd = new Random();

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name)
	{
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
	protected void MakeCharacter()
	{
		// 魔法使いのパラメータを名前から生成する
		this.hp = GetNumber(0, 100) + 50;
		this.mp = GetNumber(0, 50) + 30;;
		this.str = GetNumber(1, 49) + 1;
		this.def = GetNumber(2, 49) + 1;
		this.luck = GetNumber(3, 99) + 1;
		this.agi = GetNumber(4, 40) + 20;
	}
	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender)
	{

		int damage = 0;
		//MPが残っている場合は魔法をランダムで使用して攻撃
		if(GetMP() >= 10) {

			int select = rnd.nextInt(2);

			if(select == 0 && (GetMP() >= 30)  ) {

				System.out.println(GetName() + "はサンダーを唱えた！");
				this.mp = GetMP() - 20;
				damage = Thunder();
			}
			else {

				System.out.println(GetName() + "はファイアを唱えた！");
				this.mp = GetMP() - 10;
				damage = Fire();

			}
		}
		else {
		//MPが0の時に通常攻撃をする
        // 与えるダメージを求める
        System.out.println(GetName() + "の攻撃！");
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
	// =======================
	// private メソッド
	// =======================


	// =======================
	// public メソッド
	// =======================
}
