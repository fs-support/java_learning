package programSakusei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class blackJack {

	public static int Coin = 100;

	public static HashMap<Integer, String> Nums = new HashMap<Integer, String>();

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		ArrayList<Integer> playerCards = new ArrayList<Integer>();
		ArrayList<Integer> comCards = new ArrayList<Integer>();

		Nums.put(1, "A");
		Nums.put(2, "2");
		Nums.put(3, "3");
		Nums.put(4, "4");
		Nums.put(5, "5");
		Nums.put(6, "6");
		Nums.put(7, "7");
		Nums.put(8, "8");
		Nums.put(9, "9");
		Nums.put(10, "10");
		Nums.put(11, "J");
		Nums.put(12, "Q");
		Nums.put(13, "K");
		
		
		while (Coin > 0) {		//コインを賭ける：手持ちがゼロになったら終了
			Coin -= 10;
			
			int playTotal = 0, comTotal = 0, playHold = 0, comHold = 0;
			
			playerCards.clear();
			comCards.clear();

			System.out.println("現在の所持コイン：" + Coin);

			for (int i = 0; i < 2; i++) {
				playHold = (rand.nextInt(13) + 1);
				comHold = (rand.nextInt(13) + 1);

				System.out.println("あなたに「" + Nums.get(playHold) + "」が配られました");
				System.out.println("ディーラーに「" + Nums.get(comHold) + "」が配られました");

				playerCards.add(playHold);
				comCards.add(comHold);
			}
			
//			playerCards.set(0, 1);
//			playerCards.set(1, 1);
			
			
			playTotal = Total(playerCards);
			comTotal = Total(comCards);

			System.out.println("\nディーラーの合計は「" + comTotal + "」です");
			System.out.println("現在の合計は「" + playTotal + "」です");

			
//			if (playTotal == comTotal) {
//				System.out.println("引き分けです\n");
//				Coin(2);
//			}
			
			//ブラックジャック判定
			if (playTotal == 21) {
				System.out.println("ブラックジャックです。あなたの勝ちです\n");
				Coin(0);
			} else if (comTotal == 21) {
				System.out.println("ブラックジャックです。あなたの負けです\n");
			} else {

				//プレイヤーのターン
				while (true) {
					System.out.print("もう1枚カードを引きますか？(Y/N)：");
					String YorN = scan.nextLine();

					if (YorN.equals("n") || YorN.equals("N")) {
						break;
					} else if (YorN.equals("y") || YorN.equals("Y")) {

						playHold = (rand.nextInt(13) + 1);
						System.out.println("あなたに「" + Nums.get(playHold) + "」が配られました。");
						playerCards.add(playHold);
						playTotal = Total(playerCards);
						System.out.println("現在の合計は「" + playTotal + "」です。\n");

						if (playTotal > 21) {
							System.out.println("バーストしました");
							break;
						}
					} else {
						System.out.print("YかNを入力してください：");
					}
				}

				//ディーラーのターン
				while (comTotal <= 17 && playTotal <= 21) {
					comHold = (rand.nextInt(13) + 1);
					System.out.println("ディーラーに「" + Nums.get(comHold) + "」が配られました。");
					comCards.add(comHold);
					comTotal = Total(comCards);
					System.out.println("ディーラーの合計は「" + comTotal + "」です。\n");
					if (comTotal > 21) {
						System.out.println("バーストしました");
					}
				}

				if (playTotal > 21 && comTotal <= 21) {
					System.out.println("あなたの負けです\n");
				} else if (comTotal > 21 && playTotal <= 21) {
					System.out.println("あなたの勝ちです\n");
					Coin(1);
				} else if (playTotal == comTotal) {
					System.out.println("引き分けです\n");
					Coin(2);
				} else if (playTotal > comTotal) {
					System.out.println("あなたの勝ちです\n");
					Coin(1);
				} else {
					System.out.println("あなたの負けです\n");
				}
			}
		}
		System.out.println("コインが尽きました");

	}
	
	//合計計算メソッド
	public static int Total(ArrayList<Integer> tefuda) {
		
		int goukei = 0 , A = 0;
		
		for(int i = 0 ; i < tefuda.size() ; i++) {
			
			//Aの数を数える
			if(tefuda.get(i) == 1) A++;
			
			//合計値の計算
			goukei += Trans(tefuda.get(i));
		}
		
		// goukeiが11以下の時はバーストしないので10を足す
		if(goukei <= 11 && A >= 1) {
			goukei += 10;
		}
		return goukei;
	}
	
	//A、J、Q、K変換メソッド
	public static int Trans(int Card) {
		if ((Card == 11) || (Card == 12) || (Card == 13)) {
			return 10;
		} else {
			return Card;
		}
	}
	
	//勝敗の判定
	public static void Coin(int Syouhai) {
		if (Syouhai == 0) {
			Coin += 30;
		} else if (Syouhai == 1) {
			Coin += 20;
		} else if (Syouhai == 2) {
			Coin += 10;
		}
	}





//	static ArrayList<Integer> playerCards = new ArrayList<Integer>();
//	static ArrayList<Integer> comCards = new ArrayList<Integer>();
//
//
//	public static void main() {
//
//		playerCards.add(3);
//		playerCards.add(5);
//		int playerTotal = Total(playerCards);
//
//		comCards.add(6);
//		comCards.add(3);
//		int comTotal = Total(comCards);
//
//		playerCards.add(6);
//		playerTotal = Total(playerCards);
//	}
//
//
//	public static int Total(ArrayList<Integer> tefuda)
//	{
//		int total = 0 ;
//		for (int i = 0; i < tefuda.size(); i++)
//		{
//			total += tefuda.get(i);
//		}
//		return total;
//	}

//	1.Aを1として数える
//	2.Aの数を覚える
//	3.21より小さい場合はAの数だけ10を足す

}
