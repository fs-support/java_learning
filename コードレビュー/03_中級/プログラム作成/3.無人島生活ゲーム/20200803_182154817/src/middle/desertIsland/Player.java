package middle.desertIsland;

import java.util.ArrayList;

public class Player {
	private int HP = 50;
	private int maxHP = 100;
	private int hintNum = 3;
	ArrayList<String> dicisions = new ArrayList<String>();

	public void checkFood(EatItem item) {
		System.out.println("名前 : " + item.Name() + " 危険度 : " + item.Danger() +
				" 回復量 : " + item.HeelHP());
	}

	public void select(EatItem item, ItemManager manager, int day) {
		System.out.println("食べる：Y , 食べない : N , ヒント（残り" + hintNum + "回) : H");
		while (true) {
			String stdin = new java.util.Scanner(System.in).next();
			if (stdin.equals("Y") || stdin.equals("y")) {
				dicisions.add("食べた");
				eatItem(item);
				break;
			} else if (stdin.equals("N") || stdin.equals("n")) {
				dicisions.add("食べなかった");
				notEatItem(item);
				break;
			} else if (stdin.equals("H") || stdin.equals("h")) {
				if (hintNum <= 0) {
					System.out.println("ヒントはもう使えません！");
					continue;
				}
				manager.displayHint(day);
				hintNum--;
			} else {
				System.out.println("Y, N, Hキーで操作してください");
			}
		}
	}

	public void eatItem(EatItem item) {
		System.out.println("「" + item.Name() + "」" + "を食べた。");
		if (item.judgement()) { //成功
			HP += item.HeelHP();
			if (HP > maxHP) {
				HP = maxHP;
			}
			System.out.println("体力回復！！（HP : " + HP + ")");
		} else { //失敗
			HP = 0;
			System.out.println("死亡・・・");
		}
	}

	public void notEatItem(EatItem item) {
		System.out.println("「" + item.Name() + "」" + "を食べなかった");
		HP -= 10;
		System.out.println("体力減少・・・（HP : " + HP + ")");
		if (HP <= 0) {
			System.out.println("死亡・・・");
		}
	}

	public int getHP() {
		return this.HP;
	}
}
