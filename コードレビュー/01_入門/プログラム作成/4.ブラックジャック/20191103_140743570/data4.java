package c;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class data4 {

	//data1 乱数  data1 配る手札
	static Random re=new Random();
	static int data1 ;
	//data2 自分　リスト
	 static	List<Integer > data2 = new ArrayList<Integer >();
	//data3ディーラー　リスト
	 static	List<Integer > data3 = new ArrayList<Integer >();
	//data4カードリスト
	 static String [] data4=new String[] {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	 //data5入力値
	static Scanner sc =new Scanner(System.in);
	//Data1ディーラー合計
	 static	int Data1=0;
	//Data2自分合計
	 static	int Data2=0;
	//Data3Ｙ/Ｎ判定
	 static	String Data3="Y";
	 //合計 gou
	 static int gou=0;
	 //コイン　 Coins
	 static int Coins=100;
	 // ディーラー合計 DIDetermine()
	 // プレイヤー合計 PLDetermine()
	 //最初 First()
	 //21判定 Blackjack()
	 //プレイヤー処理 Player（）
	 //ディーラー処理 Dealer()
	 public static void main(String[] args) {
		 while(true) {
			 data2.clear();
			 data3.clear();
			 	System.out.println("参加料コイン10になります。");
				Coins=Coins-10;
				System.out.println("コインが"+Coins+"個になりました。");
				System.out.println("");
				First();
			System.out.println("");
			System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
			if(Blackjack()==0) {
					if(Player()==0) {
						Dealer();
					}

					 if(Coins<0) {
						System.out.println("");
						System.out.println("コインが"+Coins+"個になりました。");
						System.out.println("あなたの負けです。");
						sc.close();
						break;
					 }
			}
		}
	}
	 public static void	 Dealer() {
		 while(true) {
				if(DIDetermine()<18) {
						data1=re.nextInt(13);
						System.out.println("ディーラーに「"+data4[data1]+"」が配られました。");
						data3.add(data1);;
						System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
						System.out.println("");
					}else{
							if(DIDetermine()>PLDetermine()&&DIDetermine()<=21) {
								System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
								System.out.println("");
								System.out.println("あなたの負けです。");
								System.out.println("");
								System.out.println("コインが"+Coins+"個になりました。");
								break;
							}else if(DIDetermine()==PLDetermine()){
								System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
								System.out.println("");
								System.out.println("引き分けです。");
								System.out.println("コインが+10になりました。");
								System.out.println("コインが"+Coins+"個になりました。");
								Coins=Coins+310;
								break;
							}else {

								System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
								System.out.println("");
								System.out.println("あなたの勝ちです。");
								System.out.println("");
								System.out.println("コインが+20になりました。");
								Coins=Coins+20;
								System.out.println("コインが"+Coins+"個になりました。");
								break;
							}
						}
			}
	 }
		 public static int Player() {
			 while(true) {
				 if(Blackjack()==1) {
					 return 1;
				 }
					System.out.println("現在の合計は"+PLDetermine()+"です。");
					System.out.print("もう1枚カードを引きますか？（Ｙ/Ｎ）：");
					String data5 = sc.next();
					System.out.println("");
					if(data5.equals(Data3)) {
							data1=re.nextInt(13);
							System.out.println("あなたに「"+data4[data1]+"」が配られました。");
							data2.add(data1);
								if(PLDetermine()>22) {
									System.out.println("現在の合計は"+PLDetermine()+"です。");
									System.out.println("バーストしています。");
									System.out.println("あなたの負けです。");
									System.out.println("");
									System.out.println("コインが"+Coins+"個になりました。");
									return 1;
								}
					}else{
						return 0;
					}
				}

		 }
	 public static int Blackjack() {

		if(DIDetermine()==21||PLDetermine()==21) {
			if(PLDetermine()==21&&DIDetermine()==21) {
				System.out.println("ディーラー,合計"+Data1);
				System.out.println("プレイヤー,合計"+Data2);
				System.out.println("ディーラー,プレイヤー共にブラックジャックです。");
				System.out.println("引き分けです。");
				System.out.println("コインが+10になりました。");
				Coins=Coins+10;
				System.out.println("コインが"+Coins+"個になりました。");
				return 1;
			}else if(PLDetermine()==21) {
				System.out.println("ディーラー,合計"+Data1);
				System.out.println("プレイヤー,合計"+Data2);
				System.out.println("プレイヤーがブラックジャックです。");
				System.out.println("あなたの勝ちです。");
				System.out.println("コインが+30になりました。");
				Coins=Coins+30;
				System.out.println("コインが"+Coins+"個になりました。");
				return 1;
			}else if(DIDetermine()==21) {
				System.out.println("ディーラー,合計"+Data1);
				System.out.println("プレイヤー,合計"+Data2);
				System.out.println("ディーラーがブラックジャックです。");
				System.out.println("あなたの負けです。");
				System.out.println("");
				System.out.println("コインが"+Coins+"個になりました。");
				return 1;
			}

		}
		return 0;
	 }
	 public static void First(){
		for(int i=0;i<=1;i++) {
			data1=re.nextInt(13);
			System.out.println("あなたに「"+data4[data1]+"」が配られました。");
			data2.add(data1);
			data1=re.nextInt(13);
			System.out.println("ディーラーに「"+data4[data1]+"」が配られました。");
			data3.add(data1);
		}
	}
	public static int PLDetermine(){
		for(int i=0;i<=1;i++) {
		gou=0;
		for (int str : data2) {
			switch (str) {
			case 0:
				if(Data2<=21) {
					gou=gou+11;
				}else {
					gou=gou+1;
				}
				break;
			case 10:
				gou=gou+10;
				break;
			case 11:
				gou=gou+10;
				break;
			case 12:
				gou=gou+10;
				break;
			default:
				gou=gou+str+1;
				break;
			}
		}
	Data2=gou;
		}
		return gou;

	}
	public static int DIDetermine(){
		for(int i=0;i<=1;i++) {
				gou=0;
			for (int str : data3) {
				switch (str) {
				case 0:
					if(Data1<=21) {
						gou=gou+11;
					}else {
						gou=gou+1;
					}
					break;
				case 10:
					gou+=10;
					break;
				case 11:
					gou+=10;
					break;
				case 12:
					gou+=10;
					break;
				default:
					gou=gou+str+1;
					break;
				}
			}
			Data1=gou;
		}
			return gou;
	}
}
