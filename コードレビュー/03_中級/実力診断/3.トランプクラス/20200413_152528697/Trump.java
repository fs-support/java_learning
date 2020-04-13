package chukyuhen;

import java.util.ArrayList;
import java.util.Collections;

public class Trump {
	ArrayList<Card> array = new ArrayList<Card>();

	void Reset() {
		array.clear();
		for (int i = 1; i <= 13; i++) {
			Card card = new Card(Enumeration.Mark.Diamonds, i);
			array.add(card);
		}
		for(int j = 1; j <= 13; j++) {
			Card card = new Card(Enumeration.Mark.Hearts , j);
			array.add(card);
		}
		for(int p = 1; p <= 13; p++) {
			Card card = new Card(Enumeration.Mark.Diamonds , p);
			array.add(card);
		}
		for(int q = 1; q <= 13; q++) {
			Card card = new Card(Enumeration.Mark.Spades , q);
			array.add(card);
		}
	}

	void Shuffle() {
		Collections.shuffle(array);
	}

	Card Draw() {
		Card drawCard = array.get(0);
		array.remove(0);
		return drawCard;
	}
}
