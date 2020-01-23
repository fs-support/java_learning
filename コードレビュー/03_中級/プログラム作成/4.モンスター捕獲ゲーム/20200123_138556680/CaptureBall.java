package poke;

public class CaptureBall {
	private String name;
	private int correct;
	private int count;

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
	void Use()
	{
		count--;
	}
}
