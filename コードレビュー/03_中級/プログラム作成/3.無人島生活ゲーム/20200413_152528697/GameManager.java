package mujinto;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {

	//プレイヤー
	static Surviver player = new Surviver(50, 100);
	static int HP = player.HP();

	//食べたか食べてないかの記録
	static ArrayList<Boolean> eatMemory = new ArrayList<Boolean>();

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//経過日数
		int passedDays = 1;

		//救助が来るまでの日数
		int totalDays = 30;

		//ヒント使用可能回数
		int hintStock = 3;

		//入力
		int input = 0;

		//Choiceメソッドから返ってくるフラグ値
		int choiceReturnValue = 0;

		//ヒントフラグ(使用でtrue、未使用でfalse)
		boolean hintFlag = false;

		//Scanner
		Scanner sc = new Scanner(System.in);

		//ArrayList
		ArrayList<EatItem> array = new ArrayList<EatItem>();

		//totalDays+1分の食べ物リスト作成
		for (int i = 0; i <= totalDays; i++) {
			EatItem generateFood = FoodSelect();
			array.add(i, generateFood);
		}

		//=======================================================================================
		//ディスプレイ
		//=======================================================================================
		//30日以下かつ体力が0より大きい時に繰り返し
		while (passedDays <= totalDays && HP > 0) {
			EatItem selectedItem = array.get(passedDays - 1);
			if (choiceReturnValue == 0) {
				//経過日数
				System.out.println("\n～～～～～～" + passedDays + "日目～～～～～～\n");

				System.out.println("体力：" + HP + "\n");

				//今日の食べ物
				System.out.println("食べ物：" + selectedItem.Name());
				System.out.println("危険度：" + selectedItem.Danger());
				System.out.println("回復量：" + selectedItem.HeelHP());
			}

			//選択
			if (hintFlag == true) {
				System.out.print("\n1:食べる\n2:食べない\n食べますか？：");
			} else {
				System.out.print("\n1:食べる\n2:食べない\n3:ヒント\n食べますか？：");
			}
			input = sc.nextInt();

			//明日の食べ物
			EatItem tomorrowFood = array.get(passedDays);

			//選択フラグ
			choiceReturnValue = Choice(input, selectedItem, hintStock, tomorrowFood, hintFlag);

			//食べた時or食べなかった時
			if(choiceReturnValue == 0 && hintStock > 0) {
				hintFlag = false;
			}
			//ヒント使用時
			else if (choiceReturnValue == 1) {
				hintStock -= 1;
				hintFlag = true;
				continue;
			}
			//フラグが2なら再入力
			else if (choiceReturnValue == 2) {
				continue;
			}

			//現在のHP
			HP = player.HP();

			passedDays++;
		}
		if (HP > 0) {
			System.out.println("\nおめでとう！あなたは救出された！\n");
		}

		//履歴表示
		for (int j = 0; j < passedDays - 1; j++) {
			EatItem eatFood = array.get(j);
			System.out.print((j + 1) + "日目：");
			System.out.println(eatFood.Name());
			if(eatFood.GetEatHistory()) {
				System.out.println("食べた");
			}else {
				System.out.println("食べなかった");
			}

			}
		}



	//選択肢メソッド
	public static int Choice(int input, EatItem selectedItem, int hintStock, EatItem tomorrowFood, boolean hintFlag) {
		//食べる
		if (input == 1) {
			//判定
			boolean judge = selectedItem.Judgement();
			//selectedItemの回復量
			int heelAmount = selectedItem.HeelHP();
			//HP反映
			player.Eat(judge, heelAmount);

			if (judge) {
				//回復成功
				System.out.println("HP" + heelAmount + "回復！");
			} else {
				//死因
				System.out.println(selectedItem.Coroner() + "\n");
			}
			selectedItem.SetEatHistory(true);
			return 0;
		}
		//食べない
		else if (input == 2) {
			//HP反映
			player.DoNotEat();
			System.out.println("HP10減少…\n");
			HP = player.HP();
			if (HP <= 0) {
				System.out.println("空腹で死んでしまった！\n");
			}
			return 0;
		}
		//ヒント表示
		else if (input == 3 && hintStock > 0 && hintFlag == false) {
			System.out.println("\n次の日の食べ物は…" + tomorrowFood.Name());
			System.out.println("危険度：" + tomorrowFood.Danger());
			System.out.println("回復量：" + tomorrowFood.HeelHP());
			hintStock -= 1;
			System.out.println("\nヒント残り：" + hintStock + "回\n");
			return 1;
		}
		//その他
		else {
			System.out.println("再度入力してください。");
			return 2;
		}
	}

	//食べ物選定メソッド
	public static EatItem FoodSelect() {

		//食べ物を決める乱数
		Random rand = new Random();

		//食べ物の選定
		switch (rand.nextInt(6)) {
		case 0:
			return new EatItem("毒蛇", 15, 30, "毒蛇の毒に負けた…", false);
		case 1:
			return new EatItem("漂流物(缶詰)", 30, 50, "歯では開けられなかった。歯が全部折れて出血死…", false);
		case 2:
			return new EatItem("流木", 8, 20, "バイキンだらけだった…", false);
		case 3:
			return new EatItem("落ち葉", 5, 20, "口の中の水分を全部持っていかれた…", false);
		case 4:
			return new EatItem("毒々しいキノコ", 10, 30, "笑いが止まらず疲れて死んだ…", false);
		//case 5:
		default:
			return new EatItem("カラフルフルーツ", 5, 30, "種が喉に詰まった…", false);
		}
//		return null;
	}
}
