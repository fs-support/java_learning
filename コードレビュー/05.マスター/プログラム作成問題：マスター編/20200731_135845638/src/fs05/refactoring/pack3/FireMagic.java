package fs05.refactoring.pack3;

//【Skillクラスを継承した子クラス】_ ファイアの魔法を管理するクラス

import fs05.refactoring.pack2.Player;

import java.util.Random;

public class FireMagic extends Skill {

	// コンストラクタ
	public FireMagic(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println(user.getName() + "は" + this.name + "を唱えた");
		user.mpConsumption(cost);

		// ダメージ処理
		Random random = new Random();
		int damage = random.nextInt(21) + 10;
		target.skillDamage(damage);
		System.out.println
				(target.getName() + "に" + damage + "のダメージ！");
	}

}