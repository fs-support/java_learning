/**
 *
 */
/**
 * @author noinoi
 *
 */
package capture;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Capture{

	private static ArrayList<Monster> monsterList = new ArrayList<Monster>();
	private static ArrayList<Integer> encountTable = new ArrayList<Integer>();
	private static ArrayList<CaptureBall> ballList = new ArrayList<CaptureBall>();
	private static final int ROUND = 10;
	private static ArrayList<Integer> log = new ArrayList<Integer>();
	private static int ballNum = 0;
	private static boolean next = false;
	static Random rnd = new Random();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args)throws InterruptedException{
		MakeMonsterList();
		MakeEncountTable();
		MakeBallList();

		int now = 0;
		for(;now < ROUND; now++) {
			Turn();
			if(ballNum == 0)
				break;
	}
	if(now == 10||log.size() == 10)
		System.out.println("もうモンスターは見当たらないようだ\n");
	else
		System.out.println("ボールがなくなってしまった…\n");
	Thread.sleep(500);
	Ending();
	sc.close();
}
	public static void MakeMonsterList()
	{
		monsterList.add(new Monster("ザコモン",30,20,20,30,72));
		monsterList.add(new Monster("フツモン",50,20,30,30,50));
		monsterList.add(new Monster("ツヨモン",100,50,30,25,28));
		monsterList.add(new Monster("ボスモン",100,50,50,10,25));
		monsterList.add(new Monster("レアモン",150,100,100,5,14));
	}

	public static void MakeEncountTable() {
		for(int i = 0;i < monsterList.size();i++) {
			for(int j = 0;j < monsterList.get(i).Encount();j++) {
				encountTable.add(i);
			}
		}
	}
	public static void MakeBallList() {
		ballList.add(new CaptureBall("ノーマル捕獲玉",0,6));
		ballList.add(new CaptureBall("スーパー捕獲玉",20,3));
		ballList.add(new CaptureBall("ミラクル捕獲玉",50,1));
		for(int i = 0;i < ballList.size();i++) {
			ballNum += ballList.get(i).Count();
		}
	}
	/*
	 * @throws InterruptedException*/
	public static void Turn()throws InterruptedException{
		int mNumber = encountTable.get(rnd.nextInt(encountTable.size()));
		System.out.println(monsterList.get(mNumber).Name() + "が現れた！どうする？");
		next = false;
		while(true) {
			next = tryCapture(mNumber);
			if(next||ballNum==0)
				break;
		}
	}
	/*
	 @param mNumber
	 @return true
	 @throws InterruptedExcepiton*/
	public static boolean tryCapture(int mNumber)throws InterruptedException{
		int sel = 0;
		while(true) {
			for(int i = 0;i <= ballList.size();i++) {
				if(i==ballList.size())
					System.out.println((i+1) + "）モンスターを見逃す");
				else if(ballList.get(i).Count()>0)
					System.out.println((i+1) + ")" + ballList.get(i).Name() + "を使う（残り"
							+ ballList.get(i).Count() + "個。捕獲成功率：" + Math.min(100, (monsterList.get(mNumber).Capture()
									+ ballList.get(i).Correct())) + "%");
			}try {
				sel = Integer.parseInt(sc.nextLine())-1;
				if(sel < 0||sel > ballList.size()||(sel < ballList.size()&&ballList.get(sel).Count() == 0))
					throw new Exception();
				break;
			}catch(Exception e) {
				System.out.println("適切な値を入力してください");
			}
		}
		if(sel == ballList.size()) {
			System.out.println(monsterList.get(mNumber).Name() + "を見逃した。");
			return true;
		}else {
			System.out.println(ballList.get(sel).Name() + "を投げた！");
			ballList.get(sel).Use();
			ballNum--;
			Thread.sleep(500);
			if(monsterList.get(mNumber).Judgement(ballList.get(sel).Correct())) {
				System.out.println("おめでとう！" + monsterList.get(mNumber).Name()+"を捕まえた！\n");
				log.add(mNumber);
				return true;
			}
			else {
				System.out.println("ボールから出てしまった…");
				return false;
			}
		}
	}
	public static void Ending()throws InterruptedException{
		int score = 0;
		System.out.print("捕まえたモンスター：");
		if(log.size()>1)
			System.out.println("たち\n");
		else
			System.out.println("\n");
		if(log.size() == 0)
			System.out.println("なし");
		for(int i = 0;i < log.size();i++) {
			Thread.sleep(500);
			System.out.println(monsterList.get(log.get(i)).Name());
			score += monsterList.get(log.get(i)).Point();
		}
		System.out.println("\n総合評価" + score);
	}
}