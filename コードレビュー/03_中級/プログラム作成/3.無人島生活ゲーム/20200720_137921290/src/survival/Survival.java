package survival;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Survival {
	private static final int GOAL = 30;
	private final static int MAX_HP = 100;
	private final static int MAX_HINT = 3;
	private static int hitpoint = 50;
	private static int hint = MAX_HINT;
	private static ArrayList<String[]> log = new ArrayList<String[]>();
	private static ArrayList<EatItem> itemlist = new ArrayList<EatItem>();
	private static EatItem[] getItem = new EatItem[2];
	private static int day;
	static Random rnd = new Random();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		MakeItem();
		System.out.println("■プロローグ\nあなたは目が覚めたら無人島にいた。\n目の前に１枚の手紙がある。\n\n手紙にはこう書かれていた...\n");
		System.out.println("「\u001b[1m３０日間生き延びたら助けます\u001b[0m」\n");
		System.out.println("こうして無人島生活が始まった");
		for (day = 1; day <= GOAL; day++) {
			DayAction();
		}
		Ending();
	}

	/**アイテム一覧を作成するメソッド
	 * itemListにEatItemをインスタンス化して詰め込む
	 */
	private static void MakeItem() {
		itemlist.add(new EatItem("毒蛇", 15, 30, "毒蛇の毒に負けた..."));
		itemlist.add(new EatItem("漂流物(缶詰)", 30, 50, "歯では開けられなかった。歯が全部折れて失血死..."));
		itemlist.add(new EatItem("流木", 8, 20, "バイキンだらけだった..."));
		itemlist.add(new EatItem("落葉", 5, 20, "口の中の水分を全部持っていかれた..."));
		itemlist.add(new EatItem("毒々しいキノコ", 10, 30, "笑いが止まらず疲れて死んだ..."));
		itemlist.add(new EatItem("カラフルフルーツ", 5, 30, "種が喉に詰まった..."));
	}

	//1日の処理を行うメソッド
	private static void DayAction() throws InterruptedException {
		if (day == 1)//初日の場合、０日目の翌日(1日目)入手「予定」のアイテムを設定
		{
			getItem[1] = itemlist.get(rnd.nextInt(itemlist.size()));
		}
		getItem[0] = getItem[1];//前日に決定されていた入手予定アイテムを今日の入手アイテムに
		getItem[1] = itemlist.get(rnd.nextInt(itemlist.size()));//翌日の入手予定アイテムを設定
		System.out.print("\n" + day + "日目です。今日の探索結果は");
		System.out.println("\n" + getItem[0].Name() + "を見つけました。");
		System.out.println(
				"危険度は" + getItem[0].Danger() + "%、回復量は" + getItem[0].HeelHP() + "です。どうしようか...(現在の体力 " + hitpoint + ")");
		String sel;
		while (true) {
			if (hint > 0)
				System.out.println("食べる:y\t食べない:n\tヒント:h(残り" + hint + "回)");
			else
				System.out.println("食べる:y\t食べない:n\t(ヒントはもう見られません)");
			try {
				sel = sc.nextLine();
				if (sel.equals("y") || sel.equals("n") || (sel.equals("h") && hint > 0)) {
					break;
				} else {
					new Exception();
				}
			} catch (Exception e) {
				System.out.println("適切な値を入力してください");
			}
		}
		if (sel.equals("y")) {//食べた時の処理
			Eat();
		} else if (sel.equals("n")) {//食べなかった時の処理
			Not_Eat();
		} else if (sel.equals("h"))//ヒントを出すときの処理
		{
			hint--;
			while (true) {
				System.out.println("明日手に入る食べ物は" + getItem[1].Name() + "です。");
				System.out.println(getItem[0].Name() + "を食べますか？");
				System.out.print("食べる:e\t食べない:n");
				try {
					sel = sc.nextLine();
					if (sel.equals("y") || sel.equals("n"))
						break;
					else
						new Exception();
				} catch (Exception e) {
					System.out.println("適切な値を入力してください");
				}
			}
			if (sel.equals("y"))
				Eat();
			if (sel.equals("n"))
				Not_Eat();
		}
	}

	//食べた時の処理
	public static void Eat() throws InterruptedException {
		System.out.println("あなたは" + getItem[0].Name() + "を食べました...");
		log.add(new String[] { String.valueOf(hitpoint), getItem[0].Name(), String.valueOf(getItem[0].Danger()),
				"食べた" });//ログに追加
		if (getItem[0].Judgement()) {
			System.out.println("体力が" + getItem[0].HeelHP() + "回復しました");
			hitpoint = Math.min(hitpoint + getItem[0].HeelHP(), MAX_HP);
		} else {
			System.out.println(getItem[0].Coroner() + "ゲームオーバーです");
			Ending();
		}
	}

	//食べなかった場合の処理
	public static void Not_Eat() throws InterruptedException {
		System.out.println("あなたは" + getItem[0].Name() + "を食べませんでした。");
		log.add(new String[] { String.valueOf(hitpoint), getItem[0].Name(), String.valueOf(getItem[0].Danger()),
				"食べなかった" });
		System.out.println("おなかが空いてきた...(体力が10減少！)");
		hitpoint -= 10;
		if (hitpoint <= 0) {
			Ending();
		}
	}

	public static void Ending() throws InterruptedException {
		if (day == 31) {
			System.out.println("\nあなたは３０日間生き延びることに成功した。おめでとう！");
		} else {
			System.out.println("努力もむなしくあなたは死んでしまった...");
		}
		System.out.println("\n履歴");
		for (int i = 0; i < log.size(); i++) {
			System.out.println("Day" + (i + 1) + " 朝の体力:" + log.get(i)[0] + "\t" + log.get(i)[1] + "を見つけた。危険度は"
					+ log.get(i)[2] + "だった。" + log.get(i)[3]);
		}
		System.out.println("END");
		sc.close();
		System.exit(0);
	}
}