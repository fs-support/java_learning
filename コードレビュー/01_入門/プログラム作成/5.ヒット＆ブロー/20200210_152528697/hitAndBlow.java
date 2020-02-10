package programSakusei;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Random;
import java.util.Scanner;

public class hitAndBlow {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> playNum = null;
		ArrayList<Integer> comNum = new ArrayList<Integer>();
		
		//コンピュータの数字生成(重複なし)
		for(int i = 0 ; i < 10 ; i++) {
			comNum.add(i);
		}
		Collections.shuffle(comNum);
		
		int Hit = 0 , kaisuu = 0 , hint = 0;

		while(Hit < 5) {

			//入力数値を一時的に保持
			int holdNum = 0;
			System.out.print("5桁の数値を入力してください：");
			holdNum = sc.nextInt();

			//各桁の数字を取り出しArrayListに格納
			playNum = new ArrayList<Integer>();
			playNum.add(holdNum / 10000);
			playNum.add((holdNum % 10000) / 1000);
			playNum.add((holdNum % 1000) / 100);
			playNum.add((holdNum % 100) / 10);
			playNum.add(holdNum % 10);


			//Hit
			Hit = 0;
			for(int i = 0 ; i < 5 ; i++) {
				if(comNum.get(i) == playNum.get(i)) {
					Hit++;
				}
			}

			//Blow
			int Blow = 0;
			for(int i = 0 ; i < 5 ; i++) {
				for(int j = 0 ; j < 5 ; j++) {
					if (comNum.get(i) == playNum.get(j)) {
						Blow++;
					}
				}
			}
			
			//表示
			kaisuu++;
			System.out.println("ヒット：" + Hit + "個、ブロー：" + Blow + "個\n");
			
			//Hint
			if((kaisuu % 3) == 0) {
				System.out.println((hint+1) + "つ目の数字は" + comNum.get(hint) + "だよ");
				hint++;
			}
		}
		
		if(Hit == 5) {
			System.out.println("おめでとう！" + kaisuu + "回目で成功♪");
		}
		

		//確認
		System.out.print(playNum.get(0));
		System.out.print(playNum.get(1));
		System.out.print(playNum.get(2));
		System.out.print(playNum.get(3));
		System.out.println(playNum.get(4));

		System.out.print(comNum.get(0));
		System.out.print(comNum.get(1));
		System.out.print(comNum.get(2));
		System.out.print(comNum.get(3));
		System.out.println(comNum.get(4));

	}

}
