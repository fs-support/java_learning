package Battle;

import java.util.Random;

public class Conjurer extends Player {
	public Conjurer(String name) {
		super(name);

	}
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter() {
		this.hp = GetNumber(0, 100) + 80;//HP:80～180
		this.str = GetNumber(2, 20) + 10;//STR:10～30
		this.def = GetNumber(3, 40) + 10;//DEF:10～50
		this.mp = GetNumber(5, 100) + 50;//MP:50～150
		this.luck = GetNumber(6, 99) + 1;//LUCK:1～100
		this.agi = GetNumber(0, 20) + 60;//AGI:20～60
		this.abnomal = 0;//abnomal:0=通常
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
		Random random = new Random();
		// 与えるダメージを求める
		String magicName = null;
		int usemasic;
		int damage = 0;
		int debuff = 0;
		if (this.mp >= 60) {
			usemasic = random.nextInt(2);
			if (usemasic == 0) {
				magicName = "防御低下魔法";
				debuff = 50;
				this.mp = this.mp - 60;

			} else {
				magicName = "攻撃低下魔法";
				debuff = 30;
				this.mp = this.mp - 60;
			}
		} else if (this.mp >= 10) {

			magicName = "ファイア";
			damage = random.nextInt(30);
			if (damage < 10) {
				damage = 10;
			}
			this.mp = this.mp - 10;

		} else {
			magicName = "通常攻撃";
			damage = CalcDamage(defender);
		}
		// 求めたダメージを対象プレイヤーに与える
		System.out.println(GetName() + "の攻撃！");
		if (damage == 0) {
			if (debuff == 0) {
				System.out.println("ミス!");
			} else if (debuff == 50) {
				System.out.println(defender.GetName() + "に" + magicName + "！");
				defender.Debuffdef(debuff);
			} else {
				System.out.println(defender.GetName() + "に" + magicName + "！");
				defender.Debuffstr(debuff);
			}
		} else {
			System.out.println(defender.GetName() + "に" + magicName + "で" + damage + "のダメージ！");
			defender.Damage(damage);
		}
		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}
}
