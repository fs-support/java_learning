package syokyuhen;

import java.util.ArrayList;

public class MojiBunkatsu4 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		ArrayList<String> lines = lineSeparatedString("このプログラムは、句読点を行頭禁則処理するサンプル。\n最後の行です", 8);

		for (String line : lines) { //foreach文：linesの要素がなくなるまで順番に変数lineに格納する
			System.out.println(line);
		}
	}

	public static ArrayList<String> lineSeparatedString(String input, int Num) {
		ArrayList<String> lines = new ArrayList<String>();

		int wordCount = input.length();	//文字列の長さ
		int wordNum = 0;		//今見ている文字
		int beginIndex = 0;	//抜き出す部分の開始位置
		int indentNum = Num;	//何文字で改行するか
		int indentCount = 0;	//改行するためのカウンタ

		while (wordNum < wordCount) {
			String word = input.substring(wordNum, wordNum + 1);
			if (indentCount == indentNum) {
				if(word.contentEquals("。") || word.contentEquals("、")) {
					wordNum++;
					lines.add(input.substring(beginIndex,wordNum));
				}else {
					lines.add(input.substring(beginIndex,wordNum));
				}
				beginIndex = wordNum;
				wordNum++;
				indentCount = 1;
				
				//System.out.println(lines);

			} else {
				if (word.equals("。") && wordNum < wordCount - 1) {
					String nextWord = input.substring(wordNum + 1, wordNum + 2);
					if (nextWord.equals("\n")) {
						wordNum++;
						lines.add(input.substring(beginIndex, wordNum));
						//System.out.println(lines);
						wordNum++;
						beginIndex = wordNum;
						indentCount = 1;
					} else {
						wordNum++;
						lines.add(input.substring(beginIndex, wordNum));
						//System.out.println(lines);

						beginIndex = wordNum;
						indentCount = 1;
					}

				}else if(word.equals("\n")){
					wordNum++;
					indentCount = 0;
					
				} else {
					wordNum++;
					indentCount++;
				}

			}

		}
		lines.add(input.substring(beginIndex, wordNum));

		return lines;
	}

}
