package nameBattler;

import java.math.BigInteger;
//BigIntegerクラス：プリミティブ型(long型など)では扱えない巨大な数値を扱うときに使用。
//メモリは動的に割り当てられるため上限はない。
import java.security.MessageDigest;
import java.util.ArrayList;
//ハッシュ値生成に使用。(詳細はよくわからなかった)
import java.util.Random;

// プレイヤークラス(各種ジョブの基底クラス)
public class Player {
	// =======================
	// フィールド変数
	// =======================
	// 名前
	protected String name; //protected：同一パッケージ内とサブクラスから参照可能。
	// HP
	protected int hp;
	// 魔力
	protected int mp;
	// 攻撃力
	protected int str;
	// 防御力
	protected int def;
	// 運命力
	protected int luck;
	// 素早さ
	protected int agi;
	//初期HP
	protected int firstHP;

	//行動可能フラグ(trueで行動可能)
	protected boolean movableFlag;

	//状態異常フラグ(残りターン数)
	protected int badStatusTurn;

	//状態異常ダメージ値
	protected int slipDamage;

	// 乱数
	Random rand = new Random();

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

		SetMovableFlagTrue();
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
	 * 魔力を取得する
	 * @return 魔力
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
	 * 運命力を取得する
	 * @return 運命力
	 */
	public int GetLUCK() {
		return this.luck;
	}

	/**
	 * 素早さを取得する
	 * @return 素早さ
	 */
	public int GetAGI() {
		return this.agi;
	}

	/**
	 * 行動可否を取得する
	 * @return 行動可能フラグ
	 */
	public boolean GetMovableFlag() {
		return this.movableFlag;
	}

	/**
	 * 状態異常の残りターンを取得
	 * @return 状態異常ターン数
	 */
	public int GetBadStatusTurn() {
		return this.badStatusTurn;
	}

	/**
	 * 状態異常のダメージ値を取得
	 */
	public int GetSlipDamage() {
		return this.slipDamage;
	}

	/**
	 * 行動可能フラグをtrueに(行動可能)
	 */
	public void SetMovableFlagTrue() {
		this.movableFlag = true;
	}

	/**
	 * 行動可能フラグをfalseに(行動不可)
	 */
	public void SetMovableFlagFalse() {
		this.movableFlag = false;
	}

	/**
	 * 状態異常の継続ターンを設定
	 */
	public void SetBadStatus(int keepTurn) {
		this.badStatusTurn = keepTurn;
	}

	/**
	 * 状態異常のダメージ値を設定
	 */
	public void SetSlipDamage(int slipDamage) {
		this.slipDamage = slipDamage;
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
		System.out.printf("%s (HP=%3d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d)\n", this.GetName(),
				this.GetHP(), this.GetMP(), this.GetSTR(), this.GetDEF(), this.GetLUCK(), this.GetAGI());
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	public void Attack(Player defender) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 * 対象プレイヤー(target)に対して与える通常攻撃のダメージを計算する
	 * @param target : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	public int CalcDamage(Player target) {
		//int damage = GetSTR() - target.GetDEF();
		int damage = rand.nextInt(GetSTR()) + 1 - target.GetDEF();
		int critical = rand.nextInt(100) + 1; //1~100
		if (critical <= GetLUCK()) {
			System.out.println("会心の一撃!!!");
			damage = GetSTR();
		} else if (damage < 0) {
			damage = 0;
		}
		return damage;
	}

	/**
	 * 回復魔法の処理
	 * @param usableMagic：使用可能魔法
	 */
	public void Heal(Magic useMagic) {
		int healAmount = useMagic.GetHealAmount(); //回復量
		System.out.println(useMagic.GetName() + "！HP" + healAmount + "回復！");
		this.hp = Math.min(firstHP, this.hp + healAmount);
		UseMP(useMagic.GetUseMP());
	}

	/**
	 * 拘束魔法の処理
	 * @param useMagic：使用魔法
	 */
	public void Bind(Magic useMagic, Player defender) {
		System.out.println(useMagic.GetName() + "!");
		if (useMagic.JudgeBind()) {
			System.out.println("拘束成功！");
			defender.SetMovableFlagFalse();
		} else {
			System.out.println("拘束失敗！");
		}
		UseMP(useMagic.GetUseMP());
	}

	/**
	 * 継続ダメージの処理
	 */
	public void SlipDamage(Magic useMagic, Player defender) {
		System.out.println(useMagic.GetName() + "!" + defender.GetName() + "を毒状態にした！");
		defender.SetBadStatus(useMagic.GetKeepTurn());
		defender.SetSlipDamage(useMagic.GetSlipDamage());
		UseMP(useMagic.GetUseMP());
	}

	/**
	 * ダメージを受ける
	 * @param damage : ダメージ値
	 */
	public void Damage(int damage) {
		// ダメージ値分、HPを減少させる
		this.hp = Math.max(this.GetHP() - damage, 0); //Math.max：2つの引数の大きい方を返す
	}

	/**
	 * 魔力を減らす
	 * @param useMP : 魔力消費値
	 */
	public void UseMP(int useMP) {
		this.mp = Math.max(this.mp - useMP, 0);
	}

	/**
	 * 現在のMPで使用可能な魔法をリスト化する
	 * @param magicList : 覚えている魔法
	 * @return 使用可能な魔法
	 */
	public ArrayList<Magic> UsableMagic(ArrayList<Magic> magicList) {
		ArrayList<Magic> usableMagic = new ArrayList<>();
		for (int i = 0; i < magicList.size(); i++) {
			//使用MPが現在のMP以下であれば使用可能
			if (magicList.get(i).GetUseMP() <= GetMP())
				usableMagic.add(magicList.get(i));
		}
		return usableMagic;
	}

	//使用魔法の決定
	public Magic DecideUseMagic(ArrayList<Magic> usableMagic) {
		int randomNum = rand.nextInt(usableMagic.size());
		Magic useMagic = usableMagic.get(randomNum);
		return useMagic;
	}

}