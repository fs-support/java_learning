package aaa;

import java.util.Scanner;	//	読み込むための設定

public class aaa {

	//入力するためのメゾッド
	//var stdin = new Scanner(System.in);
	//stdin.nextlnt();	//小数値を読み込みたい場合はnextDouble()、文字列はnext()

	public static void main(String[] args) {

		//入力した値を読み込む
		Scanner stdin = new Scanner(System.in);
		int num1 = 0;
		num1 =stdin.nextInt();
		int num2 = stdin.nextInt();

		//↓入力した値の計算
		int tasu = num1 + num2;
		int hiku = num1 - num2;
		int kakeru = num1 * num2;
		int waru = num1 / num2;
		int amari = num1 % num2;

		//結果を出力
		System.out.println("input data1 : "+num1);
		System.out.println("input data2 : "+num2);
		System.out.println(num1+" + "+num2+" = "+tasu);
		System.out.println(num1+" - "+num2+" = "+hiku);
		System.out.println(num1+" * "+num2+" = "+kakeru);
		System.out.println(num1+" - "+num2+" = "+waru);
		System.out.println(num1+" / "+num2+" = "+amari);

	}

}
