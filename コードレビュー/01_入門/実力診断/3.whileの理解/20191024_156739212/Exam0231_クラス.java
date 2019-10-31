package exam0231_パッケージ;

import java.util.Scanner;

public class Exam0231_クラス {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int data1,data2;

		System.out.println("1つ目の整数を入力してください");
		data1=stdin.nextInt();

		while(true)
		{
			System.out.println("2つ目の整数を入力してください");

			data2 =stdin.nextInt();
			if(data2 == 0)
			{
				System.out.println("input anather data.");
			}
			else
			{
				break;
			}
		}

		System.out.println(data1+ "+" +data2+ "=" +(data1+data2));
		System.out.println(data1+ "-" +data2+ "=" +(data1-data2));
		System.out.println(data1+ "*" +data2+ "=" +(data1*data2));
		System.out.println(data1+ "/" +data2+ "=" +(data1/data2));
		System.out.println(data1+ "%" +data2+ "=" +(data1%data2));


		stdin.close();
	}

}
