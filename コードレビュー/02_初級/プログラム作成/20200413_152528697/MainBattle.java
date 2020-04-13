package nameBatttler;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Scanner;

public class MainBattle {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		//		•キャラクター２人の自動バトルゲーム
		//		•バトルの内容は、ＲＰＧ風に表示する
		//		•入力した名前で対戦ログが表示される
		//		•攻撃は交互に行う •パラメータはＨＰのみで１０固定
		//		•ダメージは１～３のランダムで計算する
		//		•いずれかのＨＰが０以下になったらバトルは終了

		String player1name;
		int player1HP = 0;
		int player1ATK = 0;
		int player1DEF = 0;
		int player1LUCK = 0;

		String player2name;
		int player2HP = 0;
		int player2ATK = 0;
		int player2DEF = 0;
		int player2LUCK = 0;

		Scanner sc = new Scanner(System.in);

		System.out.print("プレイヤー１の名前を入力してください：");
		player1name = sc.nextLine();
		System.out.print("プレイヤー２の名前を入力してください：");
		player2name = sc.nextLine();

		//player1ステータス
		player1HP = GetNumber(player1name, 0);
		player1ATK = GetNumber(player1name, 1);
		player1DEF = GetNumber(player1name, 2);
		player1LUCK = GetNumber(player1name, 3);
		System.out.println(player1name + "(HP " + player1HP + ")");
		System.out.println(player1name + "(ATK " + player1ATK + ")");
		System.out.println(player1name + "(DEF " + player1DEF + ")");
		System.out.println(player1name + "(LUCK " + player1LUCK + ")");

		//player2ステータス
		player2HP = GetNumber(player2name, 0);
		player2ATK = GetNumber(player2name, 1);
		player2DEF = GetNumber(player2name, 2);
		player2LUCK = GetNumber(player2name, 3);
		System.out.println(player2name + "(HP " + player2HP + ")");
		System.out.println(player2name + "(ATK " + player2ATK + ")");
		System.out.println(player2name + "(DEF " + player2DEF + ")");
		System.out.println(player2name + "(LUCK " + player2LUCK + ")");

		System.out.println("\n=== バトル開始 ===");
		//player1の攻撃
		player2HP = HPCalc(player1name, player1ATK, player1LUCK, player2name, player2HP, player2DEF);

		if(player2HP <= 0) {

		}else {
			//player2の攻撃
			player1HP = HPCalc(player2name, player2ATK, player2LUCK, player1name, player1HP, player1DEF);
		}

		//どちらかのHPが0になるまで繰り返し
		while (0 <= player1HP && 0 <= player2HP) {
			System.out.println("\n-次のターン-");
			System.out.println("プレイヤー１：" + player1name + "(HP " + player1HP + ")");
			System.out.println("プレイヤー２：" + player2name + "(HP " + player2HP + ")");
			System.out.println("----------------------");

			//player1の攻撃
			player2HP = HPCalc(player1name, player1ATK, player1LUCK, player2name, player2HP, player2DEF);

			//プレイヤー２が先に力尽きた場合break
			if (player2HP <= 0)
				break;

			//player2の攻撃
			player1HP = HPCalc(player2name, player2ATK, player2LUCK, player1name, player1HP, player1DEF);

		}
		if (player1HP <= 0 && player2HP > 0) {
			System.out.println(player1name + "は力尽きた…\n");
			System.out.println(player2name + "の勝利！！");
		} else {
			System.out.println(player2name + "は力尽きた…\n");
			System.out.println(player1name + "の勝利！！");
		}

	}
	//HP計算メソッド
	public static int HPCalc(String attacker, int attackerATK, int attackerLUCK, String defender, int defenderHP, int defenderDEF) {
		Random rand = new Random();
		int critical = 0;
		critical = rand.nextInt(255);
		System.out.println(attacker + "の攻撃！ ");
		//会心率の調整(50%以下)
		attackerLUCK = attackerLUCK/2;
		System.out.println(attackerLUCK);
		//会心の一撃
		if(critical <= attackerLUCK) {
			System.out.println("会心の一撃！");
		}else {
			//攻撃力計算
			attackerATK = rand.nextInt(attackerATK) + 1 - defenderDEF;
		}
		//ミスの分岐
		if(attackerATK <= 0) {
			System.out.println("攻撃がミス！");
		}else {
			defenderHP -= attackerATK;
			System.out.println(defender + "に" + attackerATK + "のダメージ！");
		}
		return defenderHP;
	}

	// ハッシュダイジェストから数値を取り出す
	// name : 名前
	// index : 何番目の数値を取り出すか
	// return 数値(0~255)
	public static Integer GetNumber(String name, Integer index) {
		try {
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
