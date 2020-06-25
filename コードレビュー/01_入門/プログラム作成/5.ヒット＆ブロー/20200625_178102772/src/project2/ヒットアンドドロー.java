package project2;



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class hitandbrow {


	public static void main (String[]args) throws Exception{
		Random random = new Random();
		Scanner stdin = new Scanner(System.in);


		//PCが選んだ数字をいれる
		ArrayList<Integer>  answer = new ArrayList<Integer>();
		//0~9までの数字を入れる
		ArrayList<Integer> numbers = new ArrayList<Integer>();


		for(int i =0; i<10; i++) {
			numbers.add(i);
		}


		for(int i=0; i<4; i++) {
			//numbersからindexに数字をいれる、ランダム仕様に

			int index = random.nextInt(numbers.size());
			answer.add(index);

			//2重を防ぐ為、使用したindexの数字を削除する。
			numbers.remove(index);
		}


		System.out.println("numbers = " + numbers);
		System.out.println("answer = " + answer);
		System.out.println("");


		int count = 1;


		//答えが全て当たるまで回る無限ループを作る。
		while(true) {

			System.out.println("4桁の数字を入力してください");
			int input = stdin.nextInt();
			System.out.println("入力された数字は"+ input + "です");

			ArrayList<Integer> user = new ArrayList<Integer>();

			user.add((input / 1000)%10);
			user.add((input / 100)%10);
			user.add((input / 10)%10);
			user.add(input % 10);

			System.out.println("");
			System.out.println(user);

			//同じ数が何個あるかcountする。
			int hit = 0;
			for(int i=0; i<4; i++) {
				if(answer.get(i) == user.get(i)) {
					hit ++;
				}
			}


			//違う数が何個あるかcountする。
			int blow =0;
			for(int i=0; i<4; i++) {

				int user_num = user.get(i);


					for(int j=0; j<4; j++) {
						if(user_num == answer.get(i)) {

							//一致じゃない個所をcountしてる。
							if(i != j) {
								blow ++;
							}
						}
					}
			}

			//４つ揃ったらwhileから抜ける。
			if(hit == 4) {
				break;
			}else {
				System.out.println("残念！ヒット"+hit+"/ブロー"+blow);
				//何回目のチャレンジかcountする。
				count ++;
			}
		}

			System.out.println("おめでとう！" +count+ "回目で当てられたね！");
			stdin.close();
	}

}
