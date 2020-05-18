package Exam0505;

import java.util.Scanner;

public class Exam0505 {


	public static void main(String[] args) {		//Main()では、文字の入力、判定結果の出力を行う
		var stdin = new Scanner(System.in);
		char character;	//文字型「character」の宣言

		while (true) {		//trueの時は以下を繰り返す(＃25まで)
			System.out.printf("input character : ");

			String s= stdin.nextLine();
			character = s.charAt(0);
//				「character = stdin.nextLine().charAt(0);」とまとめてもOK

			String message = letter_change(character);
			if (message == null) {		//変数messageがnullならばbreakする
				break;
			}

			System.out.println(message);
		}

		// 終了処理
		stdin.close();
	}

	/**
	 文字型の値を受け取り、その値を加工して返す関数
	 @param character 入力した値
	 @return 加工した値
	 */

	public static String letter_change(char character) {		//letter_change() では大文字↔小文字へ変換を行う
		String message = "'" + character + "' ";

		// 入力した値がアルファベットか判定を行う
		// true：アルファベット、false：アルファベット以外
		if (Character.isAlphabetic(character) == true) {		//もし入力した値がisAlphabetic(アルファベット)なら以下実施

			// 入力した値が大文字か小文字か判定を行う
			// true：大文字、false：小文字
			if (Character.isUpperCase(character) == true) {

				// 大文字の場合は小文字へ
				String s = String.valueOf(Character.toLowerCase(character)); //toLowerCase：文字を小文字にする
				return message + "to lower -> '" + s + "'";
			} else {
				// 小文字の場合は大文字に(文字コードを-32)
				String s = String.valueOf(Character.toUpperCase(character)); //toUpperCase：文字を大文字にする
				return message + "to upper -> '" + s + "'";
			}

		} else if (character == '.') {
			return null;	//:ならnullで返す

		} else {
			return message + "no changed";	//文字以外ならば"no changed"で返す
		}
	}
}