package Data1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Uninhabited {

	static ArrayList<EatItem>EatItemlist=new ArrayList<EatItem>();
	//クリア後の履歴
	static ArrayList<EatItemH>EatItemHi=new ArrayList<EatItemH>();

	public static void main(String[] args) {
		//入力
		Scanner sc=new Scanner(System.in);
		//乱数
		Random ran=new Random();
		//アイテム名,危険度,食べたときのHP回復量,死因　情報
		Database da=new Database();
		//体力
		int HP=50;
		//ヒント
		int Tips=3;
		System.out.println("あなたは目が冷めたら無人島にいた。\n目の前に１枚の手紙がある。");
		System.out.println("\n手紙にはこう書かれていた");
		System.out.println("\n「３０日間生き延びたら助けます」");
		System.out.println("\nこうして無人島生活が始まった。");

		for(int i=1;i<=30;i++) {
			int a=ran.nextInt(5)+1;

			EatItemlist.add(new EatItem(da.D_name(a),
										  da.D_danger(a),
										  da.D_heelHP(a),
										  da.D_coroner(a),
										  da.D_judgement(da.D_danger(a))));
		}

		for(int i=1;i<=30;i++) {
			EatItem ra=EatItemlist.get(i-1);
			System.out.println("\n～～～～"+i+"日目"+"～～～～");
			System.out.println("体力"+HP);
			System.out.println("\n品名["+ra.Name()+"] 危険度["+ra.Danger()+"]  回復量["+ra.HeelHP()+"]");
			System.out.print("\n食べますか Y/N：");
			String Reply;
			Reply =sc.next();
			if(Reply.equals("Y")) {
				//即死判定
				if(ra.Judgement()) {
					//体力判定
					if(HP+ra.HeelHP()<=100) {
						HP+=ra.HeelHP();
						EatItemHi.add(new EatItemH(HP,ra.Name(),ra.Danger(),"食べた"));
					}else {
						HP=100;
					}
				}else{
					System.out.println(ra.Coroner());
					EatItemHi.add(new EatItemH(HP,ra.Name(),ra.Danger(),"食べて死亡"));
					break;
				}
			}else {
				//ヒント判定
				if(i<30) {
					//ヒントが無い場合
					if(Tips==0) {
						if(HP-10>0){
							HP-=10;
							EatItemHi.add(new EatItemH(HP,ra.Name(),ra.Danger(),"食べていない"));
						}else {
							break;
						}
						continue;

					}
					System.out.print("\nヒント（明日の情報 "+Tips+"回） Y/N：");
						Reply =sc.next();
						if(Reply.equals("Y")) {
							Tips-=1;
							EatItem RA=EatItemlist.get(i);
							System.out.println("\n～"+(i+1)+"日目"+"～");
							System.out.println("\n品名["+RA.Name()+"] 危険度["+RA.Danger()+"]  回復量["+RA.HeelHP()+"]");
							System.out.print("\n\n食べますか("+i+"日目) Y/N：");
							Reply =sc.next();
								if(Reply.equals("Y")) {
									if(ra.Judgement()) {
											//体力判定
											if(HP+ra.HeelHP()<=100) {
												HP+=ra.HeelHP();
												EatItemHi.add(new EatItemH(HP,ra.Name(),ra.Danger(),"食べた"));
											}else {
												HP=100;
											}
										}else{
											System.out.println(ra.Coroner());
											EatItemHi.add(new EatItemH(HP,ra.Name(),ra.Danger(),"食べて死亡"));
											break;
										}
								}else {
									if(HP-10>0){
										HP-=10;
										EatItemHi.add(new EatItemH(HP,ra.Name(),ra.Danger(),"食べていない"));
									}else {
										break;
									}
								}

						}else{
							if(HP-10>0){
								HP-=10;
								EatItemHi.add(new EatItemH(HP,ra.Name(),ra.Danger(),"食べていない"));
							}else {
								break;
							}

						}

				}
			}
		}

		System.out.println("\nクリア後の履歴");
		for(int i=0; EatItemHi.size()>i;i++){
			EatItemH rad=EatItemHi.get(i);
			System.out.println("\n～～～～"+(i+1)+"日目"+"～～～～");
			System.out.println("\n 体力["+rad.Hp()+"] 品名["+rad.Name()+"] 危険度["+rad.Danger()+"]  食判定["+rad.Eat()+"]");
		}
	sc.close();
	}

}
