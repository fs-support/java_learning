package c;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Scanner;

public class data14 {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc=new Scanner(System.in);
		System.out.print("プレイヤー1の名前を入力してください：");
		String Player1=sc.next();
		System.out.print("プレイヤー2の名前を入力してください：");
		String Player2=sc.next();
		System.out.println("\n====バトル開始====");
		Random ra=new Random();
		int player1;
		int player2;
		if(PrintHashDigest(Player1,1)>150) {
			player1=PrintHashDigest(Player1,1)/5;
		}else {
			player1=PrintHashDigest(Player1,1);
		}
		if(PrintHashDigest(Player2,1)>150) {
			player2=PrintHashDigest(Player2,1)/5;
		}else {
			player2=PrintHashDigest(Player2,1);
		}

		int Damage;
		String kai="";
		while(true) {

			if(240<PrintHashDigest(Player1,ra.nextInt(10))) {
				Damage=PrintHashDigest(Player1,ra.nextInt(10));
				 kai="の会心";
			}else {
				Damage=PrintHashDigest(Player2,ra.nextInt(10))-PrintHashDigest(Player1,ra.nextInt(10));
			}
			System.out.println(Player1+kai+"の攻撃！");
			kai="";
			if(Damage<=0) {
				System.out.println("攻撃ミス！");

			}else {
				System.out.println(Player2+"に"+Damage+"のダメージ！");
				player2=player2-Damage;
			}
			if(player2<=0)break;

			if(240<PrintHashDigest(Player2,ra.nextInt(10))) {
				Damage=PrintHashDigest(Player2,ra.nextInt(10));
				 kai="の会心";
			}else {
				Damage=PrintHashDigest(Player1,ra.nextInt(10))-PrintHashDigest(Player2,ra.nextInt(10));
			}
			System.out.println(Player2+kai+"の攻撃！");
			kai="";

			if(Damage<=0) {
				System.out.println("攻撃ミス！");
			}else {
				System.out.println(Player1+"に"+Damage+"のダメージ！");
				player1=player1-Damage;
			}

			if(player1<=0)break;
			System.out.println("\n-次のターン-");
			System.out.println("プレイヤー1:"+Player1+"（HP"+player1+")");
			System.out.println("プレイヤー2:"+Player2+"（HP"+player2+")");
			System.out.println("--------------");
		}
		if(player1<=0) {
			System.out.println(Player1+"は力尽きた...\n");
			System.out.println(Player2+"の勝利！！");
		}else {
			System.out.println(Player2+"は力尽きた...\n");
			System.out.println(Player1+"の勝利！！");
		}
	sc.close();
	}
    // ハッシュ値を表示する
	 public static Integer PrintHashDigest(String name, Integer index){
	        try {
	            // ハッシュ値を取得する
	            byte[] result = MessageDigest.getInstance("SHA-1").digest(name.getBytes());
	            String digest = String.format("%040x", new BigInteger(1, result));
	            String hex =digest.substring (index * 2, index * 2 +2);
	            return Integer.parseInt (hex,16);
	            // 表示する

	        } catch (Exception e){
	            // エラー
	            e.printStackTrace();
	        }
	        return 0;
	 }
}