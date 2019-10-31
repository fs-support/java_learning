package c;

import java.util.Random;
import java.util.Scanner;

public class data36 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("メニュー番号");
		System.out.println("1　通常");
		System.out.println("2　入力１０回");
		System.out.println("3　桁数　3桁");
		System.out.println("4　離れている、近づいている");
		System.out.print("番号：");
		int DAta =sc.nextInt();
		switch(DAta) {
		 case 1:
			 Data1();
			 break;

		 case 2:
			 Data2();
			 break;

		 case 3:
			 Data3();
			 break;

		}
sc.close();
	}

	public static void Data1() {
		Scanner sc=new Scanner(System.in);
		Random ran = new Random();
		int Handle =ran.nextInt(11);
		int  Digital =ran.nextInt(3);
		String[] data1 = new 	String[] {"ハート","ダイヤ","スペード","クローバー"};
		String[] data2 = new 	String[] {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		System.out.println("トランプを選んだよ");
		System.out.println("トランプの図柄を当ててね");
		System.out.println("");
		System.out.println("0：ハート");
		System.out.println("1：ダイヤ");
		System.out.println("2：スペード");
		System.out.println("3：クローバー");
//記号あて
			for(;;) {

				System.out.print("どれだと思う？：");
				int data3 =sc.nextInt();
				System.out.println("");
				if(data3==Digital) {
					System.out.println("正解！図柄は"+data1[data3]+"だよ");
				break;
				}else if(data3>=0 &&data3<=3){

						System.out.println("残念！"+data1[data3]+"じゃないよ");
				}else {
					System.out.println("入力は0～3です。");
				}
			}
			//数字当て
			System.out.println("");
			System.out.println("次は数字を当ててね");
				for(;;) {

					System.out.println("");
					System.out.print("どれだと思う？：");
					String data3 =sc.next();
					for(int k=0;;k++) {
						if(data3.equals(data2[k])) {
							break;
						}else if(12==k) {
							System.out.println("");
							System.out.println("入力は (A,J,Q,K)大文字,1～10 です。");
							System.out.print("どれだと思う？：");
						 data3 =sc.next();
							k=-1;
						}
					}
					if(data3.equals(data2[Handle])) {
						System.out.println("正解！"+data1[Digital]+"の"+data2[Handle]);
						break;
					}else {
						System.out.println("残念！"+data3+"じゃないよ");
					}
				}
	sc.close();
	}
	 static int k=0;
  	static int o;
  	static int q=0;
  	public static void Data2() {
   		Scanner sc=new Scanner(System.in);
   		Random ran = new Random();
   		int Handle =ran.nextInt(12);
   		int  Digital =ran.nextInt(3);
   		String[] data1 = new 	String[] {"ハート","ダイヤ","スペード","クローバー"};
   		String[] data2 = new 	String[] {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
   		System.out.println("トランプを選んだよ");
   		System.out.println("トランプの図柄を当ててね");
   		System.out.println("");
   		System.out.println("0：ハート");
   		System.out.println("1：ダイヤ");
   		System.out.println("2：スペード");
   		System.out.println("3：クローバー");
   //記号あて
   			for(;;) {

   				System.out.print("どれだと思う？：");
   				int data3 =sc.nextInt();
   				System.out.println("");
   				if(data3==Digital) {
   					System.out.println("正解！図柄は"+data1[data3]+"だよ");
   				break;
   				}else if(data3>=0 &&data3<=3){

   						System.out.println("残念！"+data1[data3]+"じゃないよ");
   				}else {
   					System.out.println("入力は0～3です。");
   				}
   			}
   			//数字当て
  			System.out.println("");
  			System.out.println("次は数字を当ててね");
  				for(;;) {

  					System.out.println("");
  					System.out.print("どれだと思う？：");
  					String data3 =sc.next();
  					for(;;k++) {
  						if(data3.equals(data2[k])) {
  							break;
  						}else if(12==k) {
  							System.out.println("");
  							System.out.println("入力は (A,J,Q,K)大文字,1～10 です。");
  							System.out.print("どれだと思う？：");
  						 data3 =sc.next();
  							k=-1;
  						}
  					}
  					if(data3.equals(data2[Handle])) {
  						System.out.println("正解！"+data1[Digital]+"の"+data2[Handle]);
  						break;
  					}else {
  							int a= Math.abs(Handle-k);
  							if(q>1) {
  								if(o>a) {
  									System.out.println("近いよ");
  									o=a;
  									k=0;
  								}else if(o<a){
  									System.out.println("離れてるよ");
  									o=a;
  									k=0;
  								}else {
  									System.out.println("同じだよ");
  									o=a;
  									k=0;
  								}
  							}else {
  									System.out.println("残念！"+data3+"じゃないよ");
  									o= Math.abs(Handle-k);
  									q=3;
  									k=0;
  							}
  					}
  				}
  	sc.close();
          }
	 static int kai;
	static int kei;
	static int v;
	static int j;

  	public static void Data3() {
		Scanner sc=new Scanner(System.in);
		Random ran = new Random();
		int Handle =ran.nextInt(11);
		int  Digital =ran.nextInt(3);
		String[] data1 = new 	String[] {"ハート","ダイヤ","スペード","クローバー"};
		String[] data2 = new 	String[] {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		System.out.println("トランプを選んだよ");
		System.out.print("トランプの図柄を何回で当てる？:");
		kai =sc.nextInt();
		System.out.println("");
		System.out.print("トランプの数字を何回で当てる？:");
		kei =sc.nextInt();
		System.out.println("");
		System.out.println("0：ハート");
		System.out.println("1：ダイヤ");
		System.out.println("2：スペード");
		System.out.println("3：クローバー");
//記号あて
			for( v=0;kai>=v;v++) {

				System.out.print("どれだと思う？：");
				int data3 =sc.nextInt();
				System.out.println("");
				if(data3==Digital) {
					System.out.println("正解！図柄は"+data1[data3]+"だよ");
				break;
				}else if(data3>=0 &&data3<=3){

						System.out.println("残念！"+data1[data3]+"じゃないよ");
				}else {
					System.out.println("入力は0～3です。");
				}
			}
			if(kai<=v) {
				System.out.println("回数切れです、答えは、"+data1[Digital]+"です。");
			}
			//数字当て
			System.out.println("");
			System.out.println("次は数字を当ててね");
				for(j=0;kei>=j;j++) {

					System.out.println("");
					System.out.print("どれだと思う？：");
					String data3 =sc.next();
					for(int k=0;;k++) {
						if(data3.equals(data2[k])) {
							break;
						}else if(12==k) {
							System.out.println("");
							System.out.println("入力は (A,J,Q,K)大文字,1～10 です。");
							System.out.print("どれだと思う？：");
						 data3 =sc.next();
							k=-1;
						}
					}
					if(data3.equals(data2[Handle])) {
						System.out.println("正解！"+data1[Digital]+"の"+data2[Handle]);
						break;
					}else {
						System.out.println("残念！"+data3+"じゃないよ");
					}
				}
				if(kei<=j) {
    				System.out.println("回数切れです、答えは、"+data2[Handle]);
    			}
				sc.close();
	}
}