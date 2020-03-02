package fs03_Intermediate_ProgramCreation;

public class SurvivalHistory {

	private int hp;			// HP履歴
	private String name;	// 食料履歴
	private int danger;		// 危険度履歴
	private String meal;	// 食事履歴

	// コンストラクタ
	SurvivalHistory(int hp , String name , int danger , String meal){
		this.hp = hp;
		this.name = name;
		this.danger = danger;
		this.meal = meal;
	}

	// フィールド毎の値を取り出す各メソッド (privateにしているのでメソッドが無いと取り出せない)
	int recordHP(){
		return this.hp;
	}

	String recordName(){
		return this.name;
	}

	int recordDanger(){
		return this.danger;
	}

	String recordMeal(){
		return this.meal;
	}

}
