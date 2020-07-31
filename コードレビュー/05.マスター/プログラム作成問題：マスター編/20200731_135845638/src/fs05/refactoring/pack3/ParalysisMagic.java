package fs05.refactoring.pack3;

//【Skillクラスを継承した子クラス】_ パライズの魔法を管理するクラス

import fs05.refactoring.pack2.Player;

import java.util.Random;

public class ParalysisMagic extends Skill {

	// コンストラクタ
	public ParalysisMagic(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println(user.getName() + "は" + this.name + "を唱えた");

		// 確率50%で成功 ※既に麻痺状態（statusParalysis==true）になっている場合は不発
		if (target.isGetStatusParalysis()) {
			System.out.println
					("..しかし" + target.getName() + "は既に麻痺状態になっていた");
		} else {
			Random random = new Random();
			int paralysis = random.nextInt(100) + 1;
			if (paralysis <= 50) {
				target.setStatusParalysis(0);
				System.out.println(this.name + "..成功！");
			} else {
				System.out.println(this.name + "..失敗！");
			}
		}
		user.mpConsumption(cost);
	}

}