package Exam0504;

import java.util.Scanner;

public class Exam0504 {

	public static void main(String[] args) {

		//キーボード入力を行う準備
		Scanner stdin = new Scanner(System.in);
		String data;

		System.out.println("値を入力してください");
		data = stdin.next();	//「data」を保持

		System.out.println("input character : "+data);

		//受け取って、出力する
	int result =is_num(data);
	if (result==1)
	{
		System.out.println("'"+data+"' is a numeral.");
	}
	else {
		System.out.println("'"+data+"' is not a numeral.");
	}

	}

	//文字型の値を１つ受け取り、その値が「数字」なら1、「それ以外の文字」なら0を返す。
	private static int is_num(String data)

	{

		try {
	        Integer.parseInt(data);
	        return 1;
	        } catch (NumberFormatException e) {
	        return 0;

	    }

	}
}

