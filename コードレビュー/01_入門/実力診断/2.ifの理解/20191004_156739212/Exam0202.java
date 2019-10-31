package Exam0202;

import java.util.Scanner; //読み込み設定

public class Exam0202 {

	public static void main(String[] args) {

		//２つの文字を入力させ読みこむ
		Scanner stdin = new Scanner(System.in);
		int num1 = 0;
		num1 = stdin.nextInt();
		int num2 = stdin.nextInt();

		//値を見る
		int a = num1;
		int b = num2;

		if (a == b) { //値が等しい場合
			System.out.println("input number1 : " + a);
			System.out.println("input number2 : " + b);
			System.out.println();
			System.out.println("number1(" + a + ") is equal to number2(" + b + ").");

		} else { //値が等しくない場合
			System.out.println("input number1 : " + a);
			System.out.println("input number2 : " + b);

		}

	}

}
//numをそのまま活用したほうがよい（by中川さん）