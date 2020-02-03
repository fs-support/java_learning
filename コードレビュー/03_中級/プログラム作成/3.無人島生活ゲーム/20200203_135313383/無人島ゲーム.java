/*無人島ゲーム*/
/*EatItem*/
package surivival;

import java.util.Random;

public class EatItem{
	
	private String name; /*アイテム名*/
	private int danger; /*危険度*/
	private int heelHP; /*食べた時のHP回復量*/
	private String coroner; /*死因*/
	private Random rnd = new Random();
	/*コンストラクタ*/
	EatItem(String name,int danger,int heelHP,String coroner){
		this.name = name;
		this.danger = danger;
		this.heelHP = heelHP;
		this.coroner = coroner;
	}
	
	/*アイテム名を取得する*/
	String Name(){
		return name,
	}
	
	/*危険度を取得する*/
	int Danger(){
		return danger;
	}
	
	/*回復量を取得する*/
	int HeelHP(){
		return heelHP;
	}
	
	/*死因を取得する*/
	String Coroner(){
		return coroner;
	}
	
	/*食べた時の生死判定
	@return true:成功 false:失敗*/
	boolean Judgement(){
		if(danger > rnd.nextInt(100))
		return false;
		else
		return true;
	}
}

/*Survival.java*/
package survival;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Survival{
	private static final int GOAL = 30; /*生存目標日数を設定*/
	private final static int MAX_VITAL = 100; /*体力最大値を設定*/
	private final static int MAX_HINT = 3; /*ヒントの初期回数*/
	private static int vital = 50; /*体力の現在位置*/
	private static int hint = MAX_HINT;
	private static ArrayList<String[]> log = new ArrayList<String[]>(); /*日毎のログを記録*/
	private static ArrayList<EatItem> itemList = new ArrayList<EatItem>(); /*インスタンス化したアイテムを格納*/
	private static EatItem[] getItem = new EatItem[2]; /*入手したアイテムと翌日のアイテムを記録*/
	private static int day; /*日数を記録する変数*/
	static Random rnd = new Random();
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args)throws InterruptedException{
		MakeItem();
		System.out.println("■プロローグ¥nあなたは目が覚めたら無人島にいた。¥n目の前に1枚の手紙がある。¥n¥n手紙にはこう書かれていた…¥n");
		System.out.println("「\u001b[31m\u001b[1m30日間生き延びたら助けます\u001b[0m」\n");
		System.out.println("こうして無人島生活が始まった");
		for(day = 1; day = GOAL; day++){ /*目標日数まで1日を繰り返す*/
			DayAction();
		}
		
		Ending();
	}
	/*アイテム一覧を作成するメソッド
	itemListにEatItemをインスタンス化*/
	private static void MakeItem(){
		itemList.add(new EatItem("毒蛇",15,30,"毒蛇の毒に負けた…"));
		itemList.add(new EatItem("漂流物(缶詰)",30,50,"歯では開けられなかった。歯が全部折れて失血死…"));
		itemList.add(new EatItem("流木",8,20,"バイキンだらけだった…"));
		itemList.add(new EatItem("落葉",5,20,"口の中の水分を持って行かれた…"));
		itemList.add(new EatItem(" 毒々しいキノコ",10,30,"笑いが止まらず疲れて死んだ…"));
		itemList.add(new EatItem("カラフルフルーツ",5,30,"種が喉に詰まった…"));
		
	}
	/*1日の処理を行う*/
	private static void DayAction() throws InterruptedException{
		if(day == 1) /*初日の場合、0日目の翌日（1日目）入手「予定」アイテム設置*/{
			getItem[1] = itemList.get(rnd.nextInt(itemList.size()));
		}
		getItem[0] = getItem[1]; /*前日決定されていた入手予定アイテムを今日の入手アイテムに*/
		getItem[1] = itemList.get(rnd.nextInt(itemList.size())); /*翌日の入手予定アイテムを設定*/
		System.out.println("¥n¥" + day + "日目です。今日の探索結果は");
		for(int i = 0;i < 3;i++) /*雰囲気出すためのウェイト*/{
			Thread.sleep(750);
			System.out.print(".");
		}
		System.out.println("¥n" + getItem[0].Name() + "を見つけました");
		System.out.println("危険度は" + getItem[0].Danger() + "%,回復量は" + getItem[0].HeelHP()"です。");
		String sel;
		while(true){
			if(hint > 0)
				System.out.println("食べる:e¥t食べない:n¥tヒント:h(残り" + hint + "回)");
			else
				System.out.println("食べる:e¥t食べない:n¥t(ヒントはもう見られません)");
			try
			{
				sel = sc.nextLine();
				if(sel.equals("e")||sel.equals("n")||(sel.equals("h")&&hint > 0 ))
					break;
				else /*選択肢にない入力をされたらエラーで再入力*/
					new Exception();
			}
			catch (Exception e){
				System.out.println("適切な値を入力してください");
			}
			if(sel.equals("e")) /*食べた時の処理*/
				Eat();
			else if(sel.equals("n")) /*食べなかった時の処理*/
				Not_Eat();
			else if(sel.equals("h")) /*ヒントを出す時の処理*/
			{
				hint--;
				while(true){
					System.out.println("明日手に入る食べ物は" + getItem[1].name() + "です。");
					System.out.println(getItem[0].Name() + "を食べますか？");
					System.out.println("食べる:e¥t食べない:n");
					try{
						sel = sc.nextLine();
						if(sel.equals("e")||sel.equals("n"))
							break;
						else
							new Exception();
					}
					catch(Exception e){
						System.out.println("適切な値を入力してください");
					}
				}
				if(sel.equals("e"))
					Eat();
				if(sel.equals("n"))
					Not_Eat();
				}
			}
			/*食べた時の処理*/
			public static void Eat() throws InterruptedException{
				System.out.println("あなたは" + getItem[0].Name() + "を食べました…");
				Thread.sleep(750); /*雰囲気を出すためのウェイト*/
				log.add(new String[]{String.valueOf(vital),getItem[0].Name(),String.valueOf(getItem[0].Danger()),"食べた"}); /*ログに追加*/
				if(getItem[0].Judgement()){
					System.out.println("体力が" + getItem[0].HeelHP() + "回復しました");
					vital = Math.min(vital + getItem[0].HeelHP(),MAX_VITAL); /*上限を超えた場合は上限値に*/
				}else{
					System.out.println(getItem[0].Coroner() + "ゲームオーバーです");
					Ending();
				}
			/*食べなかった場合の処理*/
			public static void Not_Eat() throws InterruptedException{
				System.out.println("あなたは" + getItem[0].Name() + "食べませんでした。");
				log.add(new String[]{String.valueOf(vital),getItem[0].Name(),String.valueOf(getItem[0].Danger()),"食べなかった"}); /*ログに追加*/
				System.out.println("おなかが空いてきた…(体力が10減少！)");
				vital -= 10;
				if(vital <= 0){
					Ending(); /*何か知らんけどエラー出る 明日やる*/
				}
			}
			public static void Ending() throws InterruptedException{
				if(day == 31) /*31日目を迎える(=30日間生き延びた)場合*/{
					System.out.println("¥nあなたは30日間生き延びることに成功した・おめでとう！");
				}else{
					System.out.println("努力もむなしくあなたは死んでしまった");
				}
				System.out.println("¥n無人島生活の足跡");
				for(int i = 0;i < log.size(); i++) /*ログを表示するループ*/ {
					System.out.println("Day" + (i+1) + "朝の体力:" + log.get(i)[0] + "¥t" + log.get(i)[1] + "を見つけた。危険度は" + log.get(i)[2] + "だった。" + log.get(i)[3]);
					Thread.sleep(750);
				}
				System.out.println("END");
				sc.close();
				System.exit(0); /*1日の途中で呼び出されることもあるので強制終了する*/
		}
}