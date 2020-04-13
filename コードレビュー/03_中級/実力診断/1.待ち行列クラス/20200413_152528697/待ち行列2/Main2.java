package chukyuhen;

public class Main2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// Stack クラスを生成 
		Stack stack = new Stack(); 
		 
		// スタックに追加する 
		stack.push("１番目");
		stack.push("２番目"); 
		stack.push("３番目"); 
		stack.push("４番目"); 
		stack.push("５番目"); 
		 
		// スタックから取り出して表示する 
		System.out.println(stack.pop()); 
		System.out.println(stack.pop()); 
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
