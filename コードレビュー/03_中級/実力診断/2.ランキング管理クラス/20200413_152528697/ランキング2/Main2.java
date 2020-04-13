package chukyuhen;

public class Main2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Ranking ranking = new Ranking(); 
		ranking.EntryScore(300, "name300"); 
		ranking.EntryScore(100, "name100"); 
		ranking.EntryScore(400, "name400"); 
		ranking.EntryScore(200, "name200"); 
		ranking.PrintRanking();

	}

}
