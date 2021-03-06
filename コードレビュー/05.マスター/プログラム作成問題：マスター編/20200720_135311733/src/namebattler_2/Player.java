package namebattler_2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

// プレイヤークラス(各種ジョブの基底クラス)
public class Player {
	// =======================
	// フィールド変数
	// =======================
	// 名前
	protected String name;
	// HP
	protected int hp;
	// 攻撃力
	protected int str;
	// 防御力
	protected int def;
	//素早さ
	protected int agi;
	// MP
	protected int mp;
	// 運
	protected int luc;

	// 状態異常
	// 毒
	protected boolean pois;
	// 麻痺
	protected boolean para;
	// 麻痺判定
	protected Random parajudge = new Random();
	// クリティカル判定
	protected Random crtjudge = new Random();

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
	 * 素早さを取得する
	 * @return 素早さ
	 */
	public int GetAGI() {
		return this.agi;
	}

	/**
	 * 現在MPを取得する
	 * @return 現在MP
	 */
	public int GetMP() {
		return this.mp;
	}

	/**
	 * 運を取得する
	 * @return 現在MP
	 */
	public int GetLUC() {
		return this.luc;
	}

	/**
	 * 毒にかかっているかを取得する
	 * @return 毒状態
	 */
	public boolean GetPoison() {
		return this.pois;
	}

	/**
	 * 毒にかかっているかを取得する
	 * @return 麻痺状態
	 */
	public boolean GetParalys() {
		return this.para;
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
			byte[] result = MessageDigest.getInstance("SHA-1")
					.digest(this.name.getBytes());
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
		System.out.printf(
				"%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : AGI=%3d : LUC=%3d)\n",
				this.GetName(), this.GetHP(), this.GetMP(), this.GetSTR(),
				this.GetDEF(), this.GetAGI(), this.GetLUC());
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	public void Attack(ArrayList<Player> attackparty,
			ArrayList<Player> defenderparty) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 対象プレイヤー(target)に対して与えるダメージを計算する
	 * @param target : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	protected int CalcDamage(Player target) {
		// 通常攻撃は運の値を参照してクリティカルヒット判定を行う
		int crt = GetLUC() - target.GetLUC();
		if (crt < 1) {
			crt = 1;
		}
		int damage = 0;
		if (crtjudge.nextInt(100) < crt) {
			System.out.printf("クリティカルヒット！");
			damage = GetSTR() * 3;
		} else {
			damage = GetSTR() - target.GetDEF();
		}
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
	 * 状態異常 毒になる
	 */
	public void Poison() {
		this.pois = true;
	}

	/**
	 * 自身が毒ダメージを受ける
	 *
	 */
	protected void PoisDamage() {
		this.hp = Math.max(this.GetHP() - 20, 0);

		System.out.println(this.GetName() + "は毒により20ダメージうけた！");
		// プレイヤーの戦闘不能判定
		if (this.GetHP() <= 0) {
			System.out.println(this.GetName() + "は力尽きた...");
		}
	}

	/**
	 * 状態異常 麻痺になる
	 */
	public void Paralys() {
		this.para = true;
	}

	/**
	* 麻痺による自身の行動不能判定
	*
	*/
	protected boolean ParaJudge() {
		// trueで行動不能
		if (parajudge.nextInt(100) < 20) {
			return true;
		} else {
			return false;
		}
	}
}
