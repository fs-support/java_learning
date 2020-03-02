package fs03_Intermediate_ProgramCreation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UninhaditedIslandLife {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Random random = new Random();
		Scanner stdin = new Scanner(System.in);

		int hp = 50;		//初期体力
		int count = 1;		//生存日数カウント
		int tips = 0;		//ヒント使用カウント
		int totalTips = 0;	//ヒント使用総数カウント
		int hintMenu = -1;	//ヒント格納用の変数
		int hunger = 0;		//何も食べずにHPが０になったとき用

		String name = "";
		int danger = 0;
		int heelhp = 0;
		String coroner = "";

		//[選択肢]の配列
		String[] select = {"食べる","食べない","ヒント(※３回まで)"};

		//EatItem型のリストを作成し食材の各情報を格納
		ArrayList<EatItem> eatItemList = new ArrayList<EatItem>();
		eatItemList.add(new EatItem("牛肉", 30, 30, "狂牛病"));
		eatItemList.add(new EatItem("豚肉", 20, 20, "豚コレラ"));
		eatItemList.add(new EatItem("鶏肉", 15, 10, "鳥インフルエンザ"));
		eatItemList.add(new EatItem("謎肉", 10, 50, "日○食品からの刺客"));

		//１日事の生存履歴を格納するための SurvivalHistory のリスト
		ArrayList<SurvivalHistory> historyList = new ArrayList<SurvivalHistory>();

		System.out.println("無人島生活スタート（現在の体力：" + hp + " ※最大100）\n");

		while(count!=31){
			//本日のお献立
			int x = random.nextInt(eatItemList.size());

			//前日にヒントを聞いていれば今日のメニューはそのヒントの内容に書き換える
			if (hintMenu != -1) {
				x = hintMenu;
				hintMenu = -1;		//ヒントを使用するので使用後はヒント変数を初期値へリセット
			}

			//EatItem型の変数「eatItem」へ eatItemリストの中身(ランダム)を格納。情報毎にメソッドを呼び出してint型、String型の各変数へ代入する
			EatItem eatItem = eatItemList.get(x);
			name = eatItem.Name();
			danger = eatItem.Danger();
			heelhp = eatItem.HeelHP();
			coroner = eatItem.Coroner();

			//ヒントを使い切った場合は「3.ヒント(※３回まで)」は出ない様にする
			if(totalTips>2){
				if(tips==1){
					tips = 0;	//ヒントを使用するので使用後はヒント使用カウントを初期値へリセット (ヒントを使い切ったら翌日から↓のelseに自動で入る)

					//ヒント使用総数が上限でも最後のヒントは反映する必要がある
					System.out.println(count + "日目食料(ヒント反映)  " + name + " ～ 危険度：" + danger + " / 回復量：" + heelhp + "\n");
					System.out.println("          ヒント使い切りおった(ﾟ∀ﾟ ) 草生えるwww\n");
				} else {
					//ヒントを使い切った後は「食べる」「食べない」の２択
					System.out.println(count + "日目食料  " + name + " ～ 危険度：" + danger + " / 回復量：" + heelhp);
				}

				for(int i = 0; i<2; i++){
					System.out.print("[" + (i+1) + "." + select[i] + "]");
				}
			} else {
				if(tips==0){
					System.out.println(count + "日目食料  " + name + " ～ 危険度：" + danger + " / 回復量：" + heelhp);
				} else {
					tips = 0;
					System.out.println(count + "日目食料(ヒント反映)  " + name + " ～ 危険度：" + danger + " / 回復量：" + heelhp);
				}

				for(int i = 0; i<select.length; i++){
					System.out.print("[" + (i+1) + "." + select[i] + "]");
				}
			}

			System.out.println();
			System.out.print("何番？： ");
			int number = stdin.nextInt();
			System.out.println();

			//ヒントを選んだ場合の処理
			if(number==3){
				//ヒント使い切った状態で寝言言いやがったら...
				if(totalTips>2){
					//捻くれ者へのありがたい御言葉
					throw new IllegalArgumentException("「1、2以外で」とか今そういのいいからマジで(ﾟ∀ﾟ )");
				}

				tips++; totalTips++;	//ヒント使用と使用総数をプラス１

				//明日のお献立
				int y = random.nextInt(eatItemList.size());
				eatItem = eatItemList.get(y);
				System.out.println("明日のお献立  " + eatItem.Name() + " ～ 危険度：" + eatItem.Danger() + " / 回復量：" + eatItem.HeelHP());

				//ヒント用変数に代入
				hintMenu = y;

				for(int i = 0; i<2; i++){
					System.out.print("[" + (i+1) + "." + select[i] + "]");
				}
				System.out.println();
				System.out.print("何番？： ");
				number = stdin.nextInt();
				System.out.println();

				if(number!=1 && number!=2){
					//捻くれ者へのありがたい御言葉
					throw new IllegalArgumentException("「1、2以外で」とか今そういのいいからマジで(ﾟ∀ﾟ )");
				}
			}

			if(number==1){
				//食べるを選んだ場合の処理
				System.out.print("いざ、実食！..........");
				boolean judge = eatItem.judgement();
				if(judge==true){
					//食べる「成功」
					hp+=heelhp;
					if(hp>100){
						hp = 100;
					}
					System.out.println("ウマかった(ﾟ∀ﾟ )（現在の体力：" + hp + "）\n");
					historyList.add(new SurvivalHistory(hp,name,danger,"食べた"));	//その日の生存履歴をリストへ格納
				} else {
					//食べる「失敗」
					System.out.println("ヤバかった(ﾟ∀三ﾟ三∀ﾟ)");
					hp = 0;
					historyList.add(new SurvivalHistory(hp,name,danger,"食べた"));	//その日の生存履歴をリストへ格納
					break; //体力が無くなったのでwhileを抜ける
				}
			} else if(number==2){
				//食べないを選んだ場合の処理
				hp-=10;
				System.out.println("違ぇしビビってねぇし腹減ってねぇだけだし(ﾟ∀ﾟ )（現在の体力：" + hp + "）\n");
				historyList.add(new SurvivalHistory(hp,name,danger,"食べなかった"));	//その日の生存履歴をリストへ格納

				if(hp<=0){
					hunger++;
					break; //体力が無くなったのでwhileを抜ける
				}
			} else {
				//ゲーム進行を妨げる愚か者への正義の鉄槌
				throw new IllegalArgumentException("貴様に他の選択肢など無い(ﾟ∀ﾟ )");
			}

			count++;	//生存日数プラス１

		}


		//while抜けた後は結果表示
		System.out.println();
		if(hp>0){
			System.out.println((count-1) + "日経過..........え？ウソやろ生還しおったΣ(ﾟДﾟ)\n");
		} else {
			System.out.println("お逝きなさい(ﾟ∀ﾟ)\n");	//死亡原因を表示
			if(hunger==1){
				System.out.println("【死亡原因】(食料：食べなかった) ～ 空腹による餓死\n");
			} else {
				System.out.println("【死亡原因】(食料：" + name + ") ～ " + coroner + "\n");
			}

		}

		System.out.println("【生存履歴】");
		for(int i = 0; i<historyList.size(); i++){
			SurvivalHistory x = historyList.get(i);
			System.out.println
			(" " + (i+1) + "日目：" + "[HP]" + x.recordHP() + " / [食料]" + x.recordName() + " / [危険度]" + x.recordDanger() + " / [食事記録]" + x.recordMeal());
		}

	}
}