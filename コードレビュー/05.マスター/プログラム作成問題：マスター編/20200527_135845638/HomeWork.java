package fs05_Master_Refactoring;

//【Skillクラスを継承した子クラス】_ 魔法「あんた宿題やったの？」を管理するクラス

public class HomeWork extends Skill {

	// コンストラクタ
	public HomeWork(String name , int cost){
		super(name , cost);
	}

	// 魔法を使用する
	@Override
	public void Magic(Player User , Player Target){
		System.out.println(User.name + "は呟いた.." + this.name);
		User.mp-=this.cost;

		// 魔法「あんた宿題やったの？」の効果処理 ～ 相手の[HP][MP]を半分にする
		System.out.println(Target.name + "は叫んだ..「今からやろうとしてたんだよ！」");
		System.out.println(Target.name + "はやる気を削がれHPとMPが半減した");
		Target.hp/=2;
		Target.mp/=2;
	}

}
