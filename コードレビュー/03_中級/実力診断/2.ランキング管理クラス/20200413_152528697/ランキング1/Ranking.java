package chukyuhen;

import java.util.ArrayList;

public class Ranking {
	ArrayList<Integer> array = new ArrayList<Integer>();

	//点数を記録する
	public void EntryScore(int input) {
		
		array.add(input);
	}

	//ランキングを出力
	public void PrintRanking() {
		int firstSize = array.size();
		int rank = 1; //順位
		while (rank <= firstSize) {
			int hold = array.get(0); //一時的に0番目の要素を保持
			int arraySize = array.size();
			int index = 0;
			for (int i = 1; i < arraySize; i++) {
				
				if (hold < array.get(i)) {
					hold = array.get(i);
					index = i;
				}
			}
			System.out.println(rank + "位：" + hold);
			array.remove(index);
			rank++;
		}

	}

}
