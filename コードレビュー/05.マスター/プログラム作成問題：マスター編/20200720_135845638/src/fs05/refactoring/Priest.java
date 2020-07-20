package fs05.refactoring;

//【僧侶クラス】
import java.util.ArrayList;
import java.util.Random;

public class Priest extends Player {

	ArrayList<Skill> magic = new ArrayList<Skill>();
	private int maxHp; // ヒール用変数
	private int realTimeHp; // ヒール用変数

	// コンストラクタ
	public Priest(String playerName) {
		super(playerName);
		// Skill型のリストに各スキルを格納
		magic.add(new HealMagic("ヒール", 20));
		magic.add(new ParalysisMagic("パライズ", 10));
		magic.add(new PoisonMagic("ポイズン", 10));

		maxHp = 0;
		realTimeHp = 0;
	}

	// 名前(name)からキャラクターに必要なパラメータを生成する
	@Override
	protected void makeCharacter()
	{
		// 僧侶のパラメータを名前から生成する
		// 各パラメータのフィールドはplayerクラスから継承している
		// GetNumber()メソッドの第２引数は「最大値-最小値」の値にする、その後にその値をプラスして底上げ
		this.hp = getNumber(0, 120) + 80;
		this.str = getNumber(0, 60) + 10;
		this.def = getNumber(0, 60) + 10;
		this.luck = getNumber(0, 99) + 1;
		this.agi = getNumber(0, 40) + 20;
		this.mp = getNumber(0, 30) + 20;
	}

	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	@Override
	public void attack(Player defender)
	{
		// 使用する魔法を決定
		Random random = new Random();
		int use = random.nextInt(magic.size());

		// MP有り：魔法使用 ※MP残量が呼び出す魔法のコスト以上の場合
		// MP無し：通常攻撃 ※MP残量が呼び出す魔法のコスト以下の場合

		// MP残量が呼び出す魔法のコスト以上なら魔法を使用
		// その上でHPがMAXでなければヒール、HPがMAXならヒール以外で再度乱数生成
		if (magic.get(use).getSkillCost() <= this.getMp()) {

			// 回復魔法(ヒール)の対象は味方で１番ダメージを受けているプレイヤー
			// 自パーティーメンバーのダメージ量のリストを用意
			ArrayList<Integer> damagePoint = new ArrayList<Integer>();

			// ↑で用意したインスタンスを使ってプレイヤー毎のダメージ量をリスト化（最大HP - 現HP = ダメージ量）
			int numberOfLaps = this.getMyPartyInformation().getMembers().size();
			for (int i = 0; i < numberOfLaps; i++) {
				maxHp = this.getMyPartyInformation().getMembers().get(i)
						.getMaxHp();
				realTimeHp = this.getMyPartyInformation().getMembers().get(i)
						.getHp();
				damagePoint.add(maxHp - realTimeHp);
			}

			// 以下３つの変数とfor文で１番ダメージを受けているプレイヤーを選出
			int max = 0;
			int index = 0;
			int count = 0;

			for (int i = 0; i < damagePoint.size(); i++) {
				if (max < damagePoint.get(i)) {
					max = damagePoint.get(i);
					index = count;
				}
				count++;
			}

			// １番ダメージを受けているプレイヤーを回復
			maxHp = this.getMyPartyInformation().getMembers().get(index)
					.getHp();
			realTimeHp = this.getMyPartyInformation().getMembers().get(index)
					.getMaxHp();
			if (magic.get(use).getSkillName().equals("ヒール")
					&& (maxHp != realTimeHp)) {
				magic.get(use).magic(this,
						this.getMyPartyInformation().getMembers().get(index));
			} else {
				use = random.nextInt(magic.size() - 1) + 1;
				magic.get(use).magic(this, defender);
			}

		} else {
			// MPが足りない場合は通常攻撃
			System.out.println(getName() + "の攻撃！");

			// 与えるダメージを求める
			int damage = calcDamage(defender);

			// 求めたダメージを対象プレイヤーに与える
			if (damage == this.getStr()) {
				System.out.println(this.getName() + "の「会心の一撃！」");
				System.out.println
						(defender.getName() + "に" + damage + "のダメージ！");
			} else if (damage == 0) {
				System.out.println(this.getName() + "は攻撃をミスした...");
			} else {
				System.out.println
						(defender.getName() + "に" + damage + "のダメージ！");
			}
			defender.damage(damage);
		}

		// 倒れた判定
		if (defender.getHp() <= 0) {
			System.out.println(defender.getName() + "は力尽きた...");
		}
	}

}