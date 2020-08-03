package middle.pokimon;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	static CaptureBall ball1 = new CaptureBall(BallKind.BALL1, 6);
	static CaptureBall ball2 = new CaptureBall(BallKind.BALL2, 3);
	static CaptureBall ball3 = new CaptureBall(BallKind.BALL3, 1);
	static Scanner scanner = new java.util.Scanner(System.in);
	static ArrayList<Monster> getMonsters = new ArrayList<Monster>();

	public static boolean selectAction(Monster m) {
		while (true) {
			if (ball1.Count() <= 0 && ball2.Count() <= 0 && ball3.Count() <= 0) {
				return false;
			}
			int n = scanner.nextInt();
			switch (n) {
			case 1:
				if (ball1.Count() <= 0) {
					System.out.println("ボールがありません。他のボールを選んでください。");
					continue;
				}
				ball1.Use();
				if (m.Judgement(ball1.Correct()) == false) {
					displaySelecter(m);
					continue;
				} else {
					getMonsters.add(m);
					break;
				}

			case 2:
				if (ball2.Count() <= 0) {
					System.out.println("ボールがありません。他のボールを選んでください。");
					continue;
				}
				ball2.Use();
				if (m.Judgement(ball2.Correct()) == false) {
					displaySelecter(m);
					continue;
				} else {
					getMonsters.add(m);
					break;
				}

			case 3:
				if (ball3.Count() <= 0) {
					System.out.println("ボールがありません。他のボールを選んでください。");
					continue;
				}
				ball3.Use();
				if (m.Judgement(ball3.Correct()) == false) {
					displaySelecter(m);
					continue;
				} else {
					getMonsters.add(m);
					break;
				}

			case 4:
				System.out.println("モンスターを見逃した");
				break;

			default:
				System.out.println("1~4の数字で選択してください");
				continue;
			}
			break;
		}
		return true;
	}

	public static void displaySelecter(Monster m) {
		System.out.println("(1)ノーマル捕獲玉を使う(残り" + ball1.Count() + "個。"
				+ "捕獲成功率:" + checkKakuritu(m.Capture(), ball1.Correct()) + "%)");
		System.out.println("(2)スーパー捕獲玉を使う(残り" + ball2.Count() + "個。"
				+ "捕獲成功率:" + checkKakuritu(m.Capture(), ball2.Correct()) + "%)");
		System.out.println("(3)ミラクル捕獲玉を使う(残り" + ball3.Count() + "個。"
				+ "捕獲成功率:" + checkKakuritu(m.Capture(), ball3.Correct()) + "%)");
		System.out.println("(4)モンスターを見逃す");
	}

	/**
	 * 捕獲率の確認
	 * @param cap	モンスターの捕獲率
	 * @param cor 	ボールの補正値
	 * @return <=100 : kakuritu / >100 : 100
	 */
	private static int checkKakuritu(int cap, int cor) {
		int kakuritu = cap + cor;
		if (kakuritu <= 100) {
			return kakuritu;
		} else {
			return 100;
		}
	}

	/**
	 * ゲーム終了
	 */
	public static void endGame() {
		System.out.println("\n-----ゲーム終了-----\n◆捕まえたモンスター一覧◆");
		int point = 0;
		for (Monster m : getMonsters) {
			System.out.println(m.Name());
			point += m.Point();
		}
		System.out.println("\n獲得ポイント : " + point);
	}
}
