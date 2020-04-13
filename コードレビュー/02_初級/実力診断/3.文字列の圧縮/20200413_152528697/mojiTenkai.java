package syokyuhen;

public class mojiTenkai {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String text = decode("A10BCB10");
		System.out.println(text);

	}

	public static String decode(String input) {
		StringBuilder sb = new StringBuilder();

		char moji; //今見ている文字
		int suuji; //今見ている数字

		int mojiNo = 0; //今の文字が要素の何番目か
		int ketaNo = 0; //数字が何番目か
		char nextMoji; //次の文字
		char nextSuuji; //今見ている数字の次の数字

		char lastMoji = input.charAt(input.length() - 1);

		int mojisuu = input.length(); //inputの文字数

		String mojiKazu; //文字をいくつ展開するか

		//文字数よりも文字番号が小さかったら繰り返し
		while (mojiNo < mojisuu) {
			moji = input.charAt(mojiNo); //charAt:文字列の要素[index]の文字を抜き出す(Char型)

			int keta = 1; //数字が何桁か(初期化)

			if (moji == lastMoji && mojiNo + 1 == mojisuu) { //今の文字が最後の場合
				sb.append(moji);
				break;

			} else { //今の文字が最後でない
				nextMoji = input.charAt(mojiNo + 1);

				if (nextMoji == lastMoji && mojiNo + 1 == mojisuu) { //次の文字が最後の場合
					if (Character.isDigit(nextMoji)) {
						suuji = Character.getNumericValue(nextMoji);
						for (int i = 0; i < suuji; i++) {
							sb.append(moji);
						}
						break;

					} else {
						mojiNo++;

					}

				} else {
					//次の文字が数字か
					if (Character.isDigit(nextMoji)) { //isDigit:char型に対して数値ならtrue、違うならfalse
						mojiNo++;

						nextSuuji = input.charAt(mojiNo + 1);

						ketaNo = mojiNo;

						//桁数を数える
						while (Character.isDigit(nextSuuji)) { //次の文字が数字か

							keta++;
							ketaNo++;
							if (nextSuuji == lastMoji && mojiNo + keta == mojisuu) { //次の数字が最後か
								break;
							} else {
								nextSuuji = input.charAt(ketaNo + 1);
							}
						}

						//桁数をもとに展開数を抜き出す
						mojiKazu = input.substring(mojiNo, mojiNo + keta);

						//System.out.println(mojiKazu);

						suuji = Integer.parseInt(mojiKazu); //parseInt:文字列を整数に変換　substring:beginIndex～endIndexの手前までを抜き出す

						//数字の分だけ文字を展開する
						for (int i = 0; i < suuji; i++) {
							sb.append(moji);
						}
						mojiNo += keta;
					}

					//次の文字が数字でなければ文字を足す
					else {
						sb.append(moji);
						moji = nextMoji;
						mojiNo++;
					}
				}
			}
		}
		return sb.toString();
	}

}


//			//次の文字が数字か
//			if (Character.isDigit(nextMoji)) { //isDigit:char型に対して数値ならtrue、違うならfalse
//				mojiNo = mojiNo + 1;
//
//				nextSuuji = input.charAt(mojiNo);
//
//				ketaNo = mojiNo;
//
//				//桁数を数える
//				while (Character.isDigit(nextSuuji)) {
//
//					ketaNo++;
//					//System.out.println(mojiNo);
//
//					if (ketaNo < mojisuu) {
//						keta++;
//						nextSuuji = input.charAt(mojiNo);
//					} else {
//						break;
//					}
//				}
//
//				//桁数をもとに展開数を抜き出す
//				mojiKazu = input.substring(mojiNo - 1);
//				
//				System.out.println(mojiKazu);
//
//				suuji = Integer.parseInt(mojiKazu); //parseInt:文字列を整数に変換　substring:beginIndex～endIndexの手前までを抜き出す
//
//				//数字の分だけ文字を展開する
//				for (int i = 0; i < suuji; i++) {
//					sb.append(moji);
//				}
//				mojiNo += keta - 1;
//			} 
//			
//			//次の文字が数字でなければ文字を足す
//			else {
//				moji = nextMoji;
//				sb.append(moji);
//				mojiNo++;
//			}
//			System.out.println(sb);


//		//nowLetterが何番目かのカウンタ
//		int count = 1;
//
//		//圧縮された数を保持
//		String hold;
//		
//		//
//		int holdNum = 0;
//
//		//参照中の文字を保持
//		char holdChar = 0;
//		
//		int nextLetter;
//		
//		
//		
//		if(0 < nowLetter && nowLetter <9) {
//			for(int i =0 ; i < nowLetter ; i++) {
//				sb.append(nowLetter);
//			}
//		}
//		else {
//			sb.append(nowLetter);
//		}
//		count++;

//		while (count < input.length()) {

//			if(Character.isDigit(input.charAt(count + 1))) {
//				nextLetter = Integer.parseInt(holdNext);
//			}
//			
//			
//			if (0 < nowLetter && nowLetter < 9) {
//				hold = input.substring(count);
//				if(0 < nextLetter && nextLetter < 9) {
//					//nextLetter = 数字
//					
//				}
//				else {
//					//nextLetter = 文字
//					hold += input.substring(count + 1);
//					
//				}
//				holdNum = Integer.parseInt(hold);
//			} 
//			else {
//				if(0 < nextLetter && nextLetter < 9) {
//					//nextLetter = 数字
//					holdChar = nowLetter;
//					
//				}
//				else {
//					//nextLetter = 文字
//					sb.append(nowLetter);
//				}
//			}
//		
//			count++;
//			}
