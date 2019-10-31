package chukyu_shindan;

import java.util.ArrayList;

class Queue{

	private static ArrayList<String> lines = new ArrayList<String>();

	public  void push(String str) {
		lines.add(str);
	}

	public String pop() {

		String str = lines.get(0);
		lines.remove(0);

		return str;
	}
}