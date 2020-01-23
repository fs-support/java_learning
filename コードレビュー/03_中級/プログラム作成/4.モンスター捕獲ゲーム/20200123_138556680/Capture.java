package poke;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Capture {
	private static int ball = 0;
	private static int battle = 10;
	private static boolean next = false;
	private static ArrayList<Monster> monsterList = new ArrayList<Monster>();
	private static ArrayList<Integer> encount = new ArrayList<Integer>();
	private static ArrayList<CaptureBall> ballList = new ArrayList<CaptureBall>();
	private static ArrayList<Integer> log = new ArrayList<Integer>();

	static Random rnd = new Random();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		MakeMonsterList();
		Encount();
		ballList();
		System.out.println("ゲーム開始");
		for (int count = 0; count < battle; count++) {
			Battle();
			if (ball == 0)
				break;
		}
		Ending();
	}

	public static void MakeMonsterList() {
		monsterList.add(new Monster("ザコモン", 30, 20, 20, 30, 72));
		monsterList.add(new Monster("フツモン", 50, 20, 30, 30, 50));
		monsterList.add(new Monster("ツヨモン", 100, 50, 30, 25, 28));
		monsterList.add(new Monster("ボスモン", 100, 50, 50, 10, 25));
		monsterList.add(new Monster("レアモン", 150, 100, 100, 5, 14));
	}

	public static void Encount() {
		for (int i = 0; i < monsterList.size(); i++) {
			for (int j = 0; j < monsterList.get(i).Encount(); j++) {
				encount.add(i);
			}
		}
	}

	public static void ballList() {
		ballList.add(new CaptureBall("ノーマル捕獲玉", 0, 6));
		ballList.add(new CaptureBall("スーパー捕獲玉", 20, 3));
		ballList.add(new CaptureBall("ミラクル捕獲玉", 50, 1));
		for (int i = 0; i < ballList.size(); i++) {
			ball += ballList.get(i).Count();
		}
	}

	private static void Battle() throws InterruptedException {

		int x = encount.get(rnd.nextInt(encount.size()));
		System.out.println(monsterList.get(x).Name() + "が現れた！");
		boolean next = false;
		while (true) {
			next = encount(x);
			if (next || ball == 0)
				break;
		}
	}

	private static boolean encount(int x) throws InterruptedException {
		int sel = 0;
		while (true) {
			for (int i = 0; i <= ballList.size(); i++) {
				if (i == ballList.size())
					System.out.println((i + 1) + ":モンスターを見逃す");
				else if (ballList.get(i).Count() > 0)
					System.out.println((i + 1) + ":" + ballList.get(i).Name() + "を使う(残り" + ballList.get(i).Count()
							+ "個。捕獲成功率:" + Math.min(100, (monsterList.get(x).Capture() + ballList.get(i).Correct()))+ "%)");
			}
			try {
				sel = Integer.parseInt(sc.nextLine()) - 1;
				if (sel < 0 || sel > ballList.size() || (sel < ballList.size() && ballList.get(sel).Count() == 0))
					throw new Exception();
				break;
			} catch (Exception e) {
				System.out.println("入力しなおして下さい");
			}
		}
		if (sel == ballList.size()) {
			System.out.println(monsterList.get(x).Name() + "を見逃した");
			return true;
		}
		System.out.println(ballList.get(sel).Name() + "を投げた");
		ballList.get(sel).Use();
		ball--;
		if (monsterList.get(x).Judgement(ballList.get(sel).Correct())) {
			System.out.println(monsterList.get(x).Name() + "を捕まえた");
			log.add(x);
			return true;
		} else {
			System.out.println("ボールから出てしまった");
		}
		return false;
	}

	public static void Ending() {
		int point = 0;
		System.out.println("");
		System.out.println("スコア");
		System.out.println("");
		System.out.println("捕獲したモンスターは");
		for (int i = 0; i < log.size(); i++) {
			System.out.println(monsterList.get(log.get(i)).Name());
			point += monsterList.get(log.get(i)).Point();
		}
		System.out.println("");
		System.out.println("合計ポイント:" + point);
		System.exit(0);
	}
}