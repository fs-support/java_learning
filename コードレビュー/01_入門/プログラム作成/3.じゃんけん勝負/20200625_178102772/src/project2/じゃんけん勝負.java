package project2;

import java.util.Random;
import java.util.Scanner;

public class jankengame {

	public static void main(String[]args) {
		Scanner stdin = new Scanner(System.in);
		Random random = new Random();
		String[] names = {"ぐー","ちょき","ぱー"};




		System.out.print("じゃんけん勝負\nグーチョキパーを数字で入力してね\n");
		System.out.println("");
		System.out.print("0:グー\n1:チョキ\n2:パー\n");
		System.out.println("");



		Boolean first = true;

		while(true) {

			if(first == true) {

				System.out.print("最初はぐー、じゃんけん：");

			}else{
				System.out.print("あいこでっ：");

			}

			int player = stdin.nextInt();
			System.out.print(player);

			int com = random.nextInt(3);

			System.out.println(names[com] + "(COM)と" + names[player] + "(Player)で...");

			if(com == player) {

				System.out.println("あいこだよ！");

			}else if((com == 0 && player == 1)||
					(com == 1 && player == 2) ||
					(com == 2 && player == 0)){

				System.out.println("あなたの負け");

				break;

			}else {

				System.out.println("あなたの勝ち");

				break;

			}

			System.out.println();


			first = false;

		}


		stdin.close();

		}




}
