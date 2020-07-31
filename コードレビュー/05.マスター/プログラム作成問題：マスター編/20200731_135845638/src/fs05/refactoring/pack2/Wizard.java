package fs05.refactoring.pack2;

// 【魔法使いクラス】

import fs05.refactoring.pack3.Skill;
import fs05.refactoring.pack3.FireMagic;
import fs05.refactoring.pack3.ThunderMagic;

import java.util.ArrayList;
import java.util.Random;

public class Wizard extends Player {

	ArrayList<Skill> magic = new ArrayList<Skill>();

	// コンストラクタ
	public Wizard(String playerName) {
		super(playerName);
		// Skill型のリストに各スキルを格納
		magic.add(new FireMagic("ファイア", 10));
		magic.add(new ThunderMagic("サンダー", 20));
	}

	// 名前(name)からキャラクターに必要なパラメータを生成する
	@Override
	protected void makeCharacter()
	{
		// 魔法使いのパラメータを名前から生成する
		// 各パラメータのフィールドはplayerクラスから継承している
		// GetNumber()メソッドの第２引数は「最大値-最小値」の値にする、その後にその値をプラスして底上げ
		this.hp = getNumber(0, 100) + 50;
		this.str = getNumber(0, 49) + 1;
		this.def = getNumber(0, 49) + 1;
		this.luck = getNumber(0, 99) + 1;
		this.agi = getNumber(0, 40) + 20;
		this.mp = getNumber(0, 50) + 30;
	}

	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	@Override
	public void attack(Player defender)
	{
		System.out.println(getName() + "の攻撃！");

		// 使用する魔法を決定
		Random random = new Random();
		int use = random.nextInt(magic.size());

		// MP有り：魔法攻撃 ※MP残量が呼び出す魔法のコスト以上の場合
		// MP無し：通常攻撃 ※MP残量が呼び出す魔法のコスト以下の場合
		if (magic.get(use).getSkillCost() <= this.getMp()) {
			magic.get(use).magic(this, defender);
		} else {
			// 与えるダメージを求める
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