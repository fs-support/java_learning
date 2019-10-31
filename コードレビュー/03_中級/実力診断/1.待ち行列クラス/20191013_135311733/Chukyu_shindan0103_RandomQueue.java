package chukyu_shindan;

import java.util.ArrayList;
import java.util.Random;

class RandomQueue{

	private static ArrayList<String> lines = new ArrayList<String>();

	public  void push(String str) {
		lines.add(str);
	}

	public String pop() {
		Random random = new Random();

		int rdm = random.nextInt(lines.size());
		String str = lines.get(rdm);
		lines.remove(rdm);

		return str;
	}
}