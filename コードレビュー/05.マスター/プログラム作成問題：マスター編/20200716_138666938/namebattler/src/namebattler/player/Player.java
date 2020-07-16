package namebattler.player;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

import namebattler.manager.MagicManager;
import namebattler.manager.StatusManager;

// プレイヤークラス(各種ジョブの基底クラス)
public class Player {
	// =======================
	// フィールド変数
	// =======================
	//ランダムクラス
	protected Random ran = new Random();
	//魔法管理クラス
	protected MagicManager magiMana = new MagicManager();
	//ステータス管理クラス
	protected StatusManager stMana = new StatusManager();
	//名前
	protected String name;
	//職業名
	protected String jobName;
	//HP
	protected int hp;
	//最大HP
	protected int maxHp;
	//MP
	protected int mp;
	//最大MP
	protected int maxMp;
	//攻撃力
	protected int str;
	//防御力
	protected int def;
	//運
	protected int luck;
	//素早さ
	protected int agi;
	//パーティ番号 *partyクラスのpartyNoと一致
	protected int partyNo;
	//魔法使用の有無
	protected boolean magiAt = false;
	//回復使用の有無
	protected boolean pMedic = false;

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
	public String GetName(){
		return this.name;
	}

	/**
	 * 職業を取得する
	 * @return 職業名
	 */
	public String GetJobName(){
		return this.jobName;
	}

	/**
	 * 現在HPを取得する
	 * @return 現在HP
	 */
	public int GetHP(){
		return this.hp;
	}

	/**
	 * 最大HPを取得する
	 * @return 最大HP
	 */
	public int GetMaxHp(){
		return this.maxHp;
	}

	/**
	 * 現在MPを取得する
	 * @return 現在HP
	 */
	public int GetMP(){
		return this.mp;
	}

	 /** 最大MPを取得する
	 * @return 最大MP
	 */
	public int GetMaxMp(){
		return this.maxMp;
	}

	/**
	 * 攻撃力を取得する
	 * @return 攻撃力
	 */
	public int GetSTR(){
		return this.str;
	}

	/**
	 * 防御力を取得する
	 * @return 防御力
	 */
	public int GetDEF(){
		return this.def;
	}

	/**
	 * 運を取得する
	 * @return 運
	 */
	public int GetLUCK(){
		return this.luck;
	}

	/**
	 * 素早さを取得する
	 * @return 素早さ
	 */
	public int GetAGI(){
		return this.agi;
	}

	//stManaを取得
	public StatusManager GetStMana() {
		return this.stMana;
	}

	//パーティー番号を取得
	public int GetPartyNo() {
		return this.partyNo;
	}

	//魔法使用の有無を取得
	public boolean GetMagiAt() {
		return this.magiAt;
	}

	//回復使用の有無を取得
	public boolean GetPMedic() {
		return this.pMedic;
	}

	//現在HPを変更
	public void SetHP(int hp) {
		this.hp = hp;
	}

	//現在MPを変更
	public void SetMP(int mp) {
		this.mp = mp;
	}

	//パーティー番号を変更
	public void SetParty(int partyNo) {
		this.partyNo = partyNo;
	}

	//魔法使用の有無を変更
	public void SetMagiAt(boolean magiAt) {
		this.magiAt = magiAt;
	}

	//回復使用の有無を変更
	public void SetPMedic(boolean pMedic) {
		this.pMedic = pMedic;
	}

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter(){
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
			//名前からハッシュ値を生成する
			byte[] result = MessageDigest.getInstance("SHA-1").digest(this.name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));

			//ハッシュ値から指定された位置の文字列を取り出す（２文字分）
			String hex = digest.substring(index * 2, index * 2 + 2);

			//取り出した文字列（16進数）を数値に変換する
			int val = Integer.parseInt(hex, 16);
			return val * max / 255;
		} catch (Exception e) {
			//エラー
			e.printStackTrace();
		}
		return 0;
	}

    /**
     * 対象プレイヤー(target)に対して与えるダメージを計算する
     * @param target : 対象プレイヤー
     * @return ダメージ値(0～)
     */
    protected int CalcDamage(Player target){
        int damage = GetSTR() - target.GetDEF();
        if (damage < 0){
            damage = 0;
        }
        return damage;
    }

    /**
     * ダメージを受ける
     * @param damage : ダメージ値
     */
    protected void Damage(int damage){
        // ダメージ値分、HPを減少させる
        this.hp = Math.max(this.GetHP() - damage, 0);
    }

    //ダメージを与えた時の処理
    protected void DamageEffect(Player defender, int damage) {
        // ダメージを対象プレイヤーに与える
        System.out.println(defender.GetName() + "に" + damage + "のダメージ！");
        defender.Damage(damage);
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
	public void PrintStatus(){
		System.out.printf("%s (%s)\n(HP=%3d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d)\n",
									this.GetName(), this.GetJobName(), this.GetHP(), this.GetMP(),
											this.GetSTR(), this.GetDEF(), this.GetLUCK(), this.GetAGI());
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	public void Attack(Player defender){
		//ジョブごとにオーバーライドして処理を記述してください
	}

	//通常攻撃時の処理
	public void NormalAttack(Player defender) {
		//与えるダメージを求める
        System.out.println(this.GetName() + "の攻撃！");
        int damage = this.CalcDamage(defender);

        //求めたダメージを対象プレイヤーに与える
        this.DamageEffect(defender, damage);
	}

	//攻撃魔法使用時の処理
	public void MagicAttack(Player defender) {
		//MPが20以上ある場合
		if(this.mp >= 20) {
			//60％の確率でファイアー、40％の確率でサンダーを使用
			switch(this.ran.nextInt(5)) {
				case 0:
				case 1:
				case 2:
					//ファイヤー使用時の効果の処理
					this.magiMana.GetFire().use(this, defender);
					// 求めたダメージを対象プレイヤーに与える
					this.DamageEffect(defender, this.magiMana.GetFire().GetDamage());
					break;
				default:
					//サンダー使用時の効果の処理
					this.magiMana.GetThunder().use(this, defender);
					//求めたダメージを対象プレイヤーに与える
					this.DamageEffect(defender, this.magiMana.GetThunder().GetDamage());
			}
		//MPが20未満の場合はファイアーを使用
		}else {
			//ファイヤー使用時の効果の処理
			this.magiMana.GetFire().use(this, defender);
			//求めたダメージを対象プレイヤーに与える
			this.DamageEffect(defender, this.magiMana.GetFire().GetDamage());
		}
	}

	//ST魔法使用時の処理
	public void StMagiAttack(Player defender) {
		if(defender.stMana.GetStPoison() == false) {
			//ポイズン使用時の効果の処理
			this.magiMana.GetPoison().use(this, defender);
		}else if(defender.stMana.GetStParalyze() == false) {
			//パラライズ使用時の効果の処理
			this.magiMana.GetParalyze().use(this, defender);
		}
	}

	//回復魔法使用時の処理
	public void Recovery(Player target) {
		//ヒール使用時の効果の処理
		this.magiMana.GetHeal().use(this, target);
		//対象プレイヤーを回復
		target.Damage(-(this.magiMana.GetHeal().GetRecovery()));

		//最大HP以上回復した場合
		if(target.GetHP() > target.GetMaxHp()) {
			target.SetHP(target.GetMaxHp());
		}
	}

	//毒ダメージを受けた時の処理
	public void PoisonEffect(Player player) {
		player.Damage(player.stMana.GetPoisonDamage());
	}

	//対象が死亡した時のログ
	public void PlayerDead(Player target) {
		System.out.println(target.GetName() + "は力尽きた...");
	}

}