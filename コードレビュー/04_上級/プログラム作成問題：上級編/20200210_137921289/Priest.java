package namebatoler;

import java.util.ArrayList;
import java.util.Random;

public class Priest extends Player {

	// 魔法の種類を格納
	ArrayList<Mazic> maziclist = new ArrayList<Mazic>();
	ArrayList<Mazic> heellist = new ArrayList<Mazic>();

	// 魔法の種類を格納
	public void MakeMazic() {
		heellist.add(new Mazic("ヒール", 20, "HPを50回復"));
		maziclist.add(new Mazic("パラライズ", 10, "相手に確率で麻痺で行動不能"));
		maziclist.add(new Mazic("ポイズン", 10, "毎ターン20の継続ダメージを与える"));

	}

	// HPの初期値
	int ParaHP = 0;

	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name) {
		super(name);
		MakeMazic();
		ParaHP = this.hp;
	}

	// =======================
	// Getter / Setter
	// =======================

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter() {
		// 僧侶のパラメータを名前から生成する
		this.hp = GetNumber(2, 200) * 5;
		if (this.hp < 80) {
			this.hp = 80;
		}

		this.mp = GetNumber(2, 50);
		if (this.mp < 20) {
			this.mp = 20;
		}

		this.str = GetNumber(2, 70);
		if (this.str < 10) {
			this.str = 10;
		}

		this.def = GetNumber(2, 70);
		if (this.def < 10) {
			this.def = 10;
		}

		this.luk = GetNumber(2, 100);
		if (this.luk < 1) {
			this.luk = 1;
		}

		this.agi = GetNumber(2, 60);
		if (this.agi < 20) {
			this.agi = 20;
		}

		this.condition = 0;
	}

	//毒状態か否かの情報を格納
	boolean poizun = false;

	public void Attack(Player defender) {
		//魔法を使うどうか乱数を用いて決める
		Random use = new Random();
		int maziuse = use.nextInt(10);
		int paraizusucces = use.nextInt(100);

		//状態異常の確認
		if (condition == 1) {
			Condition(this);
		} else {

			if (this.mp > 0 && this.hp <= ParaHP / 2) {
				// 体力が半分になったらHPを回復
				System.out.println(GetName() + "は" + heellist.get(0).GetMagicName()
						+ "を使った！");
				System.out.println("効果は" + heellist.get(0).GetEffect() + "だ！");
				this.hp += 50;
				this.mp -= heellist.get(0).GetMagicMP();
				if (poizun == true) {
					Poizun(defender);
				}

			} else if (this.mp > 0 && poizun == false && maziuse < 3) {
				System.out.println(GetName() + "は"
						+ maziclist.get(1).GetMagicName() + "を使った！");
				this.mp -= maziclist.get(1).GetMagicMP();
				Poizun(defender);
				poizun = true;
			} else if (this.mp > 0 && maziuse < 3) {
				System.out.println(GetName() + "は" + maziclist.get(0).GetMagicName() + "を使った！");
				this.mp -= maziclist.get(1).GetMagicMP();
				if (paraizusucces < 20) {
					System.out.println(defender.GetName() + "は麻痺した！（１ターン行動不能）");
					defender.condition = 1;
				} else {
					System.out.println("レジストされた！");
				}
			} else {
				// 与えるダメージを求める
				System.out.println(GetName() + "の攻撃！");
				int damage = CalcDamage(defender);

				// 求めたダメージを対象プレイヤーに与える
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
				defender.Damage(damage);
				if (poizun == true) {
					Poizun(defender);
				}
			}

		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}

	public void Poizun(Player defender) {
		defender.hp -= 20;
		System.out.println(defender.GetName() + "は毒を受けている（HP－20）");

	}


}