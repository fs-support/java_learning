package c;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class data15 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc=new Scanner(System.in);
		System.out.println("１.待ち行列管理クラス");
		System.out.println("メニュー番号");
		System.out.print("１.課題1\n2.課題1\n2.課題1\n3.課題3\n4.課題4\n入力：");
		int key=sc.nextInt();
		switch (key) {
		case 1:
			Queue queue=new Queue();
			queue.push("1番目");
			queue.push("2番目");
			queue.push("3番目");

			System.out.println(queue.pop());
			System.out.println(queue.pop());
			System.out.println(queue.pop());
			break;
		case 2:
			Stack stsck=new Stack();
			stsck.push("1番目");
			stsck.push("2番目");
			stsck.push("3番目");

			System.out.println(stsck.pop());
			System.out.println(stsck.pop());
			System.out.println(stsck.pop());
			break;
		case 3:
			RandomQueue randomqueue=new RandomQueue();
			randomqueue.push("1番目");
			randomqueue.push("2番目");
			randomqueue.push("3番目");

			System.out.println(randomqueue.pop());
			System.out.println(randomqueue.pop());
			System.out.println(randomqueue.pop());
			break;
		case 4:
			break;
		}

	}
}
class Queue{
	static	List<String> list = new ArrayList<String>();
	static int ku=-1;
	public void push(String q){
		list.add(q);
	}
	public  String pop (){
		ku++;
	return	list.get(ku);

	}
}
class Stack{
	static	List<String> list = new ArrayList<String>();
	static int ku=0;
	public void push(String q){
		list.add(q);
		ku=list.size();
	}
	public  String pop (){

		ku--;
	return	list.get(ku);

	}
}
class RandomQueue{
	static	List<String> list = new ArrayList<String>();
	static int ku=0;
	static int d=-1;
	static 	int[] ssu= new int[3];
	public void push(String q){
		list.add(q);
		ku=list.size();
	}

	public  String pop (){


		 Random ra=new Random();
		 if(d==-1) {
			 for(int i = 0; i < list.size(); i++){
		        Loop: while(true){
		        	ssu[i] = ra.nextInt(list.size());
		            for(int j = 0; j < i; j++){
		                if(ssu[j] == ssu[i]) continue Loop;
		            }
		            break;
		        }
		    }
		 }
d++;

return	list.get(ssu[d]);

	}
}