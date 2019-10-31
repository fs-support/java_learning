package chukyu_shindan;

import java.util.ArrayList;

class Queue04{

	private static ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<Integer> lines_pri = new ArrayList<Integer>();

	public  void push(String str,int pri) {
		lines.add(str);
		lines_pri.add(pri);
	}

	public String pop() {

		int pri_high_no = 0;
		int pri_high = 0;

		for(int i = 0;i < lines_pri.size();i++) {
			if(pri_high < lines_pri.get(i)) {
				pri_high = lines_pri.get(i);
				pri_high_no = i;
			}
		}

		String str = lines.get(pri_high_no);
		lines.remove(pri_high_no);
		lines_pri.remove(pri_high_no);

		return str;
	}
}