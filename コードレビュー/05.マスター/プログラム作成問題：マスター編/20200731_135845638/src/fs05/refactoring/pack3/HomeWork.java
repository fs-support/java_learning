package fs05.refactoring.pack3;

//【Skillクラスを継承した子クラス】_ 魔法「あんた宿題やったの？」を管理するクラス

import fs05.refactoring.pack2.Player;

public class HomeWork extends Skill {

	// コンストラクタ
	public HomeWork(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println(user.getName() + "は呟いた.." + this.name);
		user.mpConsumption(cost);

		// 魔法「あんた宿題やったの？」の効果処理 ～ 相手の[HP][MP]を半分にする
		System.out.println(target.getName() + "は叫んだ..「今からやろうとしてたんだよ！」");
		System.out.println(target.getName() + "はやる気を削がれHPとMPが半減した");
		target.statusDebuff("体力");
		target.statusDebuff("魔力");
	}

}