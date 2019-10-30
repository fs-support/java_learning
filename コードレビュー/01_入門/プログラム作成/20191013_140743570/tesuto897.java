package c;

import java.util.Random;
import java.util.Scanner;

public class tesuto897 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);

		System.out.println("メニュー番号");
		System.out.println("1　通常");
		System.out.println("2　入力１０回");
		System.out.println("3　桁数　3桁");
		System.out.println("4　離れている、近づいている");
		System.out.print("番号：");
		int data =sc.nextInt();
		switch(data) {
		 case 1:
			 data();
			 break;

		 case 2:
			 data1();
			 break;

		 case 3:
			 data2();
			 break;

		 case 4:
			 data3();
			 break;
		}
sc.close();
	}

	public static void data() {
		// TODO 自動生成されたメソッド・スタブ
		Random ra = new Random();
		int numder =ra.nextInt(100);
		Scanner sc=new Scanner(System.in);
		System.out.println("数字を当てて見てね。");
		System.out.println("答えられるのは5回までだよ。");
		System.out.println();
		for(int i=1;5>=i;i++) {
			System.out.print(i+"回目 :");
			int imoutNumber =sc.nextInt();
			if(imoutNumber<numder) {
				if(i==5){
					System.out.println("残念！！正解は"+numder+"でした。!");
				}else {
					System.out.println("もっと大きい数字だよ");
				}
			}else if(imoutNumber>numder){
				if(i==5){
					System.out.println("残念！！正解は"+numder+"でした。!");
				}else {
					System.out.println("もっと小さい数字だよ");
				}
			}else if(imoutNumber==numder) {
				System.out.println();
				System.out.println("すごい！！"+i+"回で当てられちゃった!");
			break;
			}
		}
	sc.close();
	}
	public static void data1() {
		// TODO 自動生成されたメソッド・スタブ
		Random ra = new Random();
		int numder =ra.nextInt(100);
		Scanner sc=new Scanner(System.in);
		System.out.println("数字を当てて見てね。");
		System.out.println("答えられるのは10回までだよ。");
		System.out.println();
		for(int i=1;10>=i;i++) {
			System.out.print(i+"回目 :");
			int imoutNumber =sc.nextInt();
			if(imoutNumber<numder) {
				if(i==10){
					System.out.println("残念！！正解は"+numder+"でした。!");
				}else {
					System.out.println("もっと大きい数字だよ");
				}
			}else if(imoutNumber>numder){
				if(i==10){
					System.out.println("残念！！正解は"+numder+"でした。!");
				}else {
					System.out.println("もっと小さい数字だよ");
				}
			}else if(imoutNumber==numder) {
				System.out.println();
				System.out.println("すごい！！"+i+"回で当てられちゃった!");
			break;
			}
		}
	sc.close();
	}
	public static void data2() {
		// TODO 自動生成されたメソッド・スタブ
		Random ra = new Random();
		int numder =ra.nextInt(1000);
		Scanner sc=new Scanner(System.in);
		System.out.println("数字を当てて見てね。");
		System.out.println("答えられるのは5回までだよ。");
		System.out.println();
		for(int i=1;5>=i;i++) {
			System.out.print(i+"回目 :");
			int imoutNumber =sc.nextInt();
			if(imoutNumber<numder) {
				if(i==5){
					System.out.println("残念！！正解は"+numder+"でした。!");
				}else {
					System.out.println("もっと大きい数字だよ");
				}
			}else if(imoutNumber>numder){
				if(i==5){
					System.out.println("残念！！正解は"+numder+"でした。!");
				}else {
					System.out.println("もっと小さい数字だよ");
				}
			}else if(imoutNumber==numder) {
				System.out.println();
				System.out.println("すごい！！"+i+"回で当てられちゃった!");
			break;
			}
		}
	sc.close();
	}
	static int o;
	public static void data3() {
		// TODO 自動生成されたメソッド・スタブ
		Random ra = new Random();
		int numder =ra.nextInt(100);
		Scanner sc=new Scanner(System.in);
		System.out.println("数字を当てて見てね。");
		System.out.println("答えられるのは5回までだよ。");
		System.out.println();
		System.out.print("1回目 :");
		int imoutNumber =sc.nextInt();
		if(imoutNumber==numder) {
			System.out.println();
			System.out.println("すごい！！1回で当てられちゃった!");
			System.exit(0);
		}else {
			System.out.println();
			System.out.println("残念でした。!");
			 o= Math.abs(imoutNumber-numder);
		}
		for(int i=2;5>=i;i++) {
			System.out.print(i+"回目 :");
			 imoutNumber =sc.nextInt();
			int a= Math.abs(imoutNumber-numder);
			 if(o>a) {
				if(i==5){
					System.out.println("残念！！正解は"+numder+"でした。!");
				}else {
					System.out.println("近いよ");
					o=a;
				}
			}else if(o<a){
				if(i==5){
					System.out.println("残念！！正解は"+numder+"でした。!");
				}else {
					System.out.println("離れてるよ");
					o=a;
				}
			}else if(imoutNumber==numder) {
				System.out.println();
				System.out.println("すごい！！正解は"+i+"回で当てられちゃった!");
			break;
			}
		}
	sc.close();
	}
}
