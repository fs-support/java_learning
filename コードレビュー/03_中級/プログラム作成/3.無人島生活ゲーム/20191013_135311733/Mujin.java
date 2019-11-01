package chukyu_sakusei;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mujin{

	/* フィールド変数 */
	private static ArrayList<EatItem> itemList = new ArrayList<EatItem>();//インスタンス化したアイテムクラスを格納しておくリスト
	private static final int GOAL = 30;
	private final static int MAX_VITAL=100;
	private final static int MAX_HINT=3;
	private static int vital = 50;
	private static ArrayList<String[]> log = new ArrayList<String[]>();//日毎のログを記録するリスト(ログは各項目を保存したString型配列)
	private static EatItem[] getItem= new EatItem[2];//入手したアイテムと翌日の入手予定アイテム番号を記録する配列
	private static int day;//日数を記録する変数
	static Random rnd = new Random();
	static Scanner sc = new Scanner(System.in);
	private static int hint = MAX_HINT;
	private static int dead;
	private static String eat = "";

	public static void main(String[] args) {

		var stdin = new Scanner(System.in);
		//食べ物と設定値の格納
		MakeItem();
		dead = 0;

		System.out.println("開始");
		day = 1;

		while(day <= GOAL) {
			if(day == 1) {
				getItem[1] = itemList.get(rnd.nextInt(itemList.size()));
			}
			getItem[0] = getItem[1];
			getItem[1] = itemList.get(rnd.nextInt(itemList.size()));
			System.out.println("\n" + day + "日目");
			System.out.println("今日のご飯は" + getItem[0].Name() + "です。");
			System.out.println("危険度：" + getItem[0].Danger() + " 回復量：" + getItem[0].HeelHP());
			System.out.println("食べますか? y/n/h(ヒント 残り" + hint + "回");

			while(true) {
				try{
					eat = stdin.next();
					if(eat.equals("y")||eat.equals("n")||eat.equals("h")) {
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
			if(eat.equals("y")) {
				Eat();
				if(dead == 1){
					break;
				}
			}
			else if(eat.equals("n")) {
				NoEat();
			}
			else if(eat.equals("h")) {
				if(hint > 0) {
					System.out.println("明日のご飯は" + getItem[1].Name() + "です。");
					hint--;
				}
				else {
					System.out.println("ヒントを使い切った。もう明日のご飯はわからない...");
				}
				System.out.println("食べますか? y/n");
				while(true) {
					try{
						eat = stdin.next();
						if(eat.equals("y")||eat.equals("n")) {
							break;
						}
						else {
							throw new Exception();
						}
					}
					catch(Exception e)	{
						System.out.println("適切な値を入力してください");
					}
				}
				if(eat.equals("y")) {
					Eat();
					if(dead == 1){
						break;
					}
				}
				else if(eat.equals("n")) {
					NoEat();
				}
			}
			if(vital <= 0) {
				System.out.println("あなたは餓死しました...");
				break;
			}
		}

		if(day == 31) {
			System.out.println("\nあなたは" + GOAL + "日間を生き延びることができた...!");
		}
		else {
			System.out.println("\nあなたは" + day + "日目に死んだ...");
		}

		//履歴表示
		for(int i = 0 ; i < log.size() ; i++){//ログを表示するループ
			System.out.println("Day"+(i+1)+" 朝の体力:"+log.get(i)[0]+"\t"+log.get(i)[1]+"を見つけた。危険度は"+log.get(i)[2]+"だった。"+log.get(i)[3]);
		}

		stdin.close();
	}

	static void MakeItem() {
		itemList.add(new EatItem("生肉",10,30,"腐ってた...おなかを下して脱水症状により死亡"));
		itemList.add(new EatItem("魚",10,30,"小骨が胃袋を貫通！失血死しました"));
		itemList.add(new EatItem("なにかの缶詰",5,30,"シュールストレミングだ!!刺激臭によりショック死"));
		itemList.add(new EatItem("林檎",5,20,"のどに詰まった!窒息死しました"));
		itemList.add(new EatItem("鳥の卵",20,20,"キレた親鳥が襲ってきた!野生の力に屈しました"));
		itemList.add(new EatItem("黒い塊",50,100,"口に含んだ瞬間、あなたの体は爆ぜた。ﾀﾞﾝｼﾞｮｰ!"));
	}
	static void Eat() {
		log.add(new String[]{String.valueOf(vital),getItem[0].Name(),String.valueOf(getItem[0].Danger()),"食べた"});//ログに追加
		System.out.println("あなたは" + getItem[0].Name() + "を食べました。");
		if(getItem[0].Judgement() == true ) {
			vital += getItem[0].HeelHP();
			if(vital > MAX_VITAL) {
				vital = MAX_VITAL;
			}
			System.out.println("...うまい!");
			System.out.println("体力が回復しました。 残体力：" + vital);
			day++;
		}
		else {
			System.out.println(getItem[0].Coroner());
			System.out.println("あなたは死にました...");
			dead = 1;
		}

	}
	static void NoEat() {
		log.add(new String[]{String.valueOf(vital),getItem[0].Name(),String.valueOf(getItem[0].Danger()),"食べなかった"});//ログに追加
		vital -= 10;
		System.out.println("嫌な予感がしたあなたは" + getItem[0].Name() + "を食べませんでした。");
		System.out.println("おなかすいた...体力が10減りました。 残体力：" + vital);
		day++;
	}
}