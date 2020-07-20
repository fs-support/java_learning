package survival;

import java.util.Random;

public class EatItem {
	private String name;//アイテム名
	private int danger;//危険度
	private int heelHP;//食べた時のHP回復量
	private String coroner;//死因
	private Random rnd = new Random();

	//コンストラクタ(アイテムの情報を設定するメソッド)
	EatItem(String name, int danger, int heelHP, String coroner) {
		this.name = name;
		this.danger = danger;
		this.heelHP = heelHP;
		this.coroner = coroner;
	}

	//アイテム名を取得するメソッド
	String Name() {
		return name;
	}

	//危険度を取得するメソッド
	int Danger() {
		return danger;
	}

	//回復量を取得するメソッド
	int HeelHP() {
		return heelHP;
	}

	//死因を取得するメソッド
	String Coroner() {
		return coroner;
	}

	/**
	 * 食べた時の生死判定
	 * @return　true:成功	false:失敗
	 */
	boolean Judgement() {
		if (danger > rnd.nextInt(100))
			return false;
		else
			return true;
	}
}