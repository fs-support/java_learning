package getMonster;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {

	//出現モンスターのリスト
	static ArrayList<Monster> popMonsterList = new ArrayList<Monster>();

	//捕獲玉のリスト
	static ArrayList<CaptureBall> ballList = new ArrayList<CaptureBall>();

	//捕獲モンスターのリスト
	static ArrayList<Monster> getMonsterList = new ArrayList<Monster>();

	//選ばれたモンスター
	static Monster encountMonster = null;

	//乱数
	static Random rand = new Random();

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//リストを作成
		makeMonsterList();
		makeBallList();

		//モンスター登場数
		int monsters = 10;

		//モンスター番号
		int monsterNum = 1;

		//モンスター出現率の合計
		int totalEncount = 0;
		for (int i = 0; i < popMonsterList.size(); i++) {
			totalEncount += popMonsterList.get(i).Encount();
		}

		//捕獲玉の合計所持数
		int totalBall = 0;
		for (int i = 0; i < ballList.size(); i++) {
			totalBall += ballList.get(i).Count();
		}

		//捕獲ポイント合計
		int totalPoint = 0;

		//捕獲フラグ(捕獲成功でtrue)
		boolean captureFlag = true;

		//Scanner
		Scanner scan = new Scanner(System.in);

		//出現10体以下かつボール1個以上で続行
		while (monsterNum <= monsters && 1 <= totalBall) {
			//捕獲フラグがtrueで表示
			if (captureFlag) {

				//出現モンスターの決定
				encountMonster = SelectingMonster(totalEncount);

				//-----------------------------------------------------------------------------------------------------
				//ディスプレイ
				//-----------------------------------------------------------------------------------------------------
				//モンスター
				System.out.println(monsterNum + "体目出現！！=========================================\n");
				System.out.println(encountMonster.Name());
				System.out.println("HP:" + encountMonster.HP());
				System.out.println("POW:" + encountMonster.Power());
				System.out.println("DEF:" + encountMonster.Defense() + "\n");
				captureFlag = false;
			}
			//捕獲玉選択画面-------------------------------------

			for (int i = 0; i < ballList.size(); i++) {
				CaptureBall balls = ballList.get(i);
				System.out.println("(" + (i + 1) + ")" + balls.Name() + "を使う(残り" + balls.Count() + "個。捕獲成功率："
						+ PercentCalc(encountMonster.Capture() + balls.Correct()) + "％) ");
			}
			System.out.println("(" + (ballList.size() + 1) + ")モンスターを見逃す\n");

			//プレイヤー入力
			System.out.print("選択：");
			int input = scan.nextInt();
			System.out.println();

			//選択による処理
			//捕獲玉使用
			if(0 < input && input <= ballList.size()) {
				captureFlag = CaptureJudge(ballList.get(input - 1), encountMonster);
			}
			//見逃す
			else if (input == (ballList.size() + 1)) {
				//次のモンスターを選ぶために一時的にフラグをtrue
				captureFlag = true;
				System.out.println("モンスターを見逃した\n");
			}
			//再入力
			else {
				System.out.println("再入力してください\n");
			}
			if (captureFlag) {
				monsterNum++;
			}
			//ボール合計の反映
			totalBall = 0;
			for (int i = 0; i < ballList.size(); i++) {
				totalBall += ballList.get(i).Count();
			}
			if(totalBall <= 0) {
				System.out.println("ボールが無くなりました。もう捕獲できません。\n");
			}
		}

		System.out.println("捕獲履歴-----------------------------------------\n");

		//捕獲したモンスターの数
		int getMonsterNum = getMonsterList.size();

		//捕獲モンスター一匹以上
		if (0 < getMonsterNum) {
			for (int i = 0; i < getMonsterNum; i++) {
				System.out.println(getMonsterList.get(i).Name());
				totalPoint += getMonsterList.get(i).Point();
			}
		}
		//捕獲モンスターなし
		else {
			System.out.println("捕獲したモンスターはいません");
		}
		System.out.println("\n捕獲合計ポイント：" + totalPoint);
	}

	//モンスターリスト作成
	public static void makeMonsterList() {
		//モンスター生成(名前、HP、攻撃、防御、出現率、捕獲成功率)
		popMonsterList.add(new Monster("ザコモン", 30, 20, 20, 30, 72));
		popMonsterList.add(new Monster("フツモン", 50, 20, 30, 30, 50));
		popMonsterList.add(new Monster("ツヨモン", 100, 50, 30, 25, 28));
		popMonsterList.add(new Monster("ボスモン", 100, 50, 50, 10, 25));
		popMonsterList.add(new Monster("レアモン", 150, 100, 100, 5, 14));
	}

	//捕獲玉リスト作成
	public static void makeBallList() {
		//捕獲玉
		ballList.add(new CaptureBall("ノーマル捕獲玉", 0, 6));
		ballList.add(new CaptureBall("スーパー捕獲玉", 20, 3));
		ballList.add(new CaptureBall("ミラクル捕獲玉", 50, 1));
	}

	//モンスター選定
	public static Monster SelectingMonster(int totalEncount) {
		int randomNum = rand.nextInt(totalEncount);	//0~totalEncount未満で乱数作成
		Monster selectedMonster = null;

		//出現率を足していく変数
		int encountSum = 0;

		//リストのカウンタ
		int listCount = 0;
		for (int i = 0; i <= randomNum; i += popMonsterList.get(listCount - 1).Encount()) {	//listCountをインクリメントする前のモンスター出現率を足す
			encountSum += popMonsterList.get(listCount).Encount();
			for (int j = i; j < encountSum; j++) {
				selectedMonster = popMonsterList.get(listCount);
			}
			listCount++;
		}

		return selectedMonster;

		//		//ザコモン出現 @1^30
		//		if (randomNum <= zakomon.Encount()) {
		//			encountMonster = zakomon;
		//		}
		//		//フツモン出現 @31~60
		//		else if (zakomon.Encount() < randomNum && randomNum <= zakomon.Encount() + hutumon.Encount()) {
		//			encountMonster = hutumon;
		//		}
		//		//ツヨモン出現 @61~85
		//		else if (zakomon.Encount() + hutumon.Encount() < randomNum
		//				&& randomNum <= zakomon.Encount() + hutumon.Encount() + tuyomon.Encount()) {
		//			encountMonster = tuyomon;
		//		}
		//		//ボスモン出現 @86~95
		//		else if (zakomon.Encount() + hutumon.Encount() + tuyomon.Encount() < randomNum
		//				&& randomNum <= zakomon.Encount() + hutumon.Encount() + tuyomon.Encount() + bosumon.Encount()) {
		//			encountMonster = bosumon;
		//		}
		//		//レアモン出現 @96~100
		//		else {
		//			encountMonster = reamon;
		//		}
		//		return encountMonster;
	}

	//ボール選択の判定
	public static boolean CaptureJudge(CaptureBall ball, Monster monster) {
		//捕獲玉0
		if (ball.Count() <= 0) {
			System.out.println("この捕獲玉はもうありません。\n");
			return false;
		}
		//捕獲の判定
		else {
			//ボール使用
			ball.Use();
			//成功
			if (monster.Judgement(ball.Correct())) {
				System.out.println(monster.Name() + "を捕まえた！\n");
				getMonsterList.add(monster);
				return true;
			}
			//失敗
			else {
				System.out.println("捕獲失敗！\n");
				//再度選択する
				return false;
			}
		}
	}

	//捕獲成功率の表示を上限100%に補正
	public static int PercentCalc(int total) {
		if (total >= 100) {
			return 100;
		} else {
			return total;
		}
	}
}
