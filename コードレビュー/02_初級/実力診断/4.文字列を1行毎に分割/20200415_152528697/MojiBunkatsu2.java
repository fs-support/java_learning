package syokyuhen;

import java.util.ArrayList;

public class MojiBunkatsu2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		ArrayList<String> lines = lineSeparatedString("１行目。２行目。\n３行目。４行目。\n\n５行目。");

		for (String line : lines) { //foreach文：linesの要素がなくなるまで順番に変数lineに格納する
			System.out.println(line);
		}
	}

	public static ArrayList<String> lineSeparatedString(String input) {
		ArrayList<String> lines = new ArrayList<String>();

		int wordCount = input.length();
		int wordNum = 0;
		int beginIndex = 0;

		while (wordNum < wordCount) {
			String word = input.substring(wordNum, wordNum + 1);

			if (word.equals("。") && wordNum < wordCount - 1) {
				String nextWord = input.substring(wordNum + 1, wordNum + 2);
				if (nextWord.equals("\n")) {
					wordNum++;
					lines.add(input.substring(beginIndex, wordNum));
					System.out.println(lines);
					wordNum++;
					beginIndex = wordNum;
				} else {
					wordNum++;
					lines.add(input.substring(beginIndex, wordNum));
					System.out.println(lines);
					
					beginIndex = wordNum;

				}

			} else {
				wordNum++;
			}
		}
		lines.add(input.substring(beginIndex, wordNum));

		return lines;
	}

}
