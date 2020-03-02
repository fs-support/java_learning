package syokyu;

import java.util.ArrayList;
//アルファベットのみで構成される文字列を圧縮する
public class kadai3 {
	public static void main(String[] args) {

		//課題1 同じの文字が続く場合、"文字+連続する文字数"に置き換えることで、文字数を短くして圧縮する。
		String text = encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG");
		System.out.println("課題1");
		System.out.println(text);

		//課題2 課題１で圧縮した文字列を展開する。
		System.out.println("課題2");
		String text2 = decode(text);
		System.out.println(text2);

	}

	//課題1 エンコードメソッド
	private static String encode(String str) {
		String rt_str = "";

		//1文字づつ分割した配列を作成
		String[] one_moji = str.split("");

		//スタート文字の設定
		String stert_moji = one_moji[0];

		//カウンターの配列を設定
		ArrayList<Integer> counter = new ArrayList<Integer>();

		//出力文字配列を設定
		ArrayList<String> result_moji = new ArrayList<String>();

		//出力文字配列に最初の文字を入れる
		result_moji.add(stert_moji);

		//文字の配列を設定
		String[] kioku_moji;
		//カウンターの設定
		int count = 0;

		//拡張for: 最初の文字と1文字づつ
		for (String check : one_moji) {
			//最初の文字と１文字が異なるとき

			//文字列の比較は四則演算子ではなくequalメソッドを利用する

			if (stert_moji.equals(check) == false) {
				//	System.out.println("異なっている");

				//文字を更新
				stert_moji = check;

				//カウンターを配列に保存
				counter.add(count);

				//出力文字配列に保存
				result_moji.add(stert_moji);

				//カウンターリセット
				count = 0;

			}
			//文字が一致する間増やす
			count++;
		}
		//最後の文字は比較するものがなく文字が異なるときの処理に入らずに再帰を抜ける
		//最後に比較した数字のカウントを入れる
		counter.add(count);

		//カウンタ-リストと文字リストを交互に出力
		//リストの要素数分再帰処理
		//get()で出力
		String karimoji = null;
		for (int k = 0; k < counter.size(); k++) {

			if (counter.get(k) == 1) {
				//1が含まれない場合
				karimoji = result_moji.get(k);
			} else {
				//1が含まれる場合
				karimoji = result_moji.get(k) + counter.get(k);
			}
			//分割した文字を一つのStringにする
			rt_str = rt_str + karimoji;

		}

		return rt_str;
	}

	//課題2 ﾃﾞｺｰﾄﾞﾒｿｯﾄﾞ
	private static String decode(String str) {
	//変数宣言	
		//最終出力文字
		String rt_str = "";
		//1文字ずついれる配列
		String[] one_moji = str.split("");
		//int型のList
		ArrayList<Integer> intList = new ArrayList<Integer>();
		//String型のList
		ArrayList<String> strList = new ArrayList<String>();
		//キャスト変換判別フラグ
		Boolean flg = true;
		//文字連続判定フラグ
		Boolean flg2 = null;
		//数字置換フラグ
		Boolean addflg = false;
		//変換後用のint
		int num = 0;
		// 初期文字
		String stmoji = null;
		//結合文字
		String constr = null;
		//最終出力カウント
		int output_count = 0;

//------------------------------------------------------------------

		//文字列が連続したら一つ目の文字は1つ
		//数字が連続したら連なった数字
		for (String check : one_moji) {
			//try catchでintに変換できるかできないか判別
			try {
				//int型に変換できる→true
				num = Integer.valueOf(check);
				flg = true;
			} catch (NumberFormatException e) {
				//変換できない→false
				flg = false;
			}
			//文字の種類が同じで数字の時
			if(flg == flg2 && flg == true) {
				// 文字をくっつける処理
				//concatでstmojiとcheckを結合する
				constr = stmoji.concat(check);
				//結合フラグを建てる
				addflg = true;
			//文字の種類が同じで文字の場合	
			} else if(flg == flg2 && flg == false) {
				//数字に1を入れる
				intList.add(1);
			}
			//flgがtrueの時はintのListに数字を格納
			if(flg == true) {
				//置換フラグがtrueの場合
				if(addflg == true){
					//setでintListの要素数目の数字を結合した数字に置き換え得る
					intList.set( (intList.size() - 1 ) ,Integer.parseInt(constr));
					//置換フラグを初期状態に変更
					addflg = false;
				//置換フラグがfalseの場合
				}else if(addflg == false) {
					//intListに入れる
					intList.add(num);			
				}
			//flgがfalseの場合は文字を格納
			}else if(flg == false){
				//strListに入れる
				strList.add(check);
			}
			//文字連続判定フラグを現在の文字に更新
			flg2=flg;
			//スタート文字を現在の文字に更新
			stmoji = check;
		}
		//最後が文字で終了した時は数字1を入れる
		if(flg == false) {
			intList.add(1);			
		}
		//配列の要素数文繰り返す
		for(int size = 0; size < intList.size(); size++) {
			
			for (int output = 0; output < intList.get(size); output++) {
				//文字を出力
				System.out.print(strList.get(output_count));
			}
			output_count++;
		}
		return rt_str;
	}
}