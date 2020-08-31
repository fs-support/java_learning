package akagi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class Hit_and_Blow {
	/**
	 * 数字用のリスト
	 */
	static List<String> comNumberStringList = new ArrayList<String>();
	/**
	 * 桁数
	 */
	public static int DIGITS = 5;
	/**
	 * 3回ごとにヒント
	 */
	public static int hintCount = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); //スキャナー
		int count = 0;                         //回数
		String playerNumber;                  //プレイヤーの数字
		boolean victoryDecision = true;     //勝利判定

		//数字用のリストを用意(プレイヤー)
		List<Integer>playerNumberIntList = new ArrayList<Integer>();
		//ヒント用のリストを用意
		List<String> shuffleComNumberStringList = null;

		randomNumber();
		System.out.println(comNumberStringList); //確認用

		shuffleComNumberStringList = new ArrayList<String>(comNumberStringList);
	    Collections.shuffle(shuffleComNumberStringList);

		System.out.println("★★★ヒットアンドブロー！★★★");

		//当てるまで繰り返す
		while(victoryDecision)
		{
			playerNumberIntList.clear();

			System.out.println();
			System.out.print(DIGITS + "桁の数字を入力して下さい ");

			//プレイヤー入力(String型)
			playerNumber = scan.next();
			boolean isContinue = isNumber(playerNumber);
			if(isContinue)
			{
				continue;
			}
			String [] playerNumberStringList = String.valueOf(playerNumber).split("");
			isContinue = inputValueErrorCheck(playerNumberStringList);
			if(isContinue)
			{
				continue;
			}

			playerNumberIntList = typesStrFromInt(playerNumberStringList);

			int hitCount = hitAndblowCheck(playerNumberStringList, playerNumberIntList);

			//全てヒットなら繰り返し終了
			if(hitCount == DIGITS)
			{
				victoryDecision = false;
			}
			//3回ごとにヒント
			else
			{
				hint(count, shuffleComNumberStringList);
			}

			//繰り返した回数
			count++;
		}

		//最終メッセージ
		System.out.println("おめでとう！" + count + "回目で成功♪");

		scan.close();
	}

	/**
	 * randomNumberメソッド
	 * 数字をランダムに出力
	 */
	private static void randomNumber()
	{
		int comNumber; //コンピューターの数字

		//ランダムな数字用のリストを用意
		ArrayList<Integer> NumberList = new ArrayList<Integer>();

        //1～9の整数値をリストに格納
        for(int i = 0 ; i <= 9; i++)
        {
        	NumberList.add(i);
        }

        Collections.shuffle(NumberList);

        //1つずつ取り出しint型からString型に変換し、リストに格納
		for(int i = 0; i < DIGITS;i++)
		{
	        comNumber = NumberList.get(i);
			String convertIntoStringType = String.valueOf(comNumber);
			comNumberStringList.add(convertIntoStringType);
		}
	}

	/**
	 * isNumberメソッド
	 * 入力値が数字かチェック
	 * @param playerNumber プレイヤーの数字を {@code String} で受け取る
	 * @return {@code true} なら入力値は数字ではない {@code false} なら入力値は数字である
	 */
	private static boolean isNumber(String playerNumber)
	{
	    try
	    {
	        Integer.parseInt(playerNumber);
	        return false;
	    }
	    catch (NumberFormatException e)
	    {
	    	System.out.println("◇数字以外の入力値が含まれています");
	        return true;
	    }
	}

	/**
	 * inputValueErrorCheckメソッド
	 * 指定の桁数かチェック/重複している要素をチェック
	 * @param playerNumberStringList プレイヤーの数字のリストを {@code String} で受け取る
	 * @return {@code true} ならコンティニューする {@code false} ならコンティニューせず次の処理へ
	 */
	private static boolean inputValueErrorCheck(String [] playerNumberStringList)
	{
		boolean isContinue  = false; //コンティニュー判定

		List<String> duplicateCheckListA = new ArrayList<String>(Arrays.asList(playerNumberStringList));
		List<String> duplicateCheckListB = new ArrayList<String>(new LinkedHashSet<>(duplicateCheckListA));

		//指定の桁数以外はエラーメッセージ
		if(playerNumberStringList.length > DIGITS)
		{
			System.out.println("◇桁数が多いです");
			isContinue = true;
		}
		else if(playerNumberStringList.length < DIGITS)
		{
			System.out.println("◇桁数が少ないです");
			isContinue = true;
		}
		//重複している要素あればエラーメッセージ
		else if(duplicateCheckListB.size() < DIGITS)
		{
			System.out.println("◇数字が重複しています");
			isContinue = true;
		}

		return isContinue;
	}

	/**
	 * typesStrFromIntメソッド
	 * 入力値をString型からint型に変換
	 * @param playerNumberStringList プレイヤーの数字のリストを {@code String} で受け取る
	 * @return  {@code int} に変換された数字のリスト
	 */
	private static List<Integer> typesStrFromInt(String [] playerNumberStringList)
	{
		String [] NumberStringList = playerNumberStringList;
		List<Integer> NumberIntList = new ArrayList<Integer>();

		for(String NumberString: NumberStringList)
		{
			int convertIntoIntType = Integer.parseInt(NumberString);
			NumberIntList.add(convertIntoIntType);
		}
		
		return NumberIntList;
	}

	/**
	 * hitAndblowCheckメソッド
	 * ヒットアンドブロー判定
	 * @param playerNumberStringList プレイヤーの数字のリストを {@code String} で受け取る
	 * @param playerNumberIntList プレイヤーの入力値のリストを {@code int} で受け取る
	 * @return ヒットした回数
	 */
	private static int hitAndblowCheck(String [] playerNumberStringList, List<Integer> playerNumberIntList)
	{
		int comIndex;       //コンピューターの要素番号
		int playerIndex;   //プレイヤーの要素番号
		int blowCount = 0; //ブローカウント
		int hitCount = 0;  //ヒットカウント

		for(int i = 0; i < DIGITS; i++)
		{
			boolean exists = comNumberStringList.contains(playerNumberStringList[i]);

			//要素があればカウント
			if(exists)
			{
				blowCount++; //ブロー
			}

			comIndex = comNumberStringList.indexOf(playerNumberStringList[i]);
			playerIndex = playerNumberIntList.indexOf(playerNumberIntList.get(i));

			//コンピューターとプレイヤーの要素番号が同じならカウント
			if(comIndex == playerIndex)
			{
				hitCount++; //ヒット
			}
		}

		//判定結果メッセージ
		System.out.println("◆ヒット: " + hitCount + "個、" + "ブロー: " + (blowCount - hitCount) + "個");
		System.out.println();

		return hitCount;
	}

	/**
	 * hintメソッド
	 * 3回ごとにヒントを出す
	 * @param count 回数を {@code int} で受け取る
	 * @param shuffleComNumberStringList ヒント用のシャッフルされた数字のリストを {@code int} で受け取る
	 */
	private static void hint(int count, List<String> shuffleComNumberStringList)
	{
		//ヒントは残り1つになるまで出す
        if (hintCount + 1 >= DIGITS)
        {
        	System.out.println("♪これ以上ヒントは出せないよ！");
        }
        else if(!(count == 0) && count % 3 == 0)
		{
			System.out.println("♪ヒント！" + shuffleComNumberStringList.get(hintCount) + "が使われているよ！");
			hintCount++;
		}
	}

}