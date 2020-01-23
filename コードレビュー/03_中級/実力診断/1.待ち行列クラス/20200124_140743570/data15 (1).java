package c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class data15 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ


			Queue queue=new Queue();
			queue.push("1番目",10);
			queue.push("2番目",10);
			queue.push("3番目",100);
			queue.push("4番目",50);
			System.out.println(queue.pop());
			System.out.println(queue.pop());
			System.out.println(queue.pop());
			System.out.println(queue.pop());

	}
}
class Queue{
	static	List<Integer> list = new ArrayList<Integer>();
	 Map<Integer,String> prefs = new HashMap< Integer,String>();
	 static int k=0;
	static int ku=-1;
	public void push(String q ,int priority){
		if(k==priority) {

			prefs.put( priority-1,q);
			list.add(priority-1);
			Collections.sort(list, Collections.reverseOrder());
	k=priority;
		}else {
			prefs.put( priority,q);
			list.add(priority);
			Collections.sort(list, Collections.reverseOrder());
			k=priority;
		}
	}
	public  String pop (){
		ku++;
	return	prefs.get(list.get(ku));

	}
}
