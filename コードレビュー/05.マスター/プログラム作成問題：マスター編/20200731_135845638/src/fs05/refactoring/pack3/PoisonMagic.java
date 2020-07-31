package fs05.refactoring.pack3;

//【Skillクラスを継承した子クラス】_ ポイズンの魔法を管理するクラス

import fs05.refactoring.pack2.Player;

public class PoisonMagic extends Skill {

	// コンストラクタ
	public PoisonMagic(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println
				(user.getName() + "は" + this.name + "を唱えた");

		// (自然治癒するので)確率100%で成功 ※既に毒状態(Target.Status_poison==true)になっている場合は不発
		if (target.isGetStatusPoison()) {
			System.out.println
					("..しかし" + target.getName() + "は既に毒状態になっていた");
		} else {
			target.setStatusPoison(0);
			System.out.println(target.getName() + "は毒に侵された");
		}
		user.mpConsumption(cost);
	}

}