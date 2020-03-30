package Battle;

import java.util.Random;

public class Priest extends Player {

	public Priest(String name) {
		super(name);
	}
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter() {
		this.hp = GetNumber(0, 120) + 80;//HP:80～200
		this.str = GetNumber(2, 60) + 10;//STR:10～70
		this.def = GetNumber(3, 60) + 10;//DEF:10～70
		this.mp = GetNumber(4, 30) + 20;//MP:20～50
		this.luck = GetNumber(5, 99) + 1;//LUCK:1～100
		this.agi = GetNumber(0, 40) + 20;//AGI:20～60
		this.maxhp = this.hp;
		this.abnomal = 0;//abnomal:0=通常
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
		Random random = new Random();
		String magicName = null;
		int usemasic;
		int damage = 0;
		// 与えるダメージを求める
		if (this.maxhp - this.hp >= 50 && this.mp >= 20) {
			this.hp = this.hp + 50;
			this.mp = this.mp - 20;
			if (this.hp > this.maxhp) {
				this.hp = this.maxhp;
			}
			System.out.println(GetName() + "がヒールで50回復");
		} else if (this.mp >= 20) {
			usemasic = random.nextInt(2);
			System.out.println(GetName() + "の攻撃！");
			if (usemasic == 0) {
				magicName = "麻痺魔法";
				System.out.println(defender.GetName() + "に" + magicName + "！");
				defender.abnomal = 2;
			} else {
				magicName = "毒魔法";
				System.out.println(defender.GetName() + "に" + magicName + "！");
				defender.abnomal = 1;
			}
			this.mp = this.mp - 20;
		} else {
			// 求めたダメージを対象プレイヤーに与える
			System.out.println(GetName() + "の攻撃！");
			magicName = "通常攻撃";
			damage = CalcDamage(defender);
			if (damage == 0) {
				System.out.println("ミス!");
			} else {
				System.out.println(defender.GetName() + "に" + magicName + "で" + damage + "のダメージ！");
				defender.Damage(damage);
			}
		}
		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}
}
