package chukyuhen;

import java.util.ArrayList;

public class Queue {
	ArrayList<Info> array = new ArrayList<Info>();

	public void push(String input, int pri) {
		Info info = new Info();
		info.str = input;
		info.pri = pri;
		array.add(info);
	}

	public String pop() {
		String output;
		Info moji;
		int size = array.size();
		moji = array.get(0);
		for (int a = 1; a < size; a++) {
			Info j = array.get(a);
			if (moji.pri < j.pri) {
				moji = j;
			}
		}
		output = moji.str;
		array.remove(moji);
		return output;
	}

}
