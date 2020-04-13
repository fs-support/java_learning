package syokyuhen;

import java.util.ArrayList;

public class MojiBunkatsu1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		ArrayList<String> lines = lineSeparatedString("１行目。\n２行目。\n３行目。\n４行目。\n\n５行目\n6行目");

		for (String line : lines) {
			System.out.println(line);
		}
	}

	public static ArrayList<String> lineSeparatedString(String input) {
		ArrayList<String> lines = new ArrayList<String>();

		int wordCount = input.length();
		int wordNum = 0;
		int beginIndex = 0;
		//String wordEnd = input.substring(wordCount ,wordCount+1);

		while (wordNum < wordCount) {
			String word = input.substring(wordNum, wordNum + 1);
			if (word.equals("\n")) {
				lines.add(input.substring(beginIndex , wordNum));
				wordNum++;
				beginIndex = wordNum;
			} else {
				wordNum++;
			}
		}
		lines.add(input.substring(beginIndex , wordNum));

		return lines;
	}

}
