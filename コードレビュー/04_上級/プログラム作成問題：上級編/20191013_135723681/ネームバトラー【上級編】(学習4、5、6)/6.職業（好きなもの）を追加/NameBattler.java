package name_battler6;

import java.util.Random;
// JRE 1.8を使用しています
import java.util.Scanner;

public class NameBattler {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		Random rnd = new Random();
		// ==================================================
		// バトル開始準備
		// ==================================================
		System.out.print("プレイヤー1の名前を入力してください:");
		String Player1Name = stdin.next();
		System.out.println("職業を選択してください");
		System.out.print("(1)戦士 (2)魔法使い (3)僧侶 (4)勇者:");
		int Player1JOB = stdin.nextInt();

		// プレイヤー１のキャラクターを作成
		Player player1;

		while(Player1JOB != 1 && Player1JOB != 2 && Player1JOB != 3 && Player1JOB != 4) {

			System.out.println("正しい数値を入力してください");
			Player1JOB = stdin.nextInt();
		}
		if(Player1JOB == 1) {

			player1 = new Fighter(Player1Name);
		}
		else if(Player1JOB == 2){

			player1 = new Wizard(Player1Name);
		}
		else if(Player1JOB == 3){

			player1 = new Priest(Player1Name);
		}
		else{

			player1 = new Hero(Player1Name);
		}

		// プレイヤー２の名前を入力させるキャラクターを生成
		System.out.print("プレイヤー2の名前を入力してください:");
		String Player2Name = stdin.next();
		System.out.println("職業を選択してください");
		System.out.print("(1)戦士 (2)魔法使い (3)僧侶 (4)勇者:");
		int Player2JOB = stdin.nextInt();
        // プレイヤー２のキャラクターを作成
        Player player2;

		while(Player2JOB != 1 && Player2JOB != 2 && Player2JOB != 3 && Player2JOB != 4) {

			System.out.println("正しい数値を入力してください");
			Player2JOB = stdin.nextInt();
		}
		if(Player2JOB == 1) {

			player2 = new Fighter(Player2Name);
		}
		else if(Player2JOB == 2){

			player2 = new Wizard(Player2Name);
		}
		else if(Player2JOB == 3){

			player2 = new Priest(Player2Name);
		}
		else{

			player2 = new Hero(Player2Name);
		}

        stdin.close();


		// ==================================================
		// バトル処理
		// ==================================================
        // バトル開始の表示
        player1.PrintStatus();
        player2.PrintStatus();
        System.out.println("");
        System.out.println("=== バトル開始 ===");

        int turnNumber = 1;
        // 最大でも20ターンまで
        while ( turnNumber <= 20 ) {
            System.out.println("--------------------------------");
            System.out.printf("- ターン%d -\n", turnNumber);

            //素早さが同じ場合、ランダムで先に攻撃するようにする
            int select = 3;
            if(player1.GetAGI() == player2.GetAGI()){

            	//selectが0の時はプレイヤー1、1の時はプレイヤー2が先に攻撃するようにする
            	select = rnd.nextInt(2);
            }
            //素早さの高いほうから先に攻撃をする
            if((player1.GetAGI() > player2.GetAGI()) || select == 0) {

            	// ■プレイヤー１から攻撃
                player1.Attack(player2);

                // プレイヤー２の敗北判定
                if (player2.GetHP() <= 0 || player1.GetHP() <= 0) {
                    break;
                }
                // ■プレイヤー２の攻撃ターン
                player2.Attack(player1);

                // プレイヤー１の敗北判定
                if (player1.GetHP() <= 0 || player2.GetHP() <= 0) {
                    break;
                }
            }
            else if(player1.GetAGI() < player2.GetAGI() || select == 1) {

            	 // ■プレイヤー２から攻撃
                player2.Attack(player1);

                // プレイヤー１の敗北判定
                if (player1.GetHP() <= 0) {
                    break;
                }
                // ■プレイヤー１の攻撃ターン
                player1.Attack(player2);

                // プレイヤー２の敗北判定
                if (player2.GetHP() <= 0) {
                    break;
                }
            }

            // ※④と⑤の繰り返し
            // ターン終了時のステータスを表示
            System.out.println("");
            player1.PrintStatus();
            player2.PrintStatus();

            // 次のターン
            turnNumber = turnNumber + 1;
        }

        // ⑥勝ち負けの表示(ＨＰが多い方が勝ち)
        System.out.println("");
        if (player1.GetHP() > player2.GetHP())
        {
            System.out.println(player1.GetName() + "の勝利！！");
        } else {
            System.out.println(player2.GetName() + "の勝利！！");
        }


		// ==================================================
		// 終了処理
		// ==================================================
	}

}
