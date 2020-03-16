package fs04_Advanced_ProgramCreation;

//【Skillクラスを継承した子クラス】_ パライズの魔法を管理するクラス
import java.util.Random;

public class Paralysis_Magic extends Skill {

	// コンストラクタ
	public Paralysis_Magic(String name , int cost){
		super(name , cost);
	}

	// 魔法を使用する
	@Override
	public void Magic(Player User , Player Target){
		System.out.println(User.name + "は" + this.name + "を唱えた");

		// 確率50%で成功 ※既に麻痺状態になっている場合は不発
		if(Target.Status_paralysis==true){
			System.out.println("..しかし" + Target.GetName() + "は既に麻痺状態になっていた");
		} else {
			Random random = new Random();
			int paralysis = random.nextInt(100)+1;
			if(paralysis<=50){
				Target.Status_paralysis = true;
				System.out.println(this.name + "..成功！");
			} else {
				Target.Status_paralysis = false;
				System.out.println(this.name + "..失敗！");
			}
		}
		User.mp-=this.cost;
	}

}
