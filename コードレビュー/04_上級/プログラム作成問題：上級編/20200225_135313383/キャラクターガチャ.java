import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		Random random = new Random();

		while (true) {
			int count = -1; /*0にしていると、最初からtrue条件を満たしてすべてが終わってしまう…*/

			/*範囲外の数字を入れた場合などの処理*/
			while(count < 0||count > 10) {
				System.out.println("何回引きますか？：");


				String input;
				input = stdin.nextLine();

				/*文字が入力されたら数字を入力するように伝える、10回より多かったら10回までにするように伝える*/
				try {
					count = Integer.parseInt(input);

					if (count > 10) {
						System.out.println("回数が多すぎます！10回にしてください");

					} else {

						System.out.println("キャラガチャを" + count + "回引きます。");
					}
				} catch (NumberFormatException e) {
					System.out.println("数字を入力してください！");
				}
			}

			/*入力された回数分ガチャを引く！*/
			for (int i = 1; i <= count; i++) {
				int luck = random.nextInt(100);
				if (luck < 3) {
					System.out.println(i + "体目：SSRキャラ");
				} else if (luck < 3 + 12) {
					System.out.println(i + "体目：SRキャラ");
				} else {
					System.out.println(i + "体目：Rキャラ");
				}
			}
			break;
		}
		stdin.close();
	}
}