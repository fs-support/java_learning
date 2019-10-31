package c;

import java.util.Random;
import java.util.Scanner;

public class data1 {
	 static int up;
	 static int dow;
	 static int h;
	 static int j=0;
	 static String [] Results=new String[3];
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);

		System.out.println("メニュー番号");
		System.out.println("1　通常");
		System.out.println("2　3回勝負");
		System.out.println("3　あっちむいて");
		System.out.print("番号：");
		int data =sc.nextInt();
		switch(data) {
		 case 1:
			 DAta();
			 break;

		 case 2:
			 DAta1();
			 break;

		 case 3:
			 DAta2();
			 break;
		}
sc.close();
	}
	public static void DAta2(){
	Random ra = new Random();
	int COM =ra.nextInt(3);
	int Atti;
	Scanner sc=new Scanner(System.in);
	String [] names=new String[] {"グー","チョキ","パー"};
	String [] rund2=new String[] {"上","下","左","右"};
	System.out.println("じゃんけん勝負");
	System.out.println("グーチョキパーを数字で入力してね");
	System.out.println("0：グー");
	System.out.println("1：チョキ");
	System.out.println("2：パー");
	System.out.println("");
	System.out.print("最初はグー、じゃんけん：");
	int Player =sc.nextInt();

	while(true) {
		System.out.println(names[COM]+"(COM)と"+names[Player]+"(Player)で...");
		if(JDetermine( COM,Player)!=0) {
			  Atti =ra.nextInt(4);
				System.out.println("あっちむいてほい");
				System.out.println("上下左右を数字で入力してね");
				System.out.println("0：上");
				System.out.println("1：下");
				System.out.println("2：左");
				System.out.println("3：右");
				System.out.println("");
				System.out.print("あっちむいて：");
				Player =sc.nextInt();
				System.out.println(rund2[Atti]+"(COM)と"+rund2[Player]+"(Player)で...");
				if( Atti==Player) {
					if(h==1) {
						System.out.println("あなたの勝ち");
						 break;
					}else {
						System.out.println("あなたの負け");
						 break;
					}
				}else {
					if(h!=0) {
					System.out.println("");
					System.out.println("じゃんけんだよ");
					System.out.print("最初はグー、じゃんけん：");
					Player =sc.nextInt();
					 COM =ra.nextInt(3);
					}else {
					System.out.println("");
						System.out.print("あいこで：");
						Player =sc.nextInt();
						 COM =ra.nextInt(3);
					}
				}

		}else {
			 System.out.println("あいこだよ！");
			System.out.println("");
			System.out.print("あいこで：");
			Player =sc.nextInt();
			 COM =ra.nextInt(3);
		}
	}
sc.close();
}
	public static void DAta() {
		Random ra = new Random();
		int COM =ra.nextInt(3);
		Scanner sc=new Scanner(System.in);
		String [] names=new String[] {"グー","チョキ","パー"};
		System.out.println("じゃんけん勝負");
		System.out.println("グーチョキパーを数字で入力してね");
		System.out.println("0：グー");
		System.out.println("1：チョキ");
		System.out.println("2：パー");
		System.out.println("");
		System.out.print("最初はグー、じゃんけん：");
		int Player =sc.nextInt();

		while(true) {
			System.out.println(names[COM]+"(COM)と"+names[Player]+"(Player)で...");
			if(JDetermine( COM,Player)==1) {
				 System.out.println("あなたの勝ち");
				break;
			}else if(JDetermine( COM,Player)==2){
				 System.out.println("あなたの負け");
				 break;
			}else {
				 System.out.println("あいこだよ！");
				System.out.println("");
				System.out.print("あいこで：");
				Player =sc.nextInt();
				 COM =ra.nextInt(3);
			}
		}
	sc.close();
	}
	public static void DAta1() {
		Random ra = new Random();
		int COM =ra.nextInt(3);
		Scanner sc=new Scanner(System.in);
		String [] names=new String[] {"グー","チョキ","パー"};
		System.out.println("じゃんけん勝負");
		System.out.println("グーチョキパーを数字で入力してね");
		System.out.println("0：グー");
		System.out.println("1：チョキ");
		System.out.println("2：パー");
		System.out.println("");
		int Player;
		h=1;


		for( ;j<=2;) {

			if(h==0) {
				System.out.println("");
				System.out.print("あいこで：");
				Player =sc.nextInt();
			}else {
				System.out.print("最初はグー、じゃんけん：");
				 Player =sc.nextInt();
			}

			System.out.println(names[COM]+"(COM)と"+names[Player]+"(Player)で...");
			if(JDetermine( COM,Player)==1) {
				Results[j]="勝ち";
				System.out.println("");
				j++;
				up++;
			}else if(JDetermine( COM,Player)==2){
				Results[j]="負け";
				System.out.println("");
				j++;
				dow++;
			}


			COM =ra.nextInt(3);
		}
		for(int i=0;i<=2;i++) {
		System.out.println((i+1)+"回目"+Results[i]);
		}
		System.out.println("結果");
		if(up>=2) {
			System.out.println("あなたの勝ち");
		}else {
			 System.out.println("あなたの負け");
		}
		sc.close();
	}
	//じゃんけん判定
	public static int JDetermine(int COM ,int Player){

		switch(COM) {
	case 0:
		if(Player==2) {
			h= 1;
		}else if(Player==1){
			h= 2;
		}else {
			h= 0;
		}
		break;
	case 1:
		if(Player==0) {
			h= 1;
		}else if(Player==2){
			h=2;
		}else {
			h= 0;
		}
		break;
	case 2:
		if(Player==1) {
			h= 1;
		}else if(Player==0){
			h= 2;
		}else {
			h= 0;
		}
			break;
		}
		return h;
	}
}
