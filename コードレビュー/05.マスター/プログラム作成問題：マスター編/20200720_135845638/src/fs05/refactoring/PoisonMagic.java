package fs05.refactoring;

//【Skillクラスを継承した子クラス】_ ポイズンの魔法を管理するクラス

public class PoisonMagic extends Skill {

	// コンストラクタ
	public PoisonMagic(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println
				(user.name + "は" + this.name + "を唱えた");

		// (自然治癒するので)確率100%で成功 ※既に毒状態(Target.Status_poison==true)になっている場合は不発
		if (target.statusPoison) {
			System.out.println
					("..しかし" + target.getName() + "は既に毒状態になっていた");
		} else {
			target.statusPoison = true;
			System.out.println(target.name + "は毒に侵された");
		}
		user.mp -= this.cost;
	}

}