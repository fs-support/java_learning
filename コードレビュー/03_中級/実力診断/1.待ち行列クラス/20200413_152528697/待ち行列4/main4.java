package chukyuhen;

public class main4 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// Queue クラスを生成 
		Queue queue = new Queue(); 
		 
		// キューに追加する 
		queue.push("1 番目", 10); 
		queue.push("2 番目", 10); 
		queue.push("3 番目", 100); 
		queue.push("4 番目", 50); 
		 
		// キューから取り出して表示する 
		System.out.println(queue.pop()); 
		System.out.println(queue.pop()); 
		System.out.println(queue.pop()); 
		System.out.println(queue.pop());

	}

}
