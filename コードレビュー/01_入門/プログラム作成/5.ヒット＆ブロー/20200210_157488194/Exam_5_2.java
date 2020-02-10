package jp.co.FStest08;

import java.util.Random;
import java.util.Scanner;

public class Exam_5_2 {
	public static void main(String[] args) {

		//ユーザ入力をする準備。
		Scanner stdin = new Scanner(System.in);
		//CPUがランダムで出力する準備。
		Random com = new Random();
		//0～9のランダムの数字を出力
		//ユーザの値を格納
		int player = 0;
		//4桁に入力される数字の配列を用意。
		int[] number = { 0, 1, 2, 3, 4 };
		int n = 1;

		number[0] = com.nextInt(9);

		outside: while (n < 4) {
			int Ram = com.nextInt(9);
			//	System.out.println("ram=" + Ram + "   n = " + n);
			for (int j = 0; j < n; j++) {
				//	System.out.println("  j  =  " + j + "     number[j]  =  " + number[j] + "    ram  =  " + Ram);
				if (number[j] == Ram) {
					//	System.out.println("同じ数字なのでやり直し");
					continue outside;
				}
			}
			//	System.out.println("  n =   " + n + "     number[n]  =  " + number[n] + "    ram  =  " + Ram);
			number[n] = Ram;
			n++;
		}
System.out.println("" + number[0] + number[1] + number[2] + number[3]);

		int allhit = 0;

		while (true) {
			System.out.println("4桁の数字を入力して下さい。");
			player = stdin.nextInt();
			int[] playernumber = { 0, 1, 2, 3 };

			int result = 0;
			// 4桁目を配列の0番目に格納する
			result = player / 1000;
			playernumber[0] = result;

			// 3桁目を配列の1番目に格納する
			result = player / 100;
			result = result % 10;
			playernumber[1] = result;

			// 2桁目を配列の2番目に格納する
			result = player / 10;
			result = result % 10;
			playernumber[2] = result;

			// 1桁目を配列の3番目に格納する
			result = player;
			result = result % 10;
			playernumber[3] = result;

			// ヒット変数とブロー変数を作成する
			int hit = 0;
			int brow = 0;

			//4桁の数字を総当たりさせる。
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (number[i] == playernumber[j]) {//ここで箱の中の数字が同じか判断
						if (i == j) {//ここで箱の位置が同じか判断
							// もしiとjが同じならばヒット変数を＋１する
							hit++;
						} else {
							// それ以外の場合はブロー変数を＋１する
							brow++;
						}
					}
				}
			}
			if (hit == 4) {
				System.out.println("おめでとう！" + (allhit + 1) + "回目で成功♪");
				break;
			}
			// ヒット変数とブロー変数の値を表示する
			System.out.println("ヒット : " + hit + "個、 " + "ブロー：" + brow + "個");
			allhit++;
		}
	}
}