package fs05_Master_Refactoring;

import java.util.Scanner;

public class GameManager {

	void start(){

		// パーティー参加枠
		final int slots = 3;

		// キャラクターを作成
		// 複数のフィールドを持つインスタンスの初期化には「null」を入れる
		Player player1 = null;
		Player player2 = null;

		Scanner stdin = new Scanner(System.in);
		String name1;
		String name2;

		String[] joblist = {"戦士","魔法使い","僧侶","母ちゃん"};
		String job;

		// Partyクラスのインスタンス生成
		Party party1 = new Party();
		Party party2 = new Party();

		int strategy;	// 作戦用変数


		System.out.println("『??ゾウさん』チーム / " + slots + "人分のキャラクター名とジョブを入力して下さい。\n");
		System.out.println("【ジョブリスト】");
		for(String j:joblist){
			System.out.println(" ■ " + j);
		}
		System.out.println();

		// パーティー参加人数分の名前とジョブを入力
		for(int i = 0; i<slots; i++){
			System.out.print("??名前(" + (i+1) + "人目)：");
			name1 = stdin.next();
			System.out.print("??ジョブ(" + (i+1) + "人目)：");
			job = stdin.next();

			switch(job){
			case "戦士": player1 = new Fighter(name1);
			break;
			case "魔法使い":player1 = new Wizard(name1);
			break;
			case "僧侶": player1 = new Priest(name1);
			break;
			case "母ちゃん": player1 = new Mother(name1);
			break;
			}

			// 所属パーティー、敵パーティーを決定
			player1.setPartyInformation(party1,party2);
			// 所属パーティーにメンバーを追加
			party1.AppendPlayer(player1);

			System.out.println();

		}


		System.out.println("【作戦リスト】");
		for(int i = 0; i<party1.GetStrategyList().size(); i++){
			System.out.println(" " + (i+1) + party1.GetStrategyList().get(i).GetStrategyName());
		}
		System.out.println();
		System.out.print("『??ゾウさん』チームの作戦を選択（該当する作戦の番号を入力）して下さい：");
		strategy = stdin.nextInt();
		party1.setStrategy(strategy-1);
		System.out.println();

		// ステータス表示
		System.out.println("『??ゾウさん』チーム（作戦：" + party1.getStrategy().GetStrategyName() + "）");
		for(int i = 0; i<slots; i++){
			System.out.print("?? ");
			party1.GetMembers().get(i).PrintStatus();
		}

		System.out.println("\n");


		System.out.println("『??クマさん』チーム / " + slots + "人分のキャラクター名とジョブを入力して下さい。\n");
		System.out.println("【ジョブリスト】");
		for(String j:joblist){
			System.out.println(" ■ " + j);
		}
		System.out.println();

		// パーティー参加人数分の名前とジョブを入力
		for(int i = 0; i<slots; i++){
			System.out.print("??名前(" + (i+1) + "人目)：");
			name2 = stdin.next();
			System.out.print("??ジョブ(" + (i+1) + "人目)：");
			job = stdin.next();

			switch(job){
			case "戦士": player2 = new Fighter(name2);
			break;
			case "魔法使い":player2 = new Wizard(name2);
			break;
			case "僧侶": player2 = new Priest(name2);
			break;
			case "母ちゃん": player2 = new Mother(name2);
			break;
			}

			// 所属パーティー、敵パーティーを決定
			player2.setPartyInformation(party2 , party1);
			// 所属パーティーにメンバーを追加
			party2.AppendPlayer(player2);

			System.out.println();

		}


		System.out.println("【作戦リスト】");
		for(int i = 0; i<party2.GetStrategyList().size(); i++){
			System.out.println(" " + (i+1) + party2.GetStrategyList().get(i).GetStrategyName());
		}
		System.out.println();
		System.out.print("『??クマさん』チームの作戦を選択（該当する作戦の番号を入力）して下さい：");
		strategy = stdin.nextInt();
		party2.setStrategy(strategy-1);
		System.out.println();

		// ステータス表示
		System.out.println("『??クマさん』チーム（作戦：" + party2.getStrategy().GetStrategyName() + "）");
		for(int i = 0; i<slots; i++){
			System.out.print("?? ");
			party2.GetMembers().get(i).PrintStatus();
		}

		System.out.println("\n");


		// バトル開始の表示
		System.out.println("=== バトル開始 ===");

		int turnNumber = 1;
		// 最大でも20ターンまで(攻撃ターンと敗北判定の繰り返し)
		while (turnNumber<=20) {
			System.out.println("--------------------------------");
			System.out.printf("- ターン%d -\n", turnNumber);
			System.out.println();

			// AGIの高い順に行動させるための準備
			// →２つのパーティーのメンバーを１つのリストへまとめる
			Party allMembers = new Party();
			for(int i = 0; i<party1.GetMembers().size(); i++){
				allMembers.GetMembers().add(party1.GetMembers().get(i));
			}
			for(int i = 0; i<party2.GetMembers().size(); i++){
				allMembers.GetMembers().add(party2.GetMembers().get(i));
			}

			// AGIの高い順に並べ替える(バブルソート使用)
			for(int i = 0; i<allMembers.GetMembers().size()-1; i++){
				for(int j = 0; j<allMembers.GetMembers().size()-i-1; j++){
					if(allMembers.GetMembers().get(j).GetAGI() < allMembers.GetMembers().get(j+1).GetAGI()){
						Player keep = allMembers.GetMembers().get(j);
						allMembers.GetMembers().set(j, allMembers.GetMembers().get(j+1));
						allMembers.GetMembers().set(j+1, keep);
					}
				}
			}


			// AGIの高い順に攻撃
			while(allMembers.GetMembers().size()!=0){
				Player attacker = allMembers.GetMembers().get(0);

				// どちらかのパーティーが全滅していたらループ(ターン)を抜ける
				if((party1.GetMembers().size()==0) || (party2.GetMembers().size()==0)){
					break;
				}


				// 作戦を実行して攻撃
				attacker.GetMyPartyInformation().Operation(attacker , attacker.GetEnemyPartyInformation());

				// ダメージ処理済みのターゲットを用意して変数[defender]に代入する
				attacker.GetMyPartyInformation().Processed();
				Player defender = attacker.GetMyPartyInformation().ProcessedPlayer();


				// 戦闘不能プレイヤーをゲームから除外
				if(defender.GetHP() <= 0) {
					System.out.println("（" + defender.GetName() + "が戦闘不能によりパーティーから離脱しました）");

					// 戦闘不能プレイヤーをallMembersリストから離脱
					for(int i = 0; i<allMembers.GetMembers().size(); i++){
						if(allMembers.GetMembers().get(i)==defender){
							allMembers.RemovePlayer(allMembers.GetMembers().get(i));
						}
					}
					// 戦闘不能プレイヤーを所属パーティーから離脱
					defender.GetMyPartyInformation().RemovePlayer(defender);

				}

				// Attackerの攻撃が済んだらallMembersリストから０番目を除外
				allMembers.RemovePlayer(allMembers.GetMembers().get(0));

			}


			System.out.println();

			// ターン終了時のステータスを表示
			System.out.println("『??ゾウさん』チーム（作戦：" + party1.getStrategy().GetStrategyName() + "）");
			if(party1.GetMembers().size()==0){
				System.out.println("（パーティーが全滅しました）");
			} else {
				for(int i = 0; i<party1.GetMembers().size(); i++){
					System.out.print("?? ");
					party1.GetMembers().get(i).PrintStatus();
				}
			}
			System.out.println();

			System.out.println("『??クマさん』チーム（作戦：" + party2.getStrategy().GetStrategyName() + "）");
			if(party2.GetMembers().size()==0){
				System.out.println("（パーティーが全滅しました）");
			} else {
				for(int i = 0; i<party2.GetMembers().size(); i++){
					System.out.print("?? ");
					party2.GetMembers().get(i).PrintStatus();
				}
			}
			System.out.println();


			// どちらかのパーティーが全滅していたらループ(全体)を抜ける
			if((party1.GetMembers().size()==0) || (party2.GetMembers().size()==0)){
				break;
			}

			// 次のターン
			turnNumber = turnNumber + 1;

		}


		// 勝ち負けの表示(パーティーメンバーの残存数で判定)
		System.out.println();
		if (party1.GetMembers().size() > party2.GetMembers().size()){
			System.out.println("『??ゾウさん』チームの勝利！！");
		} else if(party2.GetMembers().size() > party1.GetMembers().size()){
			System.out.println("『??クマさん』チームの勝利！！");
		} else {
			System.out.println("引き分け（両チーム残存メンバー同数のため）");
		}

	}

}
