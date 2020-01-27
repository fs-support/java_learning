import java.util.ArrayList;

import equipment.EquipmentFactory;

// プレイヤー：勇者
public class Hero extends Player {

	// =======================
	// フィールド変数
	// =======================
	Magic[] healMagic= {new Heal()};
	// =======================
	// コンストラクタ
	// =======================
	public Hero(String name) {
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
		this.defaultHP =(GetNumber(0, 30) + 10) * 10;
		this.defaultMP = GetNumber(1, 30) + 20;
		this.defaultSTR = GetNumber(2, 70) + 30;
		this.defaultDEF = GetNumber(3, 70) + 30;
		this.defaultLUCK = GetNumber(4, 100) + 1;
		this.defaultAGI = GetNumber(5, 50);
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
	 * 自身のHPが減っている場合は 回復魔法 を使用し、
	 * 自身のHPが減っていない場合は 対象プレイヤー に攻撃を行う
	 * @param attacker {@inheritDoc}
	 * @param defender {@inheritDoc}
	 */
	@Override
	public void Attack(Player activePlayer,ArrayList<Player> passiveMembers) {

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


}
