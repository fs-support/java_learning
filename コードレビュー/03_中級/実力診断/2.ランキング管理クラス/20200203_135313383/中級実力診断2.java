/*0401*/
import java.util.*;

public class Main{
	public static void main(String[] args)throws Exception{
		Ranking ranking = new Ranking();
		
		ranking.EntryScore(300);
		ranking.EntryScore(100);
		ranking.EntryScore(400);
		ranking.EntryScore(200);
		
		ranking.PrintRanking();
		
	}
}

import java.util.*;
/*ランキングを管理するクラス*/
class Ranking{
	/*得点を格納しておくリスト*/
	ArrayList<Integer> _scorelist = new ArrayLisy<Integer>();
	
	/*ランキングを管理するクラス*/
	public Ranking(){}
	
	/*得点と名前を格納するメソッド
	@param point*/
	
	public void EntryScore(int point){
		/*スコアクラスをリストに格納*/
		_scorelist.add(point);
		
		/*スコアを降順になるようにソート*/
		/*Collections.reverseOrder()で降順、何も指定しないなら昇順*/
		/*ソートするなら比較するものがないといけないためリストのサイズが1以上としている*/
		if((_scorelist.size() > 1){
			Collections.sort(_scorelist,Collections.reverseOrder());
		}
	}
	
	/*ランキングを表示するメソッド*/
	public void PrintRanking(){
		for(int i = 0;i < _scorelist.size(); i++){
			System.out.println(String.valueOf(i+1) + "位：" + String.valueOf(_scorelist.get(1)));
		}
	}
}

/*0402*/
import java.util.*;

public class Main{
	public static void main(String[] args)throws Exceprion{
		
		Ranking ranking = new Ranking();
		
		ranking.EntryScore(300,"name300");
		ranking.EntryScore(100,"name100");
		ranking.EntryScore(400,"name400");
		ranking.EntryScore(200,"name200");
		
		ranking.PrintRanking();
	
	}
}

import java.util.*;
/*ランキングを管理するクラス*/
class Ranking{
	/*得点と名前をフィールドに持つscoreクラスを格納しておくリスト*/
	ArrayList<Score> _scorelist = new ArrayList<Score>();
	
	/*コンストラクタ
	ランキングを管理するクラス*/
	
	public Ranking(){}
	
	/*得点と名前を格納するメソッド
	@param point
	@param name*/
	
	public void EntryScore(int point,String name){
		/*得点と名前を格納するScoreクラスをインスタンス化*/
		Score score = new Score(point,name);
		
		/*スコアクラスをリストに格納*/
		_scorelist.add(score);
		
		/*スコアを降順になるようにソートする*/
		if(_scorelist.size() > 1){
			Collections.sort(_socrelist,Comparator.reverseOrder());
		}
	}
	
	/*ランキングを表示するメソッド*/
	public void PrintRanking(){
		for(int i = 0; i < _scorelist.size(); i++){
			System.out.println(String.valueOf(i+1) + "位：" + _scorelist.get(i).Getname() + " " + String.valueOf(_scorelist.get(i).Getpoint()));
		}
	}
}

import java.util.*;
/*得点と名前をもつscoreクラス*/
class Score implements Comparable<Score>{
	
	/*得点*/
	private int _point;
	/*名前*/
	private String _name;
	
	/*コンストラクタ
	@param point
	@param name*/
	
	public Score(int point,String name){
		this._point = point;
		this._name = name;
	}
	
	/*getter
	@return 得点*/	
	public int Getpoint(){
		return this._point;
	}
	
	/*
	@return 名前*/
	public String Getname(){
		return this._name;
	}
	
	/*setter
	@param point 得点*/
	public void Setpoint(int point){
		this._point = point;
	}
	
	/*
	@param name 名前*/
	public void Setname(String name){
		this._name = name;
	}
	
	/*
	比較を行うメソッド
	@param score 比較対象
	得点の比較を行う！*/
	
	@Override
	public int compareTo(Score score){
		/*このインスタンスが、compareToの引数として渡されたObjectより大きい(後ろに並ぶ)なら正の値、このインスタンスが、compareToの引数として渡されたObjectより小さい(前に並ぶ)なら負の値を返す。同じなら0を返す*/
		if(this._point < score.Getpoint()){
			return - 1;
		}else if(this._point > score.Getpoint()){
			return 1;
		}else{
			return 0;
		}
	}
}

/*0501*/
/*enum型で列挙させる*/
/*;を付けることで通常のクラスと同様に定義になる*/
public enum Card{
	
