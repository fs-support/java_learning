package fs05_Master_Refactoring;

//【Skillクラスを継承した子クラス】_ ポイズンの魔法を管理するクラス

public class Poison_Magic extends Skill {

	// コンストラクタ
	public Poison_Magic(String name , int cost){
		super(name , cost);
	}

	// 魔法を使用する
	@Override
	public void Magic(Player User , Player Target){
		System.out.println(User.name + "は" + this.name + "を唱えた");

		// (自然治癒するので)確率100%で成功 ※既に毒状態になっている場合は不発
		if(Target.Status_poison==true){
			System.out.println("..しかし" + Target.GetName() + "は既に毒状態になっていた");
		} else {
			Target.Status_poison = true;
			System.out.println(Target.name + "は毒に侵された");
		}
		User.mp-=this.cost;
	}

}
