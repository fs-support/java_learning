package getmonster;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Adventure {
	//モンスター情報を格納するリスト
	ArrayList<Monster> monsterlist = new ArrayList<Monster>();
	//捕獲玉情報を格納するリスト
	ArrayList<CaptureBall> balllist = new ArrayList<CaptureBall>();
	//捕獲したモンスターを格納
	ArrayList<Monster> captuermonster = new ArrayList<Monster>();

	//モンスターとの遭遇回数
	int Moncount = 0;
	//捕獲ポイント
	int Allpoint = 0;
	//出現したモンスター情報を保管
	Monster monster = null;
	//ボール総合計
	int Allball = 0;
	
	
	//モンスター情報を格納
	public void Input_Monster(){
		Monster monster1 = new Monster("ザコモン",30,20,20,30,72);
		Monster monster2 = new Monster("フツモン",50,20,30,30,50);
		Monster monster3 = new Monster("ツヨモン",100,50,30,25,28);
		Monster monster4 = new Monster("ボスモン",100,50,50,10,25);
		Monster monster5 = new Monster("レアモン",150,100,100,5,14);
		
		monsterlist.add(monster1);
		monsterlist.add(monster2);
		monsterlist.add(monster3);
		monsterlist.add(monster4);
		monsterlist.add(monster5);
	}
	
	//捕獲玉の情報を格納
	public void Input_Ball(){
		CaptureBall ball1 = new CaptureBall("ノーマルボール",0,6);
		CaptureBall ball2 = new CaptureBall("スーパーボール",20,3);
		CaptureBall ball3 = new CaptureBall("ミラクルボール",50,1);
		
		balllist.add(ball1);
		balllist.add(ball2);
		balllist.add(ball3);
		
		//ボールの合計
		for(int i = 0;i < balllist.size();i++) {
			Allball += balllist.get(i).Count();
		}
		
	}
	
	

	//どのモンスターと遭遇するか設定
	public Monster Pop_mon(){
		//乱数用に出現率総合計
		int Allencount = 0;
		for(int i = 0;i < monsterlist.size();i++) {
			Allencount += monsterlist.get(i).Encount();
		}
		//乱数
		int rand_mon = new Random().nextInt(Allencount);
		//出現率を足していく
		int monencount = 0;
		Monster popmon = null;
		for(int i = 0;rand_mon > monencount;i++) {
			monencount += monsterlist.get(i).Encount();
			if(rand_mon <= monencount) {
				 popmon = monsterlist.get(i);
			}
		}
		
		return popmon;
	}
	
	//どのモンスターが出現したかを示す
	public void Output_mon() {
		Monster popmon = Pop_mon();
		Moncount++;
		System.out.println("あ、野生の" + popmon.Name() + "が飛び出してきた！");
		System.out.println("HPは" + popmon.HP() + "\n攻撃力は" + popmon.Power() + "\n防御力は" + popmon.Defense() + "だ！\n");
		System.out.println("遭遇回数：" + Moncount + "回目");
		monster = popmon;
		
	}
	
	//いちから行動を示す
	public void SubAction() {
		while(true) {
			Output_mon();
			Action();
			if(Moncount == 10 || Allball == 0) {
				Results();
				break;
			}
		
		}
	}
	
	//出現したモンスターに対する行動のみ
	public void Action(){
		
		
		//Monster popmon = Output_mon();
		
		//行動の選択
		Scanner scan = new Scanner(System.in);
		System.out.println("-----あなたの行動----");
		
		for(int i = 0;i <= balllist.size();i++){
			if(i == balllist.size()){
				System.out.println((i + 1) + "モンスターを見逃す");
			}else if(balllist.get(i).Count() > 0){
				System.out.println((i + 1) + balllist.get(i).Name() + "を使う。(残り：" + balllist.get(i).Count() + " 捕獲補正率:" + balllist.get(i).Correct() + "%)");
			}
		}

		while (true) {
			int input = scan.nextInt();
			CaptureBall ball = null;
			if(input == 1 || input == 2 || input == 3){
				ball = balllist.get(input - 1);
				//ボールが0の時は選択させない
				if(balllist.get(input - 1).Count() == 0 ) {
					System.out.println("ボールがありません");
					System.out.println("他のボールを選択してください");
					continue;
				}
				BallUse(ball,monster);
				break;
			}else if(input == 4){
				BallNoUse();
				monster = null;
				break;
			}else{
				System.out.println("選択肢にあった数字を入力してください");
			}
		}
	}
	
	public void BallUse(CaptureBall ball,Monster popmon){
		ball.Use();
		Allball--;
		boolean judi = popmon.Judgement(ball.Correct());
		if(judi == true){
			Allpoint += popmon.Point();
			captuermonster.add(popmon);
			System.out.println("やったー！" + popmon.Name() + "を捕まえた！");
			System.out.println("捕獲ポイントは" + popmon.Point() + "だ！");
			System.out.println(ball.Name() + "の残りは" + ball.Count() + "です");
			System.out.println("");
			monster = null;
			
		}else{
			System.out.println("捕獲に失敗した……");
			System.out.println("もう一度行動を選択してください");
			System.out.println("");
			Action();
		}
	}
	
	public void BallNoUse(){
		System.out.println("見逃しました");
		System.out.println("");
	}
	
	//結果を表示
	public void Results() {
		System.out.println("お疲れ様でした！");
		System.out.println("あなたが捕まえたのは");
		for(int i = 0;i < captuermonster.size();i++) {
			System.out.println(captuermonster.get(i).Name());
		}
		System.out.println("です！\n合計獲得ポイントは" + Allpoint + "でした！");
	}
}