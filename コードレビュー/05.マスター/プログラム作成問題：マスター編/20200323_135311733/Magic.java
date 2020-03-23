package namebattler_2;

// プレイヤークラス(各種ジョブの基底クラス)
public class Magic {
	// =======================
	// フィールド変数
	// =======================
	// 魔法の名前
	protected String name;
	// 消費MP
	protected int mp;

	// =======================
	// コンストラクタ
	// =======================
	/**
	 * コンストラクタ
	 * @param name : プレイヤー名
	 */
	public Magic() {
		// 魔法の名前と消費MPを生成
		MakeMagic();
	}


	// =======================
	// Getter / Setter
	// =======================
	/**
	 * 魔法の名称を取得する
	 * @return 魔法の名称
	 */
	public String GetName()
	{
		return this.name;
	}

	/**
	 * 消費MPを取得する
	 * @return 消費MP
	 */
	public int GetMP()
	{
		return this.mp;
	}

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 魔法のパラメータを生成する
	 */
	protected void MakeMagic()
	{
		// 魔法ごとにオーバーライドして処理を記述
	}

	// =======================
	// private メソッド
	// =======================


	// =======================
	// public メソッド
	// =======================
	/**
	 * 対象プレイヤーに魔法を使用する
	 * @param player : 対象となるプレイヤー
	 */
	public void UseMagic(Player player)
	{
		// 魔法ごとにオーバーライドして処理を記述
	}
}