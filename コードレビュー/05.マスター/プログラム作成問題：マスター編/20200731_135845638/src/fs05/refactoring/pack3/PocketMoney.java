package fs05.refactoring.pack3;

//【Skillクラスを継承した子クラス】_ 魔法「お小遣い無し」を管理するクラス

import fs05.refactoring.pack2.Player;

public class PocketMoney extends Skill {

	// コンストラクタ
	public PocketMoney(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println(user.getName() + "は呟いた.." + this.name);
		user.mpConsumption(cost);

		// 魔法「お小遣い無し」の効果処理 ～ 相手の[defを半分]にして、その数値分を相手の[strに加算]する
		System.out.println
				(target.getName() + "は怒りに震えた..「ふざけんなババアァァァ！」");
		System.out.println
				(target.getName() + "は怒りにより攻撃力が上がったが冷静さを欠き防御力が下がった");
		target.statusDebuff("防御");
		target.statusBuff("攻撃", target.getDef());
	}

}