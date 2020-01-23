import java.math.BigInteger;
import java.security.MessageDigest;

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
	// MP
	protected int mp;
	// LUCK
	protected int luck;
	// 素早さ
	protected int agi;

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
	public String GetName()
	{
		return this.name;
	}

	/**
	 * 現在HPを取得する
	 * @return 現在HP
	 */
	public int GetHP()
	{
		return this.hp;
	}

	/**
	 * 攻撃力を取得する
	 * @return 攻撃力
	 */
	public int GetSTR()
	{
		return this.str;
	}

	/**
	 * 防御力を取得する
	 * @return 防御力
	 */
	public int GetDEF()
	{
		return this.def;
	}

	/**
	 * MPを取得する
	 * @return 防御力
	 */
	public int GetMp()
	{
		return this.mp;
	}

	/**
	 * 運を取得する
	 * @return 運
	 */
	public int GetLuck()
	{
		return this.luck;
	}

	/**
	 * 素早さを取得する
	 * @return 素早さ
	 */
	public int GetAgi()
	{
		return this.agi;
	}

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	protected void MakeCharacter()
	{
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
//			return Integer.parseInt(hex, 16);
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
	public void PrintStatus()
	{
		System.out.printf("%s (HP=%3d : MP=%3d  : STR=%3d : DEF=%3d : LUCK=%3d : AGI=3%d )\n", this.GetName(),this.GetHP(), this.GetMp(), this.GetSTR(), this.GetDEF(),this.GetLuck(),this.GetAgi());
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	public void Attack(Player defender)
	{
		// ジョブごとにオーバーライドして処理を記述してください
	}

    /**
     * 対象プレイヤー(target)に対して与えるダメージを計算する
     * @param target : 対象プレイヤー
     * @return ダメージ値(0～)
     */
    protected int CalcDamage(Player target)
    {
    	//戦士・魔法使い(通常攻撃)・僧侶(通常攻撃)・獣使い(通常攻撃)
    	//攻撃力 - 防御力
        int damage = GetSTR() - target.GetDEF();
        if (damage < 0)
        {
        	//防御力のほうが高い場合は1のダメージを与える
            damage = 1;
        }
        return damage;
    }

    /**
     * 対象プレイヤー(target)に対して与えるダメージを計算する
     * @param target : 対象プレイヤー
     * @return ダメージ値(0～)
     */
    protected int magicDamage(Player target,int min,int max)
    {
    	//魔法使い(魔法)
        int damage = (int)((Math.random()*max) + min);

        //ランダムなダメージを与える
        return damage;
    }

    /**
     * 対象プレイヤー(target)に対して与えるダメージを計算する
     * @param target : 対象プレイヤー
     * @return ダメージ値(0～)
     */
    protected int specialMagic(Player target,int hitDamage,int flag)
    {
    	//僧侶(パライズ・ポイズン)
    	//ランダム
    	int random = (int)((Math.random()*100));
    	if(flag == 0){
    		//回復
    		//元のHPより減少している場合は20回復
    		if (NameBattler.hp1 >= this.GetHP()){
    			//回復したHPが元のHPより高くなった場合は元のHPに合わせる
    			this.hp = this.GetHP() + hitDamage;
    			if(NameBattler.hp1 < this.hp){
    				//元の体力以上に回復した場合は元の体力に合わせる
    				this.hp = NameBattler.hp1;
    			}
    		}
    			//HPが減っていない場合は回復しない
    	}else if(flag == 1){
        	//パライズ1
        	//名称:パライズ 職業:僧侶     消費MP:10 効果:麻痺の効果を与える 麻痺：20%の確率で麻痺で行動不能
    		if(random <= 20){
    			//相手の行動不能
    			return 0;
    		}else{
    			//相手の行動不能失敗
    			return 1;
    		}
    	}else if(flag == 2){
        	//ポイズン2
    		//名称:ポイズン 職業:僧侶     消費MP:10 効果:毒状態にする 毒：毎ターン20のダメージを受ける
    		//毎ターン20ダメージ
    		return hitDamage;

    	}
		return hitDamage;
    }

    /**
     * 対象プレイヤー(target)に対して与えるダメージを計算する
     * @param target : 対象プレイヤー
     * @return ダメージ値(0～)
     */
    protected int beastDamage(Player target,int hitDamage,int flag)
    {
		//動物:トラ   効果:ひっかく 50のダメージ
		//動物:ヘビ   効果:かみつく 20のダメージ 10%の確率で毒を付与
		//動物:ネズミ 効果:かみつく 20のダメージ
		//動物:ウサギ 効果：回復    30%の確率で30回復
		//自身の攻撃                10のダメージ
    	int random = (int)((Math.random()*100));
    	int damage = 0;
    	//獣使い
    	if(flag == 0 ||flag == 3){
    		//トラ または ネズミ
    		if(random >= 50){
    			//防御を無視したダメージ
    			damage = hitDamage;
    		}else{
    			//防御ありのダメージ
    			damage = hitDamage - target.GetDEF();
    			if(damage < 0){
    				damage = 0;
    			}
    		}

    	}else if(flag == 2){
    		//ヘビ
    		damage = hitDamage;
    		if(random <= 10){
    			Beastmaster.poisonFlag = true;
    		}
    	}else if(flag == 4){
    		//ウサギ
    		if(random <=30){
    			//回復したHPが元のHPより高くなった場合は元のHPに合わせる
    			this.hp = this.GetHP() + hitDamage;
    			if(NameBattler.hp1 < this.hp){
    				//元の体力以上に回復した場合は元の体力に合わせる
    				this.hp = NameBattler.hp1;
    			}
    		}
    	}
    	return damage;
    }

    /**
     * ダメージを受ける
     * @param damage : ダメージ値
     */
    protected void Damage(int damage)
    {
        // ダメージ値分、HPを減少させる
        this.hp = Math.max(this.GetHP() - damage, 0);
    }
}