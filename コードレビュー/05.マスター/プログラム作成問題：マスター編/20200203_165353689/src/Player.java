
// プレイヤークラス(各種ジョブの基底クラス)

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

// 装備品
import equipment.Armor;
import equipment.Jewelry;
import equipment.Shield;
import equipment.Sword;

public class Player {
	// =======================
	// フィールド変数
	// =======================
	// 名前
	protected String name;
	// HP
	protected int hp;
	protected int defaultHP;
	// MP
	protected int mp;
	protected int defaultMP;
	// 攻撃力
	protected int str;

	// 防御力
	protected int def;

	// 幸運
	protected int luck;

	//速度
	protected int agi;

	// 麻痺
	protected boolean paralyze;

	protected int paralyzeTurn ;

	// 毒
	protected boolean poison;

	//行動できるかどうか
	protected boolean active;

	protected Strategy strategy;

	// 所属しているパーティー
	protected Party myParty;

	//装備品・剣
	protected Sword mySword;
	//装備品・鎧
	protected Armor myArmor;
	//装備品・盾
	protected Shield myShield;
	//装備品・装飾品
	protected Jewelry myJewelry;


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
	public int GetHP(){

		return this.hp;
	}
	/**
	 * 最大HPを取得する
	 * @return 最大HP
	 */
	public int GetMaxHP() {
		return this.defaultHP;
	}
	/**
	 * 現在MPを取得する
	 * @return 現在MP
	*/
	public int GetMP(){
		return this.mp;
	}
	/**
	 * 最大MPを取得する
	 * @return 最大MP
	 */
	public int GetMaxMP() {
		return this.defaultMP;
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

		if (isEquippedArmor()) {

			if (isEquippedShield()) {
				return this.def + myArmor.EquippedValue(def) + myShield.EquippedValue(def);

			}
			return this.def + myArmor.EquippedValue(def);


		}

		if (isEquippedShield()) {
			return this.def + myShield.EquippedValue(def);

		}

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
		return this.agi + myJewelry.EquippedValue(agi);
	}



	public boolean isParalyze(){
		return this.paralyze;
	}

	public boolean isActive() {
		return active;
	}

	public boolean isPoison(){
		return this.poison;
	}

	public boolean isEquippedSword(){
		if (mySword == null) {
			return false;
		}
		return true;
	}

	public boolean isEquippedArmor() {
		if (myArmor == null) {
			return false;
		}
		return true;

	}

	public boolean isEquippedShield() {
		if (myShield == null) {
			return false;
		}
		return true;

	}

	public boolean isEquippedJewelry() {
		if (myJewelry== null) {
			return false;
		}
		return true;

	}

	public void SetParalyze(boolean paralyze){
		this.paralyze = paralyze;
		this.paralyzeTurn = 5;
	}
	public void RecoveryParalyze() {
		this.paralyze = false;
	}

	public int GetParalyzeTurn() {
		return this.paralyzeTurn;
	}
	public void ChangeParalyzeTurn() {
		if (paralyzeTurn >0) {
			this.paralyzeTurn --;
		}
	}

	public void SetPoison(boolean poison){
		this.poison = poison;
	}




	/**
	 *自分のパーティーを取得する
	 *@return　自パーティー
	 */
	public Party GetMyParty() {
		return this.myParty;
	}


	/**
	 * 自分がどのパーティーに所属しているか設定する
	 * @param　myParty　：　自身の属するパーティー
	 */
	public void SetMyParty(Party myParty) {
		this.myParty = myParty;

	}

	/**
	 * 自分のパーティーの作戦を設定する
	 * @param strategy : 自分のパーティーの作戦
	 */
	public void SetStrategy(Strategy strategy){
		this.strategy = strategy;
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
			// 名前からハッシュ値を生成する（40桁）
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
		System.out.printf("%s (HP=%3d/%3d : MP=%3d/%3d : STR=%3d : DEF=%3d LUCK=%3d : AGI=%3d)\n", this.GetName(), this.hp,
				this.defaultHP,this.mp, this.defaultMP,this.str, this.def, this.luck, this.agi);
		System.out.printf("装備品　　%s : %s : %s : %s \n", mySword.toString(),myArmor.toString(),myShield.toString(),myJewelry.toString());
	}

	/**
	 * Playerの行動を決定し行う
	 *
	 */public void Action(Player activePlayer,Party enemyParty){

		 strategy.StartegyAction(activePlayer,activePlayer.GetMyParty(),enemyParty);

	 }

	/**
	 * 対象プレイヤー(passivePlayer)に攻撃を行う
	 * @param attacker : 攻撃側プレーヤー
	 * @param defender : 防御側プレイヤー
	 */
	public void Attack(Player ActivePlayer,ArrayList<Player> passiveParty) {
		// ジョブごとにオーバーライドして処理を記述してください
	}

	/**
	 *対象プレイヤー(passivePlayer)に回復を行う
	 */
	public boolean HealHP(Player ActivePlayer, ArrayList<Player> passiveParty) {
		// ジョブごとにオーバーライドして処理を記述してください
		return false;
	}

	public boolean HealDebuff(Player ActivePlayer, Player passivePlayer) {
		// ジョブごとにオーバーライドして処理を記述してください
		return false;
	}

	/**
	 *対象プレイヤー(passivePlayer)に状態異常魔法を使う
	 */
	public boolean Debuff(Player activePlayer, Player passivePlayer) {

		return false;
	}


	/**
	 * 対象プレイヤー(target)に対して与えるダメージを計算する
	 * @param target : 対象プレイヤー
	 * @return ダメージ値(0～)
	 */
	protected int CalcDamage(Player target) {
		Random random = new Random();
		int critical = random.nextInt(100) + 1;
		int damage;
		// 剣を持っているかどうか
		if (isEquippedSword()) {

			// 会心の一撃かどうか
			if (GetLUCK() > critical) {

				System.out.println("会心の一撃！");

				return GetSTR() + mySword.EquippedValue(str);

			}

			damage = str + mySword.EquippedValue(str) - target.GetDEF();

			if (damage < 0) {
				return 0;
			}
			return damage;
		}

		damage = str - target.GetDEF();

		if (damage < 0) {
			return 0;
		}
		return damage;

	}

	/**
	 * ダメージを受ける
	 * @param damage : ダメージ値
	 */
	protected void Damage(int damage) {
		// ダメージ値分、HPを減少させる
		this.hp =  Math.max(this.hp - damage, 0);
	}

	/**
	 * 会心の一撃
	 */

	/**
	* HPを回復する
	* @param damage : 回復値
	*/
	protected void RecoverHP(int recoverHP) {
		// 回復量分、HPを上昇させる
		this.hp = Math.min(this.hp + recoverHP, this.defaultHP);

	}

	/**
	 * MPを消費する
	 * @param usemp : 消費するMPの値
	 */
	protected void UseMP(int usemp) {

		// 消費MP分、MPを減少させる
		this.mp = (Math.max(this.mp - usemp, 0));
	}



	/**
	 * 毒状態になっている場合の処理
	 */
	public void ProcessPoison() {
		if (this.poison) {
			this.Damage(20);
		}
	}


	/**
	 * PlayerのHPがゼロになったときに表示する
	 */
	public void Down(){
	if (this.hp == 0) {
		System.out.println(this.name + "は力尽きた...");
	}
	}

	public boolean GetActive(){
		if (this.active){
			return true;
		}
		return false;
	}
	public void ChangeActive(boolean active) {
		this.active = active;
	}

}