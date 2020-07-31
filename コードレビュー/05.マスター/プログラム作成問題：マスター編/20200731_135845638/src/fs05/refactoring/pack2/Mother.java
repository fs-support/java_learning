package fs05.refactoring.pack2;

//【母ちゃんクラス】

import fs05.refactoring.pack3.Skill;
import fs05.refactoring.pack3.PocketMoney;
import fs05.refactoring.pack3.HomeWork;

import java.util.ArrayList;
import java.util.Random;

public class Mother extends Player {

	ArrayList<Skill> special = new ArrayList<Skill>();

	// コンストラクタ
	public Mother(String playerName) {
		super(playerName);
		// Skill型のリストに各スキルを格納
		special.add(new PocketMoney("「今月のお小遣い無し」", 100));
		special.add(new HomeWork("「あんた宿題やったの？」", 100));
	}

	// 名前(name)からキャラクターに必要なパラメータを生成する
	@Override
	protected void makeCharacter()
	{
		// 母ちゃんのパラメータを名前から生成する
		// 各パラメータのフィールドはplayerクラスから継承している
		// GetNumber()メソッドの第２引数は「最大値-最小値」の値にする、その後にその値をプラスして底上げ
		this.hp = getNumber(0, 500) + 500;
		this.str = getNumber(0, 100) + 100;
		this.def = getNumber(0, 100) + 100;
		this.luck = getNumber(0, 20) + 80;
		this.agi = getNumber(0, 20) + 80;
		this.mp = getNumber(0, 100) + 200;
	}

	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	@Override
	public void attack(Player defender)
	{
		// 使用する魔法を決定
		Random random = new Random();
		int use = random.nextInt(special.size());

		// MP有り：魔法攻撃 ※MP残量が呼び出す魔法のコスト以上の場合
		// MP無し：通常攻撃 ※MP残量が呼び出す魔法のコスト以下の場合
		if (special.get(use).getSkillCost() <= this.getMp()) {
			special.get(use).magic(this, defender);
		} else {
			System.out.println(getName() + "の攻撃！");
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