package jp.co.FStest08;

import java.util.ArrayList;

public class Beginner_4_02 {
	public static void main(String[] args) {

		ArrayList<String> lines = lineSeparatedString("１行目。２行目。¥n３行目。４行目。¥n¥n５行目。");

		for (String line : lines) {
			System.out.println(line);
		}
	}

	static ArrayList<String> lineSeparatedString(String string) {
		String[] splitStr = string.split("¥n", 0);//splitを使って「\n」の部分から文字列を分割する。
		ArrayList<String> originalString = new ArrayList<>();//split後の分割された文字列を入れるリストを作成。
		ArrayList<String> changedString = new ArrayList<>();//後々、「。」毎に分割した文字列を入れるためのリストを作成。
		for (String str : splitStr) {//拡張for文
			originalString.add(str);//「originalString」というリストにaddで足していく。
		}
		for (String n : originalString) {
			if (n.indexOf("。") >= 0) {//「。」がない場合になにも処理がされず改行もされなかったので「。」がない場合の処理をif文でいれる。
				while (n.indexOf("。") >= 0) {//「。」が二つ以上あった場合にさらに分割する処理を入れるためにwhileで分岐を作る。
												//「。」が見つからなければindexofは-1で返すのでwhileを抜け出せる。
					int e = n.indexOf("。");//変数eに「。」の位置を入れる。
					String Punctuation = n.substring(0, e + 1);//1周目だと「1行目。」だけを変数Punctuationに入れる。
					changedString.add(Punctuation);//リストに「1行目。」だけを入れる。
					n = n.substring(e + 1);//nの情報をindexofで見つけた「。」以降の文字列のみに更新する。
				}
			} else {
				changedString.add(n);//「。」がない場合の文字列をリストに入れる。
			}
		}
		return changedString;
	}
}
