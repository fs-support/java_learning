/**
 *
 */
/**
 * @author noinoi
 *
 */
package Main;

public class Main{
	public static void main(String[] args) {
		for(int count = 1;count <= 40;count++) {

			if(ahoDesu(count) == true) {
				System.out.print("アホな感じで");
			}
			if(syoukiDesu(count) == true){ /*else ifと間違えやすいので}ifにしないようにする*/
				System.out.print("正気に戻って");
			}
			System.out.println(count);
		}
		System.out.println("オモロー！！");
	}

	public static boolean ahoDesu(int count) {
		if((count % 3) == 0 && (count % 9) != 0) {
			return true;
		}
		String str = String.valueOf(count);
		return str.contains("3");
	}

	public static boolean syoukiDesu(int count) {
		if((count % 3) ==0 && (count % 9) == 0) {
			return true;
		}
		return false; /*3を含むかのチェックのためにあったので上と同じ記述は不要！*/
	}
}