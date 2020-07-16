package namebattler.manager;

import namebattler.magic.Fire;
import namebattler.magic.Heal;
import namebattler.magic.Magic;
import namebattler.magic.Paralyze;
import namebattler.magic.Poison;
import namebattler.magic.Thunder;

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

	// =======================
	// Getter / Setter
	// =======================
	//fireを取得
	public Magic GetFire() {
		return this.fire;
	}

	//thunderを取得
	public Magic GetThunder() {
		return this.thunder;
	}

	//healを取得
	public Magic GetHeal() {
		return this.heal;
	}

	//poisonを取得
	public Magic GetPoison() {
		return this.poison;
	}

	//paralyzeを取得
	public Magic GetParalyze() {
		return this.paralyze;
	}

}
