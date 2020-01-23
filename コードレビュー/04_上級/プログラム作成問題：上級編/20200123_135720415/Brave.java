package jp.co.FSsakusei3;

import java.util.ArrayList;
import java.util.Random;

public class Brave extends Player {

	private static ArrayList<Magic> magicList = new ArrayList<Magic>();
	private static Random r = new Random();

	public Brave(String name) {
		super(name);
	}

	protected void MakeCharacter() {
		this.hp = GetNumber(0, 100, 250);
		this.mp = GetNumber(1, 20, 40);
		this.str = GetNumber(2, 25, 85);
		this.def = GetNumber(3, 30, 80);
		this.luck = GetNumber(4, 1, 100);
		this.agi = GetNumber(5, 1, 60);
		this.state ="健康";
		this.maxhp = hp;

		magicList.add(new Magic("ヒール", 20, 50));

	}

	public void Attack(Player defender) {

		int damage = 0;
		int heel = 0;
		int max = 0;
		int min = 10000;
		int magictype = 0;
		System.out.println(GetName() + "の攻撃！");

		for (int i = 0; i < magicList.size(); i++) {
			max = Math.max(max, magicList.get(i).mpcost());
			min = Math.min(min, magicList.get(i).mpcost());
		}

		System.out.println(maxhp+">"+hp+" "+mp+">="+min);
		if ((hp < maxhp) && (mp >= min)) {
			magictype = r.nextInt(2);
			System.out.println(magictype);
			if (magictype == 1) {
				int i = r.nextInt(magicList.size());
				while (magicList.get(i).mpcost() > mp) {
					i = r.nextInt(magicList.size());
				}
				System.out.println(magicList.get(i).Name() + "の魔法");
				mp = mp - magicList.get(i).mpcost;
				heel = magicList.get(i).heelhp;
				hp = hp + magicList.get(i).heelhp;
				if (hp > maxhp) {
					hp = maxhp;
				}
			} else {
				magictype = 0;
				System.out.println("通常攻撃(テスト)");
				damage = CalcDamage(defender);
			}
		} else {
			magictype = 0;
			System.out.println("通常攻撃");
			damage = CalcDamage(defender);
		}

		if (magictype == 2) {
			System.out.println(name + "のHPが" + heel + "回復");
		} else {
			System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			defender.Damage(damage);
		}

		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}

}
