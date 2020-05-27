package fs05_Master_Refactoring;

//【Skillクラスを継承した子クラス】_ ファイアの魔法を管理するクラス
import java.util.Random;

public class Fire_Magic extends Skill {

	// コンストラクタ
	public Fire_Magic(String name , int cost){
		super(name , cost);
	}

	// 魔法を使用する
	@Override
	public void Magic(Player User , Player Target){
		System.out.println(User.name + "は" + this.name + "を唱えた");
		User.mp-=this.cost;

		// ダメージ処理
		Random random = new Random();
		int damage = random.nextInt(21)+10;
		Target.hp-=damage;
		System.out.println(Target.GetName() + "に" + damage + "のダメージ！");
	}

}
