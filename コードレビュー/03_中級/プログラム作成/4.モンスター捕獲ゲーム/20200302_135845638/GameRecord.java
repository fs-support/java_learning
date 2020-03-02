package fs03_Intermediate_ProgramCreation;

//【クラスの役割】～ 捕獲記録（モンスター種類、獲得ポイント）を管理する

public class GameRecord {

	private String monsterName;
	private int getPoint;

	// コンストラクタ
	GameRecord(String name , int point){
		this.monsterName = name;
		this.getPoint = point;
	}

	String recordName(){
		return this.monsterName;
	}

	int recordPoint(){
		return this.getPoint;
	}
}
