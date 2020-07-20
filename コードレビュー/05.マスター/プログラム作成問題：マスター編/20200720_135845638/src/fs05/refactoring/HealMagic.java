package fs05.refactoring;

//【Skillクラスを継承した子クラス】_ ヒールの魔法を管理するクラス

public class HealMagic extends Skill {

	// コンストラクタ
	public HealMagic(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println(user.name + "は" + this.name + "を唱えた");
		System.out.println(target.name + "のHPが50回復した");
		user.mp -= this.cost;
		target.hp += 50;

		// 回復後のHPが最大HPを超えた場合、回復後のHPを最大HPにする
		if (target.getMaxHp() < target.hp) {
			target.hp = target.getMaxHp();
		}
	}

}