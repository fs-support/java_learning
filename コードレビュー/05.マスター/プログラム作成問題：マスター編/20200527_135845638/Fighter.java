package fs05_Master_Refactoring;

// 【戦士クラス】

public class Fighter extends Player {

	// コンストラクタ
	public Fighter(String name){
		super(name);
	}

	// 名前(name)からキャラクターに必要なパラメータを生成する
	@Override
	protected void MakeCharacter()
	{
		// 戦士のパラメータを名前から生成する
		// 各パラメータのフィールドはplayerクラスから継承している
		// GetNumber()メソッドの第２引数は「最大値-最小値」の値にする、その後にその値をプラスして底上げ
		this.hp = GetNumber(0, 200)+100;
		this.str = GetNumber(0, 70)+30;
		this.def = GetNumber(0, 70)+30;
		this.luck = GetNumber(0, 99)+1;
		this.agi = GetNumber(0, 49)+1;
		this.mp = 0;
	}

	// 対象プレイヤーに攻撃を行う
	// @param defender : 対象プレイヤー
	@Override
	public void Attack(Player defender)
	{
		System.out.println(GetName() + "の攻撃！");

		// 追加機能を呼び出す
		this.extra.get(0).Extrafunction(defender);

		// 追加機能が不発の場合は通常通り攻撃
		if(defender.GetHP()!=0){

			int damage = CalcDamage(defender);

			// 求めたダメージを対象プレイヤーに与える
			if(damage==this.GetSTR()){
				System.out.println(this.GetName() + "の「会心の一撃！」");
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			} else if(damage==0){
				System.out.println(this.GetName() + "は攻撃をミスした...");
			} else {
				System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
			}

			defender.Damage(damage);

		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			System.out.println(defender.GetName() + "は力尽きた...");
		}
	}

}
