package fs04_Advanced_ProgramCreation;

// 【魔法使いクラス】
import java.util.ArrayList;
import java.util.Random;

public class Wizard extends Player {

	ArrayList<Skill> magic = new ArrayList<Skill>();

	// コンストラクタ
	public Wizard(String name){
		super(name);
		magic.add(new Fire_Magic("ファイア",10));		// Skill型の魔法リストに「ファイア」を格納
		magic.add(new Thunder_Magic("サンダー",20));	// Skill型の魔法リストに「サンダー」を格納
	}

	//【protected メソッド】
	// ===========================================================================
	// 名前(name)からキャラクターに必要なパラメータを生成する
	@Override
	protected void MakeCharacter()
	{
		// 魔法使いのパラメータを名前から生成する
		this.hp = GetNumber(0, 100)+50;
		this.str = GetNumber(0, 49)+1;
		this.def = GetNumber(0, 49)+1;
		this.luck = GetNumber(0, 99)+1;
		this.agi = GetNumber(0, 40)+20;
		this.mp = GetNumber(0, 50)+30;
	}

	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	@Override
	public void Attack(Player defender)
	{
		System.out.println(GetName() + "の攻撃！");

		// 使用する魔法を決定
		Random random = new Random();
		int use = random.nextInt(magic.size());

		// MP有り：魔法攻撃 ※MP残量が呼び出す魔法のコスト以上の場合
		// MP無し：通常攻撃 ※MP残量が呼び出す魔法のコスト以下の場合
		if(this.GetMP()>=magic.get(use).GetSkillCost()){
			magic.get(use).Magic(this,defender);
		} else {
			// 与えるダメージを求める
			int damage = CalcDamage(defender);

			// 求めたダメージを対象プレイヤーに与える
			if(damage==this.GetSTR()){
				System.out.println(this.GetName() + "の「会心の一撃！」");
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			} else if(damage==0){
				System.out.println(this.GetName() + "は攻撃をミスした...");
			} else {
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			}
			defender.Damage(damage);
		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}
	// ===========================================================================
}

