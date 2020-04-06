package jp.co.FStest08;

import java.util.ArrayList;
import java.util.List;

public class Beginner_4 {
	public static void main(String[] args) {
		ArrayList<String> lines = lineSeparatedString("１行目。¥n２行目。¥n３行目。¥n４行目。¥n¥n５行目¥n");
		for (String line : lines) {
			System.out.println(line);
		}
	}

	static ArrayList<String> lineSeparatedString(String string) {
		//配列からstringに入れて、newしたArrayListに一行で入れてっている。
		return new ArrayList<String>(List.of(string.split("¥n")));
	}
}
