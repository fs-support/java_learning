
public class MagicManager {

	// =======================
	// フィールド変数
	// =======================
	// 攻撃魔法
	protected Magic[] attackMagic = { new Fire(), new Thunder() };
	// 回復魔法
	protected Magic[] recoverMagic = { new Heal()};
	// 状態異常魔法
	protected Magic[] debuffMagic = { new Paralyze(), new Poison() };


	// =======================
	// protected メソッド
	// =======================

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * 攻撃魔法を管理する
	 * @return ランダムで攻撃魔法を返す
	 */
	public Magic UseAttackMagic() {
		return attackMagic[RandomGenerator.RandomRange(0,attackMagic.length)];
	}

	/**
	 * 回復魔法を管理する
	 * @return 回復魔法を返す
	 */
	public Magic UseRecoverMagic() {
		return recoverMagic[0];

	}

	/**
	 * 状態異常魔法を管理する
	 * @return ランダムで状態異常魔法を返す
	 */
	public Magic UseDebuffMagic() {

		return debuffMagic[RandomGenerator.RandomRange(0,debuffMagic.length)];

	}


}
