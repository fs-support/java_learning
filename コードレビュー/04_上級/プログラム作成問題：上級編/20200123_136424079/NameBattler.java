import java.util.InputMismatchException;
import java.util.Scanner;

// JRE 1.8を使用しています

public class NameBattler {
	public static int hp1;
	public static int hp2;

	public static void main(String[] args) throws InterruptedException {
		// 2.ベースプログラムの機能を強化
		// ・初級編の仕様を満たすようにベースプログラムを修正して実装する
		// ・戦士(Fighter)のパラメータ生成を「職業別パラメータ表」に合わせて修正する

		// 3.素早さで攻撃順が変わる
		// ・AGIを名前から決めるようにする
		// ・AGIが高い方が先に攻撃するようにする

		// 4.職業（魔法使い）を追加
		// ・攻撃魔法を使える魔法使い(Wizard)クラスを作成する
		// ・キャラクター名入力時にジョブも選べるようにする
		// ・魔法使いのパラメータ生成を「職業別パラメータ表」に合わせて実装する
		// ・魔法使いが使用できる魔法は「魔法一覧」を参照して実装する
		// ・MPがあれば魔法をランダムで使用して攻撃し、MPが無い場合は通常攻撃を行う

		// 5.職業（僧侶）を追加
		// ・回復魔法を使える僧侶(Priest)クラスを作成する
		// ・僧侶のパラメータ生成を「職業別パラメータ表」に合わせて実装する
		// ・僧侶が使用できる魔法は「魔法一覧」を参照して実装する
		// ※この段階では「ヒール」のみの実装でよい
		// ・HPが減っていれば「ヒール」を使用し、減ってなければ通常攻撃を行う

		// 6.職業（好きなもの）を追加
		// ・好きな職業を考えて作ってみる
		// 例）戦闘力もあり回復魔法も使える「勇者」

		// 7.状態異常に対応
		// ・僧侶の魔法に「パライズ」「ポイズン」を追加する
		// ・毒、麻痺などの状態異常とその効果を実装する

		// ==================================================
		// バトル開始準備
		// ==================================================
		while (true) {
			Player player1 = null;
			Player player2 = null;

			try {
				// プレイヤー１の名前を入力させてキャラクターを生成
				// プレイヤー２の名前を入力させるキャラクターを生成
				// ■処理の流れ
				Scanner scanner1 = new Scanner(System.in);
				// １．プレイヤー名の入力プレイヤー名の入力
				System.out.print("プレイヤー名1を入力してください :");
				// プレイヤー１のキャラクターを作成
				String userName1 = scanner1.nextLine();
				// キャラクター名入力時にジョブも選べるようにする
				System.out.print("ジョブを選択してください：1.剣士、2.魔法使い、3.僧侶、4.獣使い:");
				int userjob1 = scanner1.nextInt();
				if (userjob1 == 1) {
					player1 = new Fighter(userName1);
				} else if (userjob1 == 2) {
					player1 = new Wizard(userName1);
				} else if (userjob1 == 3) {
					player1 = new Priest(userName1);
				} else if (userjob1 == 4) {
					player1 = new Beastmaster(userName1);
				} else {
					System.out.println("選びなおしてください");
					continue;
				}
				Thread.sleep(2000);

				Scanner scanner2 = new Scanner(System.in);
				// １．プレイヤー名の入力プレイヤー名の入力
				System.out.print("プレイヤー名2を入力してください  :");
				// プレイヤー２のキャラクターを作成
				String userName2 = scanner2.nextLine();
				// キャラクター名入力時にジョブも選べるようにする
				System.out.print("ジョブを選択してください：1.剣士、2.魔法使い、3.僧侶、4.獣使い:");
				int userjob2 = scanner2.nextInt();
				if (userjob2 == 1) {
					player2 = new Fighter(userName2);
				} else if (userjob2 == 2) {
					player2 = new Wizard(userName2);
				} else if (userjob2 == 3) {
					player2 = new Priest(userName2);
				} else if (userjob2 == 4) {
					player2 = new Beastmaster(userName2);
				}else {
					System.out.println("選びなおしてください");
					continue;
				}

			} catch (InputMismatchException e) {
				System.out.println("選びなおしてください");
				continue;
			}

			// ==================================================
			// バトル処理
			// ==================================================
			// ステータス表示
			player1.PrintStatus();
			player2.PrintStatus();

			// バトル開始の表示
			System.out.println("");
			System.out.println("=== バトル開始 ===");
			hp1 = player1.GetHP();
			hp2 = player2.GetHP();
			int turnNumber = 1;
			// 最大でも20ターンまで
			while (turnNumber <= 20) {
				System.out.println("--------------------------------");
				System.out.printf("- ターン%d -\n", turnNumber);
				// 素早さが高い方が先に攻撃するようにする
				if (player1.GetAgi() > player2.GetAgi()) {
					// ■プレイヤー１の攻撃ターン
					player1.Attack(player2);

					// プレイヤー２の敗北判定
					if (player2.GetHP() <= 0) {
						break;
					}

					if(Priest.parizeFlag == 0){
						// ■プレイヤー２の攻撃ターン
						player2.Attack(player1);
					}

					// プレイヤー１の敗北判定
					if (player1.GetHP() <= 0) {
						break;
					}
				} else {
					// ■プレイヤー２の攻撃ターン
					player2.Attack(player1);

					// プレイヤー１の敗北判定
					if (player1.GetHP() <= 0) {
						break;
					}

					if(Priest.parizeFlag == 0){
						// ■プレイヤー１の攻撃ターン
						player1.Attack(player2);
					}

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
			if (player1.GetHP() > player2.GetHP()) {
				System.out.println(player1.GetName() + "の勝利！！");
				break;
			} else {
				System.out.println(player2.GetName() + "の勝利！！");
				break;
			}

			// ==================================================
			// 終了処理
			// ==================================================
		}
	}

}
