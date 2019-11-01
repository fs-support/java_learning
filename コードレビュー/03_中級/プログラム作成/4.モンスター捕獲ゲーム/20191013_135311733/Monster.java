package chukyu_sakusei;

import java.util.Random;

public class Monster {
	private String name; // 名前
	private int hp; // HP
	private int power; // 攻撃
	private int defense; // 防御
	private int encount; // 発生率
	private int capture; // 捕獲率
	private Random rnd = new Random();

	//コンストラクタ
	Monster(String name, int hp, int power, int defense, int encount, int capture) {
		this.name = name;
		this.hp = hp;
		this.power = power;
		this.defense = defense;
		this.encount = encount;
		this.capture = capture;
	}

	//各種ステータスを取得
	String Name() {
		return name;
	}
	int Hp() {
		return hp;
	}
	int Power() {
		return power;
	}
	int Defense() {
		return defense;
	}
	int Encount() {
		return encount;
	}
	int Capture() {
		return capture;
	}

	/**
	 *捕獲ポイントを取得（式：(HP+攻撃+防御)*10）
	 * @return 捕獲ポイント
	 */
	//
	int Point() {
		return (hp + power + defense) * 10;
	}

	/**
	 * 捕獲できたかどうかの判定を行う
	 * @param correct 捕獲率の補正値
	 * @return true: 捕獲成功 / false: 捕獲失敗
	 */
	Boolean Judgement(int correct) {
		int capcorre = capture + correct;
		if(capcorre > 100) {
			capcorre = 100;
		}

		System.out.println("捕獲成功確率" + capcorre + "%");

		if(capcorre  > rnd.nextInt(100)) {
			return true;
		}
		else {
			return false;
		}
	}
}