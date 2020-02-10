package ネームバトラー;

// 一番HPの低い敵を優先
import java.util.*;

public class Strategy2 extends Strategy {
	public Strategy2(String name) {
		super(name);
	}

	public void Action(Player attacker, Party defence) {
		List<Player> enemy = new ArrayList<Player>();
		int enumber=0;
		for (int i = 0; i < defence.size(); i++) {
			enemy.add(defence.get(i));
		}

		// ソート HPが低い順に並び変え
		for (int i = 0; i < enemy.size(); i++) {
			for (int j = 0; j < enemy.size() - i - 1; j++) {
				if (enemy.get(j).GetHP() > enemy.get(j + 1).GetHP()) {
					Player player = enemy.get(j);
					enemy.set(j, enemy.get(j + 1));
					enemy.set(j + 1, player);
				}
			}
		}
		for(int i=0;i<enemy.size();i++) {
			if(enemy.get(i).GetHP()>0) {
				enumber=i;
				break;
			}
		}
		AAaction(attacker,enemy.get(enumber));
	}
}
