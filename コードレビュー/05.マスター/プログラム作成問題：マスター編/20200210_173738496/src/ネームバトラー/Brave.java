package ネームバトラー;

public class Brave extends Player {

	// =======================
	// フィールド変数
	// =======================


	// =======================
	// コンストラクタ
	// =======================
	public Brave(String name)
	{
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
	protected void MakeCharacter()
	{
		// 戦士のパラメータを名前から生成する
		this.hp = GetNumber(0, 200)+100;
		this.mp= GetNumber(0,40);
		this.str = GetNumber(0, 70)+40;
		this.def = GetNumber(0, 70)+30;
		this.luck=GetNumber(0,100);
		this.agi=GetNumber(0,50);
	}
	public void Action1(Player attacker,Party enemy) {
		if (aaction(attacker)) {
			attacker.party.GetStrategy().Action(attacker, enemy);
		}
	}
	
	public boolean goodAisyou(Player target) {
		if(target instanceof Fighter) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean badAisyou(Player target) {
		if(target instanceof Priest) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender)
	{
     // 与えるダメージを求める
     System.out.println(GetName() + "の攻撃！");
     int damage = CalcDamage(defender);

     // 求めたダメージを対象プレイヤーに与える
     System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
     defender.Damage(damage);

     // 倒れた判定
     if (defender.GetHP() <= 0) {
         System.out.println(defender.GetName() + "は力尽きた...");
     }
     this.str+=5;
     System.out.println(GetName()+"の攻撃力が上がった");
	}
	public void Magic(Player defender) {
		
	}


	// =======================
	// private メソッド
	// =======================


	// =======================
	// public メソッド
	// =======================
}
