package chukyu_shindan;

import java.util.ArrayList;

class Stack{

	private static ArrayList<String> lines = new ArrayList<String>();

	public  void push(String str) {
		lines.add(str);
	}

	public String pop() {

		String str = lines.get(lines.size() - 1);
		lines.remove(lines.size() - 1);

		return str;
	}
}