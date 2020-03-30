package Battle;

import java.util.Random;

public class Wizard extends Player {

	public Wizard(String name) {
		super(name);
	}
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter() {
		this.hp = GetNumber(0, 100) + 50;//HP:50～100
		this.str = GetNumber(2, 49) + 1;//STR:1～50
		this.def = GetNumber(3, 49) + 1;//DEF:1～50
		this.mp = GetNumber(4, 50) + 30;//MP:30～80
		this.luck = GetNumber(5, 99) + 1;//LUCK:1～100
		this.agi = GetNumber(0, 40) + 20;//AGI:20～60
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
		if (this.mp >= 20) {
			usemasic = random.nextInt(2);
			if (usemasic == 0) {
				magicName = "ファイア";
				damage = random.nextInt(30);
				if (damage < 10) {
					damage = 10;
				}
				this.mp = this.mp - 10;
			} else {
				magicName = "サンダー";
				damage = random.nextInt(30);
				if (damage < 10) {
					damage = 10;
				}
				this.mp = this.mp - 20;
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
			System.out.println("ミス!");
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
