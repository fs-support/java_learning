package middle.desertIsland;

import java.util.ArrayList;
import java.util.Random;

public class ItemManager {
	ArrayList<EatItem> items = new ArrayList<EatItem>();
	Random rnd = new Random();

	ItemManager() {
		for (int i = 0; i < 30; i++) {
			int foodNumber = rnd.nextInt(10) + 1;
			EatItem item = new EatItem(Food.valueOf("FOOD" + String.valueOf(foodNumber)));
			items.add(item);

		}
	}

	//ゲーム終了後のログ表示
	void displayLog(int days, ArrayList<String> dicisions) {
		for (int i = 0; i < days; i++) {
			int day = i + 1;
			String dicision = dicisions.get(i);
			EatItem item = items.get(i);
			System.out.println(day + "日目 " + item.Name() + " 危険度 : " + item.Danger() +
					" 回復量 : " + item.HeelHP() + " " + dicision);
		}
	}

	void displayHint(int i) {
		EatItem item = items.get(i + 1);

		System.out.println("明日のヒント " + item.Name() + " 危険度 : " + item.Danger() +
				" 回復量 : " + item.HeelHP());
	}
}
