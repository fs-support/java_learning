// プレイヤー：戦士
package nameBattler;

public class Fighter extends Player {
	//extends(継承)：任意のクラスを継承する(今回はPlayerクラス)。継承元(スーパークラス)の変数やメソッドなどを使える。
	//継承先(サブクラス)

	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	public Fighter(String name)
	{
		super(name);	//super()：1世代上のコンストラクタを呼び出す。その際括弧内の引数を渡す。
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
	@Override	//@Override：スーパークラスの上書き
	protected void MakeCharacter()
	{
		// 戦士のパラメータを名前から生成する
		// (職種ごとにハッシュ生成位置、上限値を設定可能)

		this.hp = GetNumber(0, 200) + 100;	//100~300
		this.mp = GetNumber(1, 0);			//0
		this.str = GetNumber(1, 70) + 30;	//30~100
		this.def = GetNumber(2, 70) + 30;	//30~100
		this.luck = GetNumber(3, 99) + 1;	//1~100
		this.agi = GetNumber(4, 49) + 1;	//1~50
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
	public void Attack(Player defender)
	{
        // 与えるダメージを求める
        System.out.println(GetName() + "のターン！");
        int damage = CalcDamage(defender);

        // 求めたダメージを対象プレイヤーに与える
        System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
        defender.Damage(damage);

        // 倒れた判定
        if (defender.GetHP() <= 0) {
            System.out.println(defender.GetName() + "は力尽きた...");
        }
	}
}
