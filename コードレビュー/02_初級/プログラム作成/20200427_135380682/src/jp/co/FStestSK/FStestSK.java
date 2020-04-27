package jp.co.FStestSK;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Scanner;

public class FStestSK {

	public static void main(String[] args) {

		//乱数
		Random random = new Random();
		//文字入力
		Scanner stdin = new Scanner(System.in);

		System.out.println("プレイヤー１の名前を入力してください：");
		String player1Name = stdin.nextLine();

		System.out.println("プレイヤー２の名前を入力してください：");
		String player2Name = stdin.nextLine();

		//プレイヤー１ステータス
		int player1HP = GetNumber(player1Name, 0) * 5;
		int player1STR = GetNumber(player1Name, 1);
        int player1DEF = GetNumber(player1Name, 2);
        //会心
        int player1LUCK= GetNumber(player1Name, 3);

        //プレイヤー２ステータス
        int player2HP = GetNumber(player2Name, 0) * 5;
		int player2STR = GetNumber(player2Name, 1);
        int player2DEF = GetNumber(player2Name, 2);
        //会心
        int player2LUCK= GetNumber(player2Name, 3);


        System.out.println("プレイヤー１：" + player1Name +
        				   "(HP:" + player1HP +
        				   " / STR:" + player1STR +
        				   " / DEF:" + player1DEF +
        				   " / LUCK:" + player1LUCK + ")");

        System.out.println("プレイヤー２：" + player2Name +
        				   "(HP:" + player2HP +
        				   " / STR:" + player2STR +
        				   " / DEF:" + player2DEF +
        				   " / LUCK:" + player2LUCK + ")");

        System.out.println("");
        System.out.println("=== バトル開始 ===");

        while (true) {
			int kougeki;
			int critical;
			int damage;

		//１が攻撃
			System.out.println(player1Name + "の攻撃！");
			kougeki = random.nextInt(player1STR) + 1;

			critical = random.nextInt(500);
			damage = kougeki - player2DEF;

			//クリティカル判定
			if (player1LUCK >= critical) {
			System.out.println("会心の一撃！ "+ player2Name + "に" + kougeki + "のダメージ！");
			player2HP = player2HP - kougeki;

			//ミス判定
			} else if (damage <= 0) {
				System.out.println(player1Name + "の攻撃がミス！");

			//通常ダメージ
			} else {
				System.out.println(player2Name + "に" + kougeki + "のダメージ！");
				player2HP = player2HP - damage;
			}

			//１の攻撃で２が倒れた
			if (player2HP <= 0) {
				System.out.println(player2Name + "は力尽きた...");
				break;
			}


		//２が攻撃
			System.out.println(player2Name + "の攻撃！");
			kougeki = random.nextInt(player2STR) + 1;

			critical = random.nextInt(500);
			damage = kougeki - player1DEF;

			//クリティカル判定
			if (player2LUCK >= critical) {
			System.out.println("会心の一撃！ "+ player1Name + "に" + kougeki + "のダメージ！");
			player1HP = player1HP - kougeki;

			//ミス判定
			} else if (damage <= 0) {
				System.out.println(player2Name + "の攻撃がミス！");

			//通常ダメージ
			} else {
				System.out.println(player1Name + "に" + kougeki + "のダメージ！");
				player1HP = player1HP - damage;
			}

			//２の攻撃で１が倒れた
			if (player1HP <= 0) {
				System.out.println(player1Name + "は力尽きた...");
				break;
			}

		System.out.println("");
        System.out.println("- 次のターン -");
        System.out.println("プレイヤー１：" + player1Name + "(HP:" + player1HP + ")");
        System.out.println("プレイヤー２：" + player2Name + "(HP:" + player2HP + ")");
        System.out.println("--------------------------------");
        }

        System.out.println("");
        if (player1HP <= 0) {
            System.out.println(player2Name + "の勝利！！");
        } else {
            System.out.println(player1Name + "の勝利！！");
        }

        	// 終了処理を行う
     		stdin.close();
	}

	// ハッシュダイジェストから数値を取り出す
	// name : 名前
	// index : 何番目の数値を取り出すか
	// return 数値(0~255)
	public static Integer GetNumber(String name, Integer index)
	{
		try {
			byte[] result = MessageDigest.getInstance("SHA-1").digest(name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));
			String hex = digest.substring(index * 2, index * 2 + 2);
			return Integer.parseInt(hex, 16);
		} catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
}