package namebattler.manager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import namebattler.player.Player;

public class GameManager {
	public  void start() {
		// ==================================================
		// バトル開始準備
		// ==================================================
		// プレイヤーの名前を入力させるキャラクターを生成
		// ■処理の流れ
        // プレイヤーキャラクターを作成して、パーティを設定
		ArrayList<Party> battleParty = new ArrayList<Party>();
		for(int i = 0; i < 2; i++) battleParty.add( new Party(i) );

		// ==================================================
		// バトル処理
		// ==================================================
        // バトル開始の表示
		for(int i = 0; i < battleParty.size(); i++) {
			System.out.println( battleParty.get(i).GetPartyName() );
			for(int j = 0; j < battleParty.get(i).GetMembers().size(); j++) {
				( battleParty.get(i).GetMembers().get(j) ).PrintStatus();
			}
			System.out.println("");
		}

        System.out.println("=== バトル開始 ===");

        int turnNumber = 1;
        // 最大でも20ターンまで
        GameTurn:
        while ( turnNumber <= 20 ) {
            if(turnNumber > 1) System.out.println("\n------------------------------------------------------------");
            System.out.printf("- ターン%d -\n", turnNumber);
            System.out.println("");

            // 5ターンごとにパーティー毎にアイテムを使用するターン発生
            if( (turnNumber % 5) == 0 ) {
            	System.out.println("============================================================\n");
            	System.out.println("アイテムボックスが現れた！！！\n");

            	//アイテムがランダムで使用される
            	for(int i = 0; i < battleParty.size(); i++) battleParty.get(i).itMana.itemUse(battleParty.get(i));

            	System.out.println("============================================================\n");
            }

            //作戦を決める処理
    		for(int i = 0; i < 2; i++) {
    			//作戦番号
    			int tacticalNo;

    			//プレイヤーチームの場合
    			if(i == 0) {
    				//作戦の選択
    				System.out.println("プレイヤーの名前を入力してください。");
    				Scanner scan = new Scanner(System.in);
    				System.out.println("作戦をを選択してください。");
    				System.out.println("1:一斉攻撃");
    				System.out.println("2:HPの低い敵を攻撃");
    				System.out.println("3:魔法攻撃優先");
    				System.out.println("4:回復職を攻撃");
    				System.out.println("5:仲間を回復");

    				String inputNum = scan.nextLine();
    				while(!(inputNum.matches("[1-5]"))) {
    					System.out.println("1～5の数字をを選択してください。");
    					inputNum = scan.nextLine();
    				}

    				//選択した数字(preNum)を数値(inputNum)へ変換
    				tacticalNo = Integer.parseInt(inputNum);

    			//敵チームの場合ランダムで決める
    			}else {
    				tacticalNo = new Random().nextInt(5) + 1;
    			}

    			//選択番号に合わせて作戦を決定
    			battleParty.get(i).SetTacticalNo(tacticalNo);
    			if(tacticalNo == 1) {
    				System.out.println(battleParty.get(i).GetPartyName() + "の作戦：一斉攻撃");
    			}else if(tacticalNo == 2) {
    				System.out.println(battleParty.get(i).GetPartyName() + "の作戦：HPの低い敵を攻撃");
    			}else if(tacticalNo == 3) {
    				System.out.println(battleParty.get(i).GetPartyName() + "の作戦：魔法攻撃優先");
    			}else if(tacticalNo == 4) {
    				System.out.println(battleParty.get(i).GetPartyName() + "の作戦：回復職を攻撃");
    			}else {
    				System.out.println(battleParty.get(i).GetPartyName() + "の作戦：仲間を回復");
    			}
    		}

    		System.out.println("");

            //バトルを管理するクラス
            BattleManager battleManager = new BattleManager(battleParty);

            //プレイヤーをagiの高い順に並べ替える
            battleManager.agiCheck();

            //■素早さの高いプレイヤー順に攻撃ターン
            BattleTurn:
            for(int i = 0; i < battleManager.attackTum.size(); i++) {
            	//順番が来たプレイヤーをattackerに代入
            	Player attacker = battleManager.attackTum.get(i);

            	//攻撃ターンのプレイヤーのHPが0ならスキップ
            	if(attacker.GetHP() <= 0) continue;

            	//作戦に沿って行動
        		//作戦管理クラス
        		TacticalManager tacticalMana = new TacticalManager(battleParty, attacker);

            	//攻撃プレイヤーが選択している作戦の実行
        		tacticalMana.tacticalPlay();

            	//ターン開始時にステータスチェックで攻撃プレイヤーが死んだ場合
            	if(attacker.GetHP() <= 0) {
            		//attackerをパーティーの死亡者リストに追加
            		tacticalMana.attackP.AppendDelPlayer(attacker);
            		//attackerをパーティーの戦闘参加リストから削除
            		tacticalMana.attackP.RemovePlayer(attacker);

            		//attackerが相手パーティーの一斉攻撃ターゲットの場合、相手パーティーのIntensChをfalseに
           			if(tacticalMana.targetP.GetIntensTarget() == attacker) {
           				tacticalMana.targetP.SetIntensCh(false);
           			}
            	}

            	//ターン終了後、相手のHPが0の場合は対象パーティーから除く,一斉攻撃の終了
            	if (tacticalMana.activeTa.GetTargetPlayer().GetHP() <= 0) {
            		//ターゲットをパーティーの死亡者リストに追加
            		tacticalMana.targetP.AppendDelPlayer(tacticalMana.activeTa.GetTargetPlayer());
            		//ターゲットをパーティーの戦闘参加リストから削除
            		tacticalMana.targetP.RemovePlayer(tacticalMana.activeTa.GetTargetPlayer());

            		//ターゲットがパーティーの一斉攻撃ターゲットの場合、パーティーのIntensChをfalseに
            		if(tacticalMana.attackP.GetIntensTarget() == tacticalMana.activeTa.GetTargetPlayer()) {
	        			tacticalMana.attackP.SetIntensCh(false);
            		}
            	}

            	//System.out.println("");
            	System.out.println("------------------------------");
                //バトルの終了判定(どちらかのパーティーメンバーがいなくなったらゲーム終了)
            	for(int j = 0; j < battleParty.size(); j++) {
            		if( battleParty.get(j).GetMembers().isEmpty() ) break BattleTurn;
            	}
            }

            //ターン終了時に一斉攻撃の解除
            for(int i = 0; i < 2; i++) {
    			battleParty.get(i).SetIntensCh(false);
    		}

            // ターン終了時のステータスを表示
            for(int i = 0; i < battleParty.size(); i++) {
            	System.out.println( "\n" + battleParty.get(i).GetPartyName() );
            	//パーティーメンバーが全滅した場合
            	if( ( battleParty.get(i).GetMembers().isEmpty() ) ) {
        			System.out.println("全滅した...");
        		//パーティーメンバーが残っている場合は現状ステータスを表示
            	}else {
        			for(int j = 0; j < battleParty.get(i).GetMembers().size(); j++) {
        				( battleParty.get(i).GetMembers().get(j) ).PrintStatus();
        			}
        		}
            }

            //バトルの終了判定(どちらかのパーティーメンバーがいなくなったらゲーム終了)
        	for(int i = 0; i < battleParty.size(); i++) {
        		if( battleParty.get(i).GetMembers().isEmpty() ) break GameTurn;
        	}

            // 次のターン
            turnNumber++;
        }

		// ==================================================
		// 終了処理
		// ==================================================
        // 勝ち負けの表示(残っているパーティーメンバーが多い方が勝ち)
        System.out.println("\n------------------------------------------------------------");
        System.out.println("=== バトル結果 ===");
        if(battleParty.get(0).GetMembers().size() == battleParty.get(1).GetMembers().size()) {
        	System.out.print("引分け");
        }else if(battleParty.get(0).GetMembers().size() > battleParty.get(1).GetMembers().size()){
        	System.out.print( battleParty.get(0).partyName + "の勝利！！");
        }else {
        	System.out.print( battleParty.get(1).partyName + "の勝利！！");
        }
	}

}
