package jp.co.FSsakusei3;

import java.math.BigInteger;
import java.security.MessageDigest;
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
	// MP
	protected int mp;
	// 攻撃力
	protected int str;
	// 防御力
	protected int def;
	// LUCK
	protected int luck;
	// 素早さ
	protected int agi;

	protected int maxhp;

	protected String state;

	// =======================
	// コンストラクタ
	// =======================
	/**
	 * コンストラクタ
	 *
	 * @param name
	 *            : プレイヤー名
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
	 *
	 * @return プレイヤー名
	 */
	public String GetName() {
		return this.name;
	}

	/**
	 * 現在HPを取得する
	 *
	 * @return 現在HP
	 */
	public int GetHP() {
		return this.hp;
	}

	public int GetMP() {
		return this.mp;
	}

	/**
	 * 攻撃力を取得する
	 *
	 * @return 攻撃力
	 */
	public int GetSTR() {
		return this.str;
	}

	/**
	 * 防御力を取得する
	 *
	 * @return 防御力
	 */
	public int GetDEF() {
		return this.def;
	}

	public int GetLUCK() {
		return this.luck;
	}

	public int GetAGI() {
		return this.agi;
	}

	public int GetMAXHP() {
		return this.maxhp;
	}

	public String GetSTATE() {
		return this.state;
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
	 *
	 * @param index
	 *            : 何番目の数値を取り出すか
	 * @param max
	 *            : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
	 * @return 数値(0～max) ※maxも含む
	 */
	protected int GetNumber(int index, int max) {
		try {
			// 名前からハッシュ値を生成する
			byte[] result = MessageDigest.getInstance("SHA-1").digest(
					this.name.getBytes());
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

	/**
	 * 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
	 *
	 * @param index
	 *            : 何番目の数値を取り出すか
	 * @param min
	 *            : 最小値
	 * @param max
	 *            : 最大値
	 * @return 数値(min～max) ※minもmaxも含む
	 */
	protected int GetNumber(int no, int min, int max) {
		return GetNumber(no, (max - min)) + min;
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
		System.out
				.printf("%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d %s)\n",
						this.GetName(), this.GetHP(), this.GetMP(),
						this.GetSTR(), this.GetDEF(), this.GetLUCK(),
						this.GetAGI(), this.GetSTATE());
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 *
	 * @param defender
	 *            : 対象プレイヤー
	 */
	public void Attack(Player defender) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 対象プレイヤー(target)に対して与えるダメージを計算する
	 *
	 * @param target
	 *            : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	protected int CalcDamage(Player target) {
		int damage = GetSTR() - target.GetDEF();
		if (damage < 0) {
			damage = 0;
		}
		return damage;
	}

	protected void State(String state) {
		this.state = state;

	}

	/**
	 * ダメージを受ける
	 *
	 * @param damage
	 *            : ダメージ値
	 */
	protected void Damage(int damage) {
		// ダメージ値分、HPを減少させる
		this.hp = Math.max(this.GetHP() - damage, 0);
	}

	protected void PoisonDamage() {
		System.out.println("毒によるダメージ"+this.GetName()+"に20ダメージ");
		this.hp = Math.max(this.GetHP() - 20, 0);
		if(this.GetHP()<=0){
			System.out.println(this.GetName()+"は毒で力尽きた！");
		}
	}
	protected int Paralysis(){
	 Random r = new Random();
	int paralysis= r.nextInt(100);
	 return paralysis;
	}
}