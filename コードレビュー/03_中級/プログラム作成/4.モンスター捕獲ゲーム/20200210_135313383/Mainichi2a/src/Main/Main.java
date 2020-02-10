/**
 *
 */
/**
 * @author noinoi
 *
 */
package Main;

import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		final int MAX_LEVEL = 5;
		int[] expTable = {0,100,250,600,1000,99999};
		Scanner sc = new Scanner(System.in);

		int level = 1;
		int exp = 0;

		while(level < MAX_LEVEL) {
			System.out.println("レベル：" + level + "/exp" + exp);
			System.out.println("経験値を入力してください：");

			int getExp = sc.nextInt();
			System.out.println(getExp + "の経験値を得た！");
			exp += getExp;

			if(exp >= expTable[level]) {
				System.out.println("レベルアップ！レベル：" + level + ">" + (level+1));
				level++;
			}
		}
		System.out.println("レベルはもう上がらない！");
		sc.close();
	}
}