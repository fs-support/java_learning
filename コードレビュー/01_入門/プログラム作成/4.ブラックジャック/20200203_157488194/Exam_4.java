package jp.co.FStest08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exam_4 {
	public static void main(String[] args) {
		//ユーザ入力をする準備。
		Scanner stdin = new Scanner(System.in);

		// 最初に配る枚数
		final int FIRST_CARD_NUMS = 2;

		//図柄の配列を用意。また設定をする。
		String[] mark = { "ハート", "ダイヤ", "スペード", "クローバー" };

		//数字の配列を用意。初期値にA～Kを設定する。
		String[] number = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		//カードのデッキを作る。
		//ArrayListを使うことで52枚という制限を指定せず、枚数を数えられる。
		ArrayList<Card> deck = new ArrayList<>();

		for (int i = 0; i < 4; i++) {//図柄の回数分、リストを作る。

			for (int j = 0; j < 13; j++) {//数字分までリストを作る。

				deck.add(new Card(mark[i], number[j]));
			}
		}

		Collections.shuffle(deck);//上記のままでは0番からカードをとられていくためシャッフルしてから配る。

		// me のリストを作る
		Player me = new Player();
		Player you = new Player();

		int index = -1;
		for (int i = 0; i < FIRST_CARD_NUMS; i++) {//3回繰り返す
			me.add(deck.get(++index));//meに1枚ずつ配る
			you.add(deck.get(++index));//youに1枚ずつ配る
			//			list.remove("0");
		}

		//現在の合計値を算出
		me.printMyHand();
		me.printSum("プレイヤー");

		you.printMyHand();
		you.printSum("ディーラー");
		String draw = "";
		//追加のカードを引くかの選択
		while (me.sum() < 21) {
			System.out.println("もう１枚カードを引きますか？(Y/N)：");
			draw = stdin.next();
			if (draw.equals("y")) {
				Card drawCard = deck.get(++index);
				me.add(drawCard);

				System.out.println("あなたに「" + drawCard.getInfo() + " 」が配られました。");
				System.out.println("現在の合計は 「" + me.sum() + "」です。");
			} else {
				break;
			}
		}

		while (you.sum() <= 17) {
			Card drawCard = deck.get(++index);
			you.add(drawCard);
			System.out.println("");
			System.out.println("ディーラーに「" + drawCard.getInfo() + " 」が配られました。");
			you.printSum("ディーラー");
		}
		if (me.sum() == you.sum() || (me.sum() >= 22 && you.sum() >= 22)) {
			System.out.println("引き分けです。");
		} else if (me.sum() > you.sum() || me.sum() < 22) {
			System.out.println("あなたの勝ちです。");
		} else if (me.sum() < you.sum() || you.sum() < 22) {
			System.out.println("あなたの負けです。");
		}
	}
}

class Card {
	private String mark;
	private String number;

	Card(String mark) {
		this.mark = mark;
	}

	//Constructor
	Card(String mark, String number) {
		this.mark = mark;
		this.number = number;
	}

	//getMarkだけでは図柄しか引っ張ってこれない
	String getMark() {
		return this.mark;
	}

	//getNumberだけでは数字しか引っ張ってこれない
	String getNumber() {
		return this.number;
	}

	//markとnumberを両方取得できるgetInfoを作ることで両方取得できるようにしている。
	String getInfo() {
		return this.mark + ", " + this.number;
	}

	public int getIntNumber() {
		int result = 0;
		String strNum = this.getNumber();
		if (strNum.equals("A") || strNum.equals("J") || strNum.equals("Q") || strNum.equals("K")) {
			result = 10;
		} else {
			result = Integer.parseInt(strNum);
		}
		return result;
	}
}

class Player {//クラス定義
	private ArrayList<Card> hand;

	Player() {//フィールド定義
		this.hand = new ArrayList<>();
	}

	public void printSum(String name) {
		System.out.println(name + "の合計は 「" + this.sum() + "」です。");
	}

	void add(Card card) {//メソッド定義
		//		System.out.println("カード突っ込んだよ！");
		hand.add(card);
	}

	void printMyHand() {//メソッド定義
		for (Card card : this.hand) {//拡張for文
			System.out.println(card.getInfo());
		}
	}

	int sum() {
		int result = 0;
		for (Card c : this.hand) {
			result += c.getIntNumber();
		}
		return result;
	}

	ArrayList<Card> getMyHands() {
		return this.hand;
	}
}