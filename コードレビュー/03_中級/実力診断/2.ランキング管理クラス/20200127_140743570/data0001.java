package humi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class data0001 {

	static public class Ranking {
		static ArrayList<Score> sukoa = new ArrayList<Score>();

		void EntryScore(int point, String name) {

			sukoa.add(new Score(point, name));
		}

		void ProntRanking() {

			Collections.sort(sukoa, new SyainComparator());
			Iterator<Score> it = sukoa.iterator();
			int I = 1;
			while (it.hasNext()) {
				Score data = it.next();
				System.out.println(I + "位" + data.getname() + data.getpoint());
				I++;
			}
		}
	}

	static public class Score {

		private int point; //社員番号
		private String name; //社員名

		//コンストラクタ
		public Score(int point, String name) {
			this.point = point;
			this.name = name;
		}

		public int getpoint() {
			return this.point;
		}

		public String getname() {
			return this.name;
		}

	}

	static public class SyainComparator implements Comparator<Score> {

		//比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
		public int compare(Score a, Score b) {
			int no1 = a.getpoint();
			int no2 = b.getpoint();

			//こうするとpointの昇順でソートされる
			if (no1 < no2) {
				return 1;

			} else if (no1 == no2) {
				return 0;

			} else {
				return -1;

			}
		}

	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Ranking rankin = new Ranking();
		rankin.EntryScore(300, "mame300");
		rankin.EntryScore(100, "mame100");
		rankin.EntryScore(400, "mame400");
		rankin.EntryScore(200, "mame200");
		rankin.ProntRanking();
	}

}



//name300 300
