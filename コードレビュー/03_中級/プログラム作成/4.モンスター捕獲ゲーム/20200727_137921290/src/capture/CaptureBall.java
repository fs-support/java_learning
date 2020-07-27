  
package capture;

public class CaptureBall
{
	private String name;	//名前
	private int correct;		//補正値
	private int count;		//所持数
	//コンストラクタ(データの設定)
	CaptureBall(String name,int correct,int count)
	{
		this.name = name;
		this.correct = correct;
		this.count = count;
	}
	String Name()
	{
		return name;
	}
	int Correct()
	{
		return correct;
	}
	int Count()
	{
		return count;
	}
	void Use()//使用する(所持数を1減らす)
	{
		count--;
	}
}