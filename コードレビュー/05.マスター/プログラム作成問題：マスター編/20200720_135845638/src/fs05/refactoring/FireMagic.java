package fs05.refactoring;

//【Skillクラスを継承した子クラス】_ ファイアの魔法を管理するクラス
import java.util.Random;

public class FireMagic extends Skill {

	// コンストラクタ
	public FireMagic(String skillName, int skillCost) {
		super(skillName, skillCost);
	}

	// 魔法を使用する
	@Override
	public void magic(Player user, Player target) {
		System.out.println
				(user.name + "は" + this.name + "を唱えた");
		user.mp -= this.cost;

		// ダメージ処理
		Random random = new Random();
		int damage = random.nextInt(21) + 10;
		target.hp -= damage;
		System.out.println
				(target.getName() + "に" + damage + "のダメージ！");
	}

}