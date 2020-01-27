import java.util.ArrayList;

import equipment.EquipmentFactory;

// プレイヤー：魔法使い
public class Priest extends Player {

	// =======================
	// フィールド変数
	// =======================
Magic[] healMagic= {new Heal()};
Magic[] debuffMagic= {new Paralyze(),new Poison()};

	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name) {
		super(name);
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
		// 戦士のパラメータを名前から生成する
		this.defaultHP = (GetNumber(0, 12) + 8) * 10;
		this.defaultMP = GetNumber(1, 50) + 30;
		this.defaultSTR = (GetNumber(2, 50) + 1);
		this.defaultDEF = GetNumber(3, 50) + 1;
		this.defaultLUCK = GetNumber(4, 100);
		this.defaultAGI = GetNumber(5, 40) + 20;

		this.paralyze = false;
		this.poison = false;
		this.active=true;

		// 装備の生成
		this.mySword = EquipmentFactory.DecideSword(GetNumber(6,EquipmentFactory.CountSword()));
		this.myArmor = EquipmentFactory.DecideArmor(GetNumber(7,EquipmentFactory.CountArmor()));
		this.myShield = EquipmentFactory.DecideShield(GetNumber(8,EquipmentFactory.CountShield()));
		this.myJewelry = EquipmentFactory.DecideJewelry(GetNumber(9,EquipmentFactory.CountJewelry()));

		this.hp = defaultHP;
		this.mp = defaultMP;
		this.str = defaultSTR + mySword.EquippedSTR(defaultSTR);
		this.def = defaultDEF + myArmor.EquippedDEF(defaultDEF) +myShield.EquippedDEF(defaultDEF);
		this.luck = defaultLUCK;
		this.agi = defaultAGI + myJewelry.EquippedAGI(defaultAGI);


	}


	/**
	 * {@inheritDoc}<br>
	 */
	@Override
	public void Attack(Player activePlayer, ArrayList<Player> passiveMembers) {

		Player passivePlayer = passiveMembers.get(RandomGenerator.RandomRange(0, passiveMembers.size()));

		// 与えるダメージを求める
		System.out.println(activePlayer.GetName() + "　の　攻撃！");
		int damage = activePlayer.CalcDamage(passivePlayer);

		// 求めたダメージを対象プレイヤーに与える
		System.out.println(passivePlayer.GetName() + "　に　" + damage + "　のダメージ！");
		passivePlayer.Damage(damage);

		passivePlayer.Down();

	}

	/**
	 * {@inheritDoc}<br>
	 */
	@Override
	public boolean Heal(Player activePlayer, ArrayList<Player> passiveMembers) {

		Player passivePlayer = passiveMembers.get(0);
		//使用する魔法を決定する
		Magic UseMagic = this.healMagic[RandomGenerator.RandomRange(0,healMagic.length)];

		if (UseMagic.GetUseMP() > this.mp){

			return false;
		}

		UseMagic.effect(activePlayer, passivePlayer);

		return true;
	}

	/**
	 * {@inheritDoc}<br>
	 */

	public boolean Debuff(Player activePlayer, Player passivePlayer) {

		if (!passivePlayer.GetParalyze()) {
			if (this.debuffMagic[0].GetUseMP() > this.mp) {
				return false;
			}
			this.debuffMagic[0].effect(activePlayer, passivePlayer);
			return true;
		}

		if (!passivePlayer.GetParalyze()) {
			if (this.debuffMagic[1].GetUseMP() > this.mp) {
				return false;
			}
			this.debuffMagic[1].effect(activePlayer, passivePlayer);
			return true;
		}

		return false;
	}



	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================

}