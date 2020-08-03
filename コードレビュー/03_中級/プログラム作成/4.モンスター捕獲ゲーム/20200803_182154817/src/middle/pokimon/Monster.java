package middle.pokimon;

import java.util.Random;

public class Monster {
	private String name;
	private int hp;
	private int power;
	private int defense;
	private int encount;
	private int capture;
	Random rnd = new Random();

	Monster(MonsterKind m) {
		this.name = m.getName();
		this.hp = m.getHp();
		this.power = m.getPower();
		this.defense = m.getDefense();
		this.encount = m.getEncount();
		this.capture = m.getCapture();
	}

	String Name() {
		return this.name;
	}

	int HP() {
		return this.hp;
	}

	int Power() {
		return this.power;
	}

	int Defense() {
		return this.defense;
	}

	int Encount() {
		return this.encount;
	}

	int Capture() {
		return capture;
	}

	/**
	 * 捕獲ポイント取得(（HP＋攻撃＋防御) * 10)
	 * @return 捕獲ポイント
	 */
	int Point() {
		int p = (hp + power + defense) * 10;
		return p;
	}

	/**
	 * 捕獲判定
	 * @param correct 補正値
	 * @return true:捕獲成功　/ false:捕獲失敗
	 */
	boolean Judgement(int correct) {
		int kakuritu = capture + correct;
		if (kakuritu > 100) {
			kakuritu = 100;
		}
		if (rnd.nextInt(101) <= kakuritu) {
			System.out.println("やったー！捕獲成功!!");
			return true;
		} else {
			System.out.println("捕獲失敗・・・もう一度挑戦！！");
			return false;
		}
	}
}
