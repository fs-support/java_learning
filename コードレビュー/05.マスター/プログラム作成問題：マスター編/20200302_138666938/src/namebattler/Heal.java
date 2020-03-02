package namebattler;

public class Heal extends Magic{
	// =======================
	// フィールド変数
	// =======================
	//回復量
	private int recovery = 50;

	// =======================
	// コンストラクタ
	// =======================
	//コンストラクタ
	public Heal() {
		super();
		//名前をセット
		this.name = "ヒール";
		//使用MPをセット
		this.cost = 20;
	}

	// =======================
	// protected メソッド
	// =======================
	//個別効果
	@Override
	protected void Magieffect(Player target) {
		//ターゲットを回復
		System.out.print(target.GetName() + "を" + this.recovery + "回復！");
		target.Damage(-(this.recovery));
		//最大HP以上回復した場合
		if(target.hp > target.maxHp) {
			target.hp = target.maxHp;
		}
	}
}
