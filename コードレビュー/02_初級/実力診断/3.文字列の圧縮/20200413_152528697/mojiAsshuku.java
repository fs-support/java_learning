package syokyuhen;

public class mojiAsshuku {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String text = encode("AAAAAAABBBBBVVVFDDDDTTUUUO!!!@@ああィ鵜鵜");
		System.out.println(text);
	}

	public static String encode(String input) {

		//文字列編集用
		StringBuilder sb = new StringBuilder();

		//今の文字
		char nowLetter = input.charAt(0);

		//同じ文字数のカウンタ(今の文字と次の文字を比較する為、今の文字は既に1つある)
		int count = 1;

		//inputの文字数分繰り返し
		for (int i = 1; i < input.length(); i++) {

			//今の文字と次の文字が同じならcount+1
			if (nowLetter == input.charAt(i)) {
				count++;
			}

			else {
				//今の文字をsbに追加
				sb.append(nowLetter);
				
				//countが1以外で
				if (count != 1) {
					//countをsbに追加
					sb.append(count);
				}

				//カウンタのリセット
				count = 1;
			}

			//今の文字に次の文字をセット
			nowLetter = input.charAt(i);

		}
		//今の文字をsbに追加
		sb.append(nowLetter);
		
		//countが1以外で
		if (count != 1) {
			//countをsbに追加
			sb.append(count);
		}
		
		
		return sb.toString();
	}

}
