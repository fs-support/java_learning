package namebattler_2;

import java.util.Random;

// 魔法：サンダー
public class MagicFire extends Magic{

	// =======================
	// フィールド変数
	// =======================
	protected int MaxDamage = 30;
	protected int MinDamage = 10;
	protected Random rnd = new Random();
	// =======================
	// コンストラクタ
	// =======================
	public MagicFire()
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
		this.name = "ファイア";
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
		int damage = rnd.nextInt(MaxDamage - MinDamage) + MinDamage;
		player.Damage(damage);
		System.out.println(player.GetName() + "に" + damage + "のダメージ！");
	}
}