package poke;

import java.util.Random;

public class Monster {

	private String name;
	private int hp;
	private int power;
	private int defence;
	private int encount;
	private int capture;
	static Random rnd = new Random();

	Monster(String name,int hp,int power,int defence,int encount,int capture)
	{
		this.name = name;
		this.hp = hp;
		this.defence = defence;
		this.encount = encount;
		this.capture = capture;
	}
	String Name()
	{
		return name;
	}
	int HP()
	{
		return hp;
	}
	int Power()
	{
		return power;
	}
	int Defence()
	{
		return defence;
	}
	int Encount()
	{
		return encount;
	}
	int Capture()
	{
		return capture;
	}

	int Point()
	{
		int point=(hp+power+defence)*10;
		return point;
	}

	boolean Judgement(int correct)
	{
		if((capture+correct)>rnd.nextInt(100))
			return true;
		else
			return false;
	}
}
