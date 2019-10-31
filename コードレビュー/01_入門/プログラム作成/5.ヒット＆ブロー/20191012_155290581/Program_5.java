package test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Program_5 {

	public static void main(String[] args) throws Exception {
		// ランダムクラスを使う準備をする
		Random random = new Random();

		// 文字入力を行うので、Scanner の準備をする
		Scanner stdin = new Scanner(System.in);

		/* ----------------------------------
		 * ※５つの数字で、同じ数字を使ってはいけない
		 * ランダムクラスのみだと毎回0-9の中から決定するので数字が被ってしまうことがある
		 * 使った数字を削除して、残った数字から再度数字を決定できれば数字が重複しない
		 * ---------------------------------- */

		// 重複しないランダムな数字4桁を決める
		// コンピューターが選んだ数字を入れるArrayList answer を用意する
		ArrayList<Integer> answer = new ArrayList<Integer>();

		// 同じ数字を使わないための対策として配列から数字を取り出すようにする
		// 0-9までの数字を入れたArrayList numbers を作成する
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			numbers.add(i);
		}

		// numbers からランダムに数字を５つ取り出して、answers に入れる
		for (int i = 0; i < 5; i++) {
			// numbers から取り出す数字のインデックスをランダムで決める(0〜 numbersの要素数-1)
			int index = random.nextInt(numbers.size());

			// numbers からランダムで決めたインデックスの数字を取り出して変数 num に記憶させる
			int num = numbers.get(index);

			// 取り出した数字を answers に追加する
			answer.add(num);

			// このままだと同じ数字を取り出してしまう可能性があるので
			// numbers から answers に追加した数字を numbers から取り除く
			numbers.remove(index); // numbers から index 番目の要素を取り除く
		}
		System.out.println("numbers = " + numbers);
		System.out.println("answer = " + answer);
		System.out.println();

		// 入力回数を数える変数を用意する
		int count = 1;

		// ヒット数が５になるまでループさせるのでwhile文を使い無限にループをさせる(クリアしたらbreakで抜ける想定)
		while (true) {

			if((count - 1) % 3 == 0){

			int hantei = (count - 1) / 3;

			System.out.println("カウントは" + count);
			System.out.println("ハンテイは" + hantei);
			switch  (hantei) {
			case(1):
			case(2):
			case(3):
			case(4):
				System.out.println("ヒントです。"+ hantei + "つ目の数字は" + answer.get(hantei - 1));
			    break;
			case(5):
				System.out.println("ヒントです。"+ hantei + "つ目の数字は" + answer.get(hantei - 1));
			    System.out.println("もう全部教えたから答えてね");
			    break;
			}
			}
			// ユーザーが５桁の数字を入力する
			// 　「５桁の数字を入力してください。」を表示する
			System.out.println("５桁の数字を入力してください。");

			// 　５桁の数字を入力させる
			// 　「入力された数字は〇〇です」を表示する
			int input = stdin.nextInt();
			System.out.println("入力された数字は" + input + "です");

			// 　入力された数字を user に１桁ずつ格納する
			ArrayList<Integer> user = new ArrayList<Integer>();
			// １つ目の数字(万の位の数字)を user に追加する
			user.add((input / 10000) % 10);
			// ２つ目の数字(千の位の数字)を user に追加する
			user.add((input / 1000) % 10);
			// ３つ目の数字(百の位の数字)を user に追加する
			user.add((input / 100) % 10);
			// ４つ目の数字(十の位の数字)を user に追加する
			user.add((input / 10) % 10);
			// ５つ目の数字(一の位の数字)を user に追加する
			user.add(input % 10);
			System.out.println(user);

			// 同じインデックスの要素同士を比較してヒットの数を数える
			// ヒット数を数えるための変数を用意する
			int hit = 0;
			for (int i = 0; i < 5; i++) {
				if (answer.get(i) == user.get(i)) {
					// 位置も数字もあっている＝ヒット！
					hit++;
				}
			}

			// answer と user の数字を比較してブロー数を数える
			// ブロー数を数えるための変数を用意する
			int blow = 0;
			// userのi番目の数字を answer から探すためのループを作る
			for (int i = 0; i < 5; i++) {
				int user_num = user.get(i); // 入力された数字の i 番目の数字

				// userのi番目の数字が answer の j 番目と比較して同じ数字を探すためのループを作る
				for (int j = 0; j < 5; j++) {
					if (user_num == answer.get(j)) {
						if (i != j) { // 同じ位置の場合はヒットになるので、ヒットの時はブロー数を数えない
							blow++;
						}
					}
				}
			}

			// 正解した(ヒットが5になった)場合にループから抜け出す(breakで抜ける)
			if (hit == 5) {
				break;
			}

			// 正解していなかった場合に「残念！！ヒット:○○ / ブロー:〇〇」を表示する
			System.out.println("残念！！ヒット:" + hit + " / ブロー:" + blow);

			// 入力回数を１増やす
			count++;
		}

		// 「おめでとう！！〇〇回目で当てられちゃった！！」を表示して終了する
		System.out.println("おめでとう！！" + count + "回目で当てられちゃった！！");

		// 終了処理を行う
		stdin.close();
	}
}