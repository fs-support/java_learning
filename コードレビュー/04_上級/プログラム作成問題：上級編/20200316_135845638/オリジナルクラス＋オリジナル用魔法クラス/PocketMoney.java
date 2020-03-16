package fs04_Advanced_ProgramCreation;

//【Skillクラスを継承した子クラス】_ 魔法「お小遣い無し」を管理するクラス

public class PocketMoney extends Skill {

	// コンストラクタ
	public PocketMoney(String name , int cost){
		super(name , cost);
	}

	// 魔法を使用する
	@Override
	public void Magic(Player User , Player Target){
		System.out.println(User.name + "は呟いた.." + this.name);
		User.mp-=this.cost;

		// 魔法「お小遣い無し」の効果処理 ～ 相手の[defを半分]にして、その数値分を相手の[str]に加算する
		System.out.println(Target.name + "は怒りに震えた..「ふざけんなババアァァァ！」");
		System.out.println(Target.name + "は怒りにより攻撃力が上がったが冷静さを欠き防御力が下がった");
		int halfdef = Target.def/2;
		Target.def = halfdef;
		Target.str += halfdef;
	}

}
