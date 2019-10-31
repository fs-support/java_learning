package chukyu_shindan;

public class Chukyu_shindan0201{

	public static void main(String[] args) {
		Ranking ranking = new Ranking();
		
		ranking.EntryScore(300);
		ranking.EntryScore(100);
		ranking.EntryScore(400);
		ranking.EntryScore(200);
		ranking.PrintRanking();
	}
}
