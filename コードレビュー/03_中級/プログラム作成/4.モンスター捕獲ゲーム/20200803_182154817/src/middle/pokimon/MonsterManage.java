package middle.pokimon;

import java.util.ArrayList;
import java.util.Random;

public class MonsterManage {
	static ArrayList<Monster> monsters = new ArrayList<Monster>();

	/**
	 * Monster getter
	 * @param i index
	 * @return Monster クラス 
	 */
	public static Monster getMonster(int i) {
		return monsters.get(i);
	}

	/**
	 * １０匹のモンスターを生成
	 */
	public static void resetMonsters() {
		monsters.clear();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int kakuritu = rnd.nextInt(100) + 1; //1~100
			Monster monster = new Monster(MonsterKind.getEncount(kakuritu));
			monsters.add(monster);
		}
	}

	/**
	 * モンスター出現アナウンス
	 * @param i index
	 */
	public static void displayMonsterInfo(int i) {
		Monster m = monsters.get(i);
		System.out.println("\n" + m.Name() + "(HP:" + m.HP() + " 攻撃:" + m.Power() +
				" 防御:" + m.Defense() + ")が現れた!");
	}

}
