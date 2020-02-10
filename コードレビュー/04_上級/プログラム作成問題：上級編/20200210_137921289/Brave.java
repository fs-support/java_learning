package namebatoler;

import java.util.ArrayList;
import java.util.Random;

public class Brave extends Player {

	//HPの初期値を格納
	int ParaHP = 0;

	// 魔法の種類を格納
	ArrayList<Mazic> maziclist = new ArrayList<Mazic>();
	ArrayList<Mazic> heellist = new ArrayList<Mazic>();

	// 魔法の種類を格納
	public void MakeMazic() {
		maziclist.add(new Mazic("ファイアアロー", 20, "敵に10～30の防御力無視ダメージ"));
		maziclist.add(new Mazic("サンダアロー", 30, "敵に10～30の防御力無視ダメージ"));
		heellist.add(new Mazic("ハイヒール", 15, "HPを60回復"));

	}

	// =======================
	// コンストラクタ
	// =======================
	public Brave(String name) {
		super(name);
		MakeMazic();
		ParaHP = this.hp;
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter() {

		this.hp = GetNumber(1, 300) * 5;
		if (this.hp < 100) {
			this.hp = 100;
		}
		this.mp = GetNumber(1, 100);
		if (this.mp < 30) {
			this.mp = 30;
		}

		this.str = GetNumber(1, 150);
		if (this.str < 50) {
			this.str = 50;
		}

		this.def = GetNumber(1, 100);
		if (this.def < 40) {
			this.def = 40;
		}

		this.luk = GetNumber(1, 100);
		if (this.luk < 10) {
			this.luk = 10;
		}

		this.agi = GetNumber(1, 100);
		if (this.agi < 30) {
			this.agi = 30;
		}

		this.condition = 0;

	}

	//使用する魔法を選ぶ
	public Mazic UseMazic() {
		Random userand = new Random();
		int listno = userand.nextInt(maziclist.size());
		Mazic usemazic = maziclist.get(listno);

		return usemazic;
	}

	public void Attack(Player defender) {
		//魔法を使うどうか乱数を用いて決める
		Random use = new Random();
		int maziuse = use.nextInt(10);

		// 攻撃種類の選択
		Mazic defusemazic = UseMazic();

		//状態異常の確認
		if (condition == 1) {
			Condition(this);
		} else {

			if (mp > 0 && maziuse < 3) {
				// 与えるダメージを求める
				System.out.println(GetName() + "は" + defusemazic.GetMagicName() + "の魔法を使った！");
				System.out.println("効果は" + defusemazic.GetEffect() + "だ");
				int damage = CalcMazicDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
				this.mp -= defusemazic.GetMagicMP();

			} else if (mp > 0 && this.hp <= ParaHP / 2) {
				//体力が半分になったらHPを回復
				System.out.println(GetName() + "は" + heellist.get(0).GetMagicName() + "を使った！");
				System.out.println("効果は" + heellist.get(0).GetEffect() + "だ！");
				UseHeel();

			} else {

				// 与えるダメージを求める
				System.out.println(GetName() + "の通常攻撃！");
				int damage = CalcDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
			}

		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}

	public void UseHeel() {
		this.hp += 60;
		this.mp -= heellist.get(0).GetMagicMP();
	}

}