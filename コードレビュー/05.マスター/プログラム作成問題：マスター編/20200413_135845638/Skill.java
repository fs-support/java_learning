package fs05_Master_ProgramCreation;

//【魔法、特技などのスキルを管理するクラス】

public class Skill {

	// フィールド変数
	protected String name;	// スキル名
	protected int cost;		// 使用コスト(MP)

	// コンストラクタ
	public Skill(String name, int cost){
		this.name = name;
		this.cost = cost;
	}

	// スキル名を取得する
	public String GetSkillName(){
		return this.name;
	}

	// 使用コスト(MP)を取得する
	public int GetSkillCost(){
		return this.cost;
	}

	//「ジョブごと」の魔法を使用する
	public void Magic(Player User , Player Target){
		// ジョブごとにオーバーライドして処理を記述してください
	}

}
