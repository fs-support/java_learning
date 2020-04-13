package chukyuhen;

import java.util.ArrayList;
import java.util.Random;

public class RandomQueue {
	ArrayList<String> array = new ArrayList<String>();
	Random rand = new Random();
	public void push(String input){
		array.add(input);
	}
	public String pop() {
		String moji = "";
		int index = rand.nextInt(array.size());
		moji = array.get(index);
		array.remove(index);
		return moji;
	}
}
