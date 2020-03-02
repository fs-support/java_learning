package fs03_Intermediate_ProgramCreation;

// 【クラスの役割】～ 捕獲玉の各情報（種類、捕獲率補正値、所持数）を管理する

public class CaptureBall {

	private String name;	// 種類
	private int correct;	// 捕獲率の補正値
	private int count;		// 所持数

	// コンストラクタ
	CaptureBall(String name , int correct, int count){
		this.name = name;
		this.correct = correct;
		this.count = count;
	}

	// 各種ステータスを取得
	String Name(){
		return this.name;		// 名称を取得
	}

	int Correct(){
		return this.correct;	// 捕獲率補正値を取得
	}

	int Count(){
		return this.count;		// 所持数を取得
	}


	// 捕獲玉を１つ使用する (所持数を１つ減らす)
	void Use(){
		this.count-=1;
	}

}
