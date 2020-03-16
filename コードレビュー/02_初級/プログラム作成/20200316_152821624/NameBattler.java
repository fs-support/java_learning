package aaa;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Scanner;

public class NameBattler {

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		 Random random = new Random();
		 
		 System.out.println("プレイヤー１の名前を入力：");
		 String player1Name = stdin.nextLine();
		 System.out.println("プレイヤー２の名前を入力：");
		 String player2Name = stdin.nextLine();
		
		 int player3HP = GetNumber(player1Name, 0) * 5;
		 int player11STR = GetNumber(player1Name, 1);
		 int player100DEF = GetNumber(player1Name, 2);
		 int player2LUCK = GetNumber(player1Name, 3) * 50 / 255;
		 
		 int player8HP = GetNumber(player2Name, 0) * 5;
		 int player5STR = GetNumber(player2Name, 1);
		 int player30DEF = GetNumber(player2Name, 2);
		 int player3LUCK = GetNumber(player2Name, 3) * 50 / 255;
		  
		 System.out.println("プレイヤー１：" + player1Name + "(HP " + player3HP + " / STR " + player11STR + " / DEF " + player100DEF + " / LUCK " + player2LUCK + ")");
		 System.out.println("プレイヤー２：" + player2Name + "(HP " + player8HP + " / STR " + player5STR + " / DEF " + player30DEF + " / LUCK " + player3LUCK + ")");
		 System.out.println("");
		 System.out.println("=== バトル開始 ===");
		 
		 while (true) {
			 int power;
			 int damage;
			 int luck;
		
			 System.out.println(player1Name + "の攻撃！");
			 power = random.nextInt(player11STR) + 1;
			
			 luck = random.nextInt(100);
			 if (luck <= player2LUCK) { System.out.println("会心の一撃!");
			 damage = power;
			 } else {
				 damage = power - player30DEF;
				 }
			 
			 if (damage <= 0) { 
				 System.out.println(player1Name + "の攻撃はミス！");
				 } else {
					 System.out.println(player2Name + "に" + damage + "のダメージ！");
					 player8HP = player8HP - damage;
					 }
			
			 if (player8HP <= 0) { System.out.println(player2Name + "は力尽きた...");
			 break;
			 } 
			 
			 System.out.println(player2Name + "の攻撃！");
			 power = random.nextInt(player5STR) + 1;
			 
			 luck = random.nextInt(100);
			 if (luck <= player2LUCK) {
				 System.out.println("会心の一撃!");
				 damage = power;
				 } else {
					 damage = power - player100DEF;
					 } 
			 if (damage <= 0) {
				 System.out.println(player2Name + "の攻撃はミス！");
				 } else {
					 System.out.println(player1Name + "に" + damage + "のダメージ！");
					 player3HP = player3HP - damage;
					 } 
			 
			 if (player3HP <= 0) { System.out.println(player1Name + "は力尽きた...");
			 break;
			 }
			 
			 System.out.println("");
			 System.out.println("- 次のターン -");
			 System.out.println("プレイヤー１：" + player1Name + "(HP " + player3HP + ")");
			 System.out.println("プレイヤー２：" + player2Name + "(HP " + player8HP + ")");
			 System.out.println("--------------------------------");
			 }
		 System.out.println("");
		 if (player3HP <= 0) {
			 System.out.println(player2Name + "の勝利！！");
			 } else {
				 System.out.println(player1Name + "の勝利！！");
				 } 
		 stdin.close();
		 } 
	public static Integer GetNumber(String name, Integer index){
	try{
		byte[] result = MessageDigest.getInstance("SHA-1").digest(name.getBytes());
		String digest = String.format("%040x", new BigInteger(1, result));
		String hex = digest.substring(index * 2, index * 2 + 2);
		return Integer.parseInt(hex, 16);
		} catch (Exception e) {
			e.printStackTrace();
			} 
	return 0;
		 }

	}
