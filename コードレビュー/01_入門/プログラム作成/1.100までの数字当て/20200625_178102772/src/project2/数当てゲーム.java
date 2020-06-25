package project2;

import java.util.Random;
import java.util.Scanner;



public class kazuategame {

	public static void main(String[]args) {
		Scanner stdin = new Scanner(System.in);
		Random random = new Random();


		System.out.println("数字を当ててみてね\n答えれるのは5回までだよ。\n");
		int number = random.nextInt(99);

		for(int i=1; i<6; i++) {

		System.out.print(i + "回目：");
		int no = stdin.nextInt();

			if(no < number) {
				System.out.println("もっと大きい数字だよ");
			}else if(no > number) {
				System.out.println("もっと小さい数字だよ");
			}else{
				System.out.println("凄い！"+i+"回目であてちゃった！");
				i = 6;
			}

		}
		System.out.println("");
		System.out.println("残念！！正解は"+number+"でした！");
		stdin.close();

	}
}
