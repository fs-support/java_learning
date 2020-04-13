package chukyuhen;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// Queue クラスを生成 
		Queue queue = new Queue();

		// キューに追加する 
		queue.push("１番目");
		queue.push("２番目");
		queue.push("３番目");

		// キューから取り出して表示する 
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
	}

}
