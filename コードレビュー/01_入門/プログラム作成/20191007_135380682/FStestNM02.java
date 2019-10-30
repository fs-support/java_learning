package jp.co.FStestNM02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class FStestNM02 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//図柄のリスト
				ArrayList<String> mark = new ArrayList<String>();
				Collections.addAll(mark, "ハート", "ダイヤ", "スペード", "クローバー");

				//数字のリスト
				ArrayList<String> number = new ArrayList<>();
				Collections.addAll(number, "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");

				//数値用のクラス
				Random random = new Random();
				Scanner stdin = new Scanner(System.in);

				//図柄の答えの要素番号
				int markRam = random.nextInt(4);
				//数字の答えの要素番号
				int numberRam = random.nextInt(13);

				System.out.println("トランプを選んだよ\nトランプの図柄を当ててね");
				System.out.println("答えられるのは２回までだよ。\n");
				System.out.print("0:ハート\n1:ダイヤ\n2:スペード\n3:クローバー\n");
				System.out.println();

				// 図柄を当てさせる
				while (true) {
					//図柄の回答番号を入れる変数
					int markAns = 0;
					System.out.print("どれだと思う\n");

					for (int count = 1; count <= 2; count++) {
						System.out.print(count + "回目：");

					markAns = stdin.nextInt();

					//回答が0～3でなかった場合
					while (markAns < 0 || markAns > 3) {
						System.out.println("\n0～3を入力してください");
						System.out.print("どれだと思う：");
						markAns = stdin.nextInt();
					}

					if (markAns != markRam) {
						//回答が間違っている場合
						System.out.println("\n残念！" + mark.get(markAns) + "じゃないよ");
					} else {
						//回答があっている場合
						System.out.println("\n正解！図柄は" + mark.get(markRam) + "だよ\n");
						break;
					}

					if ((markAns != markRam) && count == 2){
						System.out.println("残念！！正解は " + mark.get(markRam) + " でした！");
						stdin.close();
						return;

					}
				}

				System.out.print("次は数字を当ててね\n");
				System.out.println("答えられるのは４回までだよ。\n");

				// 数字を当てさせる
				while (true) {
					//数字の回答番号を入れる変数
					int numberAns = 0;

					System.out.print("どれだと思う\n");

					for (int count = 1; count <= 4; count++) {
						System.out.print(count + "回目：");

					numberAns = stdin.nextInt();

					//回答が1～13でなかった場合
					while (numberAns < 1 || numberAns > 13) {
						System.out.println("\n1～13を入力してください");
						System.out.print("どれだと思う：");
						numberAns = stdin.nextInt();
					}

					if ((numberAns -1 != numberRam) && count == 4){
						System.out.println("残念！！正解は " + number.get(numberRam) + " でした！");
						stdin.close();
						return;
					}


					if (numberAns -1 == numberRam){
						System.out.print("\n正解！" + mark.get(markRam) + "の" + number.get(numberRam) + "だよ");
						stdin.close();
						return;
					}

					else if (numberAns - 1 < numberRam) {
						System.out.println("もっと大きい数字だよ");

					} else {
						System.out.println("もっと小さい数字だよ");

					}
				}
			}
		}
	}
}