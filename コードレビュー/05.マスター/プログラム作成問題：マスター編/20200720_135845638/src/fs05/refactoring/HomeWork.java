package fs05.refactoring;

//【Skillクラスを継承した子クラス】_ 魔法「あんた宿題やったの？」を管理するクラス

public class HomeWork extends Skill {

	// コンストラクタ
	public HomeWork(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println(user.name + "は呟いた.." + this.name);
		user.mp -= this.cost;

		// 魔法「あんた宿題やったの？」の効果処理 ～ 相手の[HP][MP]を半分にする
		System.out.println
				(target.name + "は叫んだ..「今からやろうとしてたんだよ！」");
		System.out.println
				(target.name + "はやる気を削がれHPとMPが半減した");
		target.hp /= 2;
		target.mp /= 2;
	}

}