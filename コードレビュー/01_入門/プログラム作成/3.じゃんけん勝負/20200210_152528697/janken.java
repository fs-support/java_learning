package programSakusei;

import java.util.Random;
import java.util.Scanner;

public class janken {
	
	public static int playWin = 0 , comWin = 0;
	public static boolean janContinue; 

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		int input = 0 , com = 0 ;
		
		System.out.println("じゃんけん勝負(3回！)\nグーチョキパーで入力してね\n0:グー\n1:チョキ\n2:パー");
		
		for(int i = 1 ; i <= 3 ; i++) {
			
			System.out.println("\n" + i + "回目");
			System.out.print("\n最初はぐー、じゃんけん：");
			
			while(true) {
			
			input = sc.nextInt();
			com = rand.nextInt(3);
			
			if(input == 0) {
				if(com == 0) {
					System.out.println("グー(COM)とグー(Player)で…\nあいこだよ！");
					janContinue = false;
				}
				else if(com == 1) {
					System.out.println("チョキ(COM)とグー(Player)で…\nあなたの勝ち");
					if(playAchi())break;
				}
				else {
					System.out.println("パー(COM)とグー(Player)で…\nあなたの負け");
					if(comAchi())break;
				}
			}
			if(input == 1) {
				if(com == 0) {
					System.out.println("グー(COM)とチョキ(Player)で…\nあなたの負け");
					if(comAchi())break;
				}
				else if(com == 1) {
					System.out.println("チョキ(COM)とチョキ(Player)で…\nあいこだよ！");
					janContinue = false;
				}
				else {
					System.out.println("パー(COM)とチョキ(Player)で…\nあなたの勝ち");
					if(playAchi())break;
				}
			}
			if(input == 2) {
				if(com == 0) {
					System.out.println("グー(COM)とパー(Player)で…\nあなたの勝ち");
					if(playAchi())break;
				}
				else if(com == 1) {
					System.out.println("チョキ(COM)とパー(Player)で…\nあなたの負け");
					if(comAchi())break;
				}
				else {
					System.out.println("パー(COM)とパー(Player)で…\nあいこだよ！");
					janContinue = false;
				}
			}
			
			if(janContinue) {
				System.out.print("\n最初はぐー、じゃんけん：");
			}
			else {
				System.out.print("\nあいこで：");
			}
			
			}
		}
		
		System.out.println("\n結果は…" + playWin + "勝" + comWin + "敗！！");
		
	}
	
	public static boolean playAchi() {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		
		int muki = 0 , comMuki = 0;
		
		System.out.print("\nあっちむいてほい勝負\n0:↑\n1:→\n2:↓\n3:←\nあっちむいて：");
		
		muki = sc.nextInt();
		comMuki = rand.nextInt(4);
		
		if(muki == comMuki) {
			System.out.println("com:" + comMuki + "\n勝利！");
			playWin++;
			
			return true;
		}else {
			System.out.println("com:" + comMuki + "\n残念！");
			janContinue = true;
			return false;
		}
	}
	public static boolean comAchi() {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		
		int muki = 0 , comMuki = 0;
		
		System.out.print("\nあっちむいてほい勝負\n0:↑\n1:→\n2:↓\n3:←\nあっちむいて：");
		
		muki = sc.nextInt();
		comMuki = rand.nextInt(4);
		
		if(muki == comMuki) {
			System.out.println("com:" + comMuki + "\n敗北！");
			comWin++;
			
			return true;
		}else {
			System.out.println("com:" + comMuki + "\nセーフ！");
			janContinue = true;
			return false;
		}
	}
}
