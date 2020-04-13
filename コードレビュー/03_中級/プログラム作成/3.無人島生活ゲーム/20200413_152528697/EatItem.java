package mujinto;

import java.util.Random;

public class EatItem {
	// --------------------------------------------------------
	// フィールド変数
	// --------------------------------------------------------
	private String name; // アイテム名
	private int danger; // 危険度
	private int heelHP; // 食べたときのHP回復量
	private String coroner; // 死因
	private boolean eatDecision; //食べたかどうか

	// コンストラクタ
	EatItem(String name, int danger, int heelHP, String coroner, boolean eatDecision) {
		this.name = name;
		this.danger = danger;
		this.heelHP = heelHP;
		this.coroner = coroner;
		this.eatDecision = eatDecision;
		}

	// --------------------------------------------------------
	// メソッド
	// --------------------------------------------------------
	// アイテム名を取得する
	String Name() {

		return name;
	}
	// 危険度を取得する
	int Danger() {

		return danger;
	}
	// 食べたときのHP回復量を取得する
	int HeelHP() {

		return heelHP;
	}
	// 死因を取得する
	String Coroner() {

		return coroner;
	}
	// 食べることができたかどうかの判定(dangerと乱数の値で判定を行う)
	// 戻り値： true:食べることができた / false:食べることができなかった
	boolean Judgement() {
		Random rand = new Random();
		int num = rand.nextInt(100) + 1;	//(0~99) + 1
		boolean judge = true;

		//numが危険度以下だと食べられない
		if(num <= danger) {
			judge = false;
		}
		return judge;
	}
	//食べもの履歴の取得
	boolean GetEatHistory() {
		return eatDecision;
	}
	//食べ物の履歴変更
	void SetEatHistory(boolean isFlg) {
		eatDecision = isFlg;
	}

}




//class Dog {
//	String name;
//	int ookisa;
//	String iro;
//
//	Dog(String nm)
//	{
//		name = nm;
//	}
//
//	void Run()
//	{
//		System.out.println(name + "は走った");
//	}
//	void naku()
//	{
//		System.out.println("きゃいん");
//	}
//}
//
//
//
//Dog pochi = new Dog("pochi");
//pochi.Run();
//pochi.naku();
//
//Dog tama = new Dog("tama");
//tama.Run();
