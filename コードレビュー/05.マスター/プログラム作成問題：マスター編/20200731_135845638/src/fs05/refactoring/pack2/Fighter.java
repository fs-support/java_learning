package fs05.refactoring.pack2;

// 【戦士クラス】

public class Fighter extends Player {

	// コンストラクタ
	public Fighter(String playerName) {
		super(playerName);
	}

	// 名前(name)からキャラクターに必要なパラメータを生成する
	@Override
	protected void makeCharacter()
	{
		// 戦士のパラメータを名前から生成する
		// 各パラメータのフィールドはplayerクラスから継承している
		// GetNumber()メソッドの第２引数は「最大値-最小値」の値にする、その後にその値をプラスして底上げ
		this.hp = getNumber(0, 200) + 100;
		this.str = getNumber(0, 70) + 30;
		this.def = getNumber(0, 70) + 30;
		this.luck = getNumber(0, 99) + 1;
		this.agi = getNumber(0, 49) + 1;
		this.mp = 0;
	}

	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	@Override
	public void attack(Player defender)
	{
		System.out.println(getName() + "の攻撃！");

		// 追加機能を呼び出す
		this.extra.get(0).extraFunction(defender);

		// 追加機能が不発の場合は通常通り攻撃
		if (defender.getHp() != 0) {

			int damage = calcDamage(defender);

			// 求めたダメージを対象プレイヤーに与える
			if (damage == this.getStr()) {
				System.out.println(this.getName() + "の「会心の一撃！」");
				System.out.println
						(defender.getName() + "に" + damage + "のダメージ！");
			} else if (damage == 0) {
				System.out.println(this.getName() + "は攻撃をミスした...");
			} else {
				System.out.println
						(defender.getName() + "に" + damage + "のダメージ！");
			}

			defender.damage(damage);

		}

		// 倒れた判定
		if (defender.getHp() <= 0) {
			System.out.println(defender.getName() + "は力尽きた...");
		}
	}

}