import java.util.Random;

// プレイヤー：魔法使い
public class Wizard extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name) {
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
		this.job = "魔法使い";
		this.hp = GetNumber(0, 100) + 50;
		this.MAX = GetNumber(0, 100) + 50;
		this.mp = GetNumber(1, 50) + 30;
		this.str = GetNumber(2, 49) + 1;
		this.def = GetNumber(3, 49) + 1;
		this.luck = GetNumber(4, 99) + 1;
		this.agi = GetNumber(5, 40) + 20;
		this.cond = " ";
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
		Random random = new Random();
		int choice = random.nextInt(2);
		int damage = 0;

		// 与えるダメージを求める
		System.out.println(GetName() + "の攻撃！");
		if (GetMP() >= 20) {
			switch (choice) {
			case 0:
				damage = random.nextInt(20) + 10;
				System.out.println("ファイア！" + defender.GetName() + "に" + damage + "のダメージ！");
				this.mp = Math.max(this.GetMP() - 10, 0);
				break;
			case 1:
				damage = random.nextInt(20) + 15;
				System.out.println("サンダー！" + defender.GetName() + "に" + damage + "のダメージ！");
				this.mp = Math.max(this.GetMP() - 20, 0);
				break;
			}
		} else if (GetMP() < 20 && GetMP() >= 10) {
			damage = random.nextInt(20) + 10;
			System.out.println("ファイア！" + defender.GetName() + "に" + damage + "のダメージ！");
			this.mp = Math.max(this.GetMP() - 10, 0);
		} else {
			damage = CalcDamage(defender);
			// 求めたダメージを対象プレイヤーに与える
			System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
		}
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
