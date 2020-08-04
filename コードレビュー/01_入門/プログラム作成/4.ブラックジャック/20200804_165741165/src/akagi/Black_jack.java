package akagi;



import java.util.ArrayList;
import java.util.List;
//インポート
import java.util.Random;
import java.util.Scanner;

/**
 * @author 09060
 *
 */
public class Black_jack {

	/**
	 * @param args
	 */

	//配列
	static String[] cardNumber = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"}; //カード(文字列)

	//格納するためのリストを用意
	static List<String>playerHandList = new ArrayList<String>();
	static List<String>dealerHandList = new ArrayList<String>();

	//変換用のリスト
	static List<Integer> playerConvetedNumberList = new ArrayList<>(); //プレイヤー
	static List<Integer> dealerConvetedNumberList = new ArrayList<>(); //ディーラー

	//宣言(グローバル変数)
	public static Scanner scan = new Scanner(System.in); //スキャナー
	public static boolean playerACheck; //プレイヤー
	public static boolean dealerACheck; //ディーラー
	public static boolean playerBurst; //プレイヤー
	public static boolean dealerBurst; //ディーラー
	public static int coin = 100; //手持ち100コインでコインが0になると終了

	//メイン
	public static void main(String[] args)
	{
		int coinStatus; //コイン判定 1:ブラックジャックで勝ち 2:ブラックジャック以外で勝ち 3:引き分け 4:負け

        //初期開始メッセージ
		System.out.println("★★★ブラックジャック！★★★");
		System.out.println("▽▲ コイン： " + coin +  " ▲▽");
		//コインが0になるまで繰り返す
		while(coin > 0)
		{
			//初期化
			initialization();

			//1回あたり10コイン
			coin -= 10;

			//開始メッセージ
			System.out.println();
			System.out.println("始めるよ！");
			System.out.println("▽▲ コイン： " + coin +  " ▲▽");

			//dealCardsからハンドを格納
		    playerHandList.add(dealCards()); //プレイヤーのハンド
		    dealerHandList.add(dealCards()); //ディーラーのハンド
		    playerHandList.add(dealCards()); //プレイヤーのハンド
		    dealerHandList.add(dealCards()); //ディーラーのハンド

	        //メッセージ
			System.out.println("最初のカードを配るよ");
			System.out.println("");

		    //ArrayListを1件ずつ取り出す
		    String playerHandPiece = null; //プレイヤー
		    String dealerHandPiece = null; //ディーラー

			//初期ハンド(カードを配ったときのメッセージ)
		    for(int i=0;i<2;i++)
		    {
		    	playerHandPiece = (playerHandList.get(i));
		    	dealerHandPiece  = (dealerHandList.get(i));

				dealCardsMsg("あなた", playerHandPiece); //プレイヤー
				dealCardsMsg("ディーラー", dealerHandPiece); //ディーラー
		    }

			//文字列から数値に変換したものを格納
			int playerConvetedNumber = 0;
			int dealerConvetedNumber = 0;

			//文字列から数値に変換したものを変換用のリストに格納
		    for(int i=0;i<playerHandList.size();i++) //プレイヤー
		    {
		    	playerHandPiece = (playerHandList.get(i));

				//文字列から数値に変換
				playerConvetedNumber = convertNumberFromString(playerHandPiece, true);

				//変換用のリストに格納
				playerConvetedNumberList.add(playerConvetedNumber);
			}
		    for(int i=0; i <dealerHandList.size(); i++) //ディーラー
		    {
		    	dealerHandPiece  = (dealerHandList.get(i));

		    	//文字列から数値に変換
				dealerConvetedNumber = convertNumberFromString(dealerHandPiece, false);

				//変換用のリストに格納
				dealerConvetedNumberList.add(dealerConvetedNumber);
			}

			//ハンド合計
			int playerTotal = addition(playerConvetedNumberList, playerACheck); //プレイヤー
			int dealerTotal = addition(dealerConvetedNumberList, dealerACheck); //ディーラー

			//合計メッセージ
			System.out.println();
			System.out.println("ディーラーの合計は" + dealerTotal + "です");
			System.out.println("あなたの合計は" + playerTotal + "です");

			//ブラックジャック判定（Aと10/J/Q/Kの組み合わせ）
			if(playerTotal == 21 && !(dealerTotal == 21))
			{
				System.out.println();
				System.out.println("◇◆◇ブラックジャック！◆◇◆");
				System.out.println("あなたの勝ちです");

				coinStatus = 1; //ブラックジャックで勝ち：30コインの払い戻し
				coinAdditionSubtract(coinStatus);

				System.out.println();
				System.out.println("======================================");
				continue;
			}
			else if(dealerTotal == 21 && !(playerTotal == 21))
			{
				System.out.println();
				System.out.println("◇◆◇ブラックジャック！◆◇◆");
				System.out.println("ディーラーの勝ちです");

				coinStatus = 4; //負け：払い戻しなし（賭け金没収）
				coinAdditionSubtract(coinStatus);

				System.out.println();
				System.out.println("======================================");
				continue;
			}
			else if(dealerTotal == 21 && playerTotal == 21)
			{
				System.out.println();
				System.out.println("両者ブラックジャックのため、引き分けです");

				coinStatus = 3; //引き分け：10コイン（掛け金）の払い戻し
				coinAdditionSubtract(coinStatus);

				System.out.println();
				System.out.println("======================================");
				continue;
			}

	        //プレイヤー関数
			playerTotal = playerTurn(playerTotal);

	        //ディーラー関数
			if(playerBurst == false) //プレイヤーがバーストしていない
			{
				dealerTotal = dealerTurn(dealerTotal);
			}

	        //勝敗判定
			victoryJudgment(playerTotal, dealerTotal);

			System.out.println();
			System.out.println("======================================");
		}

		//終了メッセージ
		System.out.println();
		System.out.println("ざんねん！コインがなくなりました");

		//スキャナーをclose
		scan.close();
	}

