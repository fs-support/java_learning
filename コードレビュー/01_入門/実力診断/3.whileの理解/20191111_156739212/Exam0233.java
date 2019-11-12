package Exam0233;

import java.util.Scanner;

public class Exam0233 {

	public static void main(String[] args) {

		// キーボード入力を行うための Scanner の準備
		Scanner stdin = new Scanner(System.in);

		//total（総和）の初期値は0であることを入力
		int total =0;

		//説明文の表示
		System.out.println("整数を入力してください");
		System.out.println("0を入力すると終わります");

		//条件式　ifの条件がくるまで繰り返します！
		while(true)
			{
				//dataはint型整数である
				int data = stdin.nextInt();

				System.out.println("input data : " + data);

				//もし値dataが0であるならばbreak(抜ける)します
				if(data == 0)
				{
					break;
				}
				//抜けた(break)後に今まで入力したtotalを
				total += data;		//total = total + data;の省力
			}

		//totalを出力します
		System.out.println("Total is" + total + ".");

		stdin.close();


		}

	}




//問題	Exam0233
//whileを使用して整数値を入力させ続け、0が入力されるまでの総和を出力するプログラムを作成してください。
//input data : 32
//input data : -15
//input data : 485
//input data : 0
//Total is 502.
