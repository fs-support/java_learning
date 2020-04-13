package chukyuhen;

import java.util.ArrayList;

public class Stack {
	ArrayList<String> array = new ArrayList<String>();
	public void push(String input){
		array.add(input);
	}
	public String pop() {
		String moji = "";
		int index = array.size() - 1;
		moji = array.get(index);
		array.remove(index);
		return moji;
	}

}
