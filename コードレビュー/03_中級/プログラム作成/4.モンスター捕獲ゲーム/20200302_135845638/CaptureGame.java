package fs03_Intermediate_ProgramCreation;

//【クラスの役割】～ 開始宣言、アクションの選択、各結果の表示

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CaptureGame {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		// 種類毎に捕獲玉クラスのインスタンス生成
		// CaptureBall型のリストを作成して格納
		ArrayList<CaptureBall> ball = new ArrayList<CaptureBall>();
		ball.add(new CaptureBall("ノーマル捕獲玉",0,6));
		ball.add(new CaptureBall("スーパー捕獲玉",20,3));
		ball.add(new CaptureBall("ミラクル捕獲玉",50,1));

		// 種類毎にモンスタークラスのインスタンス生成
		// monster型のリストを作成して格納
		ArrayList<Monster> monster = new ArrayList<Monster>();
		monster.add(new Monster("コロモン",30,20,20,30,72));
		monster.add(new Monster("アグモン",50,20,30,30,50));
		monster.add(new Monster("グレイモン",100,50,30,25,28));
		monster.add(new Monster("メタルグレイモン",100,50,50,10,25));
		monster.add(new Monster("ウォーグレイモン",150,100,100,5,14));

		// ゲーム終了時の捕獲記録(モンスター名 + 獲得ポイント)用リスト
		ArrayList<GameRecord> record = new ArrayList<GameRecord>();

		Random random = new Random();
		Scanner stdin = new Scanner(System.in);

		// ゲームのルール説明
		System.out.println("【チュートリアル】");
		System.out.println("・モンスターは全部で１０匹現れます。");
		System.out.println("・所持数０の捕獲玉は使えません。");
		System.out.println("・捕獲に成功した場合は次のモンスターが現れます。");
		System.out.println("・捕獲に失敗した場合は再度、アクションを選択します。(出現したモンスターはそのままです)");
		System.out.println("・最後(１０匹目)のモンスターの捕獲、もしくは見逃しをした時点で終了です。(捕獲玉が全てなくなった場合も終了です)\n");
		System.out.println("そゆことで、ゲームスタート (ﾟ∀ﾟ)\n");

		// モンスターの出現
		for(int i = 0; i<10; i++){
			int random_m;	// モンスターのランダム生成用変数
			int m_number;	// 出現したモンスターの番号用変数

			// switchの中のifでrandom_mがm_number(エンカウント率)より低いとエンカウントしないのでエンカウントするまで無限ループ
			while(true){
				int detekita = 0;
				random_m = random.nextInt(100)+1;
				m_number = random.nextInt(monster.size());
				switch(m_number){
				case 0:// コロモンが出た場合の処理
				case 1:// アグモンが出た場合の処理
				case 2:// グレイモンが出た場合の処理
				case 3:// メタルグレイモンが出た場合の処理
				case 4:// ウォーグレイモンが出た場合の処理
					if(random_m<=monster.get(m_number).Encount()){
						System.out.println("「" + monster.get(m_number).Name() + "」が現れた。");
						System.out.println("  HP：" + monster.get(m_number).HP() + " / 攻撃力：" + monster.get(m_number).Power() + " / 防御力：" + monster.get(m_number).Defense() + "\n");
						detekita++;
					}
					break;
				}
				// エンカウントしたらwhileを抜ける
				if(detekita!=0){
					break;
				}
			}

			int yatteyaruze;	// 選択したアクション用の変数
			boolean judge;		// 捕獲の結果判定用の変数
			int atonannko;		// 捕獲玉全部の残数用の変数

			while(true){
				// 捕獲玉の残数が０になったらwhileを抜ける
				atonannko = (ball.get(0).Count()+ball.get(1).Count()+ball.get(2).Count());
				if(atonannko==0){
					break;
				}

				System.out.println("アクションを選択して下さい。");
				// ミラクル捕獲玉の補正値込みで捕獲成功率が100%を超えた場合、捕獲成功率の上限を「100%」に設定
				int limit = monster.get(m_number).Capture()+ball.get(2).Correct();
				if(limit>100){
					limit = 100;
				}

				System.out.println(" (1)" + ball.get(0).Name() + "を使う (残り" + ball.get(0).Count() + "個。捕獲成功率：" + (monster.get(m_number).Capture()+ball.get(0).Correct()) + "%)");
				System.out.println(" (2)" + ball.get(1).Name() + "を使う (残り" + ball.get(1).Count() + "個。捕獲成功率：" + (monster.get(m_number).Capture()+ball.get(1).Correct()) + "%)");
				System.out.println(" (3)" + ball.get(2).Name() + "を使う (残り" + ball.get(2).Count() + "個。捕獲成功率：" + limit + "%)");
				System.out.println(" (4)モンスターを見逃す   (ﾟ∀ﾟ)\n");

				System.out.print("アクション：");
				yatteyaruze = stdin.nextInt();
				System.out.println();

				// 各捕獲玉を選択した時の処理(捕獲成功時のみwhileを抜ける)
				if(yatteyaruze==1 || yatteyaruze==2 || yatteyaruze==3){
					if(ball.get(yatteyaruze-1).Count()==0){
						System.out.println("品切れだから他のにしなさい (ﾟ∀ﾟ)\n");
					} else {
						System.out.print(monster.get(m_number).Name() + "に" + ball.get(yatteyaruze-1).Name() + "を投げた。捕獲...");
						ball.get(yatteyaruze-1).Use();
						judge = monster.get(m_number).Judgement(ball.get(yatteyaruze-1).Correct());
						if(judge==true){
							System.out.println("成功 (ﾟ∀三ﾟ三∀ﾟ) ｳﾎｰ!\n");
							// 捕獲したモンスターの名前と獲得ポイントを格納
							record.add(new GameRecord(monster.get(m_number).Name() , monster.get(m_number).Point()));
							break;
						} else {
							System.out.println("失敗 (ﾟ∀ﾟ)\n");
						}
					}
				} else {
					// ４(見逃す)ならその時点でwhileを抜ける
					System.out.println("モンスター逃げて！超逃げて！！(ﾟ∀ﾟ)\n");
					break;
				}

			}

			// 捕獲玉の残数が０になったらforを抜ける
			atonannko = (ball.get(0).Count()+ball.get(1).Count()+ball.get(2).Count());
			if(atonannko==0){
				break;
			}

		}

		System.out.println("やっとこさ終わったぜい (ﾟ∀ﾟ)\n");
		System.out.println("【捕獲履歴】");

		//▼ここから・・・
		///////////////////////////////////////////////////////////////////

		// 捕獲モンスター名のリスト
		ArrayList<String> allMonstreName = new ArrayList<String>();
		for(int i = 0; i<monster.size(); i++){
			allMonstreName.add(monster.get(i).Name());
		}

		// 捕獲数のリスト
		ArrayList<Integer> NumberOfCaptures = new ArrayList<Integer>();
		for(int i = 0; i<monster.size(); i++){
			// 集計前の初期値として各要素の中身は「0」に設定
			NumberOfCaptures.add(0);
		}

		// 捕獲数の集計
		for(GameRecord s:record){
			for(int i = 0; i<allMonstreName.size(); i++){
				if(s.recordName().equals(allMonstreName.get(i))){
					NumberOfCaptures.set(i,NumberOfCaptures.get(i)+1);
				}
			}
		}

		// 捕獲履歴表示（モンスター名 + 捕獲数）
		for(int i = 0; i<allMonstreName.size(); i++){
			System.out.println
			(" " + allMonstreName.get(i) + " ×" + NumberOfCaptures.get(i));
		}

		System.out.println();
		System.out.print("  合計獲得ポイント：");
		int totalPoint = 0;
		for(GameRecord s:record){
			totalPoint+=s.recordPoint();
		}
		System.out.println(totalPoint);

		///////////////////////////////////////////////////////////////////
		//▲ここまで・・・集計して表示する処理

		/*
		【実装済み】
		１．モンスターは１０匹登場する
		２．登場するモンスターは出現率を元に毎回ランダムで選択する
		３．モンスターの情報(名前、HP、攻撃、防御)を表示してプレイヤーにアクションを選択させる
		４．プレイヤーは以下のいずれかsの行動を行うことができる
			(1)ノーマル獲得玉を使う(残りｘ個。捕獲成功率：ｘｘ％)
			(2)スーパー獲得玉を使う(残りｘ個。捕獲成功率：ｘｘ％)
			(3)ミラクル獲得玉を使う(残りｘ個。捕獲成功率：ｘｘ％)
			(4)モンスターを見逃す
		５．所持数が０の捕獲玉を使うことはできない(所持数０でも表示されるのはOK)
		６．モンスター毎の捕獲率と、捕獲玉の補正値で捕獲成功判定を行う
		７．捕獲に成功した場合は、捕獲に成功した旨のメッセージを表示し、次のモンスターを出現させる
		９．捕獲玉が１つもなくなった場合、または、１０匹目のモンスターを捕獲もしくは見逃しをした場合はゲームは終了
		８．捕獲に失敗した場合は、捕獲に失敗した旨のメッセージを表示し、再度アクションを選択させる
		１０．ゲーム終了後に捕獲したモンスターの一覧と合計獲得ポイントを表示する ※モンスター名は全種類を表示し表示順は捕獲順ではなく固定とする(捕獲したかどうかは捕獲数で判断)
		 */
	}

}
