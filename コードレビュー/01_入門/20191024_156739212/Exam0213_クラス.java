
package Exam0213_パッケージ;

	import java.util.Scanner;

	public class Exam0213_クラス {

		public static void main(String[] args) {

			// キーボード入力を行うための Scanner の準備
			Scanner stdin = new Scanner(System.in);

			System.out.println("20以上の整数を入力してください");

			// 入力された数字を保持する変数
			int number = stdin.nextInt();

			if (number >= 1)

			{
				// 下1桁の数字を保持する変数
				int lastNumber = number % 10;

				if(10 <= number && number <= 19 )
				{
					// 10~19の場合、「th」をつけて表示する
					System.out.println(number + "th");
				}
				else if(lastNumber == 1)
				{
					// 下一桁が1の場合、「st」をつけて表示する
					System.out.println(number + "st");
				}
				else if (lastNumber == 2)
				{
					// 下一桁が2の場合、「nd」をつけて表示する
					System.out.println(number + "nd");
				}
				else if (lastNumber == 3)
				{
					// 下一桁が3の場合、「rd」をつけて表示する
					System.out.println(number + "rd");
				}
				else
				{
					// 下一桁が上記以外の場合、「th」をつけて表示する
					System.out.println(number + "th");
				}
			}
			else
			{
				System.out.println("input number more than 1.");
			}

			// プログラム終了前の後片付け
			stdin.close();
		}
	}


	//課題①：現状で10~19を入力すると「"input number more than 1."」が出力されてしまう。
	//			→全て「ｔｈ」をつけて表示するようにしたい。
	//課題②：1~9の値を入力すると「"input number more than 1."」が出力されてしまう。
	//			→条件に合うように出力したい。