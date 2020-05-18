package janken;

import java.util.Random;
import java.util.Scanner;

public class Program_3 {
	public static void main(String[] args) {

		Random random = new Random();

		System.out.println("じゃんけん勝負");
		System.out.println("グーチョキパーを数字で入力してね");
		System.out.println("0:グー");
		System.out.println("1:チョキ");
		System.out.println("2:パー");
		System.out.println();

		String[] names = {"グー","チョキ","パー"};

		var stdin = new Scanner(System.in);

		Boolean isFirst = true;

		while(true) {

			if(isFirst == true) {

				System.out.print("最初はぐー、じゃんっけんっ：");

			}else {

				System.out.print("あいこでっ：");
			}

			int player = stdin.nextInt();
			
			System.out.println(player);

			int com = random.nextInt(3);

			System.out.println(names[com] + "と" + names[player] + "で...");

			if(com == player) {

				System.out.println("あいこだよ！");
			}else if((com == 0 && player == 1)|| 
					(com == 1 && player == 2) || 
					(com == 2 && player == 0)){ 

				System.out.println("君の負け！");

				break;
			}else {

				System.out.println("君の勝ち！");

				break;
			}

			System.out.println();

			isFirst = false;
		}

		stdin.close();
	}

}