package namebatoler;

import java.util.ArrayList;
import java.util.Random;

public class Wizard extends Player {

	// 魔法の種類を格納
	ArrayList<Mazic> maziclist = new ArrayList<Mazic>();

	// 魔法の種類を格納
	public void MakeMazic() {
		maziclist.add(new Mazic("ファイア", 10, "敵に10～30の防御力無視ダメージ"));
		maziclist.add(new Mazic("サンダ", 20, "敵に10～30の防御力無視ダメージ"));

	}

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name) {
		super(name);
		MakeMazic();
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter() {
		// 魔法使いのパラメータを名前から生成する
		this.hp = GetNumber(1, 150) * 5;
		if (this.hp < 50) {
			this.hp = 50;
		}
		this.mp = GetNumber(1, 80);
		if (this.mp < 30) {
			this.mp = 30;
		}

		this.str = GetNumber(1, 50);
		if (this.str < 1) {
			this.str = 1;
		}

		this.def = GetNumber(1, 50);
		if (this.def < 1) {
			this.def = 1;
		}

		this.luk = GetNumber(1, 100);
		if (this.luk < 1) {
			this.luk = 1;
		}

		this.agi = GetNumber(1, 60);
		if (this.agi < 20) {
			this.agi = 20;
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

	/**
	 * 対象プレイヤーに攻撃を行う
	 * 
	 * @param defender
	 *            : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
		// 攻撃種類の選択
		Mazic defusemazic = UseMazic();

		//状態異常の確認
		if (condition == 1) {
			Condition(this);
		} else {

			if (mp > defusemazic.GetMagicMP()) {
				// 与えるダメージを求める
				System.out.println(GetName() + "は" + defusemazic.GetMagicName() + "の魔法を使った！");
				System.out.println("効果は" + defusemazic.GetEffect() + "だ");
				int damage = CalcMazicDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
				this.mp -= defusemazic.GetMagicMP();

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

}