package namebattler;

public class MagicManager {
	// =======================
	// フィールド変数
	// =======================
	//魔法クラス
	protected Magic fire = new Fire();
	protected Magic thunder = new Thunder();
	protected Magic heal = new Heal();
	protected Magic poison = new Poison();
	protected Magic paralyze = new Paralyze();

	// =======================
	// コンストラクタ
	// =======================
	public MagicManager() {

	}

}
