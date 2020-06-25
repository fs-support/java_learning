package project2;

import java.util.Random;
import java.util.Scanner;

public class tranpategame {
	public static void main(String[]args)  {
		Scanner stdin = new Scanner(System.in);
		String[] mark = {"ハート","ダイヤ","スペード","クローバー"};
		String[] namber = {"A","1","2","3","4","5","6","7","8","9","10","J","Q","K"};
		Random random = new Random();


		int markrandom = random.nextInt(4);
		int namberrandom = random.nextInt(13);


		System.out.println("トランプを選んだよ\nトランプの図柄を当ててね\n");
		System.out.print("0:ハート\n1:ダイヤ\n2:スペード\n3:クローバー\n");

		while(true) {
			System.out.print("どれだと思う：");
			int markA = stdin.nextInt();

				if(markA != markrandom) {
					System.out.println("\n残念！" + mark[markA] + "じゃないよ");
				}else {
					System.out.println("\n正解！図柄は" + mark[markrandom] + "だよ\n");
					break;
				}
		}


		System.out.println("次は数字を当ててね\n");

		while(true) {
			System.out.print("どれだと思う：");
			int markB = stdin.nextInt();

				if(markB -1 != namberrandom) {
					System.out.println("\n残念！" + namber[markB] + "じゃないよ");
				}else {
					System.out.print("\n正解！" + mark[markrandom] + "の" + namber[namberrandom] + "だよ");
					break;
				}
		}
		stdin.close();

	}

}
