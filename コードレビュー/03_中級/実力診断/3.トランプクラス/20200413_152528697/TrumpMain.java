package chukyuhen;

public class TrumpMain {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Trump trump = new Trump();
		trump.Reset();
		trump.Shuffle();
		Card card1 = trump.Draw();
		Card card2 = trump.Draw();
		
		int Score = Calc(card1, card2);

		System.out.println(card1.m_number);
		System.out.println(card2.m_number);
		System.out.println(Score);

	}

	public static int Calc(Card card1, Card card2) {
		if(card1.m_number > 10) {
			card1.m_number = 10;
		}
		if(card2.m_number > 10) {
			card2.m_number = 10;
		}

		int total = card1.m_number + card2.m_number;
		if(card1.m_number == 1 || card2.m_number == 1) {
			total = total + 10;
		}
		
		return total;
	}

}
