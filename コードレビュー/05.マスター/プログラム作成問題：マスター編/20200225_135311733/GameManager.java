package namebattler_2;

// JRE 1.8を使用しています

public class GameManager {

	public void Start() {
		//var stdin = new Scanner(System.in);
		// ==================================================
		// バトル開始準備
		// ==================================================
		// パーティーAの3人を作成
		System.out.println("パーティーAの3人を入力");
		// プレイヤー１の名前と職業を入力させてキャラクターを生成
		System.out.println("プレイヤー１の名前を入力してください：ケーンワカバ");
		String player1Name = "ケーンワカバ";
		System.out.println("プレイヤー１の職業を入力してください");
		System.out.println("1:戦士 2:魔法使い 3:僧侶 4:勇者 ：1");
		int player1Job = 1;
		System.out.println("");
		// プレイヤー２の名前と職業を入力させてキャラクターを生成
		System.out.println("プレイヤー２の名前を入力してください：タップオセアノ");
		String player2Name = "タップオセアノ";
		System.out.println("プレイヤー２の職業を入力してください");
		System.out.println("1:戦士 2:魔法使い 3:僧侶 4:勇者 ：2");
		int player2Job = 2;
		System.out.println("");
		// プレイヤー３の名前と職業を入力させてキャラクターを生成
		System.out.println("プレイヤー３の名前を入力してください：ライトニューマン");
		String player3Name = "ライトニューマン";
		System.out.println("プレイヤー３の職業を入力してください");
		System.out.println("1:戦士 2:魔法使い 3:僧侶 4:勇者 ：3");
		int player3Job = 3;
		System.out.println("");

		// パーティーBの3人を作成
		System.out.println("パーティーBの3人を入力");
		// プレイヤー４の名前と職業を入力させてキャラクターを生成
		System.out.println("プレイヤー４の名前を入力してください：スバルリョーコ");
		String player4Name = "スバルリョーコ";
		System.out.println("プレイヤー４の職業を入力してください");
		System.out.println("1:戦士 2:魔法使い 3:僧侶 4:勇者 ：1");
		int player4Job = 1;
		System.out.println("");
		// プレイヤー５の名前と職業を入力させてキャラクターを生成
		System.out.println("プレイヤー５の名前を入力してください：アマノヒカル");
		String player5Name = "アマノヒカル";
		System.out.println("プレイヤー５の職業を入力してください");
		System.out.println("1:戦士 2:魔法使い 3:僧侶 4:勇者 ：2");
		int player5Job = 2;
		System.out.println("");
		// プレイヤー６の名前と職業を入力させてキャラクターを生成
		System.out.println("プレイヤー６の名前を入力してください：マキイズミ");
		String player6Name = "マキイズミ";
		System.out.println("プレイヤー６の職業を入力してください");
		System.out.println("1:戦士 2:魔法使い 3:僧侶 4:勇者 ：3");
		int player6Job = 3;
		System.out.println("");
		//stdin.close();

        // プレイヤー１のキャラクターを作成
		Player player1;
		switch(player1Job) {
			case 2:
				player1 = new Wizard(player1Name);
				break;
			case 3:
				player1 = new Priest(player1Name);
				break;
			case 4:
				player1 = new Hero(player1Name);
				break;
			case 1:
			default:
				player1 = new Fighter(player1Name);
				break;
		}
        // プレイヤー２のキャラクターを作成
		Player player2;
		switch(player2Job) {
			case 2:
				player2 = new Wizard(player2Name);
				break;
			case 3:
				player2 = new Priest(player2Name);
				break;
			case 4:
				player2 = new Hero(player2Name);
				break;
			case 1:
			default:
				player2 = new Fighter(player2Name);
				break;
		}

		// プレイヤー３のキャラクターを作成
		Player player3;
		switch(player3Job) {
			case 2:
				player3 = new Wizard(player3Name);
				break;
			case 3:
				player3 = new Priest(player3Name);
				break;
			case 4:
				player3 = new Hero(player3Name);
				break;
			case 1:
			default:
				player3 = new Fighter(player3Name);
				break;
		}

		// プレイヤー４のキャラクターを作成
		Player player4;
		switch(player4Job) {
			case 2:
				player4 = new Wizard(player4Name);
				break;
			case 3:
				player4 = new Priest(player4Name);
				break;
			case 4:
				player4 = new Hero(player4Name);
				break;
			case 1:
			default:
				player4 = new Fighter(player4Name);
				break;
		}

		// プレイヤー５のキャラクターを作成
		Player player5;
		switch(player5Job) {
			case 2:
				player5 = new Wizard(player5Name);
				break;
			case 3:
				player5 = new Priest(player5Name);
				break;
			case 4:
				player5 = new Hero(player5Name);
				break;
			case 1:
			default:
				player5 = new Fighter(player5Name);
				break;
		}

		// プレイヤー６のキャラクターを作成
		Player player6;
		switch(player6Job) {
			case 2:
				player6 = new Wizard(player6Name);
				break;
			case 3:
				player6 = new Priest(player6Name);
				break;
			case 4:
				player6 = new Hero(player6Name);
				break;
			case 1:
			default:
				player6 = new Fighter(player6Name);
				break;
		}

		// パーティAの作成
		Party partyA = new Party();
		partyA.AppendPlayer(player1);
		partyA.AppendPlayer(player2);
		partyA.AppendPlayer(player3);
		// パーティBの作成
		Party partyB = new Party();
		partyB.AppendPlayer(player4);
		partyB.AppendPlayer(player5);
		partyB.AppendPlayer(player6);
		// 全体の行動順作成
		Party partyAgiSort = new Party();
		partyAgiSort.AppendPlayer(partyA.GetMembers().get(0));
		partyAgiSort.AppendPlayer(partyA.GetMembers().get(1));
		partyAgiSort.AppendPlayer(partyA.GetMembers().get(2));
		partyAgiSort.AppendPlayer(partyB.GetMembers().get(0));
		partyAgiSort.AppendPlayer(partyB.GetMembers().get(1));
		partyAgiSort.AppendPlayer(partyB.GetMembers().get(2));
		partyAgiSort.SortAgi();

		// ==================================================
		// バトル処理
		// ==================================================
        // バトル開始の表示
		System.out.println("パーティーA");
        partyA.GetMembers().get(0).PrintStatus();
        partyA.GetMembers().get(1).PrintStatus();
        partyA.GetMembers().get(2).PrintStatus();
        System.out.println("");
        System.out.println("パーティーB");
        partyB.GetMembers().get(0).PrintStatus();
        partyB.GetMembers().get(1).PrintStatus();
        partyB.GetMembers().get(2).PrintStatus();

        System.out.println("=== バトル開始 ===");

        int turnNumber = 1;
        int partyAHP = 0;
        int partyBHP = 0;
        // 最大でも20ターンまで
        while ( turnNumber <= 20 ) {
        	System.out.println("--------------------------------");
            System.out.printf("- ターン%d -\n", turnNumber);

            int size = partyAgiSort.GetMembers().size();
            for(int i = 0;i < size; i++) {
            	// 素早さが高い順に行動
            	// HPが0の場合は行動しない
            	Player attackPlayer = partyAgiSort.GetMembers().get(i);
            	if(attackPlayer.GetHP() > 0){
            		// 攻撃対象のパーティーを決定
                	if(partyA.GetMembers().contains(attackPlayer)) {
                		attackPlayer.Attack(partyA.GetMembers(),partyB.GetMembers());
                	}
                	else {
                		attackPlayer.Attack(partyB.GetMembers(),partyA.GetMembers());
                	}

            		// プレイヤーの毒ダメージ判定
            		if(attackPlayer.GetPoison() == true) {
            			attackPlayer.PoisDamage();
            		}

            		// 各パーティー全員のHPが0ならターン終了。ループを抜ける
            		partyAHP = partyA.GetMembers().get(0).GetHP() + partyA.GetMembers().get(1).GetHP() + partyA.GetMembers().get(2).GetHP();
            		partyBHP = partyB.GetMembers().get(0).GetHP() + partyB.GetMembers().get(1).GetHP() + partyB.GetMembers().get(2).GetHP();
            		if(partyAHP <= 0) {
            			System.out.println("\nパーティーAは全滅した...");
            			break;
            		}
            		if(partyBHP <= 0) {
            			System.out.println("\nパーティーBは全滅した...");
            			break;
            		}
            	}
            }

            // ターン終了時のステータスを表示
            System.out.println("");
            System.out.println("パーティーA");
            partyA.GetMembers().get(0).PrintStatus();
            partyA.GetMembers().get(1).PrintStatus();
            partyA.GetMembers().get(2).PrintStatus();
            System.out.println("");
            System.out.println("パーティーB");
            partyB.GetMembers().get(0).PrintStatus();
            partyB.GetMembers().get(1).PrintStatus();
            partyB.GetMembers().get(2).PrintStatus();
            System.out.println("");

            // 各パーティー全員のHPが0なら戦闘終了
    		if(partyA.GetMembers().get(0).GetHP() + partyA.GetMembers().get(1).GetHP() + partyA.GetMembers().get(2).GetHP() <= 0) {
    			break;
    		}
    		if(partyB.GetMembers().get(0).GetHP() + partyB.GetMembers().get(1).GetHP() + partyB.GetMembers().get(2).GetHP() <= 0) {
    			break;
    		}

        	// 次のターン
            turnNumber = turnNumber + 1;
        }

        // 勝ち負けの表示(ＨＰが多い方が勝ち)
        System.out.println("");
        if (partyAHP > partyBHP){
            System.out.println("パーティーAの勝利！！");
        } else {
            System.out.println("パーティーBの勝利！！");
        }

		// ==================================================
		// 終了処理
		// ==================================================
	}
}
