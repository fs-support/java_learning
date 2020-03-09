package k;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Data2 {
	//見逃し出来る回数
	static int Through=10;
	//モンスターリスト
	static 	ArrayList<Monster>History=new ArrayList<Monster>();
	public static void main(String[] args) {
		//ポイント合計
		int gou=0;
		//捕獲玉セット
		Database.DataCapture();
		//モンスター　セット
		Database.Monster();
		//出現率
		Random ra =new Random();
		int Dta;
		//出現モンスター
		Monster  Appearance;
		System.out.println("----目指せ世界一の捕獲王----");
		while (true) {
			if(Database.CaptureD[0].Count()+Database.CaptureD[1].Count()+Database.CaptureD[2].Count()<=0||Through<=0||History.size()>=10) {
				System.out.println("終了結果を発表します。");
				break;
			}
			Dta=ra.nextInt(100);
			 Appearance =Database.Monsterl[Dta];
				System.out.println("\nモンスターが現れた");
				System.out.println("名前 :"+Appearance.Name()+
					 			"\nHP :"+Appearance.HP()+
					 			"\n攻撃力　："+Appearance.Power()+
					 			"\n防御力　："+Appearance.Defense());
				Determine(Appearance);
		}
		System.out.println("\n捕獲モンスターリスト");
		for(Monster s : History){
            System.out.println("\n名前 :"+s.Name()+
		 						"\nHP :"+s.HP()+
		 						"\n攻撃力　："+s.Power()+
		 						"\n防御力　："+s.Defense());
            gou =gou+s.Point(s.HP(),s.Power(), s.Defense());
		}

		System.out.println("\n----------ポイント合計------");
		System.out.println("合計"+gou+"ポイント");
	}

	//捕獲処理
	public static void Determine(Monster Appearance) {
			int data;
		too:while(true) {
			System.out.println("\nメニュ-------------");
			System.out.println("⑴ノーマル捕獲玉を使う(残り"+Database.CaptureD[0].Count()+"個。捕獲率補正：0％)");
			System.out.println("⑵スーパー捕獲玉を使う(残り"+Database.CaptureD[1].Count()+"個。捕獲率補正：20％)");
			System.out.println("⑶ミラクル捕獲玉を使う(残り"+Database.CaptureD[2].Count()+"個。捕獲率補正：50％)");
			System.out.println("⑷モンスターを見逃す(回数残り："+Through+"回)");
			System.out.print("\n番号を入力：");
			Scanner sc=new Scanner(System.in);
			data =sc.nextInt();
				switch (data) {
				case 4:
					System.out.println("\nモンスターを見逃しました。");
					Through=Through-1;
					System.out.println("\nモンスターを見逃し回数残り"+Through);
					break too;

				default:
					//捕獲玉の残り判定
					if(Database.CaptureD[data-1].Count()>0) {
						Database.CaptureD[data-1].Use();
						//捕獲判定
						if(Appearance.Judgement(Database.CaptureD[data-1].Correct(), Appearance.Capture())){
							System.out.println("\nモンスターをゲッツだぜ！！");
							History.add(Appearance);
							break too;
						}else {
							System.out.println("\n捕獲失敗");
							System.out.println("再度捕獲玉を投げますか\n");
							Determine(Appearance);
							break too;
						}
					}else {
						if(Database.CaptureD[0].Count()+Database.CaptureD[1].Count()+Database.CaptureD[2].Count()<=0) {
							System.out.println("\n全ての捕獲玉がありません。");
							break too;
						}
						System.out.println("\n選択した捕獲玉がありません。\n他の捕獲玉を選択してください。");
					}
					break;
				}
		}
	}

}
