import java.util.ArrayList;

import equipment.EquipmentFactory;

// プレイヤー：戦士
public class Fighter extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Fighter(String name) {
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
		this.defaultHP = (GetNumber(0, 30) + 10) * 10;
		this.defaultMP = GetNumber(1, 100) * 0;
		this.defaultSTR = GetNumber(2, 100) + 30;
		this.defaultDEF = GetNumber(3, 100) + 30;
		this.defaultLUCK = GetNumber(4, 100) + 1;
		this.defaultAGI = GetNumber(5, 50) + 1;
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


	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================

	/**
	 * {@inheritDoc}<br>
	 * @param attacker {@inheritDoc}
	 * @param defender {@inheritDoc}
	 */
	@Override
	public void Attack(Player attacker,ArrayList<Player> passiveParty) {

		Player passivePlayer = passiveParty.get(RandomGenerator.RandomRange(0, passiveParty.size()));
		// 与えるダメージを求める
		System.out.println(attacker.GetName() + "の攻撃！");
		int damage = attacker.CalcDamage(passivePlayer);

		// 求めたダメージを対象プレイヤーに与える
		System.out.println(passivePlayer.GetName() + "に" + damage + "のダメージ！");
		passivePlayer.Damage(damage);

		passivePlayer.Down();
}

}