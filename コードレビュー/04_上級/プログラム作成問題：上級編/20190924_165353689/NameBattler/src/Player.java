
// プレイヤークラス(各種ジョブの基底クラス)

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class Player {
	// =======================
	// フィールド変数
	// =======================
	// 名前
	protected String name;
	// HP
	protected int hp;
	//MaxHP
	protected int maxhp;
	// MP
	protected int mp;
	// 攻撃力
	protected int str;
	// 防御力
	protected int def;
	// 幸運
	protected int luck;
	//速度
	protected int agi;

	protected boolean paralyze;

	protected boolean poison;

	// =======================
	// コンストラクタ
	// =======================
	/**
	 * コンストラクタ
	 * @param name : プレイヤー名
	 */
	public Player(String name) {
		this.name = name;

		// キャラクターのパラメータ生成
		MakeCharacter();
	}

	// =======================
	// Getter / Setter
	// =======================
	/**
	 * プレイヤー名を取得する
	 * @return プレイヤー名
	 */
	public String GetName() {
		return this.name;
	}

	/**
	 * 現在HPを取得する
	 * @return 現在HP
	 */
	public int GetHP() {
		return this.hp;
	}

	/**
	 * HPの最大値を取得する
	 * @return 最大HP
	 */
	public int GetMaxHP() {
		return this.maxhp;
	}

	/**
	 * 現在MPを取得する
	 * @return 現在MP
	 */
	public int GetMP() {
		return this.mp;
	}

	/**
	 * 攻撃力を取得する
	 * @return 攻撃力
	 */
	public int GetSTR() {
		return this.str;
	}

	/**
	 * 防御力を取得する
	 * @return 防御力
	 */
	public int GetDEF() {
		return this.def;
	}

	/**
	 * 幸運値を取得する
	 * @return 幸運値
	 */
	public int GetLUCK() {
		return this.luck;
	}

	/**
	 * 速さを取得する
	 * @return 速さ
	 */
	public int GetAGI() {
		return this.agi;
	}

	/**
	 * 麻痺状態かどうかを取得する
	 * @return 麻痺状態可否
	 */
	public boolean GetParalyze() {
		return this.paralyze;
	}

	/**
	 * 毒状態かどうかを取得する
	 * @return 毒状態可否
	 */
	public boolean GetPoison() {
		return this.poison;
	}
	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter() {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
	 * @param index : 何番目の数値を取り出すか
	 * @param max : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
	 * @return 数値(0～max) ※maxも含む
	 */
	protected int GetNumber(int index, int max) {
		try {
			// 名前からハッシュ値を生成する
			byte[] result = MessageDigest.getInstance("SHA-1").digest(this.name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));

			// ハッシュ値から指定された位置の文字列を取り出す（２文字分）
			String hex = digest.substring(index * 2, index * 2 + 2);

			// 取り出した文字列（16進数）を数値に変換する
			int val = Integer.parseInt(hex, 16);
			return val * max / 255;
		} catch (Exception e) {
			// エラー
			e.printStackTrace();
		}
		return 0;
	}

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
	/**
	 * 現在のステータスを System.out で表示する
	 */
	public void PrintStatus() {
		System.out.printf("%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d LUCK=%3d : AGI=%3d)\n", this.GetName(), this.GetHP(),
				this.GetMP(), this.GetSTR(), this.GetDEF(), this.GetLUCK(), this.GetAGI());
	}

	/**
	 * 対象プレイヤー(target)に攻撃を行う
	 * @param attacker : 攻撃側プレーヤー
	 * @param defender : 防御側プレイヤー
	 */
	public void Attack(Player attacker, Player defender) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 対象プレイヤー(target)に対して与えるダメージを計算する
	 * @param target : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	protected int CalcDamage(Player target) {
		Random random = new Random();
		int critical = random.nextInt(100) + 1;

		// 会心の一撃かどうか
		if (GetLUCK() > critical) {
			System.out.println("会心の一撃！");
			return GetSTR();
		}

		int damage = GetSTR() - target.GetDEF();

		if (damage < 0) {
			damage = 0;
		}
		return damage;

	}

	/**
	 * ダメージを受ける
	 * @param damage : ダメージ値
	 */
	protected void Damage(int damage) {
		// ダメージ値分、HPを減少させる
		this.hp = Math.max(this.GetHP() - damage, 0);
	}

	/**
	* HPを回復する
	* @param damage : 回復値
	*/
	protected void RecoverHP(int recoverHP) {
		// 回復量分、HPを上昇させる
		this.hp = Math.min(this.GetHP() + recoverHP, this.maxhp);

	}

	/**
	 * MPを消費する
	 * @param usemp : 消費するMPの値
	 */
	protected void UseMP(int usemp) {

		// 消費MP分、MPを減少させる
		this.mp = Math.max(this.GetMP() - usemp, 0);
	}

	/**
	 * 麻痺状態にする
	 * @param status : 麻痺状態にする
	 */
	protected void SetParalyze(boolean status) {
		this.paralyze = status;
	}
	/**
	 * 毒状態にする
	 * @return 毒状態にする
	 */
	protected void SetPoison(boolean status) {
		this.poison = status;
	}
	/**
	 * 毒状態になっている場合の処理
	 */
	public void ProcessPoison() {
		this.Damage(20);

}




}