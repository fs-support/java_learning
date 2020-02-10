package jp.co.FStest08;

import java.util.Scanner;

public class Beginner_1_01 {
	public static void main(String[] args) {

		//ユーザ入力をする準備。
		Scanner stdin = new Scanner(System.in);
		int totalPrice = 0;
		int man = 0;
		int woman = 0;
		int extra = 0;

		//合計金額を入力
		System.out.println("合計金額を入力してください。");
		totalPrice = stdin.nextInt();

		//男性の人数を入力
		System.out.println("男性の人数を入力してください。");
		man = stdin.nextInt();

		//女性の人数を入力
		System.out.println("女性の人数を入力してください。");
		woman = stdin.nextInt();

		//男性が多く払うときにプラスする値を入力
		System.out.println("男性が多く払うときの金額を入力してください。");
		extra = stdin.nextInt();

		//男女の平均を出力
		double average = (double)totalPrice / (double)(man + woman);

		int money_man = (int)Math.ceil(average + (double)(extra / 2));
		int money_woman = (int)Math.ceil(average - (double)(extra / 2));

		System.out.println("");
		System.out.println("男性：" + money_man + "円");
		System.out.println("女性：" + money_woman + "円");

	}
}
