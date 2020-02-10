package programSakusei;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class trumpHit {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		int i = 0, j = 0;
		
		HashMap<Integer,String> Marks = new HashMap<Integer,String>();	//連想配列：Key（前）とValue（後）...Keyで呼び出し
		Marks.put(0,"ハート");
		Marks.put(1,"ダイヤ");
		Marks.put(2,"スペード");
		Marks.put(3,"クローバー");
		
		HashMap<Integer,String> Nums = new HashMap<Integer,String>();
		Nums.put(1,"A");
		Nums.put(2,"2");
		Nums.put(3,"3");
		Nums.put(4,"4");
		Nums.put(5,"5");
		Nums.put(6,"6");
		Nums.put(7,"7");
		Nums.put(8,"8");
		Nums.put(9,"9");
		Nums.put(10,"10");
		Nums.put(11,"J");
		Nums.put(12,"Q");
		Nums.put(13,"K");
		
		int comMark = rand.nextInt(4) , comNum = (rand.nextInt(13) + 1) , playMark = 0 , playNum = 0;
		
		System.out.print("トランプを選んだよ\nトランプの図柄を当ててね\n回数は3回まで！\n\n0:ハート\n1:ダイヤ\n2:スペード\n3:クローバー\n");
		
		while(i < 3) {
			
			System.out.print("どれだと思う？：");
			playMark = sc.nextInt();
			
			if(playMark == comMark) {
			System.out.println("\n正解！図柄は" + Marks.get(comMark) + "だよ");
			break;
			}
			else {
			System.out.println("\n残念！" + Marks.get(playMark) + "じゃないよ");
			i++;
			if(i == 3)System.out.println("正解は" + Marks.get(comMark) + "でした！");
			
			}
			
		}
		
		System.out.println("\n次は数字を当ててね\n回数は4回まで！\n");
		
		while(j < 4) {
		
			System.out.print("どれだと思う？：");
			playNum = sc.nextInt();
		
			if(playNum == comNum) {
				System.out.println("\n正解！" + Marks.get(comMark) + "の" + Nums.get(comNum) + "だよ");
				break;
			}
			else {
				System.out.println("\n残念！" + Nums.get(playNum) + "じゃないよ");
				j++;
				if(j == 4) {
					System.out.println("正解は" + Nums.get(comNum) + "でした！");
					break;
				}
				
				if(playNum < comNum) {
					System.out.println("\nもっと大きい数字だよ");
				}
				else {
					System.out.println("\nもっと小さい数字だよ");
				}
				
			}
		}
	}

}
