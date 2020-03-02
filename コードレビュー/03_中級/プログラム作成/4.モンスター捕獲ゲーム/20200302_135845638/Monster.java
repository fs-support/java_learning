package fs03_Intermediate_ProgramCreation;

//【クラスの役割】～ モンスターの各情報（名前、HP、攻撃値、防御値、エンカウント率、捕獲成功率）を管理する

import java.util.Random;

public class Monster {

	private String name;	// 名前
	private int hp;			// HP
	private int power;		// 攻撃値
	private int defense;	// 防御値
	private int encount;	// エンカウント率
	private int capture;	// 捕獲成功率

	// コンストラクタ
	Monster(String name , int hp , int power , int defense , int encount , int capture){
		this.name = name;
		this.hp = hp;
		this.power = power;
		this.defense = defense;
		this.encount = encount;
		this.capture = capture;
	}

	// 各種ステータスを所得
	String Name(){
		return this.name;	// 名前を取得
	}

	int HP(){
		return this.hp;	// HPを取得
	}

	int Power(){
		return this.power;	// 攻撃値を取得
	}

	int Defense(){
		return this.defense;	// 防御値を取得
	}

	int Encount(){
		return this.encount;	// エンカウント率を取得
	}

	int Capture(){
		return this.capture;	// 捕獲成功率を取得
	}


	// 捕獲ポイントを取得（式：(HP+攻撃+防御)*10）
	int Point(){
		int point = (this.hp + this.power + this.defense)*10;
		return point;
	}


	// 捕獲できたかどうかの判定を行う
	// 		[correct]～ 捕獲率の補正値
	//		ture：捕獲成功 / false：捕獲失敗
	Boolean Judgement(int correct){

		Random random = new Random();
		int get = random.nextInt(100)+1;

		if((this.capture+correct)>get){
			return true;
		} else {
			return false;
		}

	}

}
