/*0101*/
public class Main{
	public static void main(String[] args) throws Exception{
		
		Queue queue = new Queue();
		
		queue.push("1番目");
		queue.push("2番目");
		queue.push("3番目");
		
		System.out.println("キュー表示");
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println("");
	}
}

import java.util.ArrayList;

/*文字列を格納し、順番に値を取り戻すキュークラス*/

class Queue{
	/*文字列を格納しておくためのString型のArrayListを定義*/
	/*後々値をどれ程格納するかわからない動的に値を格納できるArrayListを利用*/
	ArrayList<String> list = new ArrayList<String>();
	
	/*コンストラクタ*/
	public Queue(){};
	
	/*リストに値を格納*/
	public void push(String str){
		list.add(str);
	}
	
	/*値を取得*/
	public String pop(){
		String str = "";
		
		/*値を取り出し、その値を配列に残さないために消す*/
		if(list.size() > 0){
			str = list.get(0);
		list.remove(0);
		}
		
		return str;
	}
}

/*0102*/
public class Main{
	public static void main(String[] args)throws Exception{
		/*スタッククラスのインスタンス化*/
		Stack stack = new Stack();
		
		/*スタックに値を格納する*/
		stack.push("1番目");
		stack.push("2番目");
		stack.push("3番目");
		
		/*結果の表示*/
		System.out.println("スタック表示");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println("");
	}
}

import java.util.ArrayList;

/*文字列を格納し、値を取り出すときは最新のものを取り出していくスタッククラス*/
class Stack{
	/*文字列を格納するためのリスト*/
	ArrayList<String> list = new ArryaList<String>();
	
	/*コンストラクタ*/
	public Stack(){}
	
	/*値を格納*/
	public void push(String str){
		list.add(str);
	}
	
	/*値の取り出し*/
	public String pop(){
		/*取り出す値を格納する変数*/
		String str = "";
		
		/*リストに一つでも値があるなら*/
		if(list.size() > 0){
			/*格納した値の最新のものを取り出すためリストのサイズを表すlist.size()を使う*/
			/*list.size()で取れる値は配列の0番目を1と数えていくのでここで1をひく、そうしないと値が格納されていないところを参照してエラーとなる*/
			str = list.get(list.size() - 1);
			
			/*取得したものをリストから削除*/
			/*list.index0f("indexの位置を知りたい文字列")で文字列のindexを検索可能*/
			list.remove(list.indexOf(str));
		}
		return str;
	}
}

/*0201*/
public class Main{
	public static void main(String[] args)throws Exception{
		
		/*ランダムに値を取り出すキュークラスをインスタンスにする*/
		RandomQueue randomqueue = new RandomQueue();
		
		/*ランダムキュークラスに値を格納*/
		randomqueue.push("1番目");
		randomqueue.push("2番目");
		randomqueue.push("3番目");
		
		/*結果*/
		System.out.println("ランダムキュー表示");
		System.out.println(randomqueue.pop());
		System.out.println(randomqueue.pop());
		System.out.println(randomqueue.pop());
		System.out.println("");
	}
}

import java.util.ArrayList;
import java.util.Random;

class Randomqueue{
	/*値の格納用リスト*/
    ArrayList<String> list = new ArrayList<String>();

    /*コンストラクタ*/
    public RandomQueue() {}

    /*値の格納*/
    public void push(String str) {
        list.add(str);
    }

    /*値の取り出し*/
    public String pop() {
        String str = "";

        if(list.size() > 0) {
            /*ランダムクラスの生成*/
            Random random = new Random();

            /*取り出す文字列のインデックスを生成*/
            int index = random.nextInt(list.size());

            /*popした値の保存*/
            str = list.get(index);
            /*popした値の削除*/
            list.remove(index);

        }

        return str;
    }

}

/*0301*/
public class Main{
	public static void main(String[] args)throws Exception{
		
		/*どの値から取り出すかを優先度に設定、優先度で値を取り出す*/
		PriorityQueue priorithqueue = new PriorityQueue();
		
		/*値と優先度をセットで格納！*/
		priorityqueue.push("1番目",10);
		priorityqueue.push("2番目",10);
		priorityqueue.push("3番目",100);
		priorityqueue.push("4番目",50);
		
		/*結果の表示*/
		System.out.println("優先度キュー表示");
		System.out.println(priorityqueue.pop());
		System.out.println(priorityqueue.pop());
		System.out.println(priorityqueue.pop());
		System.out.println(priorityqueue.pop());
		System.out.println("");
	}
}

import java.util.ArrayList;

