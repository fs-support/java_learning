package name_battlerM3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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
		Map<Player, Integer> map = new HashMap<Player, Integer>();
		map.put(player1, player1.GetAGI());
		map.put(player2, player2.GetAGI());
		map.put(player3, player3.GetAGI());
		map.put(player4, player4.GetAGI());
		map.put(player5, player5.GetAGI());
		map.put(player6, player6.GetAGI());

		//※※※キーでソートする場合重複したキーの値は上書きされるため、値でソートする
		map.entrySet().stream()
				.sorted(java.util.Collections.reverseOrder(java.util.Map.Entry.comparingByValue()));

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

			for (Player key : map.keySet()) {

				int target;

				if ((key.equals(player1) || key.equals(player2) || key.equals(player3)) && key.GetHP() > 0) {

					//状態異常：麻痺の判定
					if (key.Status_check() == 1) {
						System.out.println(player1.GetName() + "は、身体が痺れて動けない！");
					} else {
						target = rnd.nextInt(targetSelect2.size());
						key.Attack(targetSelect2.get(target));

						//状態異常：毒の判定
						if (key.Status_check() == 2) {
							key.PoisonDamage();
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
					if (!key.GetStatus().equals(Player.Status.Nomal)) {
						key.Recovery();
					}
				} else if ((key.equals(player4) || key.equals(player5) || key.equals(player6)) && key.GetHP() > 0) {

					if (key.Status_check() == 1) {
						System.out.println(player1.GetName() + "は、身体が痺れて動けない！");
					} else {
						target = rnd.nextInt(targetSelect1.size());
						key.Attack(targetSelect1.get(target));

						//状態異常：毒の判定
						if (key.Status_check() == 2) {
							key.PoisonDamage();
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
					if (!key.GetStatus().equals(Player.Status.Nomal)) {
						key.Recovery();
					}
				}
				System.out.println("");
			}



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
		if (targetSelect1.size() == 0) {

			System.out.println("相手のパーティーの勝利！！");
		} else {

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
