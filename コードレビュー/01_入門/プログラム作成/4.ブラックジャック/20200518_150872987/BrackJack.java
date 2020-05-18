package bk;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack1 {

	public static void main(String[] args) {

		int mypoint = 0; // プレイヤーのポイントを記憶する変数
		ArrayList<Integer> mycard = new ArrayList<Integer>(); // 自分の手札を記憶するArraylist

		int dealerpoint = 0; // ディーラーのポイントを記憶する変数
		ArrayList<Integer> dealercard = new ArrayList<Integer>(); // ディーラーの手札を記憶するArraylist

		// プレイヤーとディーラ-にカードを２枚配る
		System.out.println("プレイヤーとディーラーにカードを２枚配ります");
		System.out.println("");

		for (int i = 0; i < 2; i++) {

			int no;
			no = (int) ((Math.random() * 13) + 1);// ランダムに1～13までの何かを配る
			System.out
					.println("プレイヤーに" + (i + 1) + "枚目のカード " + toRank(no) + " が配られました");
			mycard.add(toNumber(no,mypoint,dealerpoint));// mycardに配られた数字を記録する

			no = (int) ((Math.random() * 13) + 1);// ランダムに1～13までの何かを配る
			System.out
					.println("ディーラ-に" + (i + 1) + "枚目のカード " + toRank(no) + " が配られました");
			dealercard.add(toNumber(no,mypoint,dealerpoint));// dealercardに配られた数字を記憶する

			System.out.println("");

		}

		mypoint = GetPoint(mycard); // 今のプレイヤーのポイントを計算
		dealerpoint = GetPoint1(dealercard);// 今のディーラーのポイントを計算

		// プレイヤーの今の得点を表示する
		System.out.println("現在のプレイヤーの得点は" + mypoint + "です");

		// ディーラーの今のポイントを表示する
		System.out.println("現在のディーラーの得点は" + dealerpoint + "です");

		System.out.println("");

		//プレイヤーがバーストしたら負け
		if(Bust(mypoint)){
			System.out.println("バーストしました");
			System.out.println("あなたの負けです");
			
			return;
		}
		
		//ディーラーがバーストしたら勝ち
		if(Bust1(dealerpoint)){
			System.out.println("ディーラーがバーストしました");
			System.out.println("あなたの勝ちです");
			
			return;
		}


		// ディーラーの合計が１７以上になるまでカードを引く

		while (true) {

			if (dealerpoint <= 17) {
				System.out.println("ディーラーがカードを追加します");

				int no;
				no = (int) ((Math.random() * 13) + 1);// ランダムに配る
				System.out.println("ディーラーに" +toRank(no) + " が配られました");
				dealercard.add(toNumber(no,mypoint,dealerpoint));// dealercardに数字を記憶する
				dealerpoint = GetPoint(dealercard);// 今の合計を計算する

				// 今の合計を表示する
				System.out.println("今のディーラーの合計は" + dealerpoint + "です");
				System.out.println("");

			}
			// 17以上のためカードを追加しない
			else if (dealerpoint >= 17) {
				System.out.println("ディーラーはカードは追加しません");

				System.out.println("今のディーラーの合計は" + dealerpoint + "です");// 今の合計を表示する

				System.out.println("");

				break;

			}
			
			//ディーラーがバーストしているか確認　してたら勝ち
			if(Bust1(dealerpoint)){
				System.out.println("デーラーがバーストしました");
				System.out.println("あなたの勝ちです");
				
				return;
				
			}
		}

		// プレイヤーにカードを増やさない選択をするまでカードを引く
		// YかNを入力して手札を増やすか決める
		while (true) {
			System.out.println("カードを追加しますか？(y:yes/n:no)");

			Scanner sc = new Scanner(System.in);
			String cardplas = sc.nextLine();

			if (cardplas.equals("y")) {
				System.out.println("カードを追加します");

				int no;
				no = (int) ((Math.random() * 13) + 1);// ランダムに配る

				System.out.println("プレイヤーに" + toRank(no) + " が配られました");

				mycard.add(toNumber(no,mypoint,dealerpoint));// 配ったカードを記憶する
				mypoint = GetPoint(mycard);// 今の合計を計算する

				// 今の合計を表示する
				System.out.println("今の合計は" + mypoint + "です");
				System.out.println("");

			}
			// カードを追加しないばあい
			else if (cardplas.equals("n")) {
				System.out.println("カードは追加しません");

				System.out.println("今の合計は" + mypoint + "です");

				System.out.println("");

				break;

			}
			// ｙｎ以外が入力されたとき
			else {
				System.out.println("YかNを入力してください");

				System.out.println("");
			}
			
			//プレイヤーがバーストしてるか確認　してたら負け
			if(Bust(mypoint)){
				System.out.println("バーストしました");
				System.out.println("あなたの負けです");
				
				return;
				
			}

		}
		
	
		// プレイヤーの総合計が21かディーラーの総合計がプレイヤーの総合計より21より遠い場合はプレイヤーの勝ち
		Result(mypoint, dealerpoint);
	}
	
	
	
//配られた数字が1か11～13だった時マークにする
	private static String toRank(int no) {
		switch (no) {
		case 1:
			return "A";

		case 11:
			return "J";

		case 12:
			return "Q";

		case 13:
			return "K";

//1か11～13以外はそのままにする
		default:
			String str = String.valueOf(no);
			return str;

		}
	}
	
	/*配られた数字が11～13だった時ポイント計算するときの数字を10にする
	Aの時はポイントが１０以下なら11、以上なら1にする*/
	
	private static int toNumber(int no, int mypoint, int dealerpoint) {
		switch (no) {
		case 1:
			if(dealerpoint < 10){
				return 11;
			}
			else if(mypoint < 10){
				return 11;
				
			}
			else{
				return 1;
			}

		case 11:
			return 10;

		case 12:
			
			return 10;

		case 13:
			return 10;

//他はそのまま計算する
		default:
			int str = Integer.valueOf(no);
			return str;

		}
	}

	// プレイヤーの現在の合計を求める
	private static int GetPoint(ArrayList<Integer> mycard) {

		int sum = 0;
		for (int i = 0; i < mycard.size(); i++) {

			int num = mycard.get(i);
			sum += num;

		}
		return sum;
	}

	// ディーラーの現在のポイントを求める
	private static int GetPoint1(ArrayList<Integer> dealercard) {

		int sum = 0;
		for (int i = 0; i < dealercard.size(); i++) {

			int num = dealercard.get(i);
			sum += num;

		}
		return sum;
	}

	// 勝ち負けをきめる
	private static void Result(int mypoint, int dealerpoint) {

		if (mypoint == 21) {
			System.out.println("あなたの勝ちです");

		} else if (mypoint < 21 && dealerpoint < mypoint) {
			System.out.println("あなたの勝ちです");

		} else if (dealerpoint == 21 && mypoint < 21) {
			System.out.println("あなたのまけです");

		} else {
			System.out.println("あなたのまけです");
		}

	}
	

	//プレイヤーがバーストしてるか確認する　してたら負け
	private static boolean Bust(int mypoint){
		if(mypoint <= 21){
			return false;
		}
		else{
			return true;
		}
	}
	
	//でぃーらーがバーストしてるか確認する　してたら勝ち
	
	private static boolean Bust1(int dealerpoint){
		if(dealerpoint <= 21){
			return false;
		}
		else{
			return true;
		}
	}

}