/*優先度によって値を取り出す順番を変更するPriorityキュークラス*/
class PriorityQueue{
	/*値と優先度をフィールドに持つValue_Priorityクラスを格納*/
	ArrayList<Value_Priority> list = new ArrayList<Value_Priority>;
	
	/*コントラクタ*/
	public PriorityQueue(){};
	
	/*値を格納して優先度でソート*/
	public void push(String str,int priority){
		/*優先度と文字列を格納するクラスの生成*/
		Value_Priority val_pri = new Value_Priority(priority,str);
		
		list.add(val_pri);
		
		}
		
		/*値の出力*/
		public String pop(){
			
			/*優先度を格納する変数*/
			int max = 0;
			/*優先度が一番高いものがListの何番目かを格納する変数*/
			int imdex = 0;
			/*何度目のループかを格納する変数*/
			int counter = ;
			
			for(Value_Priority valuePriority : list){
				/*優先度が高いものがListの何番目に入っているか検索*/
				if(max < valuePriority.Getpriority()){
					max = valuePriority.Getpriority();
					index = counter;
				}
				counter++;
			}
			
			/*優先度が一番高い値を取得*/
			Value_Priority popView = list.get(index);
			/*取得したものをリストから削除*/
			list.remove(index);
			
			return popView.Getvalue();
		}
	}
	
/*優先度と文字列の値をもつクラス*/
/*何を基準にそーとを行うか記述する*/
class Value_Priority{
	/*優先度*/
	private int_priority;
	/*文字列の値*/
	private String _value;
	
	/*コントラクタ*/
	public Value_Priority(int pri,String val){
		_priority = pri;
		_value = val;
	}
	
	/*getter*/
	public int Getpriority(){
		return _priority;
	}
	
	/*getter*/
	public String Getvalue(){
		return _value;
	}
	
	/*setter*/
	public void Setpriority(int pri){
		_priority = pri;
	}
	
	/*setter*/
	public void Setvalue(String val){
		_value = val;
	}
}

/*0302*/
public class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue priorityqueue = new PriorityQueue();
		
		priorityqueue.push("1番目",10);
		priorityqueue.push("2番目",10);
		priorityqueue.push("3番目",100);
		priorityqueue.push("4番目",50);
		
		System.out.println("優先キュー表示");
		System.out.println(priorityqueue.pop());
		System.out.println(priorityqueue.pop());
		System.out.println(priorityqueue.pop());
		System.out.println(priorityqueue.pop());
		System.out.println("");
		
	}
}

import java.util.ArrayList;
import java.util.Collections;

class PriorityQueue{
	ArratList<Value_Priority> list = new ArrayList<Value_Priority>;
	
	public PriorityQueue(){};
	
	public void push(String str,int priority){
		Value_Priority val_pri = new Value_Priority(priority,str);
		
		list.add(val_pri);
		
		/*リストの要素が2つ以上あるときにソートを行うよう以下の条件を記述する*/
		if(list.size() > 1){
			/*何を基準にソートを行うかは抽象クラスComparable<T> を継承した
			Value_Priorityクラスで記述*/
			Collections.sort(list);
		}
	}
	
	public String pop(){
		String pop_str = " ";
		
		if(list.size() > 0){ /*() を忘れるな〜！？*/
			pop_str = list.get(0).Getvalue();
			list.remove(0);
		}
		
		return pop_str;
	}
}

/*インターフェース(Comparable<T>を実装することでソートを容易に！*/
class Value_Priority implements Comparable<Value_Priority>{
	/*優先度*/
	private int _priority;
	/*文字列の値*/
	public String _value;
	
	/*コントラクタ*/
	public Value_Priority(int pri,String val){
		_priority = pri;
		_value = val;
	}
	
	/*getter*/
	public int Getpriority(){
		return _priority;
	}
	
	/*getter*/
	public String Getvalue(){
		return _value;
	}
	
	/*setter*/
	public void Setpriority(int pri){
		_priority = pri;
	}
	
	/*setter*/
	public void Setvalue(String val){
		_value = val;
	}
	
	/*ここで優先度を基準にソートを行うことを記述*/
	/*Comparableを継承しているのでこのメソッドの記述がないとエラーになる*/
	public int compareTo(Value_Priority value_priority){
		/*このインスタンスが、compareToの引数として渡されたObjectより小さい(前に並ぶ)*/
		/*このインスタンスが、comparaToの引数として渡されたObjectより大きい(後ろに並ぶ)*/
		/*同じなら0を返す*/
		if(this._priority > value_priority.Getpriority()){
			return -1;
		}else if(this._priority < value_priority.Getpriority()){
			return 1;
		}else{
			return 0;
		}
	}
}