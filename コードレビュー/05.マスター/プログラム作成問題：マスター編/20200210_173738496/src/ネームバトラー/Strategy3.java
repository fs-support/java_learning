package ƒl[ƒ€ƒoƒgƒ‰[;

import java.util.ArrayList;
import java.util.List;

// –hŒä@–hŒä‚ª’á‚¢“G‚ğ—Dæ
public class Strategy3 extends Strategy{
	public Strategy3(String name) {
		super(name);
	}

	public void Action(Player attacker, Party defence) {
		List<Player> enemy = new ArrayList<Player>();
		int enumber=0;
		for (int i = 0; i < defence.size(); i++) {
			enemy.add(defence.get(i));
		}

		// ƒ\[ƒg ‚ª@–hŒä‚ª’á‚¢‡‚É•À‚Ñ•Ï‚¦
		for (int i = 0; i < enemy.size(); i++) {
			for (int j = 0; j < enemy.size() - i - 1; j++) {
				if (enemy.get(j).GetDEF() > enemy.get(j + 1).GetDEF()) {
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
