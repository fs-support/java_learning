package k;

public class CaptureBall {

	private String name; // 名称
	private int correct; // 捕獲率の補正値
	private int count; // 所持数
	CaptureBall(String name, int correct, int count)
	{
		this.name=name;
		this.correct=correct;
		this.count=count;

	}
	// 各種ステータスを取得
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
	/**
	* 捕獲玉を１つ使用する(所持数を１つ減らす)
	*/
	//
	void Use()
	{
		count=count-1;
	}
	}