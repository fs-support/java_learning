/**
 *
 */
/**
 * @author noinoi
 *
 */
package mainichi;
import java.util.Random;

public class Main{
	public static void main(String[] args) {
		final int MAX_LEVEL = 5;
		int[] expTable = {0,100,250,600,1000};

		int level =1;
		int exp = 0;

		Random random = new Random();

		while(level < MAX_LEVEL) {
			System.out.println("レベル：" + level + "/exp:" + exp);

			int getExp = random.nextInt(99)+1;

			System.out.println(getExp + "の経験値を得た！");

			exp += getExp;
			if(exp >= expTable[level]) {
				System.out.println("レベルアップ！レベル" + level + ">" + (level+1));
				level++;
			}
		}
		System.out.println("レベルはもう上がらない！");
	}
}