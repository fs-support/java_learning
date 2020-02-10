package ネームバトラー;

import java.util.*;

public class Strategy {
	String name;

	Strategy(String name) {
		this.name = name;
	}

	public void Action(Player attacker, Party defence) {

	}

	// ↓ 改善した 狙う対象が決まっていないとき
	protected void Aaction(Player attacker, Party defence) {
		Random random = new Random();
		int ra = 0, ran = 0;
		boolean boo = false;
		while (true) {
			ra = random.nextInt(defence.size());
			if (defence.get(ra).GetHP() > 0) {
				break;
			}
		}
		if (attacker.magicList.size() > 0) {
			ran = random.nextInt(100);
			// 50%の確率で魔法を使う
			if (ran <= 50) {
				for (int i = 0; i < attacker.magicList.size(); i++) {
					// 魔法リストの中に今のＭＰで使える魔法があるなら使う
					if (attacker.GetMP() >= attacker.magicList.get(i).GetUseMP()) {
						boo = true;
					}
				}
			}

		}
		if (boo) {
			attacker.Magic(attacker, defence.get(ra));
		} else {
			attacker.Attack(defence.get(ra));
		}
	}

	// あ 狙う対象が決まっているとき
	protected void AAaction(Player attacker, Player defence) {
		// 魔法の時true 攻撃の時false
		boolean boo = false;
		if (attacker.magicList.size() > 0) {
			Random random = new Random();
			int ra = random.nextInt(100);
			// 50%の確率で魔法を使う(MPがあれば)
			if (ra <= 50) {
				for (int j = 0; j < attacker.magicList.size(); j++) {
					if (attacker.GetMP() >= attacker.magicList.get(j).GetUseMP()) {
						ra = 1;
					}
				}
				if (ra == 1) {
					boo = true;
				}
			}
		}
		if (boo) {
			attacker.Magic(attacker, defence);
		} else {
			attacker.Attack(defence);
		}
	}
}
