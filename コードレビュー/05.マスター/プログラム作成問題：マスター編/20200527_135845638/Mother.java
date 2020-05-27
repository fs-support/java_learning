package fs05_Master_Refactoring;

//【母ちゃんクラス】
import java.util.ArrayList;
import java.util.Random;

public class Mother extends Player {

	ArrayList<Skill> special = new ArrayList<Skill>();

	// コンストラクタ
	public Mother(String name){
		super(name);
		// Skill型のリストに各スキルを格納
		special.add(new PocketMoney("「今月のお小遣い無し」",100));
		special.add(new HomeWork("「あんた宿題やったの？」",100));
	}

	// 名前(name)からキャラクターに必要なパラメータを生成する
	@Override
	protected void MakeCharacter()
	{
		// 母ちゃんのパラメータを名前から生成する
		// 各パラメータのフィールドはplayerクラスから継承している
		// GetNumber()メソッドの第２引数は「最大値-最小値」の値にする、その後にその値をプラスして底上げ
		this.hp = GetNumber(0, 500)+500;
		this.str = GetNumber(0, 100)+100;
		this.def = GetNumber(0, 100)+100;
		this.luck = GetNumber(0, 20)+80;
		this.agi = GetNumber(0, 20)+80;
		this.mp = GetNumber(0, 100)+200;
	}

	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	@Override
	public void Attack(Player defender)
	{
		// 使用する魔法を決定
		Random random = new Random();
		int use = random.nextInt(special.size());

		// MP有り：魔法攻撃 ※MP残量が呼び出す魔法のコスト以上の場合
		// MP無し：通常攻撃 ※MP残量が呼び出す魔法のコスト以下の場合
		if(this.GetMP()>=special.get(use).GetSkillCost()){
			special.get(use).Magic(this,defender);
		} else {
			System.out.println(GetName() + "の攻撃！");
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

}
