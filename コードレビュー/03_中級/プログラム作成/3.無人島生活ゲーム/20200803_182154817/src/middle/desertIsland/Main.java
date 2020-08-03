package middle.desertIsland;

public class Main {

	public static void main(String[] args) {
		System.out.println("無人島生活ゲーム　スタート!!\n30日間生き残れ！！");
		Player player = new Player();
		ItemManager manager = new ItemManager();
		EatItem item;

		int endDay = 30;	//生存必用日数
		for (int i = 0; i < endDay; i++) {
			System.out.println("\n【" + (i + 1) + "日目】");
			item = manager.items.get(i);
			player.checkFood(item);
			player.select(item, manager, i);
			if (player.getHP() <= 0) {
				manager.displayLog(i + 1, player.dicisions);
				return;
			}
		}
		System.out.println("\nゲームクリア！！　生き残った！！");
		manager.displayLog(endDay, player.dicisions);
		return;
	}

}
