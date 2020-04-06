package Exam0503;

import java.util.Scanner;

public class Exam0503 {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		float data1,data2;		//float：少し曖昧な小数

		System.out.println("1つ目の値を入力してください");
		data1=stdin.nextFloat();	//data1を記憶
		System.out.println("input data1 : " + data1);

		System.out.println("2つ目の値を入力してください");
		data2=stdin.nextFloat();	//data２を記憶
		System.out.println("input data2 : " + data2);

		//2つの値の平均値を出す
		float average = average(data1,data2);

		//小数点第4桁までで出力する
		System.out.println(String.format("Average"+"%.4f",average));

		// プログラム終了前の後片付け
		stdin.close();
	}

	/**
	 平均値を返す関数
	 @param data1 1つ目に入力した値
	 @param data2 1つ目に入力した値
	 @return 平均値
	 */


	private static float average(float data1, float data2) {
		float average=(data1 + data2)/2;

		//計算した値を返す
		return average;

	}

}
