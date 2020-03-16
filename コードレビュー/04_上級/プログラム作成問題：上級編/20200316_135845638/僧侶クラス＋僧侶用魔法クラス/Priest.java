package fs04_Advanced_ProgramCreation;

//【僧侶クラス】
import java.util.ArrayList;
import java.util.Random;

public class Priest extends Player {

	ArrayList<Skill> magic = new ArrayList<Skill>();

	// コンストラクタ
	public Priest(String name){
		super(name);
		magic.add(new Heal_Magic("ヒール",20));			// Skill型の魔法リストに「ヒール」を格納
		magic.add(new Paralysis_Magic("パライズ",10));	// Skill型の魔法リストに「パライズ」を格納
		magic.add(new Poison_Magic("ポイズン",10));		// Skill型の魔法リストに「ポイズン」を格納
	}


	//【protected メソッド】
	// ===========================================================================
	// 名前(name)からキャラクターに必要なパラメータを生成する
	@Override
	protected void MakeCharacter()
	{
		// 僧侶のパラメータを名前から生成する
		this.hp = GetNumber(0, 120)+80;
		this.str = GetNumber(0, 60)+10;
		this.def = GetNumber(0, 60)+10;
		this.luck = GetNumber(0, 99)+1;
		this.agi = GetNumber(0, 40)+20;
		this.mp = GetNumber(0, 30)+20;
	}

	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	@Override
	public void Attack(Player defender)
	{
		// 使用する魔法を決定
		Random random = new Random();
		int use = random.nextInt(magic.size());

		// MP有り：魔法使用 ※MP残量が呼び出す魔法のコスト以上の場合
		// MP無し：通常攻撃 ※MP残量が呼び出す魔法のコスト以下の場合

		// MP残量が呼び出す魔法のコスト以上なら魔法を使用
		// その上でHPがMAXでなければヒール、HPがMAXならヒール以外で再度乱数生成
		if(this.GetMP()>=magic.get(use).GetSkillCost()){
			if(magic.get(use).GetSkillName().equals("ヒール") && this.GetHP()!=this.GetMAXHP()){
				magic.get(use).Magic(this,this);
			} else {
				use = random.nextInt(magic.size()-1)+1;
				magic.get(use).Magic(this,defender);
			}
		} else {
			// MPが足りない場合は通常攻撃
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

