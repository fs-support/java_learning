package chukyuhen;

import java.util.ArrayList;

public class Ranking {
	ArrayList<Score> array = new ArrayList<Score>();

	//点数を記録する
	public void EntryScore(int inscore, String inname) {
		Score score = new Score();
		score.point = inscore;
		score.name = inname;
		array.add(score);
	}

	//ランキングを出力
	public void PrintRanking() {
		
		int firstSize = array.size();
		int rank = 1; //順位
		while (rank <= firstSize) {
			Score hold1 = array.get(0); //一時的に0番目の要素を保持
			int arraySize = array.size();
			int index = 0;
			for (int i = 1; i < arraySize; i++) {
				Score hold2 = array.get(i);
				if (hold1.point < hold2.point) {
					hold1 = hold2;
					index = i;
				}
			}
			System.out.println(rank + "位：" + hold1.name + " " + hold1.point);
			array.remove(index);
			rank++;
		}

	}

}
