package fs05_Master_Refactoring;

//【僧侶クラス】
import java.util.ArrayList;
import java.util.Random;

public class Priest extends Player {

	ArrayList<Skill> magic = new ArrayList<Skill>();

	// コンストラクタ
	public Priest(String name){
		super(name);
		// Skill型のリストに各スキルを格納
		magic.add(new Heal_Magic("ヒール",20));
		magic.add(new Paralysis_Magic("パライズ",10));
		magic.add(new Poison_Magic("ポイズン",10));
	}


	// 名前(name)からキャラクターに必要なパラメータを生成する
	@Override
	protected void MakeCharacter()
	{
		// 僧侶のパラメータを名前から生成する
		// 各パラメータのフィールドはplayerクラスから継承している
		// GetNumber()メソッドの第２引数は「最大値-最小値」の値にする、その後にその値をプラスして底上げ
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

			// 回復魔法(ヒール)の対象は味方で１番ダメージを受けているプレイヤー
			// 自パーティーメンバーのダメージ量のリストを用意
			ArrayList<Integer> damagePoint = new ArrayList<Integer>();

			// ↑で用意したインスタンスを使ってプレイヤー毎のダメージ量をリスト化（最大HP - 現HP = ダメージ量）
			for(int i = 0; i<this.GetMyPartyInformation().GetMembers().size(); i++){
				damagePoint.add(this.GetMyPartyInformation().GetMembers().get(i).GetMAXHP() - this.GetMyPartyInformation().GetMembers().get(i).GetHP());
			}

			// 以下３つの変数とfor文で１番ダメージを受けているプレイヤーを選出
			int max = 0;
			int index = 0;
			int count = 0;

			for(int i = 0; i<damagePoint.size(); i++){
				if(max<damagePoint.get(i)){
					max = damagePoint.get(i);
					index = count;
				}
				count++;
			}

			//
			if(magic.get(use).GetSkillName().equals("ヒール") && this.GetMyPartyInformation().GetMembers().get(index).GetHP()!=this.GetMyPartyInformation().GetMembers().get(index).GetMAXHP()){
				magic.get(use).Magic(this,this.GetMyPartyInformation().GetMembers().get(index));
			}

			else {
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
