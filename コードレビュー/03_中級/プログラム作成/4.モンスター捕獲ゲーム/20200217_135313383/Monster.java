package capture;

import java.util.Random;

public class Monster {

	private String name;
	private int hp;
	private int power;
	private int defense;
	private int encount;
	private int capture;
	static Random rnd = new Random();

	Monster(String name,int hp,int power,int defense,int encount,int capture){
		this.name = name;
		this.hp = hp;
		this.power = power;
		this.defense = defense;
		this.encount = encount;
		this.capture = capture;
	}

	String Name() {
		return name;
	}

	int HP() {
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

	/*@return*/
	int Point() {
		int point = (hp+power+defense)*10;
		return point;
	}

	/*
	 @param correct
	 @return true*/
	Boolean Judgement(int correct) {
		if((capture+correct) > rnd.nextInt(100))
			return true;
		else
			return false;
	}
}
