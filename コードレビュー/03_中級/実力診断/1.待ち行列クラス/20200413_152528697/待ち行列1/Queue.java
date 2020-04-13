package chukyuhen;

import java.util.ArrayList;

public class Queue {
	ArrayList<String> array = new ArrayList<String>();
	public void push(String input){
		array.add(input);
	}
	
	public String pop() {
		String moji = "";
		moji = array.get(0);
		array.remove(0);
		return moji;
	}

}
