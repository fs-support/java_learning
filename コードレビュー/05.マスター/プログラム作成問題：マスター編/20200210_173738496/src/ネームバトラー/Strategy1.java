package ネームバトラー;

//　ランダムに攻撃
import java.util.*;

public class Strategy1 extends Strategy {
	public Strategy1(String name) {
		super(name);
	}

	public void Action(Player attacker, Party defence) {
		Aaction(attacker,defence);
//		Random ra = new Random();
//		int random = 0;
//		for (int i = 0; i < defence.size(); i++) {
//			random = ra.nextInt(defence.size());
//			if (defence.get(random).GetHP() > 0) {
//				break;
//			}
//		}
//		attacker.Attack(defence.get(random));
	}
}
