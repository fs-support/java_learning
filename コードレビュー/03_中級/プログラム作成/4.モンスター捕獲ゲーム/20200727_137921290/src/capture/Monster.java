package capture;

import java.util.Random;

public class Monster
{
	private String name; 	//名前
	private int hp;			//HP
	private int power;		//攻撃
	private int defence;	//防御
	private int encount;	//出現率
	private int capture;	//捕獲率
	static Random rnd = new Random();

	//コンストラクタ(データの設定)
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
	/**
	 * 捕獲ポイントを取得(HP+攻撃+防御)*10
	 * @return　捕獲ポイント
	 */
	int Point()
	{
		int point=(hp+power+defence)*10;
		return point;
	}
	/**
	 * 捕獲判定
	 * @param correct　確率補正
	 * @return　true:成功　false:失敗
	 */
	boolean Judgement(int correct)
	{
		if((capture+correct)>rnd.nextInt(100))
			return true;
		else
			return false;
	}
}