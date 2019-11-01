package chukyu_shindan;

public class Chukyu_shindan0301{

	public static void main(String[] args) {
		Trump trump = new Trump();

		trump.Reset();

		trump.Shuffle();

		int get_card1 = trump.Draw();
		int get_card2 = trump.Draw();

		System.out.println("合計：" + trump.Bj(get_card1, get_card2));
	}
}
