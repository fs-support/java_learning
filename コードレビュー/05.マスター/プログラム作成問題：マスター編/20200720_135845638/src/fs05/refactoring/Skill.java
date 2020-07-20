package fs05.refactoring;

//【魔法、特技などのスキルを管理するクラス】

public class Skill {

	// フィールド変数
	protected String name; // スキル名
	protected int cost; // 使用コスト(MP)

	// コンストラクタ
	public Skill(String skillName, int skillCost) {
		this.name = skillName;
		this.cost = skillCost;
	}

	// スキル名を取得する
	public String getSkillName() {
		return this.name;
	}

	// 使用コスト(MP)を取得する
	public int getSkillCost() {
		return this.cost;
	}

	//「ジョブごと」の魔法を使用する
	public void magic(Player user, Player target) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

}