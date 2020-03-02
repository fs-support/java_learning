package syokyu;

import java.util.ArrayList;
import java.util.Arrays;

// 数字をカンマ区切り文字列に変換する
public class kadai2 {

	public static void main(String[] args) {

		System.out.println(commaSeparatedString(123));
		System.out.println(commaSeparatedString(12345));
		System.out.println(commaSeparatedString(1234567));
		System.out.println(commaSeparatedString(-1234567));
		System.out.println(commaSeparatedString(123456789));
		System.out.println(commaSeparatedString(-123456789));
	}

	private static String commaSeparatedString(int num) {
		//返却用str
		String str = "";
		//結合用str
		String cnstr = "";
		//マイナス付与用
		String ms = "-";
		//マイナス判定用flg
		boolean flg = false;
		//コンマ判定用flg
		boolean cmflg = false;
		//キャスト変換用string
		String stn = null;
		//コンマ格納タイミング判定用カウンター
		int counter = 1;
		//逆数格納用list
		ArrayList<String> relist = new ArrayList<String>();
		//----------------------------------------------------
		//絶対値に変換
		if (num < 0) {
			num = num * (-1);
			flg = true;
		}
		//キャスト変換
		stn = String.valueOf(num);
		
		//数字分割用list,この段階で分割してlistに格納
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(stn.split("")));
		//relistにコンマを入れながら逆順に格納
		for(int incm = list.size(); incm > 0; incm--) {
			//逆から見て3つ目にコンマを入れるflgを建てる
			if(counter % 3 == 0 && counter != list.size()){
				cmflg = true;
			}
			//relistに数字を格納
			relist.add(list.get(incm-1));
			//コンマflgが建っている時はコンマを格納
			if(cmflg == true){
				relist.add(",");
				cmflg = false;
			}
			//counterを加算
			counter++;
		}
		//リストサイズ分ループでrelistを後ろから結合して戻す
		for(int conect = relist.size(); conect > 0; conect--) {
			str = str.concat(relist.get(conect - 1));
		}
		//マイナスflgが建っている時は - を先頭に結合
		if(flg == true) {
		str = ms.concat(str);
		}
		return str;
	}
}
