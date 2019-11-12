package Exam0226;

import java.util.Scanner;

public class Exam0226 {

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		int data,pow;

		System.out.println("数値を入力してください");
		data=stdin.nextInt();

		System.out.println("「べき乗」となる値を入力してください");
		pow=stdin.nextInt();

		//べき乗が負ではないとき
		if(pow>0)
		{
			int Total =data;

			for(int i=1; i<pow; i++)
			{
				Total = data * Total;
			}

			System.out.println(data + "^" + pow + "=" + Total);

		}
		else if(pow==0)
		{
			System.out.println(data +"^"+pow+"="+"1");
		}
		else
		{
			System.out.print("input power more than -1.");

	}
		stdin.close();
	}

}
