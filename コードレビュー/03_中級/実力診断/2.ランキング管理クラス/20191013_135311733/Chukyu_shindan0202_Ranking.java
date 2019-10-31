package chukyu_shindan;

import java.util.ArrayList;

class Ranking02{

	private static ArrayList<Integer> lines_score = new ArrayList<Integer>();
	private static ArrayList<String> lines_name = new ArrayList<String>();

	public  void EntryScore(int point,String name) {
		lines_score.add(point);
		lines_name.add(name);
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
			System.out.println(rank_cnt + "位:" + lines_name.get(pri_high_no) + " " + pri_high_score);
			lines_score.remove(pri_high_no);
			lines_name.remove(pri_high_no);
			rank_cnt++;
		}
	}
}