package Exam0224;

import java.util.Scanner;

public class Exam0224 {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);

		System.out.println("整数を入力してください");

		// 入力された数字を保持する変数
		int data =stdin.nextInt();

		if(data > 0)
		{
			for(int i =1; i <= data; i++)
			{
				System.out.print(i+" ");
			}
		}

		else
		{
			System.out.println("input number more than 0.");
		}

		stdin.close();
	}

}
