package sv;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Survivor {
	static int HP = 50;
	static int MAXHP = 100;
	static int clearday = 30;
	static int day;
	static ArrayList<EatItem> itemList = new ArrayList<EatItem>();
	static Random rnd = new Random();
	static EatItem[] getItem = new EatItem[2];
	static Scanner sc = new Scanner(System.in);
	static int hint = 3;

	static ArrayList<String[]> log = new ArrayList<String[]>();

	public static void main(String[] args){
		System.out.println("あなたは目が覚めたら無人島にいた。");
		System.out.println("目の前に１枚の手紙がある。");
		System.out.println("手紙にはこう書かれていた…");
		System.out.println("「30日間生き延びたら助けます」");
		System.out.println("こうして無人島生活が始まった。");
		MakeItem();
		for (day = 1; day <= clearday; day++) {
			DayAction();
		}
		Ending();
	}

	private static void MakeItem() {
		itemList.add(new EatItem("毒蛇", 15, 50, "毒蛇の毒に負けた..."));
		itemList.add(new EatItem("漂流物(缶詰)", 30, 50, "歯では開けられなかった。歯が全部折れて失血死..."));
		itemList.add(new EatItem("流木", 8, 20, "バイキンだらけだった..."));
		itemList.add(new EatItem("落葉", 5, 20, "口の中の水分を全部持っていかれた..."));
		itemList.add(new EatItem("毒々しいキノコ", 30, 30, "笑いが止まらず疲れて死んだ..."));
		itemList.add(new EatItem("カラフルフルーツ", 1, 30, "種が喉に詰まった..."));
	}

	private static void DayAction(){
		if (day == 1) {
			getItem[1] = itemList.get(rnd.nextInt(itemList.size()));
		}
		getItem[0] = getItem[1];
		getItem[1] = itemList.get(rnd.nextInt(itemList.size()));
		System.out.print(+day + "日目です。今日の探索結果は");
		System.out.println(getItem[0].Name() + "を見つけた。");
		System.out.println("危険度は" + getItem[0].Danger() + "%、回復量は" + getItem[0].HeelHP() + "です");
		String sel;
			if (hint > 0) {
				System.out.println("食べる:e 食べない:n ヒント:h");
				sel = sc.nextLine();
				if (sel.equals("e")) {
					Eat();
				}else if(sel.equals("n")) {
					NoEat();
			    }else if(sel.equals("h")) {
					Hint();
			    }else {
						System.out.println("e、n、hを入力して下さい");
						System.exit(0);
			    }
			} else {
				System.out.println("食べる:e 食べない:n");
				sel = sc.nextLine();
				if (sel.equals("e")) {
					Eat();
				}else if(sel.equals("n")) {
					NoEat();
			    }else {
						System.out.println("e、nを入力して下さい");
						System.exit(0);
			    }
			}
	}

	public static void Eat() {
		System.out.println("あなたは" + getItem[0].Name() + "を食べました");
		log.add(new String[] { String.valueOf(HP), getItem[0].Name(), String.valueOf(getItem[0].Danger()), "食べた" });
		if (getItem[0].Judgement()) {
			System.out.println("体力が" + getItem[0].HeelHP() + "回復しました");
			HP = Math.min(HP + getItem[0].HeelHP(), MAXHP);
			System.out.println("あなたは残り体力は" + HP + "です");
		} else {
			System.out.println(getItem[0].Coroner() + "ゲームオーバーです");
			Ending();
		}
	}

	public static void NoEat() {
		System.out.println("あなたは" + getItem[0].Name() + "を食べませんでした。");
		log.add(new String[] { String.valueOf(HP), getItem[0].Name(), String.valueOf(getItem[0].Danger()), "食べなかった" });
		System.out.println("おなかが空いてきた(体力が10減少！)");
		HP -= 10;
		System.out.println("あなたは残り体力は" + HP + "です");
		if (HP <= 0) {
			Ending();
		}
	}

	public static void Hint() {
		System.out.println("次の日の食べ物は" + getItem[1].Name() + "です。");
		hint--;
		System.out.println("のこりヒントは" + hint + "です。");
		System.out.println("食べる:e 食べない:n");
		String sel;
		sel = sc.nextLine();
		if (sel.equals("e"))
			Eat();
		if (sel.equals("n"))
			NoEat();
		else {
			System.out.println("e、nを入力して下さい");
			System.exit(0);
		}
	}

	public static void Ending() {
		if (day == 31) {
			System.out.println("助けがきた。脱出成功！");
		} else {
			System.out.println("体力が尽き死んでしまった");
		}
		System.out.println("履歴");
		for (int i = 0; i < log.size(); i++) {
			System.out.println("Day" + (i + 1) + " 朝の体力:" + log.get(i)[0] + "　" + log.get(i)[1] +
					"を見つけた。危険度は" + log.get(i)[2] + "だった。" + log.get(i)[3]);
		}
		System.exit(0);
	}
}