package middleTest;

import java.util.ArrayList;
import java.util.Collections;

public class ManageTrump {
	static ArrayList<Trump> trumps = new ArrayList<Trump>();
	ArrayList<Trump> hand;

	ManageTrump() {
		hand = new ArrayList<Trump>();
	}
	//trumpsに52を格納
	public static void Reset() {
		for (int j = 1; j < 5; j++) {
			for (int i = 1; i < 14; i++) {
				Trump trump = new Trump(Trump.getMark(j), Trump.getNumber(i));
				trumps.add(trump);
			}
		}
	}
	//trumpsをシャッフル
	public static void Shuffle() {
		Collections.shuffle(trumps);
	}
	//一枚ドローし手札を表示
	public void Draw() {
		Trump t = trumps.get(0);
		trumps.remove(0);
		hand.add(t);
		for (Trump tmp : hand) {
			System.out.print(tmp.m + "(" + tmp.number + ") ");
		}
		System.out.println("");
	}
}
