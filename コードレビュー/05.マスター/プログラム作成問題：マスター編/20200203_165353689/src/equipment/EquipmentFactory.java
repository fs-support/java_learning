package equipment;

public class EquipmentFactory {

	// STRの上がる剣（朽ちた、銅、銀、黄金）
	protected static Sword[] SwordStock = {new Sword("朽ちた剣",10), new Sword("銅の剣",20), new Sword("銀の剣",30), new Sword("黄金の剣",40)};
	// DEFの上がる鎧（朽ちた、銅、銀、黄金）
	protected static Armor[] ArmorStock = {new Armor("朽ちた鎧",5), new Armor("銅の鎧",10), new Armor("銀の鎧",15), new Armor("黄金の鎧",20)};
	// DEFの上がる盾（朽ちた、銅、銀、黄金）
	protected static Shield[] ShieldStock = {new Shield("朽ちた盾",2), new Shield("銅の盾",5), new Shield("銀の盾",10), new Shield("黄金の盾",15)};
	// AGIの上がる装飾品（紅玉、蒼玉、翡翠、瑠璃、金剛石）
	protected static Jewelry[] JewelryStock = {new Jewelry("紅玉のネックレス",2), new Jewelry("蒼玉のネックレス",5), new Jewelry("翠玉のネックレス",10), new Jewelry("瑠璃のネックレス",15), new Jewelry("金剛石のネックレス",20)};

	// =======================
	// protected メソッド
	// =======================

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	public static int CountSword() {
		return SwordStock.length;
	}
	public static int CountArmor() {
		return ArmorStock.length;
	}
	public static int CountShield() {
		return ShieldStock.length;
	}
	public static int CountJewelry() {
		return JewelryStock.length;
	}

	/**
	 * 剣を決定する
	 * @param index
	 * @return 指定された剣を渡す
	 */
	public static Sword DecideSword(int index) {

		return SwordStock[index];
	}

	/**
	 * 鎧を決定する
	 * @param index
	 * @return 指定された鎧を渡す
	 */
	public static Armor DecideArmor(int index) {

		return ArmorStock[index];
	}

	/**
	 * 盾を決定する
	 * @param index
	 * @return 指定された盾を渡す
	 */
	public static Shield DecideShield(int index) {

		return ShieldStock[index];
	}

	/**
	 * 装飾品を決定する
	 * @param index
	 * @return 指定された装飾品を渡す
	 */
	public static Jewelry DecideJewelry(int index) {

		return JewelryStock[index];
	}

}