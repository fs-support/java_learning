package Exam0204;

import java.util.Scanner;	//読み込むやつ

public class Exam0204_class {

	public static void main(String[] args) {

		// 入力した値を読み込む
		Scanner stdin = new Scanner(System.in);
		int num1 = 0;
		num1 = stdin.nextInt();

		//正か不か判断
		if(num1 > 0) {
			System.out.println("input number : " +num1);
			System.out.println();
			System.out.println(num1+ " is Positive.");
		}else if (num1 < 0){
			System.out.println("input number : " +num1);
			System.out.println();
			System.out.println(num1 + " is Negative.");
		}else{
			System.out.println("input number : " + num1);


		}


	}

}
