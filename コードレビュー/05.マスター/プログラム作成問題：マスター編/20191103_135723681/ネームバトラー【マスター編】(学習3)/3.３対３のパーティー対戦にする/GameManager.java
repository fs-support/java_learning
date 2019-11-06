package name_battlerM3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class GameManager {

	Scanner stdin = new Scanner(System.in);

	public void Start() {

		Random rnd = new Random();
		Party party1 = new Party();
		Party party2 = new Party();

		// ==================================================
		// バトル開始準備
		// ==================================================

		System.out.println("あなたのパーティーキャラクターを3体作成してください");
		Player player1 = null;
		player1 = Jobselect(player1, 1);
		party1.AppendPlayer(player1);

		Player player2 = null;
		player2 = Jobselect(player2, 2);
		party1.AppendPlayer(player2);

		Player player3 = null;
		player3 = Jobselect(player3, 3);
		party1.AppendPlayer(player2);

		System.out.println("\n相手のパーティーキャラクターを3体作成してください");
		Player player4 = null;
		player4 = Jobselect(player4, 4);
		party2.AppendPlayer(player4);

		Player player5 = null;
		player5 = Jobselect(player5, 5);
		party2.AppendPlayer(player5);

		Player player6 = null;
		player6 = Jobselect(player6, 6);
		party2.AppendPlayer(player6);

		stdin.close();

		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		System.out.println("");
		System.out.println("◆あなたのパーティー");
		player1.PrintStatus();
		player2.PrintStatus();
		player3.PrintStatus();
		System.out.println("◇相手のパーティー");
		player4.PrintStatus();
		player5.PrintStatus();
		player6.PrintStatus();
		System.out.println("");
		System.out.println("=== バトル開始 ===");

		//TreeMap：Mapに格納した値を昇順、降順で取得できる(デフォルトだと昇順。降順に変えるには処理の追加要)
		//素早さによる行動順判定のためのMap
		Map<Integer, Player> map = new TreeMap<Integer, Player>(new Comparator<Integer>() {
			public int compare(Integer k1,Integer k2) {
				return k2.compareTo(k1);
			}
		});
		map.put(player1.GetAGI(), player1);
		map.put(player2.GetAGI(), player2);
		map.put(player3.GetAGI(), player3);
		map.put(player4.GetAGI(), player4);
		map.put(player5.GetAGI(), player5);
		map.put(player6.GetAGI(), player6);

		//攻撃対象プレイヤーを決めるためのリスト(死亡した場合リストから除外する)
		ArrayList<Player> targetSelect1 = new ArrayList<Player>();
		targetSelect1.add(player1);
		targetSelect1.add(player2);
		targetSelect1.add(player3);
		ArrayList<Player> targetSelect2 = new ArrayList<Player>();
		targetSelect2.add(player4);
		targetSelect2.add(player5);
		targetSelect2.add(player6);

		int turnNumber = 1;
		// 最大でも20ターンまで※※※今回どちらかが全滅するまで戦い続ける処理に変更しました。
		while (targetSelect1.size() > 0 && targetSelect2.size() > 0) {
			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);

			for (Player val : map.values()) {

				int target;

				if ((val.equals(player1) || val.equals(player2) || val.equals(player3)) && val.GetHP() > 0) {

					//状態異常：麻痺の判定
					if (val.Status_check() == 1) {
						System.out.println(player1.GetName() + "は、身体が痺れて動けない！");
					} else {
						target = rnd.nextInt(targetSelect2.size());
						val.Attack(targetSelect2.get(target));

						//状態異常：毒の判定
						if (val.Status_check() == 2) {
							val.PoisonDamage();
						}
						//攻撃対象の死亡判定
						if (targetSelect2.get(target).GetHP() <= 0) {

							System.out.println(targetSelect2.get(target).GetName() + "は死んでしまった！");
							targetSelect2.remove(target);
						}
					}
					//相手パーティーの全滅判定
					if (targetSelect2.size() == 0) {
						break;
					}
					//状態異常の自己治癒判定
					if (!val.GetStatus().equals(Player.Status.Nomal)) {
						val.Recovery();
					}
				}
				else if ((val.equals(player4) || val.equals(player5) || val.equals(player6)) && val.GetHP() > 0) {

					if (val.Status_check() == 1) {
						System.out.println(player1.GetName() + "は、身体が痺れて動けない！");
					}
					else {
						target = rnd.nextInt(targetSelect1.size());
						val.Attack(targetSelect1.get(target));

						//状態異常：毒の判定
						if (val.Status_check() == 2) {
							val.PoisonDamage();
						}
						//攻撃対象の死亡判定
						if (targetSelect1.get(target).GetHP() <= 0) {

							System.out.println(targetSelect1.get(target).GetName() + "は死んでしまった！");
							targetSelect1.remove(target);
						}
					}
					//相手パーティーの全滅判定
					if (targetSelect1.size() == 0) {
						break;
					}
					//状態異常の自己治癒判定
					if (!val.GetStatus().equals(Player.Status.Nomal)) {
						val.Recovery();
					}
				}
				System.out.println("");
			}

		//素早さが同じ場合、ランダムで先に攻撃するようにする
		/*int select = 3;
		if (player1.GetAGI() == player2.GetAGI()) {

			//selectが0の時はプレイヤー1、1の時はプレイヤー2が先に攻撃するようにする
			select = rnd.nextInt(2);
		}
		//素早さの高いほうから先に攻撃をする
		if ((player1.GetAGI() > player2.GetAGI()) || select == 0) {

			// ■プレイヤー１から攻撃
			//状態異常：麻痺の判定
			if (player1.Status_check() == 1) {
				System.out.println(player1.GetName() + "は、身体が痺れて動けない！");
			} else {
				player1.Attack(player2);
			}
			//状態異常：毒の判定
			if (player1.Status_check() == 2) {
				player1.PoisonDamage();
			}
			// プレイヤー２の敗北判定
			if (player2.GetHP() <= 0) {
				break;
			}
			//状態異常の自己治癒判定
			if (!player1.GetStatus().equals(Player.Status.Nomal)) {
				player1.Recovery();
			}

			// ■プレイヤー２の攻撃ターン
			if (player2.Status_check() == 1) {
				System.out.println(player2.GetName() + "は、身体が痺れて動けない！\n");
			} else {
				player2.Attack(player1);
			}
			if (player2.Status_check() == 2) {
				player2.PoisonDamage();
			}
			// プレイヤー１の敗北判定
			if (player1.GetHP() <= 0) {
				break;
			}
			//状態異常の自己治癒判定
			if (!player2.GetStatus().equals(Player.Status.Nomal)) {
				player2.Recovery();
			}

		} else if (player1.GetAGI() < player2.GetAGI() || select == 1) {

			// ■プレイヤー２の攻撃ターン
			if (player2.Status_check() == 1) {

				System.out.println(player2.GetName() + "は、身体が痺れて動けない！\n");
			} else {
				player2.Attack(player1);
			}
			if (player2.Status_check() == 2) {
				player2.PoisonDamage();
			}
			// プレイヤー１の敗北判定
			if (player1.GetHP() <= 0) {
				break;
			}
			//状態異常の自己治癒判定
			if (!player2.GetStatus().equals(Player.Status.Nomal)) {
				player2.Recovery();
			}
			// ■プレイヤー１の攻撃
			if (player1.Status_check() == 1) {
				System.out.println(player1.GetName() + "は、身体が痺れて動けない！\n");
			} else {
				player1.Attack(player2);
			}
			if (player1.Status_check() == 2) {
				player1.PoisonDamage();
			}
			// プレイヤー２の敗北判定
			if (player2.GetHP() <= 0) {
				break;
			}
			//状態異常の自己治癒判定
			if (!player1.GetStatus().equals(Player.Status.Nomal)) {
				player1.Recovery();
			}
		}*/

		// ※④と⑤の繰り返し
		// ターン終了時のステータスを表示
			System.out.println("");
			System.out.println("◆あなたのパーティー");
			player1.PrintStatus();
			player2.PrintStatus();
			player3.PrintStatus();
			System.out.println("◇相手のパーティー");
			player4.PrintStatus();
			player5.PrintStatus();
			player6.PrintStatus();

		// 次のターン
		turnNumber = turnNumber + 1;
	}

	// ⑥勝ち負けの表示(パーティーの人数が1以上のほうが勝ち)
	System.out.println("");
	if(targetSelect1.size() == 0){

		System.out.println("相手のパーティーの勝利！！");
	}
	else{

		System.out.println("あなたのパーティーの勝利！！");
	}

	// ==================================================
	// 終了処理
	// ==================================================
	}

	//職業選択
	protected Player Jobselect(Player player, int playerNumber) {

		System.out.print("プレイヤー" + playerNumber + "の名前を入力してください:");
		String PlayerName = stdin.next();
		System.out.println("職業を選択してください");
		System.out.print("(1)戦士 (2)魔法使い (3)僧侶 (4)勇者:");
		int PlayerJOB = stdin.nextInt();

		// プレイヤー１のキャラクターを作成

		while (PlayerJOB != 1 && PlayerJOB != 2 && PlayerJOB != 3 && PlayerJOB != 4) {

			System.out.println("正しい数値を入力してください");
			PlayerJOB = stdin.nextInt();
		}
		if (PlayerJOB == 1) {

			player = new Fighter(PlayerName);
		} else if (PlayerJOB == 2) {

			player = new Wizard(PlayerName);
		} else if (PlayerJOB == 3) {

			player = new Priest(PlayerName);
		} else {

			player = new Hero(PlayerName);
		}

		return player;
	}
}
