package middle.desertIsland;

import java.util.Random;

public class EatItem {
	private String name; //アイテム名
	private int danger; //危険度
	private int heelHP; //回復量
	private String coroner; //死因
	Random rand = new Random();

	EatItem(Food food) {
		this.name = food.getName();
		this.danger = food.getDanger();
		this.heelHP = food.getHeelHP();
		this.coroner = food.getCoroner();
	}

	String Name() {
		return this.name;
	}

	int Danger() {
		return this.danger;
	}

	int HeelHP() {
		return this.heelHP;
	}

	String Coroner() {
		return this.coroner;
	}

	boolean judgement() {
		if ((rand.nextInt(100) + 1) <= danger) {
			System.out.println(coroner);
			return false; //死亡
		} else {
			return true; //食べることができた
		}
	}
}
