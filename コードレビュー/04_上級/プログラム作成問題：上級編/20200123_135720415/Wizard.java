package jp.co.FSsakusei3;

import java.util.ArrayList;
import java.util.Random;

public class Wizard extends Player {

	private static ArrayList<Magic> magicList = new ArrayList<Magic>();
	private static Random r = new Random();

	public Wizard(String name) {
		super(name);
	}

	protected void MakeCharacter() {

		this.hp = GetNumber(0, 50, 150);
		this.mp = GetNumber(1, 30, 80);
		this.str = GetNumber(2, 1, 50);
		this.def = GetNumber(3, 1, 50);
		this.luck = GetNumber(4, 1, 100);
		this.agi = GetNumber(5, 20, 60);
		this.state ="健康";
		this.maxhp = hp;

		magicList.add(new Magic("ファイア", 10, 30, 10));
		magicList.add(new Magic("サンダー", 20, 30, 10));
		magicList.add(new Magic("アイス", 10, 30, 10));
	}

	public void Attack(Player defender) {

		int damage=0;
		int max = 0;
		int min = 10000;
		System.out.println(GetName() + "の攻撃！");

		for (int i = 0; i < magicList.size(); i++) {
			max = Math.max(max, magicList.get(i).mpcost());
			min = Math.min(min, magicList.get(i).mpcost());
		}
		// mpが潤沢にある
		if (mp >= max) {
			int magicid = r.nextInt(magicList.size());
			System.out.println(magicList.get(magicid).Name() + "の魔法");
			mp=mp-magicList.get(magicid).mpcost;
			int a=r.nextInt((magicList.get(magicid).maxdamage()- magicList.get(magicid).mindamage()) + 1);
			damage=a+magicList.get(magicid).mindamage();
			//下の式ではmindamage以下の数値が出力された
			//damage = (r.nextInt((magicList.get(magicid).maxdamage()
			//		- magicList.get(magicid).mindamage() + 1)
			//		+ magicList.get(magicid).mindamage()));

			// mpが微妙に残っている
		} else if ((mp < max) && (mp >= min)) {
			int i = r.nextInt(magicList.size());
			while (magicList.get(i).mpcost() > mp) {
				//System.out.println("テスト用再抽選");
				i = r.nextInt(magicList.size());
			}
			System.out.println(magicList.get(i).Name() + "の魔法");
			int a=r.nextInt((magicList.get(i).maxdamage()- magicList.get(i).mindamage()) + 1);
			damage=a+magicList.get(i).mindamage();
			mp=mp-magicList.get(i).mpcost;
			// mpが足りない
		} else {
			System.out.println("mp切れだ");
			damage = CalcDamage(defender);
		}

		System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
		defender.Damage(damage);

		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}

	}

}
