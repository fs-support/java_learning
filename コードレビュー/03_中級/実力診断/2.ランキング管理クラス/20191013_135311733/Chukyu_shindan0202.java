package chukyu_shindan;

public class Chukyu_shindan0202{

	public static void main(String[] args) {
		Ranking02 ranking = new Ranking02();
		ranking.EntryScore(300, "name300");
		ranking.EntryScore(100, "name100");
		ranking.EntryScore(400, "name400");
		ranking.EntryScore(200, "name200");
		ranking.PrintRanking();
	}
}
