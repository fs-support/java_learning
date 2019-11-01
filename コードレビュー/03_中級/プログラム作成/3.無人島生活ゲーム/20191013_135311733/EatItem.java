package chukyu_sakusei;

import java.util.Random;

public class EatItem {
	// --------------------------------------------------------
	// フィールド変数
	// --------------------------------------------------------
	private String name; // アイテム名
	private int danger; // 危険度
	private int heelHP; // 食べたときのHP回復量
	private String coroner; // 死因
	private Random rnd = new Random();
	// コンストラクタ
	EatItem(String name, int danger, int heelHP, String coroner) {
		this.name = name;
		this.danger = danger;
		this.heelHP = heelHP;
		this.coroner = coroner;
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
		if(danger > rnd.nextInt(100)) {
			return false;
		}
		else {
			return true;
		}
	}
}