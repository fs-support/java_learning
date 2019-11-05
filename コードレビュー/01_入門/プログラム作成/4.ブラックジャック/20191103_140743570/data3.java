package c;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class data3 {

	//data1 乱数
	//data2 自分　リスト
	//data3ディーラー　リスト
	//data4カードリスト
	//data5入力値
	//Datat1ディーラー合計
	//Datat2自分合計
	//Data3Ｙ/Ｎ判定
	 static	List<Integer > data2 = new ArrayList<Integer >();
	 static	List<Integer > data3 = new ArrayList<Integer >();
	 static	int Data1=0;
	 static	int Data2=0;
	 static	String Data3="Y";
	 public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Random re=new Random();
		//data1 乱数 =re.nextInt(13);
		int data1 ;
Scanner sc =new Scanner(System.in);
		//data4カードリスト
		String [] data4=new String[] {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
			for(int i=0;i<=1;i++) {
				data1=re.nextInt(13);
				System.out.println("あなたに「"+data4[data1]+"」が配られました。");
				data2.add(data1);
				data1=re.nextInt(13);
				System.out.println("ディーラーに「"+data4[data1]+"」が配られました。");
				data3.add(data1);
			}
			if(DIDetermine()==21||PLDetermine()==21) {
				if(PLDetermine()==21&&DIDetermine()==21) {
					System.out.println("ディーラー,プレイヤー共にブラックジャックです。");
					System.out.println("引き分けです。");
					System.exit(0);
				}else if(PLDetermine()==21) {
					System.out.println("プレイヤーがブラックジャックです。");
					System.out.println("あなたの勝ちです。");
					System.exit(0);
				}else if(DIDetermine()==21) {
					System.out.println("ディーラーがブラックジャックです。");
					System.out.println("あなたの負けです。");
					System.exit(0);
				}
			}
			DIDetermine();
				System.out.println("");
				System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
//自分の手番
			while(true) {
				PLDetermine();
				System.out.println("現在の合計は"+PLDetermine()+"です。");
				System.out.print("もう1枚カードを引きますか？（Ｙ/Ｎ）：");
				String data5 = sc.next();
				System.out.println("");
				if(data5.equals(Data3)) {
						data1=re.nextInt(13);
						System.out.println("あなたに「"+data4[data1]+"」が配られました。");
						data2.add(data1);
						PLDetermine();
							if(PLDetermine()>22) {
								System.out.println("現在の合計は"+PLDetermine()+"です。");
								System.out.println("バーストしています。");
								System.out.println("あなたの負けです。");
								System.exit(0);
							}
					}else{
						break;
					}
			}
			sc.close();

			while(true) {
				DIDetermine();
				if(DIDetermine()<18) {
						data1=re.nextInt(13);
						System.out.println("ディーラーに「"+data4[data1]+"」が配られました。");
						data3.add(data1);
						PLDetermine();
						System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
						System.out.println("");
					}else{
							if(Data1<Data2) {
								System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
								System.out.println("");
								System.out.println("あなたの勝ちです。");
								break;
							}else if(Data1==Data2){
								System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
								System.out.println("");
								System.out.println("引き分けです。");
								break;
							}else {
								System.out.println("ディーラーの合計は"+DIDetermine()+"です。");
								System.out.println("");
								System.out.println("あなたの負けです。");
								break;
							}
						}
			}
	 }
//合計判定 自分

	 public static int PLDetermine(){

		int gou=0;
		for (int str : data2) {
			switch (str) {
			case 0:
				if(Data2<=20) {
					gou=gou+11;
				}else if(Data2==21) {
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
	if(Data2==21) {
		System.out.println("あなたの合計は21です。");
		System.out.println("");
		System.out.println("あなたがブラックジャックになりました。");
		System.out.println("あなたの勝ちです。");
		System.exit(0);	
	}
	return gou;

	}
	//合計判定 ディーラー

	public static int DIDetermine(){

		int gou=0;
		for (int str : data3) {
			switch (str) {
			case 0:
				if(Data1<=20) {
					gou=gou+11;
				}else if(Data1==21) {
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
		if(Data1==21) {
			System.out.println("ディーラーの合計は21です。");
			System.out.println("");
			System.out.println("ディーラーがブラックジャックになりました。");
			System.out.println("あなたの負けです。");
			System.exit(0);	
		}
		return gou;
	}
}