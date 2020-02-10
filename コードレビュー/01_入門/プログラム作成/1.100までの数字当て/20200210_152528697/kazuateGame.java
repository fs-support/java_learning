package programSakusei;

import java.util.Scanner;

public class kazuateGame {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int ref = 10;
		System.out.println("数字を当ててみてね。\n答えられるのは" + ref + "回までだよ。\n");
		Scanner sc = new Scanner(System.in);
		
		int count = 1 , input;
		int kazu = (int) (Math.random()*1000);
		
		while(count<=ref) {
			System.out.print(count + "回目：");
			input = sc.nextInt();
			
			if(input == kazu) {
				System.out.println("\nすごい！！"+ count + "回で当てられちゃった！");
				break;
			}
			else if(input < kazu) {
				System.out.println("もっと大きい数だよ");
			}
			else {
				System.out.println("もっと小さい数だよ");
			}
			
			count++;
		}
		
		if(count >= ref)
			System.out.println("\n残念！！正解は" + kazu + "でした！");
		

	}

}
