package sv;

import java.util.Random;

public class EatItem {
	private String name;//アイテム名
	private int danger;//危険度
	private int heelHP;//食べた時のHP回復量
	private String coroner;//死因
	private Random rnd = new Random();//コンストラクタ(アイテムの情報を設定するメソッド)
	EatItem(String name,int danger,int heelHP,String coroner)
	{
		this.name=name;
		this.danger=danger;
		this.heelHP=heelHP;
		this.coroner=coroner;
	}

	String Name()
	{
		return name;
	}

	int Danger()
	{
		return danger;
	}

	int HeelHP()
	{
		return heelHP;
	}

	String Coroner()
	{
		return coroner;
	}

	boolean Judgement()
	{
		if(danger>rnd.nextInt(100))
			return false;
		else
			return true;
	}
}