	d1(mark.Diamonds,1),h1(mark.Hearts,1),c1(mark.Clubs,1),s1(mark.Spades,1),
	d2(mark.Diamonds,2),h2(mark.Hearts,2),c2(mark.Clubs,2),s2(mark.Spades,2),
	d3(mark.Diamonds,3),h3(mark.Hearts,3),c3(mark.Clubs,3),s3(mark.Spades,3),
	d4(mark.Diamonds,4),h4(mark.Hearts,4),c4(mark.Clubs,4),s4(mark.Spades,4),
	d5(mark.Diamonds,5),h5(mark.Hearts,5),c5(mark.Clubs,5),s5(mark.Spades,5),
	d6(mark.Diamonds,6),h6(mark.Hearts,6),c6(mark.Clubs,6),s6(mark.Spades,6),
	d7(mark.Diamonds,7),h7(mark.Hearts,7),c7(mark.Clubs,7),s7(mark.Spades,7),
	d8(mark.Diamonds,8),h8(mark.Hearts,8),c8(mark.Clubs,8),s8(mark.Spades,8),
	d9(mark.Diamonds,9),h9(mark.Hearts,9),c9(mark.Clubs,9),s9(mark.Spades,9),
	d10(mark.Diamonds,10),h10(mark.Hearts,10),c10(mark.Clubs,10),s10(mark.Spades,10),
	d11(mark.Diamonds,11),h11(mark.Hearts,11),c11(mark.Clubs,11),s11(mark.Spades,11),
	d12(mark.Diamonds,12),h12(mark.Hearts,12),c12(mark.Clubs,12),s12(mark.Spades,12),
	d13(mark.Diamonds,13),h13(mark.Hearts,13),c13(mark.Clubs,13),s13(mark.Spades,13);
	
	/*コンストラクタ*/
	private Card(Mark mark,int number){
		this.mark = mark;
		this.number = number;
	}
	
	/*数値*/
	private int number = 0;
	public int getNumber(){ return mark; }
	
	/*マーク*/
	private Mark mark = mark.Diamonds;
	public Mark getMark(){ return mark;}
	
	/*マーク*/
	public enum Mark{
		
		Diamonds("ダイヤ",1),
		Hearts("ハート",2),
		Clubs("クラブ",3),
		Spades("スペード",4);
		
		/*コンストラクタ*/
		private Mark(String jpName, int rank){
			this.jpName = jpName;
			this.rank = rank;
		}
		
		/*日本語名*/
		private String jpName = " ";
		public String getJpName(){ return jpName; }
		
		/*優先度*/
		private int rank = 0;
		public int getRank(){ return rank; }
		
	}
}

import java.util.*

class TrumpCards{
	private String card;
	private int num;
	
	public class TrumpCard(String card,int num){

/*========================*/
/*色々見て書いてみる*/
public class Deck{
	private final LinkedList<Card> deck = new LinkedList<Card>();
	
	public Deck(){
		for(Mark m : Mark.values())
		for(Number v : Number.values())
		deck.add(new Card(m,v));
	}
	
	public Card deal(){
		return deck.poll();
	}
	
	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	public String toString(){
		return deck.toString():
	}
}

/*他にも参考になりそうなものを*/
package noviceH;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

public class ClackjackClass{
	
