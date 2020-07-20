package fs05.refactoring;

//【Skillクラスを継承した子クラス】_ パライズの魔法を管理するクラス
import java.util.Random;

public class ParalysisMagic extends Skill {

	// コンストラクタ
	public ParalysisMagic(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println(user.name + "は" + this.name + "を唱えた");

		// 確率50%で成功 ※既に麻痺状態（statusParalysis==true）になっている場合は不発
		if (target.statusParalysis) {
			System.out.println
					("..しかし" + target.getName() + "は既に麻痺状態になっていた");
		} else {
			Random random = new Random();
			int paralysis = random.nextInt(100) + 1;
			if (paralysis <= 50) {
				target.statusParalysis = true;
				System.out.println(this.name + "..成功！");
			} else {
				target.statusParalysis = false;
				System.out.println(this.name + "..失敗！");
			}
		}
		user.mp -= this.cost;
	}

}