	//プレイヤー関数
	private static int playerTurn(int playerTotal)
	{
		boolean repeat = true;
    	String answer = "";
    	int total = playerTotal;


		//プレイヤー繰り返し判定(「Y」のとき繰り返す・「N」のときbreak・バーストのとき抜ける・合計値が21のときは強制終了)
        while(repeat == true && total < 21)
        {
    		//プレイヤーにカードを引くか聞く
    		System.out.print("もう1枚カードを引きますか？(Y/N)：");
    		answer = scan.next();

	        if(answer.equals("Y")) //引く
	        {
	        	//カードを配る
	        	playerHandList.add(dealCards());

    	    	//ArrayListを1件ずつ取り出す
    	    	String playerHandPiece = null; //プレイヤー

	    		//カードを配ったときのメッセージ
	    	    playerHandPiece = playerHandList.get(playerHandList.size() -1);
	    	    System.out.println();
	    		dealCardsMsg("あなた", playerHandPiece); //プレイヤー

	    		//文字列から数値に変換したものを格納
	    		int playerConvetedNumber = 0;

	    		//文字列から数値に変換したものを変換用のリストに格納

	    	    playerHandPiece = playerHandList.get(playerHandList.size() -1);

	    		//文字列から数値に変換
	    		playerConvetedNumber = convertNumberFromString(playerHandPiece, true); //プレイヤー

	    		//変換用のリストに格納
	    		playerConvetedNumberList.add(playerConvetedNumber);

	    		//ハンド合計
	    		total = addition(playerConvetedNumberList, playerACheck); //プレイヤー

	    		//合計メッセージ
	    		System.out.println("あなたの合計は" + total + "です");

	    		//バースト判定
	    		if(total > 21)
	    		{
	    			playerBurst = true;
	    			break;
	    		}
	        }
	        else if(answer.equals("N")) //引かない
	        {
	        	repeat = false;
	        	break;
	        }
	        else //Y/N以外
	        {
	        	System.out.println("YかNを入力してね");
	        }
        }
		return total;
	}

	//ディーラー関数
	private static int dealerTurn(int dealerTotal)
	{
		int total = dealerTotal;
		//ディーラー繰り返し判定（17以下なら繰り返す・バーストのとき抜ける）
		while(total <= 17)
		{
        	//カードを配る
			dealerHandList.add(dealCards());

	    	//ArrayListを1件ずつ取り出す
	    	String dealerHandPiece = null; //ディーラー

    		//カードを配ったときのメッセージ
    	    dealerHandPiece = dealerHandList.get(dealerHandList.size() -1);
    	    System.out.println();
    		dealCardsMsg("ディーラー", dealerHandPiece); //ディーラー

    		//文字列から数値に変換したものを格納
    		int dealerConvetedNumber = 0;

    		//文字列から数値に変換したものを変換用のリストに格納
    	    dealerHandPiece = dealerHandList.get(dealerHandList.size() -1);

    		//文字列から数値に変換
    	    dealerConvetedNumber = convertNumberFromString(dealerHandPiece, true); //ディーラー

    		//変換用のリストに格納
    	    dealerConvetedNumberList.add(dealerConvetedNumber);

    		//ハンド合計
    	    total = addition(dealerConvetedNumberList, dealerACheck); //ディーラー

    		//合計メッセージ
    		System.out.println("ディーラーの合計は" + total + "です");
		}
    	//バースト判定
    	if(total > 21)
    	{
    		dealerBurst = true;
    	}
		return total;
	}

