package namebattler;

public class Thunder extends Magic{
	// =======================
	// フィールド変数
	// =======================
	//ダメージ量
	private int damage;

	// =======================
	// コンストラクタ
	// =======================
	//コンストラクタ
	public Thunder() {
		super();
		//名前をセット
		this.name = "サンダー";
		//使用MPをセット
		this.cost = 20;
	}

	// =======================
	// protected メソッド
	// =======================
	//個別効果
	@Override
	protected void Magieffect(Player target) {
		//ダメージ量を設定(10～30)
		this.damage = this.ran.nextInt(21)+10;
		//ターゲットにダメージ
		System.out.print(target.GetName() + "に" + this.damage + "のダメージ！");
		target.Damage(this.damage);
	}
}