	public static void main(String[] args){
		
		System.out.println("ゲーム開始！");
		/*空の山札を作成*/
		List <Integer> deck = new ArrayList<>(52);
		/*山札をシャッフル*/
		
		/*プレイヤー・ディーラーの手札リストを生成*/
		List<Integer> player = new ArrayList<>();
		List<Integer> dealer = new ArrayList<>();
		
		/*プレイヤー・ディーラーがカードを2枚引く*/
		player.add(deck.get(0);
		dealer.add(deck.get(1);
		player.add(deck.get(2);
		dealer.add(deck.add(3);
		
		/*山札の進行状況を記録する*（deckCount)*/
		int deckCount = 4;
				
		/*プレイヤーの手札枚数を記録する変数（playerHands)*/
		int playerHands = 2;
		
		/*プレイヤー・ディーラーの手札のポイントを表示*/
		System.out.println("プレイヤー/1枚目" + toDescription(player.get(0)));
		System.out.println("ディーラー/1枚目" + toDescription(dealer.get(0)));
		System.out.println("プレイヤー/2枚目" + toDescription(player.get(1)));
		System.out.println("ディーラー/2枚目？？？");
		
		/*それぞれのポイントを集計*/
		int playerPoint = sumPoint(player);
		int dealerPoint = sumPoint(dealer);
		
		System.out.println("あなたのポイント/" + playerPoint);
		
		/*プレイヤーのドローフェイズ*/
		while(true){
			System.out.println("カードを引く？ はい：y　いいえ：n");
			
			Scanner scan = new Scanner(System.in);
			String str = scan.next();
			
			if("n".equals(str)){
				break;
			}else if("y".equals(syr)){
				/*山札から1枚加える*/
				player.add(deck.get(deckCount));
				/*山札と手札を進める*/
				deckCount++;
				playerHnads++;
				
				System.out.println("あなたの" + playerHands + "枚目のカードは" + toDescription(player.get(playerHnads - 1)));
				playerPoint = sumPoint(player);
				System.out.println("合計" + playerPoint);
				
				if(isBusterd(playerPoint){
					System.out.println("プレイヤーバースト");
					return
				}
			}else{
				System.out.println("あなたの入力は" + str +"。y か n を入力する"
			}
		}
		
		/*ディーラーが手札を17以上にするまでカードを引く*/
		while(true){
			/*手札が17以上ならブレイク*/
			if(dealerPoint >= 17){
				break;
			}else{
				/*手札に1枚追加*/
				dealer.add(deck.get(deckCount));
				/*山札を進める*/
				deckCount++;
			/*ディーラーの合計ポイント計算*/
			dealerPoint = sumPoint(dealer);
			/*ディーラーのバーストチェック*/
			if(isBusterd(dealerPoint){
				System.out.println("ディーラーバースト。あなたの勝利！");
				return;
			}
		}
	}
		/*ポイントの比較*/
		System.out.println("あなたのポイント/" + playerPoint); 
		System.out.println("ディーラーのポイント/" + dealerPoint);
		
		if(playerPoint == dealerPoint){
			System.out.println("引き分け");
		}else if(playerPoint > dealerPoint){
			System.out.println("勝利！");
		}else{
			System.out.println("敗北...");
	}
	
	/*山札(deck)に値を入れてシャッフルする*/
	private static void Shuffle(List<Integer> deck){
		
		/*リストに1-52の連番を入れる*/
		for(int i = 1; i <= 52; i++){
			deck.add(i);
		}
		/*山札をシャッフル*/
		Collections.shuffle(deck);
	}
	
	/*手札がバーストしているか判定*/
	private static boolean isBusted(int point){
		if(point <= 21){
			return false;
		}else{
			return true;
		}
	}
	
	/*現在の合計ポイントを計算*/
	private static int sumPoint(List<Integer> list){
		int sum = 0;
		
		for(int i = 0;i < list.size(); i++){
			sum = sum + toPoint(toNumber(list.get(i)));
		}
		
		return sum;
	}
	
	/*山札の通し番号を得点計算用のポイントに変換する*/
	private static int toPoint(int num){
		if(num == 11||num == 12||num == 13){
			num = 10;
		}
		return num;
	}
	
	/*山札の数をスートの文字列に置き換える*/
	private static String toDescription(int cardNumber){
		String rank = toRank(toNumber(cardNumber));
		String suit = toSuit(cardNumber);
		
		retunr suit + "の" + rank;
	}
	
	/*山札の数をカードの数に置き換える*/
	private static int toNumber(int cardNumber){
		int number = cardNumber % 13;
		if(number == 0){
			number = 13;
		}
		return number;
	}
	
	/*カード番号をランクに変換*/
	private static String toRank(int number){
		switch(number){
			case 1:
				return "A";
			case 11:
				return "J";
			case 12:
				return "Q";
			case 13:
				return "K";
			default:
				String str = String.valueOf(number);
				return str;
		}
	}
	
	/*山札の数をスートに置き換える*/
	private static String toSunt(int cardNumber){
		switch((cardNumber - 1)/13){
			case 0:
				return "クラブ";
			case 1:
				return "ダイヤ";
			case 2:
				return "ハート";
			case 3:
				return "スペード";
			default:
				return "例外です";
		}
	}
}