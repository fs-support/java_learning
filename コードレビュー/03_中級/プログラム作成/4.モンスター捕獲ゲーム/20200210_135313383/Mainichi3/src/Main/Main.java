/**
 *
 */
/**
 * @author noinoi
 *
 */
package Main;

public class Main{
	public static void main(String[] args){
		for(int count = 1;count <= 40;count++) {

			if(ahoDesu(count) == true) {
				System.out.print("アホな感じで"); /*printlnだと改行されてまう*/
			}
			System.out.println(count);
		}
		System.out.println("オモロー！！");
	}
	public static boolean ahoDesu(int count) {
		if((count % 3) == 0) {
			return true;
		}
		String str = String.valueOf(count);
		return str.contains("3");
	}
}