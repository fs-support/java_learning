package chukyu_shindan;

import java.util.ArrayList;

class Ranking{

	private static ArrayList<Integer> lines_score = new ArrayList<Integer>();

	public  void EntryScore(int pri) {
		lines_score.add(pri);
	}

	public void PrintRanking() {
		int rank_cnt = 1;

		while(lines_score.size() > 0) {
			int pri_high_score = 0;
			int pri_high_no = 0;
			for(int i = 0;i < lines_score.size();i++) {
				if(pri_high_score < lines_score.get(i)) {
					pri_high_score = lines_score.get(i);
					pri_high_no = i;
				}
			}
			System.out.println(rank_cnt + "位:" + pri_high_score);
			lines_score.remove(pri_high_no);
			rank_cnt++;
		}
	}
}