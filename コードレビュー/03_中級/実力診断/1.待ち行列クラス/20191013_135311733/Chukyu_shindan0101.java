package chukyu_shindan;

public class Chukyu_shindan0101{

	public static void main(String[] args) {

		Queue queue = new Queue();

		queue.push("1番目");
		queue.push("2番目");
		queue.push("3番目");

		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
	}
}
