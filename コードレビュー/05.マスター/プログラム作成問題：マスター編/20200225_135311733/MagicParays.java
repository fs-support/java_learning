package namebattler_2;

// 魔法：パライズ
public class MagicParays extends Magic{

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public MagicParays()
	{
		super();
	}

	// =======================
	// Getter / Setter
	// =======================

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 魔法のパラメータを設定する
	 */
	@Override
	protected void MakeMagic()
	{
		// 魔法のパラメータを生成する
		this.name = "パライズ";
		this.mp = 10;
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * 対象プレイヤーに魔法を使用する
	 */
	@Override
	public void UseMagic(Player player)
	{
		player.Paralys();
		System.out.println(player.GetName() + "は麻痺した！");
	}
}