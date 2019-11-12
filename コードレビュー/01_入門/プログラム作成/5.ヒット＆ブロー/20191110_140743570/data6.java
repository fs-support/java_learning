package c;

import java.util.Random;
import java.util.Scanner;

public class data6 {
	//ヒントリスト
	static int [] data3=new int[5];
	static int c=0;
	//リスト
	static int [] data2=new int[5];
	//ヒット
	static int data5;
	//ブロー
	static int data6;
	//回数
	static int d=0;

	public static void main(String[] args) {

		//乱数
		Random re=new Random();
		Scanner sc=new Scanner(System.in);
			for(int i = 0; i < data2.length; i++){
				Loop: while(true){
					data2[i] = re.nextInt(10);
						for(int j = 0; j < i; j++){
							if(data2[j] == data2[i]) continue Loop;
						}
						break;
				}
			}

			//ヒントリスト
				for(int i = 0; i < data3.length; i++){
					Loop: while(true){
						data3[i] = re.nextInt(5);
							for(int j = 0; j < i; j++){
								if(data3[j] == data3[i]) continue Loop;
							}
							break;
					}
				}

			while(true) {
				d++;
				Data2() ;
				System.out.print(data2.length+"桁の数字を入力してください。");
				String data3=sc.next();
				 if( Data1(data3)==1) {
					 System.out.println("おめでとう！"+d+"回目で成功♪");
				break;
				 }else {
					 System.out.println("ヒット："+data5+"個、ブロー："+data6+"個");
					 System.out.println();
				 }
			}
	sc.close();
	}
	public static int Data1(String data3) {
		data5=0;
		data6=0;
			for(int i=0;i<data2.length;i++) {
				String data1 = String.valueOf(data2[i] );
					if(data1.equals(data3.substring(i,i+1))) {
						data5++;
					}else {
						for(int l=0;l<data2.length;l++) {
							if(data1.equals(data3.substring(l,l+1))) {
								data6++;
							}
						}
					}
			}
			if(data5==data2.length) {
				return 1;
			}else {
				return 0;
			}
	}
	public static void Data2() {
		if(d%3==0&&c<data2.length) {
			 System.out.println("ヒント！！"+data2[data3[c]]+"があるよ");
			 c++;
		}
	}
}



