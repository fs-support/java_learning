package fs05.refactoring.pack3;

//【Skillクラスを継承した子クラス】_ ヒールの魔法を管理するクラス

import fs05.refactoring.pack2.Player;

public class HealMagic extends Skill {

	// コンストラクタ
	public HealMagic(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println(user.getName() + "は" + this.name + "を唱えた");
		System.out.println(target.getName() + "のHPが50回復した");
		user.mpConsumption(cost);
		target.recovery(50);
	}

}