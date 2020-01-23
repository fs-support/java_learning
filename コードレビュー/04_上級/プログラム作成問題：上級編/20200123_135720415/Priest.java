package jp.co.FSsakusei3;

import java.util.ArrayList;
import java.util.Random;

public class Priest extends Player {

	private static ArrayList<Magic> magicList = new ArrayList<Magic>();
	private static ArrayList<Magic> magicList2 = new ArrayList<Magic>();
	private static Random r = new Random();

	public Priest(String name) {
		super(name);
	}

	protected void MakeCharacter() {

		this.hp = GetNumber(0, 80, 200);
		this.mp = GetNumber(1, 20, 50);
		this.str = GetNumber(2, 10, 70);
		this.def = GetNumber(3, 10, 70);
		this.luck = GetNumber(4, 1, 100);
		this.agi = GetNumber(5, 20, 60);
		this.state ="健康";
		this.maxhp = hp;

		magicList.add(new Magic("ヒール", 20, 50));
		magicList.add(new Magic("ヒール2", 30, 50));
		magicList2.add(new Magic("パライズ", 10, "麻痺"));
		magicList2.add(new Magic("ポイズン", 10, "毒"));


	}

	public void Attack(Player defender) {

		int damage = 0;
		int heel = 0;
		int max = 0;
		int min = 10000;
		int min2 = 10000;
		int magictype = 0;
		System.out.println(GetName() + "の攻撃！");

		for (int i = 0; i < magicList.size(); i++) {
			max = Math.max(max, magicList.get(i).mpcost());
			min = Math.min(min, magicList.get(i).mpcost());
		}

		for (int i = 0; i < magicList2.size(); i++) {
			min2 = Math.min(min, magicList2.get(i).mpcost());
		}

		if ((hp < maxhp) && (mp >= min)) {
			magictype = 2;
			int i = r.nextInt(magicList.size());
			while (magicList.get(i).mpcost() > mp) {
				i = r.nextInt(magicList.size());
			}
			System.out.println(magicList.get(i).Name() + "の魔法");
			mp = mp - magicList.get(i).mpcost;
			heel = magicList.get(i).heelhp;
			hp = hp + magicList.get(i).heelhp;
			if(hp>maxhp){
				hp=maxhp;
			}
		} else if (mp >= min2) {
			magictype = r.nextInt(2);
			if (magictype == 1) {
				int i = r.nextInt(magicList2.size());
				while (magicList2.get(i).mpcost() > mp) {
					i = r.nextInt(magicList2.size());
				}
				System.out.println(magicList2.get(i).Name() + "の魔法");
				defender.State(magicList2.get(i).state());
			} else {
				magictype = 0;
				System.out.println("通常攻撃");
				damage = CalcDamage(defender);
			}

		} else {
			magictype = 0;
			System.out.println("mp切れだ");
			damage = CalcDamage(defender);
		}

		if (magictype == 2) {
			System.out.println(name + "のHPが" + heel + "回復");
		}else if(magictype == 1) {
			System.out.println(defender.GetName()+"に状態異常:"+defender.GetSTATE());
		} else {
			System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			defender.Damage(damage);
		}

		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}

}
