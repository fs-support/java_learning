package namebattler;

import java.util.Random;

public class GameManager {

	public  void start() {
		// ==================================================
		// バトル開始準備
		// ==================================================

		//作戦管理クラス
		TacticalManager TA = new TacticalManager();

					//==================================
					//仮ステータス
					Random ran = new Random();

					String[] S1 = {"Aあいうえお", "Aかきくけこ", "Aさしすせそ"};
					int I1[] = new int[3];
					for(int i = 0; i < 3; i++) {
						I1[i] = ran.nextInt(4) + 1;
					}

					String[] S2 = {"Bたちつてと", "Bなにぬねの", "Bはひふへほ"};
					int I2[] = new int[3];
					for(int i = 0; i < 3; i++) {
						I2[i] = ran.nextInt(4) + 1;
					}
					//==================================

		// プレイヤーの名前を入力させるキャラクターを生成
		// ■処理の流れ
        // プレイヤーキャラクターを作成して、パーティを設定
		//パーティを管理するクラス
		Party partyA = new Party(/*＝＝＝＝＝＝＝＝＝＝後で消す＝＝＝＝＝＝＝＝＝＝*/S1, I1, 1);
		Party partyB = new Party(/*＝＝＝＝＝＝＝＝＝＝後で消す＝＝＝＝＝＝＝＝＝＝*/S2, I2, 2);

		// ==================================================
		// バトル処理
		// ==================================================
        // バトル開始の表示
		System.out.println("パーティーA");
		for(int i = 0; i < partyA.GetMembers().size(); i++) (partyA.GetMembers().get(i)).PrintStatus();
		System.out.println("\nパーティーB");
		for(int i = 0; i < partyB.GetMembers().size(); i++) (partyB.GetMembers().get(i)).PrintStatus();

        System.out.println("");
        System.out.println("=== バトル開始 ===");

        int turnNumber = 1;
        // 最大でも20ターンまで
        while ( turnNumber <= 20 ) {
            if(turnNumber > 1) System.out.println("\n--------------------------------");
            System.out.printf("- ターン%d -\n", turnNumber);



            // 5ターンごとにパーティー毎にアイテムを使用するターン発生
            if( (turnNumber % 5) == 0 ) {
            	System.out.println("============================================================\n");
            	System.out.println("アイテムボックスが現れた！！！\n");

            	partyA.itMana.itemUse(partyA);
            	partyB.itMana.itemUse(partyB);

            	System.out.println("============================================================\n");
            }

          //バトルを管理するクラス
            BattleManager battleManager = new BattleManager(partyA,partyB);

            //プレイヤーをagiの高い順に並べ替える
            battleManager.agiCheck();

         // ■素早さの高いプレイヤー順に攻撃ターン
            for(int i = 0; i < battleManager.attackTum.size(); i++) {
            	Player attacker = battleManager.attackTum.get(i);

            	//攻撃ターンのプレイヤーのHPが0ならスキップ
            	if(attacker.hp <= 0) {
            		continue;
            	}

            	attacker.Attack(TA.targetT(attacker, partyA, partyB));

            	//ターン開始時にステータスチェックで攻撃プレイヤーが死んだ場合
            	if(attacker.hp <= 0) {
           			TA.attackP.AppendDelPlayer(attacker);
           			TA.attackP.RemovePlayer(attacker);

           			if(TA.targetP.IntensTarget == attacker) {
           				TA.targetP.intensCh = false;
           			}
            	}

            	//ターン終了後、相手のHPが0の場合は対象パーティーから除く,一斉攻撃の終了
            	if (TA.targetM.hp <= 0) {

            		TA.targetP.AppendDelPlayer(TA.targetM);
            		TA.targetP.RemovePlayer(TA.targetM);

            		if(!(attacker.party == TA.targetM.party)) {
	        			System.out.println("作戦：一斉攻撃の終了");
	            		TA.attackP.intensCh = false;
            		}
            	}

            	System.out.println("");

                //バトルの終了判定(どちらかのパーティーメンバーがいなくなったら)
                if(partyA.GetMembers().isEmpty() || partyB.GetMembers().isEmpty()) break;
            }

            // ターン終了時のステータスを表示
            System.out.println("");
    		if(!(partyA.GetMembers().isEmpty())) {
    			System.out.println("パーティーA");
    			for(int i = 0; i < partyA.GetMembers().size(); i++) (partyA.GetMembers().get(i)).PrintStatus();
    		}
    		if(!(partyB.GetMembers().isEmpty())) {
    			System.out.println("\nパーティーB");
    			for(int i = 0; i < partyB.GetMembers().size(); i++) (partyB.GetMembers().get(i)).PrintStatus();
    		}

            //バトルの終了判定(どちらかのパーティーメンバーがいなくなったら)
            if(partyA.GetMembers().isEmpty() || partyB.GetMembers().isEmpty()) break;

            // 次のターン
            turnNumber = turnNumber + 1;
        }

		// ==================================================
		// 終了処理
		// ==================================================
        // 勝ち負けの表示(残っているパーティーメンバーが多い方が勝ち)
        System.out.println("--------------------------------");
        System.out.println("=== バトル結果 ===");
        if(partyA.GetMembers().size() == partyB.GetMembers().size()) {
        	System.out.println("引分け");
        }else if(partyA.GetMembers().size() > partyB.GetMembers().size()){
        	System.out.println("Aチームの勝利！！");
        }else {
        	System.out.println("Bチームの勝利！！");
        }

	}

}