	//カードを配る
	private static String dealCards()
	{
	    //Randomクラスのインスタンス化
        Random rnd = new Random();
		//乱数の取得
        String getRandomList = cardNumber[rnd.nextInt(13)];
        //結果を返す
        return getRandomList;
	}

	//カードを配ったときのメッセージ
	private static void dealCardsMsg(String who, String handPiece)
	{
		System.out.println(who + "に「" + handPiece + "」が配られました");
	}

	//文字列から数字に変換する
	private static int convertNumberFromString(String handPiece, boolean playerCheck)
	{
		String status = handPiece;
		int convetedNumber;

		if(status.equals("J") || status.equals("Q") || status.equals("K")) //J,Q,Kの場合
		{
			//10に変換
			convetedNumber = 10;

			return convetedNumber;
		}
		else if(status.equals("A")) //Aの場合
		{
			//1に変換
			convetedNumber = 1;

			//Aチェック
			if(playerCheck == true)
			{
				playerACheck = true;
			}
			else
			{
				dealerACheck = true;
			}

			return convetedNumber;
		}
		else //2～10の場合
		{
			//数字に変換
			convetedNumber = Integer.parseInt(status);

			return convetedNumber;
		}

	}

	//足し算
	private static int addition(List<Integer> convetedNumberList, boolean aCheck)
	{
		//和を計算
		int sum = 0;
		for (int i=0; i < convetedNumberList.size(); i++)
		{
			//System.out.println(convetedNumberList.get(i)); //確認用
			sum += ((Integer)convetedNumberList.get(i)).intValue();
		}
		//Aチェック 合計値が11以下の場合プラス10する
		if(aCheck == true && sum <= 11) //プレイヤー
		{
			sum += 10;
			return sum;
		}
		return sum;
	}

	//勝敗判定
	private static void victoryJudgment(int playerTotal, int dealerTotal)
	{
		int coinStatus; //コイン判定

		System.out.println();
		//バースト判定 プレイヤー
		if(playerBurst == true && dealerBurst == false)
		{
			System.out.println("◆◆◆バースト！◆◆◆");
			System.out.println("ディーラーの勝ちです");
			coinStatus = 4; //負け：払い戻しなし（賭け金没収）
			coinAdditionSubtract(coinStatus);

		}
		//バースト判定 ディーラー
		else if(dealerBurst == true && playerBurst == false)
		{
			System.out.println("◆◆◆バースト！◆◆◆");
			System.out.println("あなたの勝ちです");

			coinStatus = 2; //ブラックジャック以外で勝ち：20コインの払い戻し
			coinAdditionSubtract(coinStatus);
		}
		else if(playerBurst == true && dealerBurst == true)
		{
			System.out.println("◆◆◆バースト！◆◆◆");
			System.out.println("両者バーストしたため、引き分けです");

			coinStatus = 3; //引き分け：10コイン（掛け金）の払い戻し
			coinAdditionSubtract(coinStatus);
		}
		//どちらが21に近いか
		else
		{
			//引き分け
			if(playerTotal == dealerTotal)
			{
				System.out.println("引き分けです");

				coinStatus = 3; //引き分け：10コイン（掛け金）の払い戻し
				coinAdditionSubtract(coinStatus);
			}
			//プレイヤーの方が近い
			else if(playerTotal > dealerTotal)
			{
				System.out.println("あなたの勝ちです");

				coinStatus = 2; //ブラックジャック以外で勝ち：20コインの払い戻し
				coinAdditionSubtract(coinStatus);
			}
			//ディーラーの方が近い
			else if(playerTotal < dealerTotal)
			{
				System.out.println("ディーラーの勝ちです");

				coinStatus = 4; //負け：払い戻しなし（賭け金没収）
				coinAdditionSubtract(coinStatus);
			}
			else
			{
				System.out.println("error"); //確認用
			}
		}
	}

	//コイン処理
	private static void coinAdditionSubtract(int coinStatus)
	{
		if(coinStatus == 1) //ブラックジャックで勝ち：30コインの払い戻し
		{
			coin += 30;
		}
		else if(coinStatus == 2) //ブラックジャック以外で勝ち：20コインの払い戻し
		{
			coin += 20;
		}
		else if(coinStatus == 3) //引き分け：10コイン（掛け金）の払い戻し
		{
			coin += 10;
		}
		else if(coinStatus == 4) //負け：払い戻しなし（賭け金没収）
		{

		}
		else
		{
			System.out.println("error"); //確認用
		}
		System.out.println("▽▲ コイン： " + coin +  " ▲▽");
	}

	//コイン処理
	private static void initialization()
	{
		playerHandList.clear();
		dealerHandList.clear();
		playerConvetedNumberList.clear();
		dealerConvetedNumberList.clear();
		playerACheck = false;
		dealerACheck = false;
		playerBurst = false;
		dealerBurst = false;
	}

}