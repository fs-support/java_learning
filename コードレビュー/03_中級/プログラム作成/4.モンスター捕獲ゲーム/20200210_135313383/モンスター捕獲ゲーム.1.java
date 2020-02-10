/*Capture.Java*/
package capture:

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Capture{
	
	private static ArrayList<Monster> monsterlist = new ArrayList<Monster>(); /*モンスターの一覧*/
	private static ArrayList<Integer> encountTable = new ArrayList<Integer>(); /*エンカウントテーブル。出現モンスターの番号を格納*/
	private static ArrayList<CaptureBall> ballList = new ArrayList<CaptureBall>(); /*捕獲玉の一覧*/
	private static final int ROUND = 10; /*モンスターとの遭遇回数*/
	private static ArrayList<Integer> log = new ArrayList<Integer>(); /*捕獲したモンスターの番号を順番に格納するリスト*/
	private static int ballNum = 0; /*ボールの残り数の合計*/
	private static boolean next = false; /*進行判定*/
	static Random rnd = new Random();
	static Scanner sc = new Scanner(SYstem.in);
	public static void main(String[] args)throws InterruptedException{
		
		MakeMonsterList(); /*モンスターの一覧作成*/
		MakeEncountTable(); /*エンカウントテーブル作成*/
		MakeBallList();
		int now = 0; /*モンスターの出現数をカウントする変数*/
		for( ; now < ROUND ; now++) /*指定数のモンスターが出現するまでループ*/ {
			Turn(); /*ターンの処理*/
			if(ballNum == 0) /*ボール0で強制終了*/
				break;
			}
			if(now == 10||log.size() == 10) /*10体目のモンスターを見逃すまで捕獲した場合*/
				System.out.println("もうモンスターは見当たらないようだ¥n");
			else
				System.out.println("ボールがなくなってしまった…¥n");
			Thread.sleep(500);
			Ending();
			sc.close();
		}
		
		/*モンスターの一覧を生成する*/
		
		public static void MakeMonsuterList(){
			monsterList.add(new Monster("ザコモン",30,20,20,30,72));
			monsterList.add(new Monster("フツモン",50,20,20,30,50));
			monsterList.add(new Monster("ツヨモン",100,50,30,25,28));
			monsterList.add(new Monster("ボスモン",100,50,50,10,25));
			monsterList.add(new Monster("レアモン",150,100,100,5,14));
		}
		
		/*モンスターの出現率を設定する*/
		
		public static void MakeEncountTable(){
			for(int i = 0; i < monster.size(); i++){
				for(int j = 0; j < monsterList.get(i).Encount ; j++) /*出現率の回数分エンカウントテーブルにモンスター番号追加*/
				{
					encountTable.add(i);
				}
			}
		}
		
		/*ボールの一覧の設定*/
		
		public static void makeBallList(){
			ballList.add(new CaptureBall("ノーマル捕獲玉",0,6));
			ballList.add(new CaptureBall("スーパー捕獲玉",20,3));
			ballList.add(new CaptureBall("ミラクル捕獲玉",50,1));
			for(int i = 0; i < ballList.size(); i++) /*ボールの初期数の合計を取得*/
			{
				ballNum += ballList.get(i).Count();
			}
		}
		
		/*ターンの処理をする
		@throws InterruptedException*/
		
		public static void Turn() throws InterruptedException{
			int Number = encountTable.get(rnd.nextInt(encountTable.size())); /*確率に応じて出現したモンスターの番号を格納*/
			System.out.println(monsterList.get(mNumber).Name() + "が現れた！どうする？");
			next = false;
			while(true) /*捕獲するか見逃すを選ぶ、もしくはボールがなくなるまで捕獲をループ*/
			{
				next = tryCapture(mNumber);
				if(next||ballNum == 0)
				break;
			}
		}
		
		/*捕獲処理
		@param mNumber 対象のモンスターの種類
		@return 次のターンへ行くかの判定 true:次のターンへ
		@throws InterruptedException*/
		
		public static boolean tryCapture(int mNumber)throws InterruptedException{
			int sel = 0;
			while(true){
				for(int i = 0; i <= ballList.size(); i++) /*ボールの種類+1の選択肢を表示*/{
					if(i == ballList.size()) /*モンスターを見逃す選択肢を表示*/
						System.out.println((i + 1) + "）モンスターを見逃す");
					else if(ballList.get(i).Count() > 0)/*残っているボールの選択肢を表示*/
						System.out.println((i + 1) + "）" + ballList.get(i).Name() + "を使う(残り" + ballList.get(i).Count() + "個。捕獲成功率：" + Math.min(100,(monsterList.get(mNumber).Caprture() + ballList.get(i).Correct())) + "%");
					}
					try
					{
						sel = Integer.parseInt(sc.nextLine()) - 1;
						if(sel < 0 || sel > ballList.size() || (sel < ballList.size() && ballList.get(sel).Count() == 0 )) /*洗濯以外の値が入力されたらエラーで再入力*/
							throw new Exception();
						break;
					}
					catch(Exception e)
					{
						System.out.println("適切な値を入力してください");
					}
				}
				if(sel == ballList.size()) /*見逃すを選んだら次のターンへ*/
				{
					System.out.println(monsterList.get(mNumber).Name() + "を見逃した。");
					return true;
				}
				else
				{
					System.out.println(ballList.get(sel).Name() + "を投げた！");
					ballList.get(sel).use(); /*ボールを使用*/
					ballNum--;
					Thread.sleep(500);
					if(monsterList.get(mNumber).Jugdement(ballList.get(sel).Correct())) /*捕獲成功*/{
						System.out.println("おめでとう！" + monsterList.get(mNumber).Name() + "を捕まえた！¥n");
						log.add(mNumber); /*ログに捕まえたモンスターの番号を記録*/
						return true; /*次のターンへ*/
					}
					else /*失敗！*/
					{
						System.out.println("ボールから出てしまった…");
						return false;
					}
				}
			}
			public static void Ending() throws InterruptedExceprion{
				int score = 0;
				System.out.println("捕まえたモンスター");
				if(log.size() > 1)
					System.out.println("たち¥n");
				else
					System.out.println("¥n");
				if(log.size() == 0)
					System.out.println("なし");
				for(int i = 0;i < log.size();i++){
					Thread.sleep(500); /*それっぽくするためのウェイト*/
					System.out.println(monsterList.get(log.get(i)).Name()); /*名前を表示*/
					score+=monsterList.get(log.geti(i)).Point(); /*ポイントを加算*/
				}
				System.out.println("¥n総合評価" + score);
			}
		}

