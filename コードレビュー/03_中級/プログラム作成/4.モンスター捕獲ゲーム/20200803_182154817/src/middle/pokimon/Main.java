package middle.pokimon;

public class Main {

	public static void main(String[] args) {
		System.out.println("-----モンスター捕獲ゲーム　スタート！！-----");
		MonsterManage.resetMonsters();
		for (int i = 0; i < 10; i++) {
			MonsterManage.displayMonsterInfo(i);
			Monster m = MonsterManage.getMonster(i);
			Player.displaySelecter(m);
			if (Player.selectAction(m) == false) {
				break;
			}
		}
		Player.endGame();
	}

}
