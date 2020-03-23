package jp.co.FStest08;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Beginner_3_2 {
	public static void main(String[] args) {
		String str = "A5B10CD9E2F5G";
		Pattern p1 = Pattern.compile("[A-Z]{1}[0-9]*");//0から9、A-Zをセットで正規表現をしている。
		Matcher Matcher = p1.matcher(str);//チェックしたい数値と正規表現とのマッチングを行う。


		while (Matcher.find()) {//findはfalseかtrueしか返さない。
											//文字列が続く限りfalseを返すため、whileで最後(G)まで繰り返す。
			String group = Matcher.group();//groupはA5などの文字列を保持している。
			System.out.println(Matcher.group());
															//A5ということはわかったので文字列と数値を分解する
			if (group.length() > 1) {//文字列が１つ以上あるかどうか判定するためのif文。
				String stringOnly = group.substring(0, 1);//A5の場合、Aだけを抜き出すための処理。
				String n1 = group.substring(1);//A5の場合、5だけを抜き出すための処理。
				int numberOnly = Integer.parseInt(n1);//5がまだ文字列のため数値に変換する処理。
				for (int i = 0; i < numberOnly; i = i + 1) {//5という数値がわかったのでその回数までfor文で繰り返す。
					System.out.print(stringOnly);//5回分、Aを表示する。
				}
			} else {//文字列が1つ未満(CやGなど)の場合に入る処理。
				System.out.print(group);//そのままgroupの値を表示。
			}
		}
	}
}