/*CaptureBall.java*/
public class CaptureBall{
	private String name; /*名前*/
	private int correct; /*補正値*/
	private int count; /*所持数*/
	/*コントラクタ(データの設定)*/
	CaptureBall(String name,int correct,int count){
		this.name = name;
		this.correct = correct;
		this.count = count;
	}
	String Name(){
		return name;
	}
	int Correct(){
		return correct;
	}
	int Count(){
		return count;
	}
	void Use() /*所持する*/{
		count--;
	}
}

/*Monster.java*/
import java.util.Random;

public class Monster{
	private String name; /*名前*/
	private int hp; /*HP*/
	private int power; /*攻撃*/
	private int defence; /*防御*/
	private int encount; /*出現率*/
	private int capture; /*捕獲率*/
	static Random rnd = new Random();
	
	/*コンストラクタ(データの設定)*/
	Monster(String name,int power;int defence,int encount,int capture){
		this.name = name;
		this.hp = hp;
		this.defence = defence;
		this.encount = encount;
		this.capture = capture;
	}
	
	String Name(){
		return name;
	}
	int HP(){
		return hp;
	}
	int Power(){
		return power;
	}
	int Defence(){
		return defence;
	}
	int Encount(){
		return encount;
	}
	int Capture(){
		return capture;
	}
	
	/*
	捕獲ポイントを取得(HP+攻撃+防御)*10
	@return 捕獲ポイント*/
	int Point(){
		int point = (hp+power+defence)*10;
		return point;
	}
	/*捕獲判定
	@param correct 確率補正
	@return true:成功 false:失敗*/
	boolean Judgement(int correct){
		if((capture+correcr)>rnd.nextInt(100))
			return true;
		else
			return false;
		}
	}
	
