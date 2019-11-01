package chukyu_sakusei;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Pokemon{

	private static ArrayList<Monster> monsList = new ArrayList<Monster>();
	private static ArrayList<Integer> encountTable = new ArrayList<Integer>();
	private static ArrayList<CaptureBall> ballList = new ArrayList<CaptureBall>();
	private static ArrayList<Monster> getmonsList = new ArrayList<Monster>();
	private static Monster[] encountMons = new  Monster[1];
	private static CaptureBall[] getball = new  CaptureBall[3];
	private static Monster[] getMons = new  Monster[1];
	private static int encountCount = 0;
	private static int encountCount_max = 10;
	private static int ball_nomal = 0;
	private static int ball_super = 1;
	private static int ball_miracle = 2;
	private static int ball_allCount = 0;
	static Random rnd = new Random();

	public static void main(String[]args) {
		makeMonster();
		makeBall();
		int ball_allCount_Full = getball[ball_nomal].Count() + getball[ball_super].Count() + getball[ball_miracle].Count();
		ball_allCount = ball_allCount_Full;

		var stdin = new Scanner(System.in);

		System.out.println("開始\n");

		//*エンカウント回数が10以内かつボールが0になるまで処理を繰り返す
		while(encountCount <= encountCount_max &&  ball_allCount > 0) {
			Boolean captureJudge = false;
			Boolean notCapture = false;

			encountCount++;
			encountMons[0] = monsList.get(encountTable.get(rnd.nextInt(encountTable.size())));
			System.out.println(encountCount + "ひきめ");
			System.out.println(encountMons[0].Name() + " が あらわれた!");
			System.out.println("HP：" + encountMons[0].Hp() + " 攻撃：" + encountMons[0].Power() + " 防御：" + encountMons[0].Defense());

			while(true) {
				Boolean notHaveBall = false;
				System.out.println("どうする？");
				System.out.println("1:" + getball[ball_nomal].Name() + "を使う 残り" + getball[ball_nomal].Count() + "個。捕獲成功率：" + getball[ball_nomal].Correct() + "％");
				System.out.println("2:" + getball[ball_super].Name() + "を使う 残り" + getball[ball_super].Count() + "個。捕獲成功率：" + getball[ball_super].Correct() + "％");
				System.out.println("3:" + getball[ball_miracle].Name() + "を使う 残り" + getball[ball_miracle].Count() + "個。捕獲成功率：" + getball[ball_miracle].Correct() + "％");
				System.out.println("4:モンスターを見逃す ");

				int action = 0;
				while(true) {
					try{
						action = stdin.nextInt();
						if(action >= 1 && action <= 4) {
							break;
						}
						else {
							throw new Exception();
						}
					}
					catch(Exception e){
						System.out.println("適切な値を入力してください");
					}
				}

				switch(action) {
					case 1:
						if(getball[ball_nomal].Count() <= 0) {
							System.out.println("そのボールはもうない");
							notHaveBall = true;
						}
						else {
							System.out.println("あなたは "+ getball[ball_nomal].Name() + " を なげた!");
							getball[ball_nomal].Use();
							captureJudge = encountMons[0].Judgement(getball[ball_nomal].Correct());
						}
						break;
					case 2:
						if(getball[ball_super].Count() <= 0) {
							System.out.println("そのボールはもうない");
							notHaveBall = true;
						}
						else {
							System.out.println("あなたは "+ getball[ball_super].Name() + " を なげた!");
							getball[ball_super].Use();
							captureJudge = encountMons[0].Judgement(getball[ball_super].Correct());
						}
						break;
					case 3 :
						if(getball[ball_miracle].Count() <= 0) {
							System.out.println("そのボールはもうない");
							notHaveBall = true;
						}
						else {
							System.out.println("あなたは "+ getball[ball_miracle].Name() + " を なげた!");
							getball[ball_miracle].Use();
							captureJudge = encountMons[0].Judgement(getball[ball_miracle].Correct());
						}
						break;
					case 4:
					default:
						System.out.println("あなたは モンスターを みのがした");
						notCapture = true;
						break;
				}

				/* モンスターを見逃した場合はwhile文を抜ける */
				if(notCapture == true) {
					break;
				}

				if(notHaveBall != true) {
					ball_allCount--;
					if(captureJudge == true) {
						System.out.println(encountMons[0].Name() + "を つかまえた! ");
						getmonsList.add(encountMons[0]);
						break;
					}
					else {
						System.out.println(encountMons[0].Name() + "を つかまえられなかった... ");
					}
				}
			}

			if(encountCount >= encountCount_max) {
				System.out.println("モンスターは もう いないようだ");
				break;
			}
			else if(ball_allCount <= 0) {
				System.out.println("もう ボール が ない!");
				break;
			}
		}
		int totalScore = 0;
		System.out.println("\n捕獲結果");
		System.out.println("捕獲数：" + getmonsList.size() + "匹");
		for(int i = 0;i < getmonsList.size();i++) {
			getMons[0] = getmonsList.get(i);
			System.out.println((i+1) + "匹目：" + getMons[0].Name() + " ポイント：" + getMons[0].Point());
			totalScore += getMons[0].Point();
		}
		System.out.println("合計獲得ポイント：" + totalScore);

		stdin.close();
	}

	static void makeMonster() {
		monsList.add(new Monster("ザコモン",30, 20, 20, 30,72));
		monsList.add(new Monster("フツモン",50, 20, 30, 30,50));
		monsList.add(new Monster("ツヨモン",100,50, 30, 25,28));
		monsList.add(new Monster("ボスモン",100,50, 50, 10,25));
		monsList.add(new Monster("レアモン",150,100,100,5, 14));

		/* 出現率の数だけテーブルにモンスターの定義を追加 */
		for(int i = 0;i < monsList.size();i++) {
			for(int j = 0;j < monsList.get(i).Encount();j++) {
				encountTable.add(i);
			}
		}
	}

	static void makeBall() {
		ballList.add(new CaptureBall("ノーマル捕獲玉",0, 6));
		ballList.add(new CaptureBall("スーパー捕獲玉",20,3));
		ballList.add(new CaptureBall("ミラクル捕獲玉",50,1));

		getball[ball_nomal] = ballList.get(ball_nomal);
		getball[ball_super] = ballList.get(ball_super);
		getball[ball_miracle] = ballList.get(ball_miracle);
	}
}