import java.util.ArrayList;

import equipment.EquipmentFactory;

// プレイヤー：魔法使い
public class Wizard extends Player {

	// =======================
	// フィールド変数
	// =======================
	Magic[] attackMagic={new Fire(),new Thunder()};
	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name) {
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
		// 魔術師のパラメータを名前から生成する
		this.defaultHP =(GetNumber(0, 10) + 5) * 10;
		this.defaultMP = GetNumber(1, 50) + 30;
		this.defaultSTR = GetNumber(2, 50) + 1;
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

	// ↑を修正して魔法使い用のパラメーターに直す
	// ↓を修正して通常攻撃か魔法攻撃を行うようにする
	/**
	 * {@inheritDoc}<br>
	 * 攻撃側プレイヤー(atacker)のMPが魔法の消費MPに足りている場合は
	 * @param attacker {@inheritDoc}
	 * @param defender {@inheritDoc}
	 */
	@Override
	public void Attack(Player activePlayer,ArrayList<Player> passiveMembers) {

		Player passivePlayer = passiveMembers.get(RandomGenerator.RandomRange(0, passiveMembers.size()));
		//使用する魔法を決定する
		Magic UseMagic = this.attackMagic[RandomGenerator.RandomRange(0,attackMagic.length)];


		if (UseMagic.GetUseMP() <=this.mp) {

			UseMagic.effect(activePlayer,passivePlayer);
			return;

		}
		// 与えるダメージを求める
		System.out.println(activePlayer.GetName() + "　の　攻撃！");
		int damage = activePlayer.CalcDamage(passivePlayer);

		// 求めたダメージを対象プレイヤーに与える
		System.out.println(passivePlayer.GetName() + "　に　" + damage + "　のダメージ！");
		passivePlayer.Damage(damage);

		passivePlayer.Down();

	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================

}