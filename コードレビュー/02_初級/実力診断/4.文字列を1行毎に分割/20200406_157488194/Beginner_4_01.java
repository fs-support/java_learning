package jp.co.FStest08;

public class Beginner_4_01 {
	public static void main(String[] args) {
		String lines = "１行目。\n２行目。\n３行目。\n４行目。\n\n５行目\n";

		String[] fruit = lines.split("\n", 0);
		for (int i = 0; i < fruit.length; i++) {
			System.out.println(fruit[i]);
		}
	}
}
