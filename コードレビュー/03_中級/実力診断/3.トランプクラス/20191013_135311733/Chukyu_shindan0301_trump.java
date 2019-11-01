package chukyu_shindan;

import java.util.ArrayList;
import java.util.Collections;

class Trump{
	private enum mark{
		Diamonds,
		Hearts,
		Clubs,
		Spades;
	}
	private int number;

	private static ArrayList<Integer> lines_trump_num = new ArrayList<Integer>();
	private static ArrayList<String> lines_trump_mark = new ArrayList<String>();

	public void Reset() {
		//52枚のカードを配列に突っ込む(数値,絵柄)
		for(int i = 0;i < 4;i++) {
			for(int j = 1;j <= 13;j++) {
				lines_trump_num.add(j);
			}
		}

		for(int i = 0;i < 13;i++) {
			lines_trump_mark.add(mark.Diamonds.name());
		}
		for(int i = 0;i < 13;i++) {
			lines_trump_mark.add(mark.Hearts.name());
		}
		for(int i = 0;i < 13;i++) {
			lines_trump_mark.add(mark.Clubs.name());
		}
		for(int i = 0;i < 13;i++) {
			lines_trump_mark.add(mark.Spades.name());
		}
	}

	public void Shuffle() {
		//Resetメソッドで作った配列の中身をランダムにする
		Collections.shuffle(lines_trump_num);
		Collections.shuffle(lines_trump_mark);
	}

	public int Draw() {
		//Shuffleメソッドでつくったランダムなカードを引く
		number = lines_trump_num.get(0);
		System.out.println("引いたのは" + lines_trump_mark.get(0) + "の" + lines_trump_num.get(0));
		lines_trump_num.remove(0);
		lines_trump_mark.remove(0);

		return number;
	}

	public int Bj(int get_card1,int get_card2) {
		switch(get_card1) {
		case 11:
			System.out.println("1枚目：J");
			get_card1 = 10;
			break;
		case 12:
			System.out.println("1枚目：Q");
			get_card1 = 10;
			break;
		case 13:
			System.out.println("1枚目：K");
			get_card1 = 10;
			break;
		case 1:
			System.out.println("1枚目：A");
			get_card1 = 11;
			break;
		default:
			System.out.println("1枚目：" + get_card1);
		}

		switch(get_card2) {
		case 11:
			System.out.println("2枚目：J");
			get_card2 = 10;
			break;
		case 12:
			System.out.println("2枚目：Q");
			get_card2 = 10;
			break;
		case 13:
			System.out.println("2枚目：K");
			get_card2 = 10;
			break;
		case 1:
			System.out.println("2枚目：A");
			if(get_card1 == 11) {
				get_card2 = 1;
			}
			else {
				get_card2 = 11;
			}
			break;
		default:
			System.out.println("2枚目：" + get_card2);
		}
		return get_card1+get_card2;
	}
}