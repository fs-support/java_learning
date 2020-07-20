package namebattler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GameManager {

	// パーティ1
	static Party makeparty = new Party();
	// パーティ2
	static Party makeparty2 = new Party();
	
	//戦闘不能プレイヤーを格納（死亡リスト）
	static Party deadparty = new Party();
	static Party deadparty2 = new Party();

	// 攻撃順に格納
	static ArrayList<Player> turnorder = new ArrayList<Player>();
	
	
	
	// パーティメンバーの人数を保存
	int Allpartymenber = 3;

	// 攻撃順に格納
	//static ArrayList<Player> turnorder = new ArrayList<Player>();

	Player player = null;
	Player player2 = null;

	// 作戦を格納
	MakeListOperation OperationList = new MakeListOperation();
	
	//アイテムの呼び出し
		UseItem useitem = new UseItem();
		

	public void Start() {
		// ==================================================
		// バトル開始準備
		// ==================================================

		// 名前、職業の入力
		Scanner stdin1name = new Scanner(System.in);
		Scanner stdin1job = new Scanner(System.in);
		Scanner stdin2name = new Scanner(System.in);
		Scanner stdin2job = new Scanner(System.in);

		System.out.println("名前を入力した後に職業を選択してください");
		System.out
				.println("-----------------------------------------------------------------------------------");
		System.out.println("職業には相性があります");
		System.out.println("魔法使いは戦士に強く、戦士は僧侶に強く、僧侶は魔法使いに強いです。(ダメージ1.5倍)");
		System.out.println("勇者は全ての職業に強いですが、受けるダメージが1.25倍になります");
		System.out
				.println("-----------------------------------------------------------------------------------");

		// パーティ１プレイヤーの名前を入力させてキャラクターを生成
		MakeCharacter(1);
		// パーティ2プレイヤーの名前を入力させてキャラクターを生成
		MakeCharacter(2);

		// ==================================================
		// 作戦を立てる
		// ==================================================
		System.out.println("作戦を番号で選んでください");
		OperationList.Printoperatiomlist();


		// 選んだ作戦を格納
		Operation choiseoperation = Choiceoperation();
		System.out.println("あなたは「" + choiseoperation.operationName
				+ "」を選択しました\n");
		System.out
				.println("---------------------------------------------------");



		// ==================================================
		// バトル処理
		// ==================================================
		// バトル開始の表示
		//パーティ1の表示
		PrintParty(1);
		//パーティ2の表示
		PrintParty(2);
		
		System.out.println("");
		System.out.println("=== バトル開始 ===");

		// 素早さで攻撃する順番を変える
		Collections.sort(turnorder, new AgiHigherComparator());
		

		int turnNumber = 1;
		// 最大でも20ターンまで
		while (turnNumber <= 20) {

			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);

			
			// プレイヤーが全員攻撃したら1ターン
			for (Player atackplayer : turnorder) {
				// パーティが全滅したら終了
				if (makeparty.GetMembers().size() == 0
						|| makeparty2.GetMembers().size() == 0) {
					break;
				}

				// ■プレイヤーの攻撃ターン

				atackplayer.Attack(atackplayer, choiseoperation, turnNumber);

			}
			
			
			// パーティが全滅したら終了
			if (makeparty.GetMembers().size() == 0
					|| makeparty2.GetMembers().size() == 0) {
				break;
			}

			//アイテムを使用
			//パーティ毎に一回ずつ
			useitem.Itemuse();
			
			// ターン終了時のステータスを表示
			System.out.println("");
			System.out.println("各パーティステータス");
			// attackplayer.PrintStatus();
			for (int i = 0; i < makeparty.GetMembers().size(); i++) {
				makeparty.GetMembers().get(i).PrintStatus();
			}
			for (int i = 0; i < makeparty2.GetMembers().size(); i++) {
				makeparty2.GetMembers().get(i).PrintStatus();
			}

			// 次のターン
			turnNumber = turnNumber + 1;

		}

		// ⑥勝ち負けの表示(生き残っているものが多い方が勝ち)
		System.out.println("");
		// 生存者のHPを集約
		// パーティ1用
		int Allpartylife = 0;
		for (int i = 0; i < makeparty.GetMembers().size(); i++) {
			Allpartylife += makeparty.GetMembers().get(i).hp;
		}
		// パーティ2用
		int Allpartylife2 = 0;
		for (int i = 0; i < makeparty2.GetMembers().size(); i++) {
			Allpartylife2 += makeparty2.GetMembers().get(i).hp;
		}

		if (makeparty.GetMembers().size() > makeparty2.GetMembers().size()) {
			System.out.println("パーティ2は全滅した！");
			System.out.println("パーティ1の勝利");
		} else if (makeparty.GetMembers().size() < makeparty2.GetMembers()
				.size()) {
			System.out.println("パーティ1は全滅した！");
			System.out.println("パーティ2の勝利");
		} else {
			if (Allpartylife > Allpartylife2) {
				System.out.println("パーティ1の勝利");
			} else if (Allpartylife < Allpartylife2) {
				System.out.println("パーティ2の勝利");
			} else {
				System.out.println("引き分けだ！");
			}
		}

		stdin1name.close();
		stdin1job.close();
		stdin2name.close();
		stdin2job.close();
		
		// ==================================================
		// 終了処理
		// ==================================================

	}

	// プレイヤーのキャラクターを作成
	public void MakeCharacter(int partyNO) {
		// 名前、職業の入力
		Scanner stdinname = new Scanner(System.in);
		Scanner stdinjob = new Scanner(System.in);

		// パーティ１のプレイヤーの名前を入力させてキャラクターを生成
		for (int i = 0; i < Allpartymenber; i++) {

			System.out.println("パーティ" + partyNO + "の" + (i + 1)
					+ "人目の名前を入力してください");
			String playerName = stdinname.nextLine();
			System.out.println("職業を選んで下さい");
			System.out.println("1：戦士\n2：魔法使い\n3:僧侶\n4:勇者");
			int playerJob = stdinjob.nextInt();

			// ■処理の流れ
			// プレイヤーのキャラクターを作成

			switch (playerJob) {
			case 1:
				player = new Fighter(playerName);
				player.partynum = partyNO;
				break;
			case 2:
				player = new Wizard(playerName);
				player.partynum = partyNO;
				break;
			case 3:
				player = new Priest(playerName);
				player.partynum = partyNO;
				break;
			case 4:
				player = new Brave(playerName);
				player.partynum = partyNO;
				break;
			default:
				System.out.println("該当する選択肢を選んでください");
				playerJob = stdinjob.nextInt();
			}

			if (partyNO == 1) {
				makeparty.AppendPlayer(player);
				turnorder.add(player);
			} else {
				makeparty2.AppendPlayer(player);
				turnorder.add(player);
			}

			player = null;

		}

	}

	// 作戦を立てる
	public Operation Choiceoperation() {
		Operation choiseoperation = null;

		// 作戦を入力
		Scanner stdinpulan = new Scanner(System.in);
		int INputchoiseOperation = stdinpulan.nextInt();
		System.out.println("選んだ作戦番号：" + INputchoiseOperation);

		// 一致する作戦を検索
		int pulanslistNO = 0;
		while (true) {
			if (OperationList.pulansList.indexOf(OperationList.pulansList
					.get(INputchoiseOperation - 1)) == -1) {
				System.out.println("番号が違います。もう一度選んでください");
				// 作戦を入力
				INputchoiseOperation = stdinpulan.nextInt();
			} else {
				pulanslistNO = INputchoiseOperation - 1;
				break;
			}
		}

		choiseoperation = OperationList.pulansList.get(pulanslistNO);

		return choiseoperation;
	}
	
	// バトル開始の表示
	public void PrintParty(int partyNO){
		if(partyNO  == 1){
			for (int i = 0; i < makeparty.GetMembers().size(); i++) {
				makeparty.GetMembers().get(i).PrintStatus();

				switch(makeparty.GetMembers().get(i).job){
				case 1:
					System.out.println("職業：戦士");
					break;
				case 2:
					System.out.println("職業：魔法使い");
					break;
				case 3:
					System.out.println("職業：僧侶");
					break;
				case 4:
					System.out.println("職業：勇者");
					break;
				}
			}

			}else{
				for (int i = 0; i < makeparty2.GetMembers().size(); i++) {
					makeparty2.GetMembers().get(i).PrintStatus();

					switch(makeparty2.GetMembers().get(i).job){
					case 1:
						System.out.println("職業：戦士");
						break;
					case 2:
						System.out.println("職業：魔法使い");
						break;
					case 3:
						System.out.println("職業：僧侶");
						break;
					case 4:
						System.out.println("職業：勇者");
						break;
					}

				}
			}
		}
	}
