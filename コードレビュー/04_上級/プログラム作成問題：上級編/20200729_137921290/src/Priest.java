import java.util.Random;

// プレイヤー：僧侶
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
		this.job = "僧侶";
		this.hp = GetNumber(0, 120) + 80;
		this.MAX = GetNumber(0, 120) + 80;
		this.mp = GetNumber(1, 30) + 20;
		this.str = GetNumber(2, 60) + 10;
		this.def = GetNumber(3, 60) + 10;
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
		if (GetMP() >= 20 && GetHP() <= MAXHP() - 50) {
			System.out.println(GetName() + "のヒール！体力を５０回復！");
			this.mp = Math.max(this.GetMP() - 20, 0);
			this.hp = Math.max(this.GetHP() + 50, 0);
		} else {
			if (GetMP() >= 10 && defender.cond == " ") {
				switch (choice) {
				case 0:
					System.out.println("パライズ！" + defender.GetName() + "は痺れてしまった！");
					defender.cond = " 麻痺";
					this.mp = Math.max(this.GetMP() - 10, 0);
					break;
				case 1:
					System.out.println("ポイズン！" + defender.GetName() + "は毒に侵されてしまった…");
					defender.cond = " 毒";
					this.mp = Math.max(this.GetMP() - 10, 0);
					break;
				}
			} else {
				System.out.println(GetName() + "の攻撃！");
				damage = CalcDamage(defender);
				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			}
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
