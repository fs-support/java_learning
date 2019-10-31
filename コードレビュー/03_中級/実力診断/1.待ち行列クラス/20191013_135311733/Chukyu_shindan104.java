package chukyu_shindan;

public class Chukyu_shindan104{

	public static void main(String[] args) {

		Queue04 queue = new Queue04();

		queue.push("1 番目", 10);
		queue.push("2 番目", 10);
		queue.push("3 番目", 100);
		queue.push("4 番目", 50);

		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
	}
}
