package chukyuhen;

public class Main3 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// RandomQueue クラスを生成 
		RandomQueue queue = new RandomQueue(); 
		 
		// キューに追加する 
		queue.push("１番目"); 
		queue.push("２番目"); 
		queue.push("３番目"); 
		queue.push("４番目");
		queue.push("５番目");
		 
		// キューから取り出して表示する 
		System.out.println(queue.pop()); 
		System.out.println(queue.pop()); 
		System.out.println(queue.pop()); 
		System.out.println(queue.pop()); 
		System.out.println(queue.pop()); 
	}

}